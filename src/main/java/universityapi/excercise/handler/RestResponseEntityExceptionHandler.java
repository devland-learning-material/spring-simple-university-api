package universityapi.excercise.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
  // HttpHeaders headers,
  // HttpStatusCode status, WebRequest request) {
  // Map<String, String> errors = new HashMap<>();
  // exception.getBindingResult().getAllErrors().forEach(error -> {
  // String fieldName = ((FieldError) error).getField();
  // String errorMessage = error.getDefaultMessage();
  // errors.put(fieldName, errorMessage);
  // });

  // return ResponseEntity.status(status).body(errors);
  // }

  @Override
  @Nullable
  @NotBlank
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.status(status).body(errors);
  }

}
