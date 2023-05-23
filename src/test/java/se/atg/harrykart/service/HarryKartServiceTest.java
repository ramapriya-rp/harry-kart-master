
  package se.atg.harrykart.service;
  
  import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.test.context.SpringBootTest; import
  se.atg.harrykart.exceptions.HarryKartException; import
  se.atg.harrykart.model.HarryKartDO; import se.atg.harrykart.model.ResultDO;
  import se.atg.harrykart.rest.HarryKartController; import
  se.atg.harrykart.service.HarryKartService; import
  se.atg.harrykart.validations.XmlValidator;
  
  import javax.xml.bind.JAXBContext; import javax.xml.bind.JAXBException;
  import javax.xml.bind.Unmarshaller; import java.io.File; import
  java.util.ArrayList; import java.util.List;
  
  import static org.junit.jupiter.api.Assertions.assertEquals;
  
  @SpringBootTest public class HarryKartServiceTest {
  
  @Autowired private HarryKartService harryKartService;
  
  @BeforeEach public void setup() { 
	  // Perform any setup tasks }
  }
  
  @Test public void testCalculateRaceResult() throws JAXBException,
  HarryKartException { HarryKartDO harryKartData =  createHarryKartData("input_1.xml"); 
  int numberOfLoops = 3;
  ResultDO actualResult = harryKartService.calculateRaceResult(harryKartData,
  harryKartData.getStartList().get(0), numberOfLoops);
  assertEquals(getExpectedResult(), actualResult); }
  
  @Test public void testGetRaceWinnerList() throws JAXBException,
  HarryKartException { HarryKartDO harryKartData =
  createHarryKartData("input_1.xml"); List<ResultDO> actualResult =
  harryKartService.getRaceWinnerList(harryKartData);
  assertEquals(getExpectedWinnerList(), actualResult); }
  
  private HarryKartDO createHarryKartData(String inputFile) throws
  JAXBException { File file = new File(inputFile); JAXBContext jaxbContext =
  JAXBContext.newInstance(HarryKartDO.class); Unmarshaller unmarshaller =
  jaxbContext.createUnmarshaller(); return (HarryKartDO)
  unmarshaller.unmarshal(file); }
  
  private ResultDO getExpectedResult() { return new ResultDO(1,
  "TIMETOBELUCKY", 111.3); }
  
  private List<ResultDO> getExpectedWinnerList() { List<ResultDO> winnerList =
  new ArrayList<>(); winnerList.add(new ResultDO(1, "TIMETOBELUCKY", 111.3));
  winnerList.add(new ResultDO(2, "WAIKIKI SILVIO", 111.3)); winnerList.add(new
  ResultDO(3, "HERCULES BOKO", 111.3)); return winnerList; } }
 