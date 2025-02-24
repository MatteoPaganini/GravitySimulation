import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public  class GravitySimulation extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double G = 6.67430e-11; // gravitational constant
    private static final double DT = 5000; // time variable

    private List<Body> bodies;

    public GravitySimulation() {
        bodies = new ArrayList<>();
        // Add some initial bodies

        bodies.add(new Body(5.972e3, WIDTH / 2 , HEIGHT / 2, 0, 0)); // Earth
        // I got one of the planets to show up when I changed the above x for earth and moon
        bodies.get(0).name = "earth";
        System.out.println(bodies.get(0).name + "(" + bodies.get(0).x + ", " + bodies.get(0).y + ")");
        bodies.add(new Body(7.347e1, WIDTH / 2 - 100, HEIGHT / 2, 0, (6.0 * Math.random()*1.0e-5))); // Moon
        System.out.println(bodies.get(0).name + "(" + bodies.get(1).x + ", " + bodies.get(1).y + ")");
        bodies.get(1).name = "moon";

    }

    public void update() {
        // Calculate acceleration for each body
        for (Body body1 : bodies) {
            body.ax = 0;
            body.ay = 0;
            for (Body body2 : bodies) {
                if (body1 != body2) {
                    double dx = body2.x - body1.x; //distance between Moon and Earth in x direction (i hat)
                    double dy = body2.y - body1.y; //distance between Moon and Earth in y direction (j hat)
                    double rSquared = dx * dx + dy * dy; //pythagorian theorem
                    double r = Math.sqrt(rSquared); //radius
                    double force = G * body1.mass * body2.mass / (rSquared); //force of gravity
                    double ax = force * dx / r / body.mass; //accelerate in x using F(g)
                    double ay = force * dy / r / body.mass; //acceleration in y using F(g)
                    //declaration of physics specific variables
                    body.ax += ax;
                    body.ay += ay;
                }
            }
        }

        // Update position and velocity of each body
        for (Body body : bodies) {
            body.update(DT);
        } //sets the speed for the simulation
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Clear the screen
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw each body
        for (Body body : bodies) {
            if(body.name.equals("earth")) {
                g2d.setColor(Color.BLUE);
                g2d.fillOval((int) (body.x-15), (int) (body.y-15), 30, 30);
                g2d.setColor(Color.RED);
                g2d.fillRect((int) (body.x), (int) (body.y), 2, 2);

            }else {
                g2d.setColor(Color.WHITE);
                g2d.fillOval((int) (body.x), (int) (body.y), 10, 10);

            }
            System.out.print(body.name + ": (" + body.x +", "+ body.y + ")   ");
        }
        System.out.println();
    }



    public static void main(String[] args) {
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } //painting the simulation to the screen
    }
}
