package ui;

import exception.EmptyListException;
import model.Course;
import model.CourseList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;
import ui.panels.title.CourseCommentPanel;
import ui.panels.title.CourseGradePanel;
import ui.panels.title.CourseNamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//courselist GUI class/graphical
public class CourseListGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/courselist.json";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;
    private CourseList courseList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private TypePanel typePanel;
    private InteractionPanel interactionPanel;
    private JPanel listPanel;
    private CourseNamePanel namePanel;
    private CourseGradePanel gradePanel;
    private CourseCommentPanel commentPanel;
    private JLabel courseName;
    private JLabel courseGrade;
    private JLabel courseComment;
    private JTextField gradeField;
    private JTextField commentField;
    private JTextField courseNameField;
    private JTextField courseNameField2;
    private JTextField courseNameField3;
    private ImageIcon imageIcon;

    //EFFECTS: Creates GUI for user interactions
    public CourseListGUI() {
        super("Course List");
        init();
        initGraphics();
    }

    //MODIFIES: this
    //EFFECTS: Initialize
    private void init() {
        courseList = new CourseList();
        jsonReader = new JsonReader("./data/courselist.json");
        jsonWriter = new JsonWriter("./data/courselist.json");
        imageIcon = new ImageIcon("./data/UBC_COA.png");
    }

    //MODIFIES: CourseList
    //EFFECTS: allow users to load course list
    private void loadCourses(int i) {
        if (i == 0) {
            try {
                courseList = jsonReader.read();
                courseList.getPanelForCourseList();
                add(courseList.getPanelForCourseList(), "Center");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //MODIFIES: CourseList
    //EFFECTS: allow users to save course list
    private void saveCourses(int i) {
        if (i == 0) {
            try {
                jsonWriter.open();
                jsonWriter.write(this.getCourseList());
                jsonWriter.close();
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (i == 1) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
    }


    //EFFECTS: create visualization and displays
    private void initGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(imageIcon.getImage());
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        splashWindow();
        window();
        repaint();
        makeMain();
        makeType();
        makeListPanel();
        setVisible(true);
    }

    //MODIFIES: JPanels, JTextField
    //EFFECTS: Creating the type area for users to interact
    private void makeType() {
        typePanel = new TypePanel();
        courseNameField = typePanel.getCourseNameFieldAdd();
        gradeField = typePanel.getGradeField();
        commentField = typePanel.getCommentField();
        courseNameField2 = typePanel.getCourseNameFieldRemove();
        courseNameField3 = typePanel.getCheckField();
        courseNameField.addActionListener(this);
        gradeField.addActionListener(this);
        commentField.addActionListener(this);
        courseNameField2.addActionListener(this);
        courseNameField3.addActionListener(this);
        add(typePanel, BorderLayout.PAGE_END);
    }

    //https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
    //EFFECTS: display splash screen when launching the application
    private void splashWindow() {
        JWindow window = new JWindow();
        try {
            window.getContentPane().add(
                    new JLabel("My Course List", new ImageIcon(
                            new URL("https://i.pinimg.com/originals/7c/af/a3/7cafa33a607df89cc065d516a8c9f1df.gif")), SwingConstants.CENTER));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        window.setBounds(500, 150, 630, 500);
        window.setVisible(true);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(true);
        window.dispose();
    }

    //MODIFIES: JButton, JPanel
    //EFFECTS: Creating the functionality and interaction area such as buttons and instructions
    private void makeMain() {
        JPanel courseNGC = courseNgcPanel();
        interactionPanel = new InteractionPanel();
        JButton add = interactionPanel.getAdd();
        JButton delete = interactionPanel.getDelete();
        JButton grade = interactionPanel.getGrade();
        JButton calculate = interactionPanel.getCalculateAvg();
        add.addActionListener(this);
        delete.addActionListener(this);
        grade.addActionListener(this);
        calculate.addActionListener(this);
        JPanel showInteractionPanel = new JPanel(new GridLayout(2, 0));
        showInteractionPanel.add(interactionPanel);
        showInteractionPanel.add(courseNGC);
        add(showInteractionPanel, "First");
    }

    //https://stackoverflow.com/questions/9829319/centering-a-jlabel-in-a-jpanel
    //MODIFIES: JPanel, JLabel
    //EFFECTS: Creating the instruction panel
    private JPanel courseNgcPanel() {
//        CourseNamePanel name = new CourseNamePanel();
        namePanel = new CourseNamePanel();
        courseName = new JLabel("Course Name");
        courseName.setFont(new Font("Verdana", 1, 20));
        namePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        namePanel.add(courseName);
//        CourseGradePanel grade = new CourseGradePanel();
        gradePanel = new CourseGradePanel();
        courseGrade = new JLabel("Grade");
        courseGrade.setFont(new Font("Verdana", 1, 20));
        gradePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        gradePanel.add(courseGrade);
//        CourseCommentPanel comment = new CourseCommentPanel();
        commentPanel = new CourseCommentPanel();
        courseComment = new JLabel("Comment");
        courseComment.setFont(new Font("Verdana", 1, 20));
        commentPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        commentPanel.add(courseComment);
        GridLayout gridLayout1 = new GridLayout(0, 3);
        gridLayout1.setHgap(5);
        JPanel courseNGC = new JPanel(gridLayout1);
        courseNGC.add(namePanel);
        courseNGC.add(gradePanel);
        courseNGC.add(commentPanel);
        return courseNGC;
    }

    //EFFECTS: display message for users to load
    private int openWindow() {
        return JOptionPane.showConfirmDialog(this, "Ready to load your course list?",
                "Load", 1);
    }

    //EFFECTS: display message for users to save
    private int closeWindow() {
        return JOptionPane.showConfirmDialog(this, "Ready to save your course list?",
                "Save", 0);
    }

    //MODIFIES: this
    //EFFECTS: Creating pop-out window functionality for users to load and save the course list
    private void window() {
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                int n = CourseListGUI.this.openWindow();
                CourseListGUI.this.loadCourses(n);
                CourseListGUI.this.revalidate();
            }

            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int n = CourseListGUI.this.closeWindow();
                CourseListGUI.this.saveCourses(n);
                CourseListGUI.this.revalidate();
            }
        });
    }

    //MODIFIES: JPanel
    //EFFECTS: creating the panel that displays the courses
    private void makeListPanel() {
        listPanel = courseList.getPanelForCourseList();
        setPanel(listPanel);
        add(this.listPanel, "Center");
    }

    // setter
    public void setPanel(JPanel cl) {
        listPanel = cl;
    }

    // getter
    public CourseList getCourseList() {
        return courseList;
    }

    //EFFECTS: allow users to perform commands and interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add") && courseNameField.getText().length() > 0
                && gradeField.getText().length() > 0 && commentField.getText().length() > 0) {
            addText();
        }
        if (e.getActionCommand().equals("Delete") && courseNameField2.getText().length() > 0) {
            removeText();
        }
        if (e.getActionCommand().equals("Calculate")) {
            calculateIt();
        }
        if (e.getActionCommand().equals("Grade") && courseNameField3.getText().length() > 0) {
            checkGrade();
        }
    }

    //MODIFIES: JFrame
    //EFFECTS: display this window when exception is caught
    private void failGradeWindow() {
        JFrame gradeFrame = new JFrame();
        gradeFrame.getContentPane().add(
                new JLabel("Cannot find course...",
                        SwingConstants.CENTER));
        gradeFrame.setBounds(550, 200, 300, 200);
        gradeFrame.setVisible(true);
    }

    //EFFECTS: allow users to calculate overall average, throws exception if list is empty
    private void calculateIt() {
        try {
//            courseList.calculateAvg();
            calculateSuccessfulWindow();
        } catch (EmptyListException e) {
            calculateFailedWindow();
        }
    }

    //EFFECTS: display this window when calculate average function successfully
    private void calculateSuccessfulWindow() throws EmptyListException {
        JFrame calculateSuccessfulFrame = new JFrame();
        calculateSuccessfulFrame.getContentPane().add(
                new JLabel(Double.toString(courseList.calculateAvg()),
                        SwingConstants.CENTER));
        calculateSuccessfulFrame.setBounds(550, 200, 300, 200);
        calculateSuccessfulFrame.setVisible(true);
    }

    //EFFECTS: display this window when exception is caught
    private void calculateFailedWindow() {
        JFrame calculateFailedFrame = new JFrame();
        calculateFailedFrame.getContentPane().add(
                new JLabel("Looks like your list is empty...",
                        SwingConstants.CENTER));
        calculateFailedFrame.setBounds(550, 200, 300, 200);
        calculateFailedFrame.setVisible(true);
    }

    //MODIFIES: CourseList
    //EFFECTS: allow users to add course
    private void addText() {
        Course course = new Course(courseNameField.getText(), Integer.parseInt(gradeField.getText()),
                commentField.getText());
        courseList.addCourse(course);
        revalidate();
    }

    //MODIFIES: CourseList
    //EFFECTS: allow users to remove a course, throws exception if list is empty
    private void removeText() {
        try {
            String courseName = courseNameField2.getText();
            courseList.removeCourse(courseName);
            revalidate();
        } catch (Exception e) {
            deleteFailedWindow();
        }
    }

    //EFFECTS: allow users to check grade for a specific course, throws exception if list is empty
    private void checkGrade() {
        try {
            successfulGradeWindow();
            revalidate();
        } catch (EmptyListException e) {
            failGradeWindow();
        }
    }

    //EFFECTS: display this window when check grade function successfully
    private void successfulGradeWindow() throws EmptyListException {
        JFrame calculateSuccessfulFrame = new JFrame();
        calculateSuccessfulFrame.getContentPane().add(
                new JLabel(courseList.findCourseGrade(courseNameField3.getText())),
                SwingConstants.CENTER);
        calculateSuccessfulFrame.setBounds(550, 200, 300, 200);
        calculateSuccessfulFrame.setVisible(true);
    }

    //EFFECTS: display this window when exception is caught
    private void deleteFailedWindow() {
        JFrame removeFailedFrame = new JFrame();
        removeFailedFrame.getContentPane().add(
                new JLabel("Please try again, this course might not exist within the list!",
                        SwingConstants.CENTER));
        removeFailedFrame.setBounds(550, 200, 300, 200);
        removeFailedFrame.setVisible(true);
    }

//    public String getCComment(String name) throws EmptyListException {
//        if (courseList.size() == 0) {
//            throw new EmptyListException();
//        }
//        for (Course c : courseList) {
//            if (c.getCourseName().equals(name)) {
//                return c.getComment();
//            }
//        }
//        return "Cannot find the course you are looking for!";
//    }


}
