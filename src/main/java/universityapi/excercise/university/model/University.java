package universityapi.excercise.university.model;

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
import universityapi.excercise.university.model.dto.UniversityResponseDTO;
import universityapi.excercise.universitycourse.model.UniversityCourse;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseResponseDTO;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class University {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String city;

  @OneToMany(mappedBy = "university") // harus sama dgn nama variabel relasi di tabel course
  @JsonIgnore
  private List<UniversityCourse> universityCourses;

  public UniversityResponseDTO convertToResponse() {
    if (this.universityCourses == null) {
      return UniversityResponseDTO.builder().id(this.id).name(this.name).city(this.city).build();
    }
    List<UniversityCourseResponseDTO> universityCourseResponseDTOs = this.universityCourses.stream()
        .map(UniversityCourse::convertToResponseUniversity).toList();
    return UniversityResponseDTO.builder().id(this.id).name(this.name).city(this.city)
        .universityCourseResponseDTOs(universityCourseResponseDTOs).build();
  }
}
