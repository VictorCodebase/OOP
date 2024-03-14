import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaApp extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton addButton;
    private JButton displayButton;
    private int[] array;
    private int currentIndex;

    public JavaApp() {
        setTitle("Array Input Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(inputField);
        inputPanel.add(addButton);

        JPanel displayPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        displayButton = new JButton("Display Array");
        displayButton.addActionListener(new DisplayButtonListener());
        displayPanel.add(outputArea, BorderLayout.CENTER);
        displayPanel.add(displayButton, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);

        array = new int[10];
        currentIndex = 0;
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentIndex < array.length) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    array[currentIndex] = value;
                    currentIndex++;
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JavaApp.this, "Please enter a valid integer.");
                }
            } else {
                JOptionPane.showMessageDialog(JavaApp.this, "Array is full.");
            }
        }
    }

    private class DisplayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder sb = new StringBuilder("Array Elements:\n");
            for (int i = 0; i < currentIndex; i++) {
                sb.append(array[i]).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JavaApp app = new JavaApp();
            app.setVisible(true);
        });
    }
}
