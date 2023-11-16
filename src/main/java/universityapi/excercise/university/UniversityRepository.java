package universityapi.excercise.university;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import universityapi.excercise.university.model.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

  List<University> findByNameIgnoreCaseContainingOrCityIgnoreCaseContaining(
      String optionalName, String optionalCity, Sort sort);
}
