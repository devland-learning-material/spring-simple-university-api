package universityapi.excercise.universitycourse;

import org.springframework.data.jpa.repository.JpaRepository;

import universityapi.excercise.universitycourse.model.UniversityCourse;

public interface UniversityCourseRepository extends JpaRepository<UniversityCourse, Long> {

}
