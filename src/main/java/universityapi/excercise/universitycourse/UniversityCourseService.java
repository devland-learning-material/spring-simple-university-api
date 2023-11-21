package universityapi.excercise.universitycourse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.course.CourseService;
import universityapi.excercise.course.model.Course;
import universityapi.excercise.university.UniversityService;
import universityapi.excercise.university.model.University;
import universityapi.excercise.universitycourse.model.UniversityCourse;

@Service
@RequiredArgsConstructor
public class UniversityCourseService {
  private final UniversityCourseRepository universityCourseRepository;

  private final UniversityService universityService;
  private final CourseService courseService;

  public UniversityCourse create(UniversityCourse newUniversityCourse) {
    University existingUniversity = this.universityService.getOneById(newUniversityCourse.getUniversity().getId());
    Course existingCourse = this.courseService.getOneById(newUniversityCourse.getCourse().getId());
    newUniversityCourse.setUniversity(existingUniversity);
    newUniversityCourse.setCourse(existingCourse);
    return this.universityCourseRepository.save(newUniversityCourse);
  }

}
