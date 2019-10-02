/**
 * Planet
 */
public class Planet {
    public double xxPos; // x position
    public double yyPos; // x position
    public double xxVel; // velocity in the x direction
    public double yyVel; // in the y direction
    public double mass; // mass
    public String imgFileName;  // The name of the file that corresponds to the image that depicts the planet
    
    private static final double G = 6.67e-11; 

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet b) {
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce * (b.xxPos - xxPos) / distance;
    }
    public double calcForceExertedByY(Planet b) {
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce * (b.yyPos - yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double totalForceX = 0;
        for (Planet planet : planets){
            if (!planet.equals(this)){
                totalForceX += calcForceExertedByX(planet);
            }
        }
        return totalForceX;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double totalForceY = 0;
        for (Planet planet : planets){
            if (!planet.equals(this)){
                totalForceY += calcForceExertedByY(planet);
            }
        }
        return totalForceY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = aX * dt + xxVel; // new velocity
        yyVel = aY * dt + yyVel;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "/images/" + imgFileName);
    }
}