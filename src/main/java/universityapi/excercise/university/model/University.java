package universityapi.excercise.university.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
import universityapi.excercise.student.model.Student;
import universityapi.excercise.student.model.dto.StudentResponseDTO;
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

  @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<UniversityCourse> universityCourses;

  @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
  private List<Student> students;

  public UniversityResponseDTO convertToResponse() {
    if (this.universityCourses == null || this.students == null) {
      return UniversityResponseDTO.builder().id(this.id).name(this.name).city(this.city).build();
    }
    List<UniversityCourseResponseDTO> universityCourseResponseDTOs = this.universityCourses.stream()
        .map(UniversityCourse::convertToResponseUniversity).toList();

    List<StudentResponseDTO> studentResponseDTOs = this.students.stream().map(Student::convertToResponse).toList();
    return UniversityResponseDTO.builder().id(this.id).name(this.name).city(this.city)
        .universityCourseResponseDTOs(universityCourseResponseDTOs).studentResponseDTOs(studentResponseDTOs).build();
  }
}
