package universityapi.excercise.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import universityapi.excercise.university.model.University;
import universityapi.excercise.university.model.dto.UniversityRequestDTO;
import universityapi.excercise.university.model.dto.UniversityResponseDTO;

@RestController
@RequiredArgsConstructor
public class UniversityController {
  private final UniversityService universityService;

  @GetMapping("university/{id}")
  public ResponseEntity<UniversityResponseDTO> getOneById(@PathVariable("id") Long id) {
    University existingUniversity = this.universityService.getOneById(id);
    return ResponseEntity.ok(existingUniversity.convertToResponse());
  }

  @GetMapping("university")
  public ResponseEntity<List<UniversityResponseDTO>> getAll(
      @RequestParam(value = "q", required = false) Optional<String> optionalQ,
      @RequestParam(value = "field", defaultValue = "name", required = false) Optional<String> optionalFieldName,
      @RequestParam(value = "order", defaultValue = "asc", required = false) Optional<String> optionalOrder) {
    List<University> university = this.universityService.getAll(optionalQ, optionalFieldName, optionalOrder);
    List<UniversityResponseDTO> universityResponseDTO = university.stream().map(University::convertToResponse).toList();
    return ResponseEntity.status(HttpStatus.OK).body(universityResponseDTO);
  }

  @PostMapping("university")
  public ResponseEntity<UniversityResponseDTO> create(@Valid @RequestBody UniversityRequestDTO universityRequestDTO) {
    University newUniversity = universityRequestDTO.convertToEntity();
    University savedUniversity = this.universityService.create(newUniversity);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUniversity.convertToResponse());
  }

  @PutMapping("university/{id}")
  public ResponseEntity<UniversityResponseDTO> updateById(@PathVariable("id") Long id,
      @Valid @RequestBody UniversityRequestDTO universityRequestDTO) {
    University university = universityRequestDTO.convertToEntity();
    university.setId(id);
    University updatedUniversity = this.universityService.updateById(university);
    return ResponseEntity.status(HttpStatus.OK).body(updatedUniversity.convertToResponse());
  }

  @DeleteMapping("university/{id}")
  public ResponseEntity<Map<String, String>> deleteById(@PathVariable("id") Long id) {
    this.universityService.deleteById(id);
    Map<String, String> responseDelete = new HashMap<>();
    responseDelete.put("status", "success");
    responseDelete.put("message", "Success delete university");
    return ResponseEntity.ok(responseDelete);
  }

}
