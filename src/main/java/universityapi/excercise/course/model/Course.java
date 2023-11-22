package universityapi.excercise.course.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.course.model.dto.CourseResponseDTO;
import universityapi.excercise.universitycourse.model.UniversityCourse;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseResponseDTO;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<UniversityCourse> universityCourses;

  public CourseResponseDTO convertToResponse() {
    if (this.universityCourses == null) {
      return CourseResponseDTO.builder().id(this.id).name(this.name).build();
    }
    List<UniversityCourseResponseDTO> universityCourseResponseDTOs = this.universityCourses.stream()
        .map(UniversityCourse::convertToResponseCourse).toList();
    return CourseResponseDTO.builder().id(this.id).name(this.name)
        .universityCourseResponseDTOs(universityCourseResponseDTOs).build();
  }

}
