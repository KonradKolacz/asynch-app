package com.example.demoasynch.config;

import com.example.demoasynch.dto.ExceptionDto;
import com.example.demoasynch.dto.ValidationErrorDto;
import com.example.demoasynch.exception.BadInputLengthException;
import com.example.demoasynch.exception.NotStartedOrCompletedTaskException;
import com.example.demoasynch.exception.UnfinishedTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnfinishedTaskException.class, NotStartedOrCompletedTaskException.class})
    public ResponseEntity<ExceptionDto> handleNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadInputLengthException.class)
    public ResponseEntity<ExceptionDto> handleBadInputLengthException(BadInputLengthException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationErrors(MethodArgumentNotValidException e) {
        final List<ValidationErrorDto> errors = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
