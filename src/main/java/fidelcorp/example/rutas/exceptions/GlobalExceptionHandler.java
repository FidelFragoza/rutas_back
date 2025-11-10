package fidelcorp.example.rutas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FieldErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        FieldErrorResponse error = new FieldErrorResponse("capacidad", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Otros manejadores de excepciones si deseas
}
