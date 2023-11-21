package universityapi.excercise.universitycourse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.universitycourse.model.UniversityCourse;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseRequestDTO;

@RestController
@RequiredArgsConstructor
public class UniversityCourseController {
  private final UniversityCourseService universityCourseService;

  @PostMapping("university-courses")
  public ResponseEntity<UniversityCourse> create(@RequestBody UniversityCourseRequestDTO universityCourseRequestDTO) {
    UniversityCourse newUniversityCourse = universityCourseRequestDTO.convertToEntity();
    UniversityCourse savedUniversityCourse = this.universityCourseService.create(newUniversityCourse);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversityCourse);
  }
}
