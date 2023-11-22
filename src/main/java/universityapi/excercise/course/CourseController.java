package universityapi.excercise.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  private final CourseService courseService;

  @PostMapping("/courses")
  public ResponseEntity<CourseResponseDTO> create(@RequestBody CourseRequestDTO courseRequestDTO) {
    Course newCourse = courseRequestDTO.convertToEntity();
    Course savedCourse = this.courseService.create(newCourse);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse.convertToResponse());
  }

  @GetMapping("/courses")
  public ResponseEntity<List<CourseResponseDTO>> getAll() {
    List<Course> courses = this.courseService.getAll();
    List<CourseResponseDTO> courseResponseDTOs = courses.stream().map(Course::convertToResponse).toList();
    return ResponseEntity.ok(courseResponseDTOs);
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDTO> getById(@PathVariable("id") Long id) {
    Course existingCourse = this.courseService.getOneById(id);
    return ResponseEntity.ok(existingCourse.convertToResponse());
  }

  @PutMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDTO> updateById(@PathVariable("id") Long id,
      @RequestBody CourseRequestDTO courseRequestDTO) {
    Course newCourse = courseRequestDTO.convertToEntity();
    newCourse.setId(id);
    Course savedCourse = this.courseService.updateById(newCourse);
    return ResponseEntity.status(HttpStatus.OK).body(savedCourse.convertToResponse());
  }

  @DeleteMapping("/courses/{id}")
  public ResponseEntity<Map<String,String>> deleteById(@PathVariable("id") Long id){
    this.courseService.deleteById(id);
    Map<String, String> responseDelete = new HashMap<>();
    responseDelete.put("status", "success");
    responseDelete.put("message", "Success delete course");
    return ResponseEntity.ok(responseDelete);
  }

}
