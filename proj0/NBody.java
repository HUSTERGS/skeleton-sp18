import java.lang.reflect.Array;

import sun.awt.image.ShortBandedRaster;

/**
 * NBody
 */

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double currentTime = 0;
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        // StdDraw.clear();
        // StdDraw.picture(0, 0, "images/starfield.jpg");

        
        while (currentTime < T) {
            currentTime += dt;
            double[] xForces = new double[Array.getLength(bodies)];
            double[] yForces = new double[Array.getLength(bodies)];
            for (int count = 0; count < Array.getLength(bodies); ++count) {
                xForces[count] = bodies[count].calcNetForceExertedByX(bodies);
                yForces[count] = bodies[count].calcNetForceExertedByY(bodies);

            }

            for (int count = 0; count < Array.getLength(bodies); ++count) {
                bodies[count].update(dt, xForces[count], yForces[count]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body body : bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        
    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int bodyConut = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int bodyConut = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[bodyConut];
        for (int count = 0; count < bodyConut; ++count) {
            bodies[count] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }
        return bodies;
    }
}