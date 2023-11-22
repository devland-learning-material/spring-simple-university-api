package universityapi.excercise.student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.student.model.Student;

@Service
@RequiredArgsConstructor
public class StudentService {
  private final StudentRepositoty studentRepositoty;

  public Page<Student> getAll(Pageable pageable) {
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
