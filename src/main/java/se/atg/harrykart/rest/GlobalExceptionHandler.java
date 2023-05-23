package se.atg.harrykart.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.UnmarshalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
@ExceptionHandler(MethodArgumentNotValidException.class)    
public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	log.info("playHarryKart :: Handling invalid field errors in the input xml");
	BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    // Create a map to hold the error messages
    Map<String, String> errors = new HashMap<>();

    // Extract field errors and add them to the errors map
    for (FieldError fieldError : fieldErrors) {
        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    // Create a response entity with the errors map and return it
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
}

@ExceptionHandler(IllegalStateException.class)    
public ResponseEntity<Map<String, String>> handlexmdValidationException(IllegalStateException ex) {
	log.info("playHarryKart :: Handling errors while validating input xml against xsd");
	
	// Create a map to hold the error messages
    Map<String, String> errors = new HashMap<>();
    errors.put(ex.getMessage(), ex.getCause().toString().split("cvc-complex-type.2.4.i:")[1]);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
       
	}

@ExceptionHandler(Exception.class)    
public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
	
    Map<String, String> errors = new HashMap<>();
    
    if(ex.getCause() instanceof UnmarshalException) {
    	log.info("playHarryKart :: Handling Http request parsing errors");
    	 errors.put("INVALID XML FORMAT",ex.getCause().toString().split("SAXParseException;")[1]);
    }else {
        errors.put("GENERC EXCEPTON Data Errors ",ex.getMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
}


}
