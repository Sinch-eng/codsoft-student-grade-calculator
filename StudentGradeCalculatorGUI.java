import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGradeCalculatorGUI {

    private static JTextField subjectCountField;
    private static JTable marksTable;
    private static DefaultTableModel marksModel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ---------- TITLE ----------
        JLabel title = new JLabel("Student Grade Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(title, BorderLayout.NORTH);

        // ---------- INPUT PANEL ----------
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Number of Subjects: "));
        subjectCountField = new JTextField(5);
        inputPanel.add(subjectCountField);

        JButton createBtn = new JButton("Create Fields");
        inputPanel.add(createBtn);

        frame.add(inputPanel, BorderLayout.BEFORE_FIRST_LINE);

        // ---------- MARKS PANEL WITH BORDER ----------
        JPanel marksPanel = new JPanel(new BorderLayout());
        marksPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Marks",
                TitledBorder.LEFT, TitledBorder.TOP));

        // Table
        marksModel = new DefaultTableModel(new String[]{"Subject", "Marks"}, 0);
        marksTable = new JTable(marksModel);
        JScrollPane scrollPane = new JScrollPane(marksTable);

        marksPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(marksPanel, BorderLayout.CENTER);

        // ---------- BOTTOM BUTTON ----------
        JPanel bottomPanel = new JPanel();
        JButton calcButton = new JButton("Calculate Grade");
        bottomPanel.add(calcButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // ---------- ACTION: CREATE TABLE FIELDS ----------
        createBtn.addActionListener(e -> {
            String countText = subjectCountField.getText().trim();

            if (!countText.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Enter a valid number.");
                return;
            }

            int count = Integer.parseInt(countText);
            marksModel.setRowCount(0);

            for (int i = 1; i <= count; i++) {
                marksModel.addRow(new Object[]{"Subject " + i, ""});
            }
        });

        // ---------- ACTION: CALCULATE GRADE ----------
        calcButton.addActionListener(e -> calculateGrade(frame));

        frame.setVisible(true);
    }

    private static void calculateGrade(JFrame frame) {

        int rows = marksModel.getRowCount();
        if (rows == 0) {
            JOptionPane.showMessageDialog(frame, "Please create subject fields first.");
            return;
        }

        double total = 0;

        for (int i = 0; i < rows; i++) {
            String marksText = marksModel.getValueAt(i, 1).toString().trim();

            if (!marksText.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(frame,
                        "Enter valid marks for " + marksModel.getValueAt(i, 0));
                return;
            }

            double marks = Double.parseDouble(marksText);

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(frame,
                        "Marks must be between 0 and 100 for " + marksModel.getValueAt(i, 0));
                return;
            }

            total += marks;
        }

        double avg = total / rows;

        char grade;
        String remark;

        if (avg >= 90) {
            grade = 'A';
            remark = "Excellent";
        } else if (avg >= 80) {
            grade = 'B';
            remark = "Very Good";
        } else if (avg >= 70) {
            grade = 'C';
            remark = "Good";
        } else if (avg >= 60) {
            grade = 'D';
            remark = "Satisfactory";
        } else {
            grade = 'F';
            remark = "Needs Improvement";
        }

        // Show result in neat popup
        JOptionPane.showMessageDialog(frame,
                "Total Marks: " + total +
                "\nAverage: " + avg +
                "\nGrade: " + grade +
                "\nRemark: " + remark,
                "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
