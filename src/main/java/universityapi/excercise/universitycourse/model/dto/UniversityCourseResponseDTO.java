package universityapi.excercise.universitycourse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.course.model.dto.CourseResponseDTO;
import universityapi.excercise.university.model.dto.UniversityResponseDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityCourseResponseDTO {

  private Long id;
  private CourseResponseDTO courseResponseDTO;
  private UniversityResponseDTO universityResponseDTO;
}
