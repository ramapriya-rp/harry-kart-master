package se.atg.harrykart.validations;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import se.atg.harrykart.exceptions.HarryKartException;
import se.atg.harrykart.model.HarryKartDO;
import se.atg.harrykart.model.LaneDO;
import se.atg.harrykart.model.LoopDO;
import se.atg.harrykart.model.ParticipantDO;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Slf4j
public class HarryKartValidatorImpl  {

  
    private List<Integer> participantLaneValues;
    private List<List<Integer>> laneNumbers;
    private List<String> errorMessageList;

   
    public boolean validate(HarryKartDO harryKartDO) throws HarryKartException {

		errorMessageList = new ArrayList<>();
    	try {
    		
    		//validate whether all details for each loop is given.
	   	     if(harryKartDO.getPowerUps().size()+1 < harryKartDO.getNumberOfLoops()) {
	   	    	 errorMessageList.add("Loop details(powerup data) do not match with the number of loops");
	   	     }
	    		
		    //validate whether the lane number values are unique in ParticipantDO.
		     participantLaneValues = harryKartDO.getStartList().stream()
		                .map(ParticipantDO::getLane)
		                .collect(Collectors.toList());
		     if(participantLaneValues.size() != participantLaneValues.stream().distinct().count()){
		    	   errorMessageList.add(" Lane numbers in participantType are not unique");
		       }
		     
		   
		     laneNumbers = harryKartDO.getPowerUps().stream()
	   	                .map(LoopDO::getLane)
	   	                .map(laneDOList -> laneDOList.stream().map(LaneDO::getNumber).collect(Collectors.toList()))
	   	                .collect(Collectors.toList());
		     
		     //validate whether the lane number values are unique in laneType. check for all laneTypes.
		     if (!(laneNumbers.stream()
		    		    .allMatch(list -> list.size() == list.stream().distinct().count()))){
		    	 errorMessageList.add(" Lane numbers in loopType are not unique");
		     }else {
		    	 //validate the lane numbers are same in all the lane details under startups --> loop
			     if (!(laneNumbers.stream()
			    		    .map(list -> new HashSet<>(list))
			    		    .distinct()
			    		    .count() == 1 && laneNumbers.stream().map(List::size).distinct().count() == 1)) {
		        	errorMessageList.add(" Lane numbers in each loopType are not same");
			     }else {
			    	 //validate whether the lane number in participantDO have one to one mapping with the lane number in laneDO.
					  
			    	 if(!(new HashSet<>(participantLaneValues).equals(new HashSet<>(laneNumbers.get(0))))) {
			        		errorMessageList.add(" Lane numbers in participantType are not same as lane number in laneType");
			        	}
			     }
		     }
		     if(errorMessageList.size() > 0) {
		    	 log.info(String.join("\r\n", errorMessageList));
		    	 return false;
		     }else {
		    	 return true;
		     }	
			
    	}catch(Exception e) {
			throw new HarryKartException(e.getMessage());    		
    	}  		    
    }
	   
    
}