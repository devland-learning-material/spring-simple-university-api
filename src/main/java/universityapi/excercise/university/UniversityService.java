package universityapi.excercise.university;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import universityapi.excercise.university.model.University;

@Service
@RequiredArgsConstructor
public class UniversityService {
  private final UniversityRepository universityRepository;

  public University getOneById(Long id) {
    return this.universityRepository.findById(id).orElseThrow(UniversityNotFoundException::new);
  }

  public List<University> getAll(Optional<String> optionalQ, Optional<String> optionalFieldName,
      Optional<String> optionalOrder) {
    Sort sort = Sort.by(Sort.Direction.fromString(optionalOrder.get()), optionalFieldName.get());
    if (!optionalQ.isPresent()) {
      return this.universityRepository.findAll(sort);
    }

    return this.universityRepository.findByNameIgnoreCaseContainingOrCityIgnoreCaseContaining(optionalQ.get(),
        optionalQ.get(), sort);
  }

  public University create(University newUniversity) {
    return this.universityRepository.save(newUniversity);
  }

  public University updateById(University university) {
    University existingUniversity = this.getOneById(university.getId());

    university.setId(existingUniversity.getId());
    return this.universityRepository.save(university);
  }

  public void deleteById(Long id) {
    University existingUniversity = this.getOneById(id);
    this.universityRepository.deleteById(existingUniversity.getId());
  }

}
