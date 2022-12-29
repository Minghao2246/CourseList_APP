package model;

import exception.EmptyListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a list of courses
public class CourseList implements Writable {
    private List<Course> courses;
    private double avg;
    private JPanel panelForCourseList;

    // EFFECTS: construct an empty list of courses with 0 completed courses
    public CourseList() {
        courses = new ArrayList();
        avg = 0;
        this.createPanels();
    }

    // MODIFIES: this
    // EFFECTS: add course to the list of courses
    public boolean addCourse(Course course) {
        if (!(courses.contains(course))) {
            panelForCourseList.add(course.getCoursePanel());
            courses.add(course);
            EventLog.getInstance().logEvent(new Event(course.getCourseName() + " added to the list"));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: remove course from the list of courses
    public boolean removeCourse(String name) throws EmptyListException {
        if (courses.size() == 0) {
            throw new EmptyListException();
        }
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                courses.remove(course);
                course.getCoursePanel().setVisible(false);
                panelForCourseList.remove(course.getCoursePanel());
                System.out.println(name + " is successfully removed!");
                EventLog.getInstance().logEvent(new Event(course.getCourseName() + " removed to the list"));
                return true;
            }
        }
        System.out.println("Cannot find the course you are looking for!");
        return false;
    }

    // MODIFIES: this
    // EFFECTS: calculate the average grade of courses within the list
    public double calculateAvg() throws EmptyListException {
        if (courses.size() == 0) {
            throw new EmptyListException();
        }
        EventLog.getInstance().logEvent(new Event("Average calculated"));
        double a = 0;
        double sum = 0;
        for (Course c : courses) {
            a = c.getGrade();
            sum = sum + a;
        }
        avg = sum / courses.size();
        return (double) Math.round(avg * 100d) / 100d;
    }

    // EFFECTS: get the name of courses within the list
    public ArrayList<String> getCoursesNames() {
        ArrayList<String> courseStrings = new ArrayList<>();
        if (courses.isEmpty()) {
            courseStrings.add("Sorry your current list is empty!");
        } else {
            for (Course c : courses) {
                courseStrings.add(c.getCourseName());
            }
        }
        return courseStrings;
    }

    // MODIFIES: this
    // EFFECTS: obtain the number of courses within the list
    public int getCoursesCompNum() {
        return courses.size();
    }

    // MODIFIES: this
    // EFFECTS: obtain the course comment of a specific course within the list
    public String getCourseComment(String name) throws EmptyListException {
        if (courses.size() == 0) {
            throw new EmptyListException();
        }
        for (Course c : courses) {
            if (c.getCourseName().equals(name)) {
                return c.getComment();
            }
        }
        return "Cannot find the course you are looking for!";
    }


    // MODIFIES: this
    // EFFECTS: obtain the grade of a specific course within the list
    public String findCourseGrade(String name) throws EmptyListException {
        if (courses.size() == 0) {
            throw new EmptyListException();
        }
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                EventLog.getInstance().logEvent(new Event("Looked up grade for " + course.getCourseName()));
                return "Your grade for " + name + " is " + course.getGrade() + " %";
            }
        }
        return "Cannot find the course you are looking for!";
    }


    // EFFECTS: return the size of the list
    public int getCourseListSize() {
        return courses.size();
    }

    // EFFECTS: return true if the course is in the list else false
    public boolean courseContain(Course course) {
        if (courses.contains(course)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Course> getCourses() {
        return courses;
    }

    // EFFECTS: for JsonReader/Writer
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", thingiesToJson());
        return json;
    }

    // EFFECTS: returns courses in this course list as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: add grid to panel
    public void createPanels() {
        GridLayout layout = new GridLayout(30, 1);
        layout.setVgap(5);
        layout.setHgap(5);
        makeCoursesPanel(layout);
    }

    //EFFECTS: create courses panel
    private void makeCoursesPanel(GridLayout layout) {
        panelForCourseList = new JPanel();
        panelForCourseList.setBackground(new Color(255, 255, 255));
        panelForCourseList.setPreferredSize(new Dimension(800, 700));
        panelForCourseList.setLayout(layout);
    }

    //getter
    public JPanel getPanelForCourseList() {
        return panelForCourseList;
    }
}
