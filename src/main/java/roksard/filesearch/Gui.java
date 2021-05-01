package roksard.filesearch;

import roksard.json_serializer.JsonSerializer;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

public class Gui {
    final static String CONFIG_FILE = "settings.p";
    final static JsonSerializer<Config> serializer = new JsonSerializer<>(Config.class);
    final static Config config = serializer.load(CONFIG_FILE, Config.DEFAULT);
    static JFrame frame;
    final static String TITLE = "fileSearch by content";

    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch();

        frame = new JFrame();
        frame.setTitle(TITLE);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.toString() + ": " + e.getMessage());
            }
        });
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

        ActionListener searchActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtResult.setText("...");
                frame.setTitle("..." + " - " + TITLE);
                jpanel.repaint();
                Result result = new Result();
                String subString = jtSubString.getText();
                Future<Result> future = fileSearch.searchBySubstringAsync(jtDir.getText(), true, subString, result);
                runSearchingTimer(frame, future, jtResult, subString);
                config.setDirectory(jtDir.getText());
                config.setSubString(jtSubString.getText());
            }
        };

        jtSubString.addActionListener(searchActionListener);

        jpanel.add(jtResult);
        jpanel.add(jtSubString);

        Button button = new Button();
        button.setBounds(160, y+hy*1, 50, 20);
        button.setLabel("Search");
        button.addActionListener(searchActionListener);
        jpanel.add(button);

        JCheckBox jchSearchFromClip = new JCheckBox("scan clip");
        jchSearchFromClip.setToolTipText("automatically search text from clipboard");
        jchSearchFromClip.setBounds(215, y+hy*1, 100, 20);
        jpanel.add(jchSearchFromClip);

        jchSearchFromClip.addActionListener(new ActionListener() {
            Timer clipboardTimer;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jchSearchFromClip.isSelected()) {
                    if (clipboardTimer == null) {
                        clipboardTimer = clipboardTimer(searchActionListener, jtSubString);
                    }
                } else {
                    clipboardTimer.cancel();
                    clipboardTimer = null;
                }
            }
        });

        jpanel.setLayout(null);
        frame.add(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }

    static Timer clipboardTimer(ActionListener searchAction, JTextField jtSubString) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            String last = "";

            @Override
            public void run() {
                Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();

                // Get data stored in the clipboard that is in the form of a string (text)
                try {
                    String clip = c.getData(DataFlavor.stringFlavor).toString();
                    if (!clip.equals(last)) {
                        last = clip;
                        jtSubString.setText(clip);
                        searchAction.actionPerformed(null);
                    }
                } catch (UnsupportedFlavorException e) {
                } catch (IOException e) {
                }
            }
        }, 1000, 1000);
        return timer;
    }

    static void runSearchingTimer(JFrame frame, Future<Result> future, JTextArea jtResult, String subString) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (future.isDone()) {
                    try {
                        timer.cancel();
                        jtResult.setText(subString + ":\n" + future.get().getResult().toString());
                        frame.setTitle(future.get().getResult().size() + ": " + subString + " - " + TITLE);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    if (jtResult.getText().equals(":..")) {
                        jtResult.setText(".:.");
                    } else {
                        if (jtResult.getText().equals(".:.")) {
                            jtResult.setText("..:");
                        } else {
                            jtResult.setText(":..");
                        }
                    }
                }
            }
        }, 100, 500);
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
