package com.graphics;

import java.awt.*;

public class Crop {
    int xL, xR, yB, yT;

    public Crop(int xL, int xR, int yB, int yT) {
        this.xL = xL;
        this.xR = xR;
        this.yB = yB;
        this.yT = yT;
    }

    int code(int x, int y) {
        int c = 0;
        if (x < xL) {
            c = c | 1;
        } else if (x > xR) {
            c = c | 2;
        }
        if (y < yB) {
            c = c | 4;
        } else if (y > yT) {
            c = c | 8;
        }
        return c;
    }

    public void Sutherland_Cohen(Graphics g, int x0, int y0, int x1, int y1) {
        int c1, c2, c;
        float x, y, wx, wy;
        boolean accept = false;
        boolean done = false;
        c1 = code(x0, y0);
        c2 = code(x1, y1);
        do {
            if ((c1 | c2) == 0) {
                accept = true;
                done = true;
            } else if ((c1 & c2) != 0) {
                done = true;
            } else {
                c = c1;
                if (c == 0) {
                    c = c2;
                }
                wx = x1 - x0;
                wy = y1 - y0;
                if ((c & 8) == 8) {
                    x = x0 + wx * (yT - y0) / wy;
                    y = yT;
                } else if ((c & 4) == 4) {
                    x = x0 + wx * (yB - y0) / wy;
                    y = yB;
                } else if ((c & 1) == 1) {
                    y = y0 + wy * (xL - x0) / wx;
                    x = xL;
                } else {
                    y = y0 + wy * (xR - x0) / wx;
                    x = xR;
                }
                if (c == c1) {
                    x0 = (int) x;
                    y0 = (int) y;
                    c1 = code(x0, y0);
                } else {
                    x1 = (int) x;
                    y1 = (int) y;
                    c2 = code(x1, y1);
                }
            }
        } while (done == false);
        if (accept) {
            g.drawLine(x0, y0, x1, y1);
        }
    }

    public void Cyrus_Beck(Graphics g, double A[][], double N[][], double x[], double y[], double ts, double te,
            int k) {
        boolean draw = true;
        int i;
        double t, dn, nw;
        for (i = 0; i < k; i++) {
            dn = N[i][0] * (x[1] - x[0]) + N[i][1] * (y[1] - y[0]);
            nw = N[i][0] * (x[0] - A[i][0]) + N[i][1] * (y[0] - A[i][1]);
            t = -nw / dn;
            if (dn < 0) {
                if (t < te) {
                    te = t;
                }
            } else if (t > ts) {
                ts = t;
            }
            if (ts > te) {
                draw = false;
            }
        }
        if (draw) {
            double xs = (x[1] - x[0]) * ts + x[0];
            double ys = (y[1] - y[0]) * ts + y[0];
            double xe = (x[1] - x[0]) * te + x[0];
            double ye = (y[1] - y[0]) * te + y[0];
            g.drawLine((int) xs, (int) ys, (int) xe, (int) ye);
        }
    }
}
