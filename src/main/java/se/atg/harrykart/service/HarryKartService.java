package se.atg.harrykart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.atg.harrykart.exceptions.HarryKartException;
import se.atg.harrykart.model.HarryKartDO;
import se.atg.harrykart.model.LaneDO;
import se.atg.harrykart.model.LoopDO;
import se.atg.harrykart.model.ParticipantDO;
import se.atg.harrykart.model.ResultDO;
import se.atg.harrykart.validations.HarryKartValidatorImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class HarryKartService {
	
  @Value("${track.length}")
  private int trackLength;
  
  public List<ResultDO> getRaceWinnerList(final HarryKartDO harryKartData)  throws HarryKartException {
	
	  
	  List<ResultDO> resultList = new ArrayList<ResultDO>();
	  int numberOfLoops = harryKartData.getNumberOfLoops();
	  
	  //validate the inputs as per business logic.
	  HarryKartValidatorImpl validator = new HarryKartValidatorImpl();
	  if(!validator.validate(harryKartData)) {
		  log.info("XML business validation fails");
		  throw new HarryKartException(validator.getErrorMessageList().toString()); 
	  }
	  log.info("XML business validation is success");

      // Calculate the race results for each participant
	  resultList = harryKartData.getStartList().stream()
			    .map(participant -> {
			        try {
			            return calculateRaceResult(harryKartData, participant, numberOfLoops);
			        } catch (HarryKartException e) {
			            // Handle the exception or perform any necessary actions
			            // For example, you can log the exception or set a default result
			            log.error("Error calculating race result for participant: {}", participant.getName());
			            return new ResultDO(0, participant.getName(), Double.MAX_VALUE); // Set a default result
			        }
			    })
			    .collect(Collectors.toList());

      // Sort the results based on points in ascending order
      resultList.sort(Comparator.comparingDouble(ResultDO::getPoints));

      // Assign positions to the results
      int position = 1;
      resultList.get(0).setPosition(position);
      for (int i = 1; i < resultList.size(); i++) {
          if (resultList.get(i).getPoints() == resultList.get(i - 1).getPoints()) {
              // Same points as the previous horse, assign the same position
              resultList.get(i).setPosition(position);
          } else {
              // Different points, assign the next position
              resultList.get(i).setPosition(++position);
          }
      }

      // Filter the results to include only the top 3 ranking
      List<ResultDO> top3Ranking = resultList.stream()
              .filter(result -> result.getPosition() <= 3)
              .collect(Collectors.toList());

      return top3Ranking;
  }

  // Calculate the race result for a participant
  public ResultDO calculateRaceResult(HarryKartDO harryKartData, ParticipantDO participant, int numberOfLoops) throws HarryKartException {
      int baseSpeed = participant.getBaseSpeed();
      double points = 0;

      for (int loop = 0; loop < numberOfLoops; loop++) {
          points += calculatePointsForLoop(harryKartData, participant, loop, baseSpeed);
      }

      return new ResultDO(0, participant.getName(), points);
  }

  // Calculate the points for a participant in a specific loop
  private double calculatePointsForLoop(HarryKartDO harryKartData, 
		  ParticipantDO participant, int loop, int baseSpeed) throws HarryKartException {
	  //if loop = 0, then horse will run only in their base speed.
	  if(loop > 0) {
		  LoopDO loopData = harryKartData.getPowerUps().stream()
	              .filter(loopDO -> loopDO.getNumber() == loop)
	              .findFirst()
	              .orElseThrow(() -> new HarryKartException("Loop not found for participant"));

	      LaneDO lane = loopData.getLane().stream()
	              .filter(l -> l.getNumber() == participant.getLane())
	              .findFirst()
	              .orElseThrow(() -> new HarryKartException("Lane not found for participant"));

	      // Update the base speed with power-up value
	      baseSpeed += lane.getValue();
	  }
      // Check if the horse is still in the race
      if (baseSpeed <= 0) {
          // Horse is out of the race
          return Double.MAX_VALUE;
      }
      // Calculate the points based on track length and base speed
      return (double) trackLength / baseSpeed;
  }
}
	  
	  
	  
	  


