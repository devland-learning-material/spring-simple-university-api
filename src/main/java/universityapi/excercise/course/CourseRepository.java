package universityapi.excercise.course;

import org.springframework.data.jpa.repository.JpaRepository;

import universityapi.excercise.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
