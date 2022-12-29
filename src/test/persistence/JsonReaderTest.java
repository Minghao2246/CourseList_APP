package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList courseList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            CourseList courseList = reader.read();
            assertEquals(0, courseList.getCourseListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            CourseList courseList = reader.read();
            List<Course> courses = courseList.getCourses();
            assertEquals(2, courses.size());
            checkCourse("name", 90, "do better", courses.get(0));
            checkCourse("name2", 50, "study more", courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
