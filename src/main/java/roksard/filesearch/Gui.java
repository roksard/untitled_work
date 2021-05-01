package roksard.filesearch;

import roksard.json_serializer.JsonSerializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;

public class Gui {
    final static String CONFIG_FILE = "settings.p";
    final static JsonSerializer<Config> serializer = new JsonSerializer<>(Config.class);
    final static Config config = serializer.load(CONFIG_FILE, Config.DEFAULT);
    static JFrame frame;

    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch();

        frame = new JFrame();
        frame.setLocation(config.getX(), config.getY());
        frame.addWindowListener(getMainWindowListener());
                JPanel jpanel = new JPanel() {
                    {
                        setBackground(Color.WHITE);
                        setPreferredSize(new Dimension(400, 115));
                    }

                    private void drawCircle(Graphics g, double x, double y, double size, Color color) {
                        double s = 1; //some scale for coorinates
                        Graphics2D g2d = (Graphics2D) g;
                        Ellipse2D.Double circle = new Ellipse2D.Double(x * s, y * s, size, size);
                        g2d.setColor(color);
                        g2d.fill(circle);
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        double height = g.getClipBounds().getHeight();
                        for (int i = 0; i < (int) g.getClipBounds().getWidth(); i += 5) {
                            double x = i;
                            double y = Math.sin(Math.toRadians(i)) * height / 2 + height / 2;
                            drawCircle(g, x, y, Math.abs((y - height / 2) / 100) + 2, Color.GRAY);
                        }
                    }
                };

        int y = 5;
        int hy = 25;
        JTextField jtDir = new JTextField();
        jtDir.setBounds(5, y+hy*0, 390, 20);
        jtDir.setText(config.getDirectory());
        jtDir.setToolTipText("Directory where to search for files");
        jpanel.add(jtDir);

        JTextField jtSubString = new JTextField();
        jtSubString.setBounds(5, y+hy*1, 150, 20);
        jtSubString.setToolTipText("Text to search in files (press Enter here to start search)");
        jtSubString.setText(config.getSubString());

        JTextArea jtResult = new JTextArea();
        jtResult.setBounds(5, y+hy*2, 390, 50);
        jtResult.setBorder(BorderFactory.createEtchedBorder());

        jtSubString.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result result = new Result();
                String subString = jtSubString.getText();
                try {
                    fileSearch.searchBySubstring(jtDir.getText(), true, subString, result);
                } catch (Throwable err) {
                    JOptionPane.showMessageDialog(jpanel, "Error: " + err.toString() + ": " + err.getMessage());
                }
                jtResult.setText(subString + ":\n" + result.getResult().toString());
                config.setDirectory(jtDir.getText());
                config.setSubString(jtSubString.getText());
            }
        });

        jpanel.add(jtResult);
        jpanel.add(jtSubString);

        jpanel.setLayout(null);
        frame.add(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }

    static WindowListener getMainWindowListener() {
        return new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                config.setX((int)frame.getLocation().getX());
                config.setY((int)frame.getLocation().getY());
                serializer.save(CONFIG_FILE, config);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };
    }
}
