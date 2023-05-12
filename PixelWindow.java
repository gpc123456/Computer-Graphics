import javax.swing.*;

import com.graphics.DrawCircle;
import com.graphics.DrawLine;
import com.graphics.Point;
import com.graphics.ScanConversion;
import com.graphics.AreaFill;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;

class SetPixel extends JPanel {
    Color color;
    int startX, startY, endX, endY;

    public SetPixel() {
        color = Color.blue;
        startX = 60;
        startY = 230;
        endX = 300;
        endY = 50;
    }

    void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void paintComponent(Graphics g) {
        g.setColor(color);

        /*
         * // DrawLine
         * // StdDrawLine
         * // drawLine(g, startX, startY, endX, endY);
         * 
         * DrawLine drawline = new DrawLine();
         * 
         * // dda method
         * g.drawString("DDA", startX + 50, startY + 50);
         * drawline.dda(g, startX + 50, startY + 50, endX + 50, endY + 50);
         * 
         * // Bresenham method
         * g.drawString("Bresenham", startX, startY);
         * drawline.Bresenham(g, startX, startY, endX, endY);
         */

        /*
         * // DrawCircle
         * DrawCircle drawcircle = new DrawCircle();
         * g.drawString("Pnac", 500, 800);
         * drawcircle.pnarc(g, 500, 500, 300);
         * g.drawString("Bresenham", 500, 700);
         * drawcircle.bresenham_arc(g, 500, 500, 200);
         * g.drawString("Polygon", 500, 600);
         * drawcircle.polygon_arc(g, 500, 500, 100, 25);
         */

        // ScanConversion
        ArrayList<Point> points_star = new ArrayList<Point>();
        ArrayList<Point> points_rectangle = new ArrayList<Point>();
        ScanConversion scanconversion = new ScanConversion();
        int pixels[] = new int[600 * 600];

        // Star
        points_star.add(new Point(300, 101));
        points_star.add(new Point(347, 238));
        points_star.add(new Point(491, 238));
        points_star.add(new Point(377, 328));
        points_star.add(new Point(420, 466));
        points_star.add(new Point(300, 386));
        points_star.add(new Point(182, 470));
        points_star.add(new Point(220, 328));
        points_star.add(new Point(104, 238));
        points_star.add(new Point(249, 238));
        points_star.add(new Point(300, 101));
        scanconversion.PolygonBoundary_SeedFill(points_star, pixels, 600);

        // Rectangle
        points_rectangle.add(new Point(296, 214));
        points_rectangle.add(new Point(378, 292));
        points_rectangle.add(new Point(296, 374));
        points_rectangle.add(new Point(217, 292));
        points_rectangle.add(new Point(296, 214));
        scanconversion.PolygonBoundary_SeedFill(points_rectangle, pixels, 600);

        // Circle
        scanconversion.CircleBoundary_SeedFill(pixels, 300, 300, 230, 600);

        // Fill
        AreaFill areafill = new AreaFill();
        // ScanLineFill
        // areafill.ScanLineFill(300, 300, pixels);

        // SacanLineSandFill
        areafill.SeedFill(pixels, new Point(300, 75), 600, Color.red.getRGB(), 0, Color.cyan.getRGB());
        areafill.SeedFill(pixels, new Point(295, 193), 600, Color.red.getRGB(), 0, Color.yellow.getRGB());
        areafill.SeedFill(pixels, new Point(298, 301), 600, Color.red.getRGB(), 0, Color.green.getRGB());

        // ShowImage
        ImageProducer ip_interior = new MemoryImageSource(600, 600, pixels, 0, 600);
        Image image_interior = createImage(ip_interior);
        g.drawImage(image_interior, 0, 0, null);
    }
}

public class PixelWindow extends JFrame {
    public PixelWindow() {
        super("PixelColor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(new Rectangle(100, 100, 1000, 1000));
        SetPixel set = new SetPixel();
        add(set);
    }

    public static void main(String args[]) {
        PixelWindow setPixel = new PixelWindow();
        setPixel.setVisible(true);
    }
}