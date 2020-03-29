package roksard.GUI.EvolutionSimulator.gui;

import java.awt.*;

public interface Shape {
    public void draw(Graphics g);
    public Point getLocation();
    public void setLocation(Point location);
}
