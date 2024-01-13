package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public double posX, posY;

    public double velX, velY;

    private double finalV;
    

    public static final int RADIUS = 10;



    public void finalVe(){
         //velX = finalV;
         velY = finalV;
         //this.posX += this.velX;
         this.posY += this.velY;


    }
    public Ball(double posX, double posY, double fV) {
        this.posX = posX;
        this.posY = posY;
        this.finalV = fV;
    }

    public void render(Graphics g, Player player) {
        // Update
        System.out.printf("fV=%.2f velocity=[%.2f,%.2f] position=[%.2f,%.2f]\n", finalV, velX, velY, posX, posY);
        finalVe();
        
        

        // Collisions
        checkCollisions(player);

        // Render
        g.setColor(Color.WHITE);
        g.fillOval((int) posX, (int) posY, RADIUS, RADIUS);

        g.setColor(Color.RED);
        g.drawOval(0, 0, 40, 40);
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
