package universityapi.excercise.course;

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
}
