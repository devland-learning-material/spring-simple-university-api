package universityapi.excercise.course;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.course.model.Course;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;

  public Course getOneById(Long id) {
    return this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
  }

  public Course create(Course newCourse) {
    return this.courseRepository.save(newCourse);
  }

  public Course updateById(Course newCourse) {
    Course existingCourse = this.getOneById(newCourse.getId());
    newCourse.setId(existingCourse.getId());
    return this.courseRepository.save(newCourse);
  }

  public List<Course> getAll() {
    return this.courseRepository.findAll();
  }

  public void deleteById(Long id) {
    Course existingCourse = this.getOneById(id);
    this.courseRepository.deleteById(existingCourse.getId());
  }
}
