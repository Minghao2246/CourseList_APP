package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            CourseList courseList = new CourseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            CourseList courseList = new CourseList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(courseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            courseList = reader.read();
            assertEquals(0, courseList.getCourseListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CourseList courseList = new CourseList();
            courseList.addCourse(new Course("name", 90, "do better"));
            courseList.addCourse(new Course("name2", 50, "study more"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(courseList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            courseList = reader.read();
            List<Course> courses = courseList.getCourses();
            assertEquals(2, courses.size());
            checkCourse("name", 90, "do better", courses.get(0));
            checkCourse("name2", 50, "study more", courses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
