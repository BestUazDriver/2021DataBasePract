package Data.Models;

import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private String dateOfBeginEnd;
    private Teacher teacher;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBeginEnd() {
        return dateOfBeginEnd;
    }

    public void setDateOfBeginEnd(String dateOfBeginEnd) {
        this.dateOfBeginEnd = dateOfBeginEnd;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public Course(String name, String dateOfBeginEnd, Teacher teacher) {
        this.name = name;
        this.dateOfBeginEnd = dateOfBeginEnd;
        this.teacher = teacher;
    }

    public Course(Integer id, String name, String dateOfBeginEnd, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.dateOfBeginEnd = dateOfBeginEnd;
        this.teacher = teacher;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBeginEnd='" + dateOfBeginEnd + '\'' +
                ", teacher id=" + teacher.getId() +
                ", students=" + students +
                '}';
    }
}
