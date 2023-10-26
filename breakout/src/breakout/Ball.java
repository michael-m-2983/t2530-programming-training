package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public double posX, posY;
    public double velX, velY;

    public static final int RADIUS = 10;

    public Ball(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void render(Graphics g) {

        // Update
        this.posX += this.velX;
        this.posY += this.velY;

        // Render
        g.setColor(Color.WHITE);
        g.fillOval((int) posX, (int) posY, RADIUS, RADIUS);
    }
}
