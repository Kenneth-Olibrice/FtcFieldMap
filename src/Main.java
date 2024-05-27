import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        double FieldSizeCentimeters = 365.76;
        double WindowSizePixels = 980;

        JFrame window = new JFrame("Field");
        window.setSize((int) WindowSizePixels, (int) WindowSizePixels);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/centerstage_map (2).png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        window.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                BigDecimal mouseX = new BigDecimal(Double.toString(((((double) mouseLocation.x) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2))).setScale(2, RoundingMode.UP);
                BigDecimal mouseY = new BigDecimal(Double.toString(-(((((double) mouseLocation.y) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2)))).setScale(2, RoundingMode.UP);

                window.setTitle("Field X: " + Double.toString(mouseX.doubleValue()) + " Y: " + Double.toString(mouseY.doubleValue()));
            }
        });
        window.setVisible(true);
    }
}