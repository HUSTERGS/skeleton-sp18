/**
 * Body
 */
public class Body {
    public double xxPos; // x position
    public double yyPos; // x position
    public double xxVel; // velocity in the x direction
    public double yyVel; // in the y direction
    public double mass; // mass
    public String imgFileName;  // The name of the file that corresponds to the image that depicts the body
    
    public static final double G = 6.67e-11; 

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Body b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Body b) {
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce * (b.xxPos - xxPos) / distance;
    }
    public double calcForceExertedByY(Body b) {
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce * (b.yyPos - yyPos) / distance;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double totalForceX = 0;
        for (Body body : bodies){
            if (!body.equals(this)){
                totalForceX += calcForceExertedByX(body);
            }
        }
        return totalForceX;
    }
    public double calcNetForceExertedByY(Body[] bodies){
        double totalForceY = 0;
        for (Body body : bodies){
            if (!body.equals(this)){
                totalForceY += calcForceExertedByY(body);
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