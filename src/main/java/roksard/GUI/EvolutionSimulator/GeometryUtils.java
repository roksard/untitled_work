package roksard.GUI.EvolutionSimulator;

import java.awt.*;

public abstract class GeometryUtils {
    public static Point getPointOnVector(Point vectorBegin, Point vectorEnd, double desiredDistFromVectorBegin) {
        double x1 = vectorBegin.getX();
        double y1 = vectorBegin.getY();
        double x2 = vectorEnd.getX();
        double y2 = vectorEnd.getY();
        double deltaY = y2-y1;
        double deltaX = x2-x1;
        double alpha = Math.atan(deltaY / deltaX);
        double newX = x1 + desiredDistFromVectorBegin * Math.sin(Math.PI/2 - alpha);
        double newY = y1 + desiredDistFromVectorBegin * Math.sin(alpha);
        return new Point((int)Math.round(newX), (int)Math.round(newY));
    }
}
