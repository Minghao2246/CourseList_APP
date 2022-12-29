package ui.panels;

import javax.swing.*;
import java.awt.*;

// create interaction panel
public class InteractionPanel extends JPanel {
    JButton add;
    JButton delete;
    JButton grade;
    JButton calculateAvg;

    public InteractionPanel() {
        setBackground(new Color(238, 120, 120));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1800,90));
        addButton();
        deleteButton();
        checkGradeButton();
        calculateButton();
    }

    //MODIFIES: this
    //EFFECTS: Create add button
    private void addButton() {
        add = new JButton("Add Course");
        add.setOpaque(true);
        add.setBackground(new Color(218, 55, 55));
        add.setPreferredSize(new Dimension(150,85));
        add.setForeground(new Color(205, 139, 229));
        add.setActionCommand("Add");
        add(this.add);
    }

    //MODIFIES: this
    //EFFECTS: Create delete button
    private void deleteButton() {
        delete = new JButton("Remove Course");
        delete.setOpaque(true);
        delete.setBackground(new Color(218, 55, 55));
        delete.setPreferredSize(new Dimension(150,80));
        delete.setForeground(new Color(205, 139, 229));
        delete.setActionCommand("Delete");
        add(this.delete);
    }

    //MODIFIES: this
    //EFFECTS: Create check grade button
    private void checkGradeButton() {
        grade = new JButton("Check Grade");
        grade.setOpaque(true);
        grade.setBackground(new Color(218, 55, 55));
        grade.setPreferredSize(new Dimension(150,80));
        grade.setForeground(new Color(205, 139, 229));
        grade.setActionCommand("Grade");
        add(this.grade);
    }

    //MODIFIES: this
    //EFFECTS: Create calculation button
    private void calculateButton() {
        calculateAvg = new JButton("Calculate Average");
        calculateAvg.setOpaque(true);
        calculateAvg.setBackground(new Color(218, 55, 55));
        calculateAvg.setPreferredSize(new Dimension(150,80));
        calculateAvg.setForeground(new Color(205, 139, 229));
        calculateAvg.setActionCommand("Calculate");
        add(this.calculateAvg);
    }

    //getters
    public JButton getAdd() {
        return add;
    }

    public JButton getDelete() {
        return delete;
    }

    public JButton getCalculateAvg() {
        return calculateAvg;
    }

    public JButton getGrade() {
        return grade;
    }
}
