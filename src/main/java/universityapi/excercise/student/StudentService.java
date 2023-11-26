package universityapi.excercise.student;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.student.model.Student;

@Service
@RequiredArgsConstructor
public class StudentService {
  private final StudentRepositoty studentRepositoty;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public Page<Student> getAll(Pageable pageable) {
    this.logger.info("Get All");
    return this.studentRepositoty.findAll(pageable);
  }

  public Student getOneById(Long id) {
    return this.studentRepositoty.findById(id).orElseThrow(StudentNotFoundException::new);
  }

  public Student create(Student newStudent) {
    return this.studentRepositoty.save(newStudent);
  }

  public Student updateById(Student student) {
    Student existingStudent = this.getOneById(student.getId());

    student.setId(existingStudent.getId());
    return this.studentRepositoty.save(student);
  }

  public void deleteById(Long id) {
    Student exisingStudent = this.getOneById(id);
    this.studentRepositoty.deleteById(exisingStudent.getId());
  }

}
