package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public static final double WIDTH = 20, HEIGHT = 20;
    public double posX, posY;
    public double velX, velY;

    public Player(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void jump() {
        posY += -10;
    }
    public void gravity(Game game) {
        if (posY > game.WinHeight-20) {
            // posY -= 1;
        } else {
            posY += 1;
        }
    }

    public void render(Graphics g, Game game) {
        // update
        this.gravity(game);

        g.setColor(Color.GREEN);
        g.fillRect((int) this.posX, (int) this.posY, (int) WIDTH, (int) HEIGHT);
    }
}
