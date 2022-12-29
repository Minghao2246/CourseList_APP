package model;

import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a course
public class Course implements Writable, ActionListener {
    private String name;
    private String comment;
    private int grade;
    private JLabel showCourseName;
    private JLabel showCourseGrade;
    private JLabel showCourseComment;
    private JButton showCourseCommentButton;
    private JPanel box;

    // MODIFIES: this
    // EFFECTS: initiate a course object along with parameters
    public Course(String name, int grade, String comment) {
        this.name = name;
        this.grade = grade;
        this.comment = comment;
        this.createCourseListPanel(name, grade, comment);
    }

    //EFFECTS: return the course name of the course
    public String getCourseName() {
        return name;
    }

    //EFFECTS: return the grade of the course
    public int getGrade() {
        return grade;
    }

    //EFFECTS: return the comment of the course
    public String getComment() {
        return comment;
    }

    //MODIFIES: JPanel
    //EFFECTS: creating a course panel with name, grade and comment
    private void createCourseListPanel(String name, int grade, String comment) {
        createBar();
        createShowName(name);
        createShowGrade(grade);
        createShowCommentButton();
        createShowComment(comment);
        box.add(this.showCourseName, "West");
        box.add(this.showCourseGrade, "Center");
        box.add(this.showCourseCommentButton, "East");
    }

    //MODIFIES: JPanel
    //MODIFIES: creating a course box for course
    private void createBar() {
        box = new JPanel();
        box.setPreferredSize(new Dimension(100, 70));
        box.setLayout(new BorderLayout());
        box.setBackground(new Color(255, 255, 255));
    }

    //MODIFIES: JLabel
    //EFFECTS: Create the course name JLabel
    private void createShowName(String courseName) {
        showCourseName = new JLabel(courseName);
        showCourseName.setPreferredSize(new Dimension(80, 70));
        showCourseName.setHorizontalAlignment(0);
    }

    //MODIFIES: JLabel
    //EFFECTS: Create the course grade JLabel
    private void createShowGrade(int grade) {
        showCourseGrade = new JLabel(Integer.toString(grade));
        showCourseGrade.setPreferredSize(new Dimension(80, 70));
        showCourseGrade.setHorizontalAlignment(0);
    }

    //MODIFIES: JButton
    //EFFECTS: Create a button for comment
    private void createShowCommentButton() {
        showCourseCommentButton = new JButton("Read");
        showCourseCommentButton.setOpaque(true);
        showCourseCommentButton.setPreferredSize(new Dimension(100, 70));
        showCourseCommentButton.setForeground(new Color(116, 81, 248));
        showCourseCommentButton.setHorizontalAlignment(0);
        showCourseCommentButton.setActionCommand("Comment");
        showCourseCommentButton.addActionListener(this);
    }

    //MODIFIES: JLabel
    //EFFECTS: Create the course comment JLabel
    public JLabel createShowComment(String comment) {
        showCourseComment = new JLabel(comment);
        return showCourseComment;
    }

    //getter
    public JPanel getCoursePanel() {
        return box;
    }

    //EFFECTS: for JsonReader/Writer
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("grade", grade);
        json.put("comment", comment);
        return json;
    }

    //EFFECTS: allow user to read comment when command
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Comment")) {
            EventLog.getInstance().logEvent(new Event("Viewed comment for " + name));
            showComment();
        }
    }

    //MODIFIES: JFrame
    //EFFECTS: shows the comment for course in a new JFrame window
    private void showComment() {
        JFrame commentFrame = new JFrame();
        commentFrame.getContentPane().add(createShowComment(comment), SwingConstants.CENTER);
        commentFrame.setBounds(550, 200, 500, 200);
        commentFrame.setVisible(true);
    }
}
