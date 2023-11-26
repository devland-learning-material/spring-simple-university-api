package universityapi.excercise.university.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.student.model.dto.StudentResponseDTO;
import universityapi.excercise.universitycourse.model.UniversityCourse;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseResponseDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityResponseDTO {
  private Long id;
  private String name;
  private String city;
  private List<UniversityCourseResponseDTO> universityCourseResponseDTOs;
  private List<StudentResponseDTO> studentResponseDTOs;
}
