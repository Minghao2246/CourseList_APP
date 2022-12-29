package ui.panels;

import javax.swing.*;
import java.awt.*;

// create type panel
public class TypePanel extends JPanel {
    private JPanel addPanel;
    private JPanel removePanel;
    private JTextField courseNameFieldAdd;
    private JTextField gradeField;
    private JTextField commentField;
    private JTextField courseNameFieldRemove;
    private JTextField courseNameFieldCheck;

    public TypePanel() {
        setBackground(new Color(175, 102, 174));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1200,100));
        makeAddPanel();
        makeRemovePanel();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: make the addPanel
    private void makeAddPanel() {
        addPanel = new JPanel();
        JPanel addFieldPanel = (JPanel) createAddFieldPanel();
        addPanel.add(addFieldPanel);
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        this.add(addPanel);
    }

    // MODIFIES: this
    // EFFECTS: make a removePanel
    private void makeRemovePanel() {
        removePanel = new JPanel();
        JPanel removeFieldPanel = (JPanel) createRemoveFieldPanel();
        removePanel.add(removeFieldPanel);
        removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.LINE_AXIS));
        this.add(removePanel);
    }

    // MODIFIES: this
    // EFFECTS: return a JComponent - AddFieldPanel
    //the methods is implemented from ListDemo linked below:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    protected JComponent createAddFieldPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(175, 102, 174));
        String[] labelStrings = {"Course name: ", "Grade(on a scale of 0 to 100): ", "Comment: "};
        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;
        courseNameFieldAdd = new JTextField();
        courseNameFieldAdd.setColumns(15);
        fields[fieldNum++] = courseNameFieldAdd;
        gradeField = new JTextField();
        gradeField.setColumns(15);
        fields[fieldNum++] = gradeField;
        commentField = new JTextField();
        commentField.setColumns(30);
        fields[fieldNum++] = commentField;
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            labels[i].setFont(new Font("Verdana", 1, 12));
            panel.add(labels[i]);
            panel.add(fields[i]);
        }
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a JComponent - RemoveFieldPanel
    //the methods is implemented from ListDemo linked below:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    protected JComponent createRemoveFieldPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(148, 124, 241));
        String[] labelStrings = {"Remove Course: ", "Check Grade"};
        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;
        courseNameFieldRemove = new JTextField();
        courseNameFieldRemove.setColumns(35);
        fields[fieldNum++] = courseNameFieldRemove;
        courseNameFieldCheck = new JTextField();
        courseNameFieldCheck.setColumns(35);
        fields[fieldNum++] = courseNameFieldCheck;
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);
        }
        return panel;
    }

    //getters
    public JTextField getCourseNameFieldAdd() {
        return courseNameFieldAdd;
    }

    public JTextField getCourseNameFieldRemove() {
        return courseNameFieldRemove;
    }

    public JTextField getGradeField() {
        return gradeField;
    }

    public JTextField getCommentField() {
        return commentField;
    }

    public JTextField getCheckField() {
        return courseNameFieldCheck;
    }
}
