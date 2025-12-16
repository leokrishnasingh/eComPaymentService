package com.krishna.ecomproductservice.controllerAdvisor;

import com.krishna.ecomproductservice.exceptions.InvalidData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidData.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidData ex, HttpServletRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
