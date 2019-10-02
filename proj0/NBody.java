
/**
 * NPlanet
 */

public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double currentTime = 0;
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        // StdDraw.clear();
        // StdDraw.picture(0, 0, "images/starfield.jpg");

        while (currentTime < T) {
            currentTime += dt;
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int count = 0; count < planets.length; ++count) {
                xForces[count] = planets[count].calcNetForceExertedByX(planets);
                yForces[count] = planets[count].calcNetForceExertedByY(planets);

            }

            for (int count = 0; count < planets.length; ++count) {
                planets[count].update(dt, xForces[count], yForces[count]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int planetConut = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int planetConut = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planetConut];
        for (int count = 0; count < planetConut; ++count) {
            planets[count] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }
        return planets;
    }
}