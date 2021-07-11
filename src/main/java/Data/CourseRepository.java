package Data;

import Data.Models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findCourseById(Integer id);
    List<Course> findCourseByName(String name);
    void save(Course course);
    void update(Course course);

}
