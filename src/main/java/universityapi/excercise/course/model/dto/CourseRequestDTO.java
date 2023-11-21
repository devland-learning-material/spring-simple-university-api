package universityapi.excercise.course.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import universityapi.excercise.course.model.Course;
import universityapi.excercise.university.model.University;
import universityapi.excercise.university.model.dto.UniversityRequestDTO;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
  // private Long id;
  // private String name;
  // private UniversityRequestDTO universityRequestDTO;

  // public Course convertToEntity() {
  // University university = this.universityRequestDTO.convertToEntity();
  // return
  // Course.builder().id(this.id).name(this.name).university(university).build();
  // }

  private Long id;
  private String name;

  public Course convertToEntity() {
    return Course.builder().id(this.id).name(this.name).build();

  }
}
