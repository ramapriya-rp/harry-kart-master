package se.atg.harrykart.rest;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
import se.atg.harrykart.exceptions.HarryKartException;
import se.atg.harrykart.model.HarryKartDO;
import se.atg.harrykart.model.ResultDO;
import se.atg.harrykart.service.HarryKartService;
import se.atg.harrykart.validations.XmlValidator;

    
@RestController
@Slf4j
public class HarryKartController {	

	private List<ResultDO> resultData;	 	 
	
	@Autowired
	private HarryKartService harryKartService;
	
	@Autowired
	private XmlValidator xmlValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(xmlValidator);
	}
	
	
	@PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> playHarryKart(@RequestBody @Valid final HarryKartDO harryKartData) {
		log.info("playHarryKart :: Starting Horse Race");
		try {
			 resultData = harryKartService.getRaceWinnerList(harryKartData);
			 return new ResponseEntity<List<ResultDO>>(resultData, HttpStatus.OK);
		} catch (final HarryKartException e) {
			log.info("playHarryKart :: HarryKartException caught in the controller");
			 // Create a map to hold the error messages
	        Map<String, String> errors = new HashMap<>();

	        // Extract field errors and add them to the errors map
	        errors.put("inputXML Data Errors ",e.getMessage());
	       
	        // Create a response entity with the errors map and return it
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }

	}

}



    
