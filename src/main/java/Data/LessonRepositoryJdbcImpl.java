package Data;

import Data.Models.Course;
import Data.Models.Lesson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class LessonRepositoryJdbcImpl implements LessonRepository {
    //language=SQL
    private final String SQL_FIND_BY_ID = "select * from lesson where id=?";
    //language=SQL
    private final String SQL_FIND_BY_NAME = "select * from lesson where lname =?";
    //language=SQL
    private static final String SQL_SAVE = "insert into lesson (lname, timeandweek) values (?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "update lesson set lname = ?, timeandweek=? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public LessonRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Lesson> lessonRowMapper = (rows, rowsNumber) -> {
        Integer id=rows.getInt("id");
        String name = rows.getString("lname");
        String date = rows.getString("timeandweek");
        return new Lesson(id,name, date);
    };

    @Override
    public Optional<Lesson> findLessonById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, lessonRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> findLessonByName(String name) {
        return jdbcTemplate.query(SQL_FIND_BY_NAME, lessonRowMapper, name);
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDateTime());
            return statement;
        }, keyHolder);
        lesson.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE, lesson.getName(), lesson.getDateTime(), lesson.getId());
    }
}
