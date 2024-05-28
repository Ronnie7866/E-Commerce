package com.backend.ecommerce.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException e,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND // .value() code ke liye
//				, HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
//	}
//
//	//Repeated Value posted in db
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<ErrorMessage> AlreadyPresentException(ConstraintViolationException e,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), LocalDateTime.now(),
//				e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.CONFLICT);
//	}
//
//	@ExceptionHandler(InsufficientAuthenticationException.class)
//	public ResponseEntity<ErrorMessage> insufficientResExceptionHandler(Exception e, HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
//	}
//
////	//For Validators in entity/records
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ErrorMessage> handleMethodArgsNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
//
//		Map<String, String> errors = new LinkedHashMap<String, String>();
//		e.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				errors.toString(), request.getRequestURI());
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//
//	//for path /0
//	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
//	private ResponseEntity<ErrorMessage> handleRequestPathVariablesValidationException(Exception ex,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				ex.getMessage(), request.getRequestURI());
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
////------------------
//
//	//Manually throw catch
//	@ExceptionHandler(IllegalArgumentException.class)
//	public ResponseEntity<ErrorMessage> handleWrongDataTypeException(IllegalArgumentException e,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//	// type conversion failure
//	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
//	public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				"Invalid data type for parameter: " + e.getName(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
////	//----------------
//
//	//Redirection of wrong JSON form format
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
//			HttpServletRequest request) {
//
//		if (e.getCause().getClass().getSimpleName().equals("InvalidFormatException")) {
//			return handleInvalidFormatException((InvalidFormatException) e.getMostSpecificCause(), request);}
//		else if(e.getCause().getClass().getSimpleName().equals("JsonParseException")){
//			return handleJsonParseException((JsonParseException) e.getMostSpecificCause(), request);}
//		else {
//			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//					e.getMessage(), request.getRequestURI());
//			return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	//InvalidFormatException example= user : "a" // it should be 1 / "1"
//	private ResponseEntity<ErrorMessage> handleInvalidFormatException(InvalidFormatException ex, HttpServletRequest request) {
//		System.out.println("hmm");
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				"Wrong data type provided in JSON Form with given value: " + ex.getValue(), request.getRequestURI());
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//	//InvalidFormatException example= user : asdasd
//	private ResponseEntity<ErrorMessage> handleJsonParseException(JsonParseException ex,
//			HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
//				ex.getOriginalMessage(), request.getRequestURI());
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(InvalidJsonDataException.class)
//	public ResponseEntity<ErrorMessage> InvalidJsonDataExceptionHandler(Exception e, HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//	// missing query parameters
//	@ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
//	public ResponseEntity<ErrorMessage> MissingServletRequestParameterException(Exception e, HttpServletRequest request) {
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
//		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
//	}
//
//	---------- BadCredentialsException
	@ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception e, HttpServletRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
	}

//	//	---------- UsernameNotFoundException
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<ErrorMessage> exceptionHandler(Exception e, HttpServletRequest request) {
//		System.out.println("\n \n \n aaya yaha \n \n \n \n ");
//		ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), e.getMessage(), request.getRequestURI());
//
//		return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
//	}
	
//-------------------- Fallback Handler
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception e, HttpServletRequest request) {
		System.out.println("Fall Back Case - " + e.getClass());
		e.printStackTrace();

		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), e.getMessage() + " (Fall Back Case) ",
				request.getRequestURI());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
