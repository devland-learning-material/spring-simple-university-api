package universityapi.excercise.course;

public class CourseNotFoundException extends RuntimeException {
  public CourseNotFoundException() {
    super("Course not found!");
  }
}
