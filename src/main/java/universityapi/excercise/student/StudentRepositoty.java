package universityapi.excercise.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import universityapi.excercise.student.model.Student;

public interface StudentRepositoty extends JpaRepository<Student, Long> {
  Page<Student> findAll(Pageable pageable);
}
