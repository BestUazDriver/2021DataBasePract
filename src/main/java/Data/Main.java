package Data;

import Data.Models.Course;
import Data.Models.Lesson;
import Data.Models.Teacher;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src\\main\\Resources\\application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config=new HikariConfig();
        config.setPassword(properties.getProperty("db.password"));
        config.setUsername(properties.getProperty("db.user"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikariPoolSize")));
        DataSource dataSource = new HikariDataSource(config);

        CourseRepositoryJdbcImpl courseRep = new CourseRepositoryJdbcImpl(dataSource);
        System.out.println(courseRep.findCourseById(1));
        System.out.println(courseRep.findCourseByName("2nd"));
        Teacher teacher = new Teacher();
        teacher.setId(1);
        Course course = new Course("4th", "01.09.10009", teacher);
        System.out.println(course);
        courseRep.save(course);
        System.out.println(course);


        LessonRepositoryJdbcImpl lessonRep = new LessonRepositoryJdbcImpl(dataSource);
        System.out.println(lessonRep.findLessonById(1));
        System.out.println(lessonRep.findLessonByName("math"));
        Lesson lesson = new Lesson("english","12:00, monday");
        lessonRep.save(lesson);
        System.out.println(lesson);
        lessonRep.update(new Lesson(2,"english", "12:00, sunday"));

    }
}
