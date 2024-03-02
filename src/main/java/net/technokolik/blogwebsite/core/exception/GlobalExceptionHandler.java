package net.technokolik.blogwebsite.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final HttpServletRequest request;

    public GlobalExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }
    @ExceptionHandler({MethodArgumentNotValidException.class, BusinessException.class, RuntimeException.class})
    public ResponseEntity<ErrorMessage> handleException ( Exception exception){
        ErrorMessage errorMessage = new ErrorMessage();
        if (exception instanceof  MethodArgumentNotValidException) {
            errorMessage.setValidationErrors(new HashMap<>());
            for (FieldError fieldError:((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()) {
                errorMessage.addValidationError(fieldError.getField(),fieldError.getDefaultMessage());
            }
        } else if (exception instanceof  BusinessException) {
            errorMessage.setStatus(400);
        } else if (exception instanceof  NotUniqueEmailException) {
            errorMessage.setMessage(exception.getMessage());
            errorMessage.setValidationErrors(((NotUniqueEmailException) exception).getValidationErros());
        }

        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }
}
