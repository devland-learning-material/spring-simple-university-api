package universityapi.excercise.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import universityapi.excercise.student.model.dto.StudentResponseDTO;
import universityapi.excercise.university.model.University;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false, length = 512)
  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender; 

  @ManyToOne
  @JoinColumn(name = "university_id")
  private University university;

  public StudentResponseDTO convertToResponse() {
    return StudentResponseDTO.builder().id(this.id).name(this.name).gender(this.gender).build();
  }
}
