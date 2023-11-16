package universityapi.excercise.university;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UniversityNotFoundException extends RuntimeException {
  public UniversityNotFoundException() {
    super("University not found");
  }
}
