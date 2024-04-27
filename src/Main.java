import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gravity Simulation");
            GravitySimulation simulation = new GravitySimulation();
            frame.add(simulation);
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            while (true) {
                simulation.update();
                simulation.repaint();
                try {
                    Thread.sleep(10); // speed of each individual update
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }




}
