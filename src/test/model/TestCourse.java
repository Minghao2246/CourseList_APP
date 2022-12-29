package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {
    Course course1;
    Course course2;

    @BeforeEach
    void TestCourse() {
        course1 = new Course("cpsc 210", 45, "need to code more");
        course2 = new Course("cpsc 110", 91, "well done!");
    }

    @Test
    void TestConstructor() {
        assertEquals(course1.getCourseName(),"cpsc 210");
        assertEquals(course2.getCourseName(),"cpsc 110");
        assertEquals(course1.getGrade(),45);
        assertEquals(course2.getGrade(),91);
        assertEquals(course1.getComment(),"need to code more");
        assertEquals(course2.getComment(),"well done!");
//        assertFalse(course1.getCourseComp());
//        assertFalse(course2.getCourseComp());
    }

//    @Test
//    void TestSetComp() {
//        course2.setCourseComp();
//        assertTrue(course2.getCourseComp());
//        assertFalse(course1.getCourseComp());
//    }
}
