package universityapi.excercise.university.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import universityapi.excercise.university.model.University;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityRequestDTO {
  private Long id;
  @NotBlank(message = "Name is required")
  private String name;
  @NotBlank(message = "City is required")
  private String city;

  public University convertToEntity() {
    return University.builder().id(this.id).name(this.name).city(this.city).build();
  }
}
