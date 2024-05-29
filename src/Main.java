import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        double FieldSizeCentimeters = 365.76;
        double WindowSizePixels = 980;
        double RobotWidthCentimeters = 43.2;
        double RobotLengthCentimeters = 40.8;
        int robotWidthPixels = (int) ((RobotWidthCentimeters / FieldSizeCentimeters) * WindowSizePixels);
        int robotLengthPixels = (int) ((RobotLengthCentimeters / FieldSizeCentimeters) * WindowSizePixels);

        JFrame window = new JFrame("Field");
        window.setSize((int) WindowSizePixels, (int) WindowSizePixels);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        JPanel robot = new JPanel();
        robot.setSize(robotWidthPixels, robotLengthPixels);
        robot.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        robot.setOpaque(false);

        try {
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/centerstage_map (2).png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        window.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                BigDecimal mouseX = new BigDecimal(Double.toString(((((double) mouseLocation.x) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2))).setScale(2, RoundingMode.UP);
                BigDecimal mouseY = new BigDecimal(Double.toString(-(((((double) mouseLocation.y) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2)))).setScale(2, RoundingMode.UP);

                window.setTitle("Field X: " + Double.toString(mouseY.doubleValue()) + " Y: " + Double.toString(-mouseX.doubleValue()));
                robot.setLocation(e.getX() - (int) robotWidthPixels / 2, -25 + e.getY() - (int) robotLengthPixels / 2);
                robot.setVisible(true);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                BigDecimal mouseX = new BigDecimal(Double.toString(((((double) mouseLocation.x) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2))).setScale(2, RoundingMode.UP);
                BigDecimal mouseY = new BigDecimal(Double.toString(-(((((double) mouseLocation.y) / WindowSizePixels) * FieldSizeCentimeters) - (FieldSizeCentimeters / 2)))).setScale(2, RoundingMode.UP);

                window.setTitle("Field X: " + Double.toString(mouseY.doubleValue()) + " Y: " + Double.toString(-mouseX.doubleValue()));
            }
        });

        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                robot.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        window.add(robot);
        window.setVisible(true);
    }
}