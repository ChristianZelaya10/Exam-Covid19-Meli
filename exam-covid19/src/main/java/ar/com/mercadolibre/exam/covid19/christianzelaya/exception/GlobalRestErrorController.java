package ar.com.mercadolibre.exam.covid19.christianzelaya.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.mercadolibre.exam.covid19.christianzelaya.util.ApiErrorResponse;
import javassist.tools.web.BadHttpRequest;

@RestControllerAdvice(basePackages = {"ar.com.mercadolibre.exam.covid19.christianzelaya.controller"})
public class GlobalRestErrorController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> getEmptyDataAccessException(EmptyResultDataAccessException ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@ExceptionHandler({DuplicateKeyException.class})
	public ResponseEntity<Object> handleException(DuplicateKeyException ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> getDataIntegrityViolationException(DataIntegrityViolationException ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<Object> getNotFoundException(EntityNotFoundException ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@ExceptionHandler({BadHttpRequest.class})
	public ResponseEntity<Object> getBadRequest(BadHttpRequest ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Object> handlerException(IllegalArgumentException ex, ServletWebRequest webRequest){
		
		ApiErrorResponse apiError = new ApiErrorResponse();
		apiError.setMessage(ex.getMessage());
		apiError.setMethod(webRequest.getHttpMethod().name());
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
}
