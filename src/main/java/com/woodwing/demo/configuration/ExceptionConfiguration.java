package com.woodwing.demo.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionConfiguration {

    public static class SummandIsNegative extends RuntimeException {
        public SummandIsNegative() {
            super();
        }
    }

    public static class SummandTypeNotFound extends RuntimeException {
        public SummandTypeNotFound () {
            super();
        }
    }

    public static class SumTypeNotFound extends RuntimeException {
        public SumTypeNotFound () {
            super();
        }
    }

    public static class PayloadNotComplete extends RuntimeException {
        public PayloadNotComplete () {
            super();
        }
    }

    /**
     * Handles exceptions thrown due to summand being negative.
     * @return BAD_REQUEST 400
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Summand cannot be negative")
    public ResponseEntity<String> handleException(SummandIsNegative ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions thrown due to summand type not found.
     * @return BAD_REQUEST 400
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Summand type not found")
    public ResponseEntity<String> handleException(SummandTypeNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions thrown due to sum type not found.
     * @return BAD_REQUEST 400
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Sum type not found")
    public ResponseEntity<String> handleException(SumTypeNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions thrown due to sum type not found.
     * @return BAD_REQUEST 400
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Request payload not complete")
    public ResponseEntity<String> handleException(PayloadNotComplete ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
