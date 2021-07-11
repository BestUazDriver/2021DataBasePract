package Data.Models;

public class Lesson {
    private Integer id;
    private String name;
    private String dateTime;
    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public Lesson(Integer id, String name, String dateTime) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", course=" + course +
                '}';
    }

    public Lesson(String name, String dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
}
