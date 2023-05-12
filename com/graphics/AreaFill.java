package com.graphics;

import java.util.*;
import java.awt.*;

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

    public void ScanLineFill(int height, int width, int[] pixels) {
        int max_x = width;
        int min_x = 1;
        int max_y = height;
        int min_y = 1;
        int in_flag = 0;
        int x, y = 0;
        int red = Color.red.getRGB();
        for (y = min_y - 1; y < max_y; y++) {
            in_flag = 0;
            for (x = min_x - 1; x < max_x; x++) {
                if (pixels[y * width + x] == red) {
                    if (in_flag == 0) {
                        in_flag = 1;
                    } else {
                        in_flag = 0;
                    }
                }
                if (in_flag == 1) {
                    pixels[y * width + x] = red;
                }
            }
        }
    }
}
