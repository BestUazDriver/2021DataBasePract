package Data;

import Data.Models.Course;
import Data.Models.Student;
import Data.Models.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryJdbcImpl implements CourseRepository {

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from course where id=?";
    //language=SQL
    private static final String SQL_FIND_BY_NAME = "select * from course where name =?";
    //language=SQL
    private static final String SQL_COURSE_SAVE = "insert into course (name, dateofbeginend, teacherid) values (?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "update course set name = ?, start_date = ?, end_date = ?, teacher_id = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public CourseRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Course> courseRowsMapper = (rows, rowNumber) -> {
        Integer id = rows.getInt("id");
        String name = rows.getString("name");
        String date = rows.getString("dateOfBeginEnd");

        Teacher teacher = new Teacher();
        teacher.setId(rows.getInt("teacherId"));

        Course course = new Course(id, name, date, teacher);
        course.setStudents(new ArrayList<>());
        return course;

    };
    private RowMapper<Student> studentRowsMapper=(rows,rowNumber)->{
        Integer id = rows.getInt("id");
        String name = rows.getString("name");
        String surname = rows.getString("surname");
        Integer group = rows.getInt("group");
        return new Student(id, name, surname, group);
    };

    @Override
    public Optional<Course> findCourseById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, courseRowsMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return jdbcTemplate.query(SQL_FIND_BY_NAME, courseRowsMapper, name);
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_COURSE_SAVE, new String[]{"id"});
            statement.setString(1, course.getName());
            statement.setString(2, course.getDateOfBeginEnd());
            statement.setInt(3, course.getTeacher().getId());
            return statement;
        }, keyHolder);
        course.setId(keyHolder.getKey().intValue());
    }


    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE, course.getName(), course.getDateOfBeginEnd(), course.getTeacher().getId(), course.getId());
    }

    private final ResultSetExtractor<Course> courseExtractor = resultSet -> {
        Course course = null;
        if (resultSet.next()) {
            course = courseRowsMapper.mapRow(resultSet, resultSet.getRow());
            course.setStudents(new ArrayList<>());

            do {
                Integer id = resultSet.getObject("student_id", Integer.class);
                if (id != null) {
                    Student student = studentRowsMapper.mapRow(resultSet, resultSet.getRow());
                    course.getStudents().add(student);
                }
            } while (resultSet.next());
        }
        return course;
    };
}
