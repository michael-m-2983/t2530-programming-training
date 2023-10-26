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

    public void render(Graphics g, Player player) {
        // Update
        this.posX += this.velX;
        this.posY += this.velY;

        // Collisions
        checkCollisions(player);

        // Render
        g.setColor(Color.WHITE);
        g.fillOval((int) posX, (int) posY, RADIUS, RADIUS);
    }

    private void checkCollisions(Player player) {
        // Top, left and right walls
        if (posX <= 0 || posX >= Game.WINDOW_WIDTH) {
            velX = -velX;
        }
        if (posY <= 0) {
            velY = -velY;
        }

        // Player
        if (posX > player.posX && posX < player.posX + Player.WIDTH && posY >= player.posY) {
            velY = -velY;
            velX += (Math.random() - 0.5);
        }

        // Ground
        if (posY >= Game.WINDOW_HEIGHT + 50) {
            System.out.println("Game over, you lose!");
            System.exit(0);
        }
    }
}
