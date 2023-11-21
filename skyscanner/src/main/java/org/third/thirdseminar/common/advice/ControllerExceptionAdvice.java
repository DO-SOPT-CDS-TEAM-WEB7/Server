package org.third.thirdseminar.common.advice;

import org.third.thirdseminar.exception.model.CustomException;
import org.third.thirdseminar.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.NoArgsConstructor;

@RestControllerAdvice
@Component
@NoArgsConstructor
public class ControllerExceptionAdvice {
	/**
	 * custom error
	 */
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
		return ResponseEntity.status(e.getHttpStatus())
			.body(ApiResponse.error(e.getError(), e.getMessage()));
	}

	// @ExceptionHandler(IllegalArgumentException.class)
	// public ResponseEntity<Void> handleIllegalArgumentException(final IllegalArgumentException e) {
	// 	return ResponseEntity.badRequest().build();
	// }
}
