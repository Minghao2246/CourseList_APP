package ui;

import exception.EmptyListException;
import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// courselist app class
public class CourseListApp {
    private static final String JSON_STORE = "./data/courselist.json";
    private CourseList list;
    private Course course;
    private Scanner text;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: call to run the app
    public CourseListApp() {
        runCourseListApp();
    }

    //MODIFIES: this
    //EFFECTS: runs the app
    private void runCourseListApp() {
        boolean go = true;
        String command = null;

        init();

        while (go) {
            displayMenu();
            command = text.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                go = false;
            } else {
                actionCommand(command);
            }
        }

        System.out.println("\nBye! Thanks for using!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void actionCommand(String command) {
        if (command.equals("a")) {
            addThis();
        } else if (command.equals("r")) {
            removeC1();
        } else if (command.equals("c")) {
            checkList();
        } else if (command.equals("g")) {
            checkGrade();
        } else if (command.equals("k")) {
            checkCompCourses();
        } else if (command.equals("w")) {
            checkComment();
        } else if (command.equals("m")) {
            avgThem();
        } else if (command.equals("s")) {
            save();
        } else if (command.equals("l")) {
            load();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    //MODIFIES: this
    //EFFECTS: initialize the course list
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        list = new CourseList();
        text = new Scanner(System.in);
        text.useDelimiter("\n");
    }

    //EFFECTS: display the menu options for user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course");
        System.out.println("\tr -> remove course");
        System.out.println("\tc -> check all courses in the list");
        System.out.println("\tg -> check the grade for a specific course");
//        System.out.println("\td -> complete course");
        System.out.println("\tk -> check number of completed courses");
        System.out.println("\tw -> check the comment for a specific course");
        System.out.println("\tm -> check the average");
        System.out.println("\ts -> save");
        System.out.println("\tl -> load");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: interaction with user to add course to the list
    private void addC1() {
        System.out.print("Enter the name of the course you would like to add:");
        String name = text.next();
        System.out.println("Enter the grade % of this course (from 0 to 100)");
        int grade = text.nextInt();
        System.out.println("Enter any comment for this course");
        String comment = text.next();
        Course c = new Course(name, grade, comment);
        course = c;
    }

    //MODIFIES: this
    //EFFECTS: remove course from the list
    private void removeC1() {
        System.out.println("Enter the name of the course you would like to delete:");
        String name = text.next();
        try {
            System.out.println(list.removeCourse(name));
        } catch (EmptyListException e) {
            System.out.println("Your list is empty!");
        }
    }

    //MODIFIES: this
    //EFFECTS: complete course within the list
//    private void compC1() {
//        System.out.println("Enter the name of the course you completed:");
//        String name2 = text.next();
//        try {
//            System.out.println(list.setCourseCompInList(name2));
//        } catch (EmptyListException e) {
//            System.out.println("Your list is empty!");
//        }
//    }

    //MODIFIES: this
    //EFFECTS: add course to the list
    private void addThis() {
        addC1();
        list.addCourse(course);
        System.out.println("Course successfully added");
    }

    //EFFECTS: check the grade of a specific course
    private void checkGrade() {
        System.out.println("Enter the name of the course you would like to check:");
        String name3 = text.next();
        String s = null;
        try {
            s = list.findCourseGrade(name3);
        } catch (EmptyListException e) {
            s = "Your list is empty!";
        }
        System.out.println(s);
    }

    private void checkComment() {
        System.out.println("Enter the name of the course you would like to check:");
        String name4 = text.next();
        String c = null;
        try {
            c = list.getCourseComment(name4);
        } catch (EmptyListException e) {
            c = "Your list is empty!";
        }
        System.out.println(c);
    }

    //EFFECTS: output the courses within the list
    private void checkList() {
        System.out.println("Your courses are: " + list.getCoursesNames());
    }

    //EFFECTS: output the number of completed courses within the list
    private void checkCompCourses() {
        System.out.println("You have completed: " + list.getCoursesCompNum() + " course(s).");
    }

    //EFFECTS: output the average of courses within the list
    private void avgThem() {
        try {
            System.out.println("Your average is: " + list.calculateAvg());
        } catch (EmptyListException e) {
            System.out.println("Your list is empty!");
        }
    }

    // EFFECTS: saves the course list to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads course list from file and print it out
    private void load() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        List<Course> courses = list.getCourses();

        for (Course c : courses) {
            System.out.println(c.getCourseName());
        }
    }

}
