package model;

import exception.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestCourseList {
    CourseList courseList1;
    CourseList courseList2;
    Course course1;
    Course course2;
    Course course3;
    Course course4;

    @BeforeEach
    void setup() {
        courseList1 = new CourseList();
        courseList2 = new CourseList();
        course1 = new Course("econ 102", 84, "do practice!");
        course2 = new Course("stat 302", 66, "review more!");
        course3 = new Course("stat 306", 74, "do not panic during midterm period!");
        course4 = new Course("cpsc 121", 84, "good job!");
    }

    @Test
    void TestConstructor() throws EmptyListException {
        assertEquals(courseList1.getCourseListSize(), 0);
        assertEquals(courseList2.getCourseListSize(), 0);
        assertEquals(courseList1.getCoursesCompNum(), 0);
        assertEquals(courseList2.getCoursesCompNum(), 0);
    }

    @Test
    void TestAddCourse() {
        assertEquals(courseList1.getCourseListSize(), 0);
        courseList1.addCourse(course1);
        assertEquals(courseList1.getCourseListSize(), 1);
        assertEquals(courseList2.getCourseListSize(), 0);
        courseList1.addCourse(course4);
        courseList1.addCourse(course3);
        assertEquals(courseList1.getCourseListSize(), 3);
        courseList2.addCourse(course2);
        assertEquals(courseList2.getCourseListSize(), 1);
        courseList1.addCourse(course1);
        assertEquals(courseList1.getCourseListSize(), 3);
    }

    @Test
    void TestRemoveCourse() throws EmptyListException {
        assertEquals(courseList1.getCourseListSize(), 0);
        courseList1.addCourse(course1);
        courseList1.addCourse(course2);
        courseList1.addCourse(course3);
        courseList1.addCourse(course4);
        assertFalse( courseList1.removeCourse("Test"));
        assertEquals(courseList1.getCourseListSize(), 4);
        assertEquals(courseList2.getCourseListSize(), 0);
        courseList2.addCourse(course2);
        courseList1.removeCourse("econ 102");
        assertFalse(courseList1.courseContain(course1));
        assertTrue(courseList1.courseContain(course2));
        assertEquals(courseList1.getCourseListSize(), 3);
        assertTrue(courseList1.removeCourse("cpsc 121"));
        assertFalse(courseList1.courseContain(course4));
        assertEquals(courseList1.getCourseListSize(), 2);
        assertEquals(courseList2.getCourseListSize(), 1);
        courseList2.removeCourse("stat 302");
        assertEquals(courseList2.getCourseListSize(), 0);
        courseList1.removeCourse("stat 302");
        courseList1.removeCourse("stat 306");
        assertEquals(courseList1.getCourseListSize(), 0);
    }

    @Test
    void TestCalculate() throws EmptyListException {
        courseList1.addCourse(course1);
        courseList1.addCourse(course2);
        courseList1.addCourse(course3);
        assertEquals(courseList1.calculateAvg(), (double) Math.round(224f / 3f * 100d) / 100d);
        courseList2.addCourse(course2);
        assertEquals(courseList2.calculateAvg(), 66.00);
    }

//    @Test
//    void TestCourseComp() throws EmptyListException {
//        courseList1.addCourse(course3);
//        courseList1.addCourse(course1);
//        courseList1.setCourseCompInList("econ 102");
//        assertTrue(course1.getCourseComp());
//        assertEquals(courseList1.getCoursesCompNum(), 1);
//        courseList1.setCourseCompInList("stat 306");
//        assertTrue(course3.getCourseComp());
//        assertEquals(courseList1.getCoursesCompNum(), 2);
//        assertEquals(courseList1.setCourseCompInList("null"), "Cannot find the course you are looking for!");
//
//    }

    @Test
    void TestGetCoursesNames() {
        ArrayList<String> testList = new ArrayList();
        testList.add("Sorry your current list is empty!");
        assertEquals(testList, courseList2.getCoursesNames());
        courseList2.addCourse(course1);
        courseList2.addCourse(course2);
        courseList2.addCourse(course3);
        testList.remove("Sorry your current list is empty!");
        testList.add("econ 102");
        testList.add("stat 302");
        testList.add("stat 306");
        assertEquals(testList, courseList2.getCoursesNames());
    }

    @Test
    void TestGetCourseComment() throws EmptyListException {
        courseList1.addCourse(course1);
        courseList1.addCourse(course2);
        assertEquals(courseList1.getCourseComment("econ 102"), "do practice!");
        assertEquals(courseList1.getCourseComment("stat 302"), "review more!");
        assertEquals(courseList1.getCourseComment("test"), "Cannot find the course you are looking for!");
    }

    @Test
    void TestGetCourseGrade() throws EmptyListException {
        assertEquals(courseList1.getCourseListSize(), 0);
        courseList1.addCourse(course1);
        courseList1.addCourse(course2);
        courseList1.addCourse(course3);
        courseList1.addCourse(course4);
        assertEquals(courseList1.findCourseGrade("econ 102"), "Your grade for econ 102 is 84 %");
        assertEquals(courseList1.findCourseGrade("cpsc 121"), "Your grade for cpsc 121 is 84 %");
        assertEquals(courseList1.findCourseGrade("stat 302"), "Your grade for stat 302 is 66 %");
        assertEquals(courseList1.findCourseGrade("stat 306"), "Your grade for stat 306 is 74 %");
        assertEquals(courseList1.findCourseGrade("stat 301"), "Cannot find the course you are looking for!");
    }

    @Test
    void TestEmptyException() {
        assertEquals(0, courseList1.getCourseListSize());
        try {
            courseList1.removeCourse("test");
            fail("EmptyListException not thrown");
        } catch (EmptyListException e) {
            //catches it
        }
        try {
            courseList1.calculateAvg();
            fail("EmptyListException not thrown");
        } catch (EmptyListException e) {
            //catches it
        }
        try {
            courseList1.getCourseComment("test");
            fail("EmptyListException not thrown");
        } catch (EmptyListException e) {
            //catches it
        }
        try {
            courseList1.findCourseGrade("test");
            fail("EmptyListException not thrown");
        } catch (EmptyListException e) {
            //catches it
        }
        courseList1.addCourse(course1);
        courseList1.addCourse(course2);
        courseList1.addCourse(course3);
        courseList1.addCourse(course4);
        assertEquals(4, courseList1.getCourseListSize());
        try {
            courseList1.removeCourse("econ 102");
        } catch (EmptyListException e) {
            fail("EmptyListException shouldn't be thrown");
        }
        assertEquals(3, courseList1.getCourseListSize());
        try {
            courseList1.calculateAvg();
        } catch (EmptyListException e) {
            fail("EmptyListException shouldn't be thrown");
        }
        try {
            courseList1.getCourseComment("stat 302");
        } catch (EmptyListException e) {
            fail("EmptyListException shouldn't be thrown");
        }
        try {
            courseList1.findCourseGrade("econ 102");
        } catch (EmptyListException e) {
            fail("EmptyListException shouldn't be thrown");
        }
    }
}