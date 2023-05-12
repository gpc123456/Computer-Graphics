package com.graphics;

import java.awt.*;
import java.util.ArrayList;

public class ScanConversion {
    public void PolygonBoundary(ArrayList<Point> vertices, int[] pixels, int width) {
        int red = Color.red.getRGB();
        int dy, y, ymax, ymin = 0;
        float dx, x = 0;
        for (int i = 0; i < vertices.size() - 1; i++) {
            dy = vertices.get(i + 1).getY() - vertices.get(i).getY();
            dx = (vertices.get(i + 1).getX() - vertices.get(i).getX()) / (float) dy;
            if (dy > 0) {
                x = vertices.get(i).getX();
            } else {
                x = vertices.get(i + 1).getX();
            }
            ymax = (Math.max(vertices.get(i).getY(), vertices.get(i + 1).getY()));
            ymin = (Math.min(vertices.get(i).getY(), vertices.get(i + 1).getY()));
            for (y = ymin + 1; y <= ymax; y++) {
                // 当pixels中的元素为red时，表示该边被涂色
                x = x + dx;
                if (pixels[y * width + (int) x] == red) {
                    pixels[y * width + (int) x + 1] = red;
                } else {
                    pixels[y * width + (int) x] = red;
                }
            }
        }
    }

    public void CircleBoundary(int[] pixels, int CenterX, int CenterY, int r, int width) {
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
        int color = Color.red.getRGB();
        for (int i = 0; i < CirclePlotX.size(); i++) {
            System.out.println(CirclePlotX.get(i));
            int boundary_x;
            int boundary_y;
            boundary_x = CirclePlotX.get(i);
            boundary_y = CirclePlotY.get(i);
            pixels[boundary_y * width + boundary_x] = color;
        }
    }

    public void Interior(int height, int width, int[] pixels) {
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
