package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String name, int grade, String comment, Course course) {
        assertEquals(name, course.getCourseName());
        assertEquals(grade, course.getGrade());
        assertEquals(comment, course.getComment());
    }
}
