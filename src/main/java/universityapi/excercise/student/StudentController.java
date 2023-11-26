package universityapi.excercise.student;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import universityapi.excercise.student.model.Student;
import universityapi.excercise.student.model.dto.StudentRequestDTO;
import universityapi.excercise.student.model.dto.StudentResponseDTO;

@RestController
@RequiredArgsConstructor
public class StudentController {
  private final StudentService studentService;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/students")
  public ResponseEntity<Page<StudentResponseDTO>> getAll(
      @RequestParam(value = "page", required = false, defaultValue = "1") String pageString,
      @RequestParam(value = "size", required = false, defaultValue = "5") String sizeString) {
    this.logger.info("Get All " + ", page : " + pageString + ", size : " + sizeString);
    int page = Integer.parseInt(pageString) - 1;
    int size = Integer.parseInt(sizeString);
    Pageable pageable = PageRequest.of(page, size);
    Page<Student> students = this.studentService.getAll(pageable);
    Page<StudentResponseDTO> studentResponseDTOPage = students.map(Student::convertToResponse);

    return ResponseEntity.ok(studentResponseDTOPage);
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<StudentResponseDTO> getOneById(@PathVariable("id") Long id) {
    Student student = this.studentService.getOneById(id);
    return ResponseEntity.ok(student.convertToResponse());
  }

  @PostMapping("students")
  public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    Student newStudent = studentRequestDTO.convertToEntity();
    Student savedStudent = this.studentService.create(newStudent);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent.convertToResponse());
  }

  @PutMapping("students/{id}")
  public ResponseEntity<StudentResponseDTO> updateById(@PathVariable Long id,
      @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    Student student = studentRequestDTO.convertToEntity();
    student.setId(id);
    Student updatedStudent = this.studentService.updateById(student);
    return ResponseEntity.ok(updatedStudent.convertToResponse());
  }

  @DeleteMapping("students/{id}")
  public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
    this.studentService.deleteById(id);
    Map<String, String> responseDelete = new HashMap<>();
    responseDelete.put("status", "success");
    responseDelete.put("message", "Success delte student");
    return ResponseEntity.ok(responseDelete);
  }
}
