package com.graphics;

import java.awt.*;
import java.util.ArrayList;

public class DrawCircle {
    public void pnarc(Graphics g, int r) {
        int x, y, f;
        x = 0;
        y = r;
        f = 0;
        ArrayList<Integer> CirclePlotX = new ArrayList<Integer>();
        ArrayList<Integer> CirclePlotY = new ArrayList<Integer>();
        while (y > 0) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
            if (f > 0) {
                f = f - 2 * y + 1;
                y--;
            } else {
                f = f + 2 * x + 1;
                x++;
            }
        }
        if (y == 0) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
        }

        // translation
        for (int i = 0; i < CirclePlotX.size(); i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.set(i, value + r + 1);
        }
        for (int i = 0; i < CirclePlotY.size(); i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.set(i, value + r + 1);
        }

        // mirror-x
        int x_size = CirclePlotX.size();
        int y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value - 2 * (value - (r + 1)));
        }

        // mirror-y
        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value - 2 * (value - (r + 1)));
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value);
        }

        // paint
        for (int i = 0; i < CirclePlotX.size(); i++) {
            g.drawLine(CirclePlotX.get(i), CirclePlotY.get(i), CirclePlotX.get(i), CirclePlotY.get(i));
        }
    }

    public void bresenham_arc(Graphics g, int r) {
        int x, y, d;
        ArrayList<Integer> CirclePlotX = new ArrayList<Integer>();
        ArrayList<Integer> CirclePlotY = new ArrayList<Integer>();
        x = 0;
        y = r;
        d = 3 - 2 * r;
        while (x < y) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
        if (x == y) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
        }

        // 45Line-mirror
        int x_size = CirclePlotX.size();
        int y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            CirclePlotX.add(CirclePlotY.get(i));
        }
        for (int i = 0; i < y_size; i++) {
            CirclePlotY.add(CirclePlotX.get(i));
        }

        // mirror-x

        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(-value);
        }

        // mirror-y
        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(-value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value);
        }

        // translation
        for (int i = 0; i < CirclePlotX.size(); i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.set(i, value + r + 1);
        }
        for (int i = 0; i < CirclePlotY.size(); i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.set(i, value + r + 1);
        }

        // paint
        for (int i = 0; i < CirclePlotX.size(); i++) {
            System.out.println(CirclePlotX.get(i));
            g.drawLine(CirclePlotX.get(i), CirclePlotY.get(i), CirclePlotX.get(i), CirclePlotY.get(i));
        }
    }

    public void polygon_arc(Graphics g, int CenterX, int CenterY, int r, int sides_n) {
        int xPoints[] = new int[sides_n];
        int yPoints[] = new int[sides_n];
        for (int i = 0; i < sides_n; i++) {
            double angle = 2 * Math.PI * i / sides_n;
            xPoints[i] = (int) (CenterX + r * Math.cos(angle));
            yPoints[i] = (int) (CenterY + r * Math.sin(angle));
        }
        g.drawPolygon(xPoints, yPoints, sides_n);
    }

    public void pnarc(Graphics g, int CenterX, int CenterY, int r) {
        int x, y, f;
        x = 0;
        y = r;
        f = 0;
        ArrayList<Integer> CirclePlotX = new ArrayList<Integer>();
        ArrayList<Integer> CirclePlotY = new ArrayList<Integer>();
        while (y > 0) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
            if (f > 0) {
                f = f - 2 * y + 1;
                y--;
            } else {
                f = f + 2 * x + 1;
                x++;
            }
        }
        if (y == 0) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
        }

        // translation
        for (int i = 0; i < CirclePlotX.size(); i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.set(i, value + CenterX);
        }
        for (int i = 0; i < CirclePlotY.size(); i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.set(i, value + CenterY);
        }

        // mirror-x
        int x_size = CirclePlotX.size();
        int y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value - 2 * (value - CenterY));
        }

        // mirror-y
        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value - 2 * (value - CenterX));
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value);
        }

        // paint
        for (int i = 0; i < CirclePlotX.size(); i++) {
            g.drawLine(CirclePlotX.get(i), CirclePlotY.get(i), CirclePlotX.get(i), CirclePlotY.get(i));
        }
    }

    public void bresenham_arc(Graphics g, int CenterX, int CenterY, int r) {
        int x, y, d;
        ArrayList<Integer> CirclePlotX = new ArrayList<Integer>();
        ArrayList<Integer> CirclePlotY = new ArrayList<Integer>();
        x = 0;
        y = r;
        d = 3 - 2 * r;
        while (x < y) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
        if (x == y) {
            CirclePlotX.add(x);
            CirclePlotY.add(y);
        }

        // 45Line-mirror
        int x_size = CirclePlotX.size();
        int y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            CirclePlotX.add(CirclePlotY.get(i));
        }
        for (int i = 0; i < y_size; i++) {
            CirclePlotY.add(CirclePlotX.get(i));
        }

        // mirror-x

        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(-value);
        }

        // mirror-y
        x_size = CirclePlotX.size();
        y_size = CirclePlotY.size();
        for (int i = 0; i < x_size; i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.add(-value);
        }
        for (int i = 0; i < y_size; i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.add(value);
        }

        // translation
        for (int i = 0; i < CirclePlotX.size(); i++) {
            int value = CirclePlotX.get(i);
            CirclePlotX.set(i, value + CenterX);
        }
        for (int i = 0; i < CirclePlotY.size(); i++) {
            int value = CirclePlotY.get(i);
            CirclePlotY.set(i, value + CenterY);
        }

        // paint
        for (int i = 0; i < CirclePlotX.size(); i++) {
            System.out.println(CirclePlotX.get(i));
            g.drawLine(CirclePlotX.get(i), CirclePlotY.get(i), CirclePlotX.get(i), CirclePlotY.get(i));
        }
    }
}
