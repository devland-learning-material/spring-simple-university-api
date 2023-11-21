package universityapi.excercise.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.course.model.Course;
import universityapi.excercise.course.model.dto.CourseRequestDTO;
import universityapi.excercise.course.model.dto.CourseResponseDTO;

@RestController
@RequiredArgsConstructor
public class CourseController {
  private final CourseRepository courseRepository;

  @PostMapping("/courses")
  public ResponseEntity<Course> create(@RequestBody CourseRequestDTO courseRequestDTO) {
    Course newCourse = courseRequestDTO.convertToEntity();
    Course savedCourse = this.courseRepository.save(newCourse);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDTO> getById(@PathVariable("id") Long id) {
    Course existingCourse = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    return ResponseEntity.ok(existingCourse.convertToResponse());
  }

  @PutMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDTO> updateById(@PathVariable("id") Long id,
      @RequestBody CourseRequestDTO courseRequestDTO) {
    Course newCourse = courseRequestDTO.convertToEntity();
    newCourse.setId(id);
    Course existingCourse = this.courseRepository.findById(newCourse.getId())
        .orElseThrow(CourseNotFoundException::new);
    newCourse.setId(existingCourse.getId());
    Course savedCourse = this.courseRepository.save(newCourse);
    return ResponseEntity.status(HttpStatus.OK).body(savedCourse.convertToResponse());
  }
}
