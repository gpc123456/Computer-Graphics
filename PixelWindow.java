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
        ArrayList<Point> points = new ArrayList<Point>();

        // Star
        /*
         * points.add(new Point(150, 32));
         * points.add(new Point(179, 116));
         * points.add(new Point(267, 116));
         * points.add(new Point(197, 171));
         * points.add(new Point(222, 256));
         * points.add(new Point(150, 206));
         * points.add(new Point(78, 255));
         * points.add(new Point(102, 171));
         * points.add(new Point(33, 116));
         * points.add(new Point(121, 116));
         * points.add(new Point(150, 32));
         */

        /*
         * // Rectangle
         * points.add(new Point(110, 105));
         * points.add(new Point(150, 105));
         * points.add(new Point(150, 150));
         * points.add(new Point(110, 150));
         * points.add(new Point(110, 105));
         */

        // TextBook
        points.add(new Point(10, 40));
        points.add(new Point(30, 20));
        points.add(new Point(90, 20));
        points.add(new Point(120, 50));
        points.add(new Point(120, 110));
        points.add(new Point(10, 110));
        points.add(new Point(10, 40));

        ScanConversion scanconversion = new ScanConversion();
        int pixels[] = new int[90000];
        scanconversion.CircleBoundary(pixels, 150, 150, 50, 300);
        // scanconversion.PolygonBoundary(points, pixels, 300);
        // ImageProducer ip = new MemoryImageSource(300, 300, pixels, 0, 300);
        // Image image = createImage(ip);
        // g.drawImage(image, 0, 0, null);

        // scanconversion.Interior(300, 300, pixels);
        AreaFill areafill = new AreaFill();
        areafill.SeedFill(pixels, new Point(150, 150), 300, Color.red.getRGB(), 0, Color.green.getRGB());
        ImageProducer ip_interior = new MemoryImageSource(300, 300, pixels, 0, 300);
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