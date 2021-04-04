package roksard.algorithms.sort;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SortPanel extends javax.swing.JPanel {
        private Data data;
        {
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(900, 400));
        }
        private void drawRect(Graphics g, double x, double y, double w, double h, Color color) {
            Graphics2D g2d = (Graphics2D)g;
            Rectangle2D.Double rect = new Rectangle2D.Double(x, y, w, h);
            g2d.setColor(color);
            g2d.fill(rect);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (data == null) {
                return;
            }
            int maxVal = data.getMaxValue();
            int[] array = data.getArray();
            for (int i = 0; i < array.length; i++) {
                double winW = g.getClip().getBounds().getWidth();
                double winH = g.getClip().getBounds().getHeight();

                double x = i * winW / array.length +2;
                double y = 0;
                double w =  winW / array.length -1;
                double h =  winH * (array[i] / (double)maxVal) ;
                drawRect(g, x, y, w, h, i == data.getCursor() ? Color.RED : i == data.getSeekCursor() ? Color.BLUE : Color.GRAY);
            }
        }

        public void setData(Data data) {
            this.data = data;
        }
}
