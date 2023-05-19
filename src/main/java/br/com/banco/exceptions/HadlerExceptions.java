package br.com.banco.exceptions;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HadlerExceptions {

	@ExceptionHandler(ObjectNotFound.class)
	public ResponseEntity<Error> objectoNaoEncontrado(ObjectNotFound e, HttpServletRequest request) {
		Error error = new Error(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now(), request.getRequestURI());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> argumentoNaoValido(MethodArgumentNotValidException e, HttpServletRequest request) {
		Error error = new Error("Argumento não validado", HttpStatus.BAD_REQUEST.value(), LocalDate.now(),
				request.getRequestURI());
		for (FieldError field : e.getBindingResult().getFieldErrors()) {
			error.setFields(field.getField(), field.getDefaultMessage());
		}
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Error> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
		Error error = new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDate.now(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Error> messageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
		Error error = new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDate.now(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
