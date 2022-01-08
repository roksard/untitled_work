package roksard.GUI.scrollable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Scrollable {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(200, 200));
        Container contentPane = frame.getContentPane();

        JTextArea text = new JTextArea("ABCD");
//        text.setBounds(5, 5, 50, 50);
        text.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(text);
        contentPane.add(scrollPane);

        JTextArea text2 = new JTextArea("TEXT2");
        contentPane.add(text2, BorderLayout.PAGE_END);

        JButton button = new JButton("Button");
        contentPane.add(button, BorderLayout.PAGE_START);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
    }
}
