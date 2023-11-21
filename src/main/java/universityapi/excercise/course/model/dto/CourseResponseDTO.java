package universityapi.excercise.course.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseResponseDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
  private Long id;
  private String name;
  private List<UniversityCourseResponseDTO> universityCourseResponseDTOs;
}
