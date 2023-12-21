package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public double posX, posY;
    public double velX, velY;

    public static final int RADIUS = 10;

    public Ball(double posX, double posY) {
        this.posX = posX; // 325
        this.posY = posY; // 450
    }

    public void render(Graphics g, Player player, Player2 player2) {
        // Update
        this.posX += this.velX;
        this.posY += this.velY;

        // Collisions
        checkCollisions(player, player2);

        // Render
        g.setColor(Color.WHITE);
        g.fillOval((int) posX, (int) posY, RADIUS, RADIUS);
    }

    private void checkCollisions(Player player, Player2 player2) {
        // left and right walls
        if (posX <= 0 || posX >= Game.WINDOW_WIDTH - 30) {
            velX = -velX;
        }

        // Player collision
        if ((
            posX > player.posX && // bx > playerx(325 if middle)
            posX < player.posX + Player.WIDTH && // bx < 325(playerx) + 100(playerwidth)
            posY >= player.posY - RADIUS && // by >= 450(playery) - ball radius (10 default)
            posY < player.posY + Player.HEIGHT // by < 450 + 10(height)
            ) || (
            posX > player2.posX && 
            posX < player2.posX + Player2.WIDTH &&
            posY >= player2.posY - RADIUS &&
            posY < player2.posY + Player.HEIGHT
            )
            ) {
            velY *= -1 - Math.random() / 10;
            velX *= 1 + Math.random() / 10;
        }

        // Top/ bottom
        if (posY >= Game.WINDOW_HEIGHT) {
            System.out.println("Player 2 wins!");
            System.exit(0);
        } else if (posY < -10) {
            System.out.println("Player 1 wins!");
            System.exit(0);
        }
    }
}
