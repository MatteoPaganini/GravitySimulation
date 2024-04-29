public class Body {
    double mass;
    double x, y;
    double vx, vy;
    double  ax, ay;
    String name;

    public Body(double mass, double x, double y, double vx, double vy) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void update(double dt) {
        vx += ax * dt;
        vy += ay * dt;
        x += vx * dt;
        y += vy * dt;
    } //physics
} //dt is the time step that determines the speed of the simulation