package universityapi.excercise.student.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.student.model.Gender;
import universityapi.excercise.student.model.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
  private Long id;
  @NotBlank(message = "Name is required")
  private String name;
  // @NotBlank(message = "Gender is required")
  private Gender gender;

  public Student convertToEntity() {
    return Student.builder().id(this.id).name(this.name).gender(this.gender).build();
  }
}
