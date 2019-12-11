package com.claim.medicalclaim.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler  extends ResponseEntityExceptionHandler {

	public ResponseEntity<ErrorResponse> userException(Exception exception){
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}
