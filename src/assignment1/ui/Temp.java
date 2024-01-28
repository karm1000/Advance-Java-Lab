package assignment1.ui;

import javax.swing.*;
import java.awt.*;

public class Temp extends JFrame {
    public Temp() {
        setTitle("GridBagLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Add components with constraints
        JButton button1 = new JButton("Button 1");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(button1, constraints);

        JButton button2 = new JButton("Button 2");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(button2, constraints);

        JButton button3 = new JButton("Button 3");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Span 2 columns
        panel.add(button3, constraints);

        // Add the panel to the frame
        getContentPane().add(panel);

        pack(); // Adjust frame size based on preferred sizes of components
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Temp example = new Temp();
            example.setVisible(true);
        });
    }
}
