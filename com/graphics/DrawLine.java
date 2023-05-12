package com.graphics;

import java.awt.*;

public class DrawLine {
    public void dda(Graphics g, int x1, int y1, int x2, int y2) {
        int k;
        float x, y, dx, dy;
        k = Math.abs(x2 - x1);
        if (Math.abs(y2 - y1) > k) {
            k = Math.abs(y2 - y1);
        }
        dx = (float) (x2 - x1) / k;
        dy = (float) (y2 - y1) / k;
        x = (float) x1;
        y = (float) y1;
        for (int i = 0; i < k; i++) {
            g.drawLine((int) (x1 + 0.5f), (int) (y1 + 0.5f), (int) (x2 + 0.5f), (int) (y2 + 0.5f));
            x += dx;
            y += dy;
        }
    }

    public void Bresenham(Graphics g, int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int temp;
            temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        if (x2 > x1 && y2 < y1) {
            int dx = x2 - x1;
            int dy = -(y2 - y1);
            int x = x1;
            int y = y1;
            if (Math.abs(dx) >= Math.abs(dy)) {
                int e = 2 * dy - dx;
                for (int i = 0; i < dx; i++) {
                    g.drawLine(x, y, x, y);
                    if (e >= 0) {
                        y = y - 1;
                        e = e - 2 * dx;
                    }
                    x = x + 1;
                    e = e + 2 * dy;
                }
            } else {
                int e = 2 * dx - dy;
                for (int i = 0; i < dy; i++) {
                    g.drawLine(x, y, x, y);
                    if (e >= 0) {
                        x = x + 1;
                        e = e - 2 * dy;
                    }
                    y = y - 1;
                    e = e + 2 * dx;
                }
            }
        } else {
            int dx = x2 - x1;
            int dy = y2 - y1;
            int x = x1;
            int y = y1;
            if (Math.abs(dx) >= Math.abs(dy)) {
                int e = 2 * dy - dx;
                for (int i = 0; i < dx; i++) {
                    g.drawLine(x, y, x, y);
                    if (e >= 0) {
                        y = y + 1;
                        e = e - 2 * dx;
                    }
                    x = x + 1;
                    e = e + 2 * dy;
                }
            } else {
                int e = 2 * dx - dy;
                for (int i = 0; i < dy; i++) {
                    g.drawLine(x, y, x, y);
                    if (e >= 0) {
                        x = x + 1;
                        e = e - 2 * dy;
                    }
                    y = y + 1;
                    e = e + 2 * dx;
                }
            }
        }
    }
}
