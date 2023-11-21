package universityapi.excercise.universitycourse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.course.model.Course;
import universityapi.excercise.university.model.University;
import universityapi.excercise.universitycourse.model.UniversityCourse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityCourseRequestDTO {
  private Long id;
  private Long courseId;
  private Long universityId;

  public UniversityCourse convertToEntity() {
    Course course = Course.builder().id(this.courseId).build();
    University university = University.builder().id(this.universityId).build();
    return UniversityCourse.builder().id(this.id).course(course).university(university).build();
  }
}
