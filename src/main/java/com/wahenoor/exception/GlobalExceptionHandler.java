package com.wahenoor.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Use Log4j2 logger
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	// Handle specific campaign-related exceptions
	@ExceptionHandler(CampaignException.class)
	public ResponseEntity<ErrorDetails> handleCampaignException(CampaignException e, WebRequest req) {
		return buildErrorResponse(e, req, HttpStatus.BAD_REQUEST, "CAMPAIGN_ERROR");
	}

	// Handle specific admin-related exceptions
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> handleAdminException(AdminException e, WebRequest req) {
		return buildErrorResponse(e, req, HttpStatus.BAD_REQUEST, "ADMIN_ERROR");
	}

	// Handle specific advertiser-related exceptions
	@ExceptionHandler(AdvertiserException.class)
	public ResponseEntity<ErrorDetails> handleAdvertiserException(AdvertiserException e, WebRequest req) {
		return buildErrorResponse(e, req, HttpStatus.BAD_REQUEST, "ADVERTISER_ERROR");
	}

	// Handle specific affiliate-related exceptions
	@ExceptionHandler(AffiliateException.class)
	public ResponseEntity<ErrorDetails> handleAffiliateException(AffiliateException e, WebRequest req) {
		return buildErrorResponse(e, req, HttpStatus.BAD_REQUEST, "AFFILIATE_ERROR");
	}

	// Handle illegal argument exceptions
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest req) {
		logger.error("Illegal argument exception: {}", ex.getMessage(), ex);
		return buildErrorResponse(ex, req, HttpStatus.BAD_REQUEST, "INVALID_ARGUMENT");
	}

	// Handle unsupported HTTP method exceptions
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDetails> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			WebRequest req) {
		logger.error("Method not supported: {}", ex.getMessage(), ex);
		return buildErrorResponse(ex, req, HttpStatus.METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED");
	}

	// Handle rate-limit exceeded exceptions
	@ExceptionHandler(RateLimitExceededException.class)
	public ResponseEntity<ErrorDetails> handleRateLimitExceeded(RateLimitExceededException ex, WebRequest req) {
		logger.warn("Rate limit exceeded: {}", ex.getMessage(), ex);
		return buildErrorResponse(ex, req, HttpStatus.TOO_MANY_REQUESTS, "RATE_LIMIT_EXCEEDED");
	}

	// Handle validation exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		logger.error("Validation error: {}", ex.getMessage(), ex);

		Map<String, Object> body = createErrorBody(HttpStatus.BAD_REQUEST, "Validation failed", null);

		// Collect validation errors
		Map<String, String> errors = new HashMap<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	// Handle 'No Handler Found' exceptions
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
		logger.error("No handler found: {}", ex.getMessage(), ex);

		Map<String, Object> body = createErrorBody(HttpStatus.NOT_FOUND, "Endpoint not found", ex.getRequestURL());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	// Handle general runtime exceptions
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException ex, WebRequest req) {
		logger.error("Runtime exception: {}", ex.getMessage(), ex);
		return buildErrorResponse(ex, req, HttpStatus.INTERNAL_SERVER_ERROR, "RUNTIME_ERROR");
	}

	// Handle generic exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGenericException(Exception e, WebRequest req) {
		logger.error("Unhandled exception: {}", e.getMessage(), e);
		return buildErrorResponse(e, req, HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC_ERROR");
	}

	// Build detailed error response
	private ResponseEntity<ErrorDetails> buildErrorResponse(Exception e, WebRequest req, HttpStatus status,
			String errorCode) {
		logger.error("Exception: {}, Error Code: {}", e.getMessage(), errorCode, e);

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(e.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setDetails(req.getDescription(false));
		errorDetails.setErrorCode(errorCode);

		return new ResponseEntity<>(errorDetails, status);
	}

	// Create common error body for responses
	private Map<String, Object> createErrorBody(HttpStatus status, String message, String path) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("message", message);
		if (path != null) {
			body.put("path", path);
		}
		return body;
	}
}
