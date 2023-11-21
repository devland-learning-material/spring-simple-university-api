package universityapi.excercise.universitycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
import universityapi.excercise.course.model.Course;
import universityapi.excercise.course.model.dto.CourseResponseDTO;
import universityapi.excercise.university.model.University;
import universityapi.excercise.university.model.dto.UniversityResponseDTO;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseRequestDTO;
import universityapi.excercise.universitycourse.model.dto.UniversityCourseResponseDTO;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UniversityCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "university_id")
  private University university;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  public UniversityCourseResponseDTO convertToResponseUniversity() {
    CourseResponseDTO courseResponseDTO = CourseResponseDTO.builder().id(this.course.getId())
        .name(this.course.getName()).build();
    return UniversityCourseResponseDTO.builder().id(this.id).courseResponseDTO(courseResponseDTO).build();
  }

  public UniversityCourseResponseDTO convertToResponseCourse() {
    UniversityResponseDTO universityResponseDTO = UniversityResponseDTO.builder().id(this.university.getId())
        .name(this.university.getName()).city(this.university.getCity()).build();
    return UniversityCourseResponseDTO.builder().id(this.id).universityResponseDTO(universityResponseDTO).build();
  }
}
