package br.ada.americanas.moviebattle.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = {RestController.class})
public class RestGenericExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handlerConstraintViolationException(
            ConstraintViolationException ex,
            WebRequest request
    ) {
        logger.error("Exception unhandled", ex);

        List<Map<String, Object>> errors = new ArrayList<>();
        for (ConstraintViolation error : ex.getConstraintViolations()) {
            errors.add(
                    Map.of(
                            error.getPropertyPath().toString(),
                            error.getMessage()
                    )
            );
        }

        Map<String, Object> body = Map.of(
                "code", HttpStatus.BAD_REQUEST,
                "errors", errors,
                "date-time", LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerAnyException(
            Exception ex,
            WebRequest request
    ) {
        logger.error("Unhandled exception", ex);

        Map<String, Object> body = Map.of(
                "code", HttpStatus.INTERNAL_SERVER_ERROR,
                "message", "Sorry, we failed with you. Try again later.....",
                "date-time", LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

}
