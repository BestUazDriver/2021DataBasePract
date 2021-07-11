package Data;

import Data.Models.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Optional<Lesson> findLessonById(Integer id);
    List<Lesson> findLessonByName(String name);
    void save(Lesson lesson);
    void update(Lesson lesson);
}
