package roksard.GUI.baseGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BaseGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new JPanel() {
            {
                setBackground(Color.BLACK);
                setPreferredSize(new Dimension(400, 400));
            }
            private void drawCircle(Graphics g, double x, double y, double size, Color color) {
                double s = 1; //some scale for coorinates
                Graphics2D g2d = (Graphics2D)g;
                Ellipse2D.Double circle = new Ellipse2D.Double(x * s, y * s, size, size);
                g2d.setColor(color);
                g2d.fill(circle);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < 400; i++) {
                    double x = i;
                    double y = Math.sin(Math.toRadians(i*2)) * 100 + 150;
                    drawCircle(g, x, y, Math.abs((y-150)/30)+1, Color.CYAN);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }
}
