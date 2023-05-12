package com.graphics;

import java.util.*;

public class AreaFill {
    public void SeedFill(int[] pixels, Point point, int width, int boundary_color, int old_color, int new_color) {
        int x, y, save_x, x_right, x_left;
        Point p = new Point(0, 0);
        Stack<Point> stack = new Stack<Point>();
        stack.push(point);
        boolean span_need_fill;
        while (!stack.empty()) {
            p = stack.pop();
            x = p.getX();
            y = p.getY();
            save_x = x;
            while (pixels[y * width + x] != boundary_color) {
                pixels[y * width + x] = new_color;
                x++;
            }
            x_right = x - 1;
            x = save_x - 1;
            while (pixels[y * width + x] != boundary_color) {
                pixels[y * width + x] = new_color;
                x--;
            }
            x_left = x + 1;
            x = x_left;
            y = y + 1;
            while (x <= x_right) {
                span_need_fill = false;
                while (pixels[y * width + x] == old_color && x <= x_right) {
                    span_need_fill = true;
                    x++;
                }
                if (span_need_fill == true) {
                    p = new Point(x - 1, y);
                    stack.push(p);
                    span_need_fill = false;
                }
                while (pixels[y * width + x] != old_color && x <= x_right) {
                    x++;
                }
            }
            x = x_left;
            y = y - 2;
            while (x <= x_right) {
                span_need_fill = false;
                while (pixels[y * width + x] == old_color && x <= x_right) {
                    span_need_fill = true;
                    x++;
                }
                if (span_need_fill == true) {
                    p = new Point(x - 1, y);
                    stack.push(p);
                    span_need_fill = false;
                }
                while (pixels[y * width + x] != old_color && x <= x_right) {
                    x++;
                }
            }
        }
    }
}
