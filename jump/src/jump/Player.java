package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public static final double WIDTH = 20, HEIGHT = 20;
    public double posX, posY;
    public double velX, velY;
    public boolean jumpable;
    public boolean orbcontact;

    public Player(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void jump() {
        if (orbcontact) {
            posY -= 1;
            velY = -3.4;
        } else if (jumpable) {
            posY -= 2;
            velY = -3.6;
        }
    }
    public void gravity() {
        // Blocks gravity collision
        if ((posY < Game.WinHeight-20)) { // if not on the ground <430
            velY += 0.16;
            jumpable = false;
            orbcontact = false;
        }
        if ((posY-1 > Game.WinHeight-20)) { // if under the ground >431
            velY = 0;
            posY -= 1;
            posY = 430;
            jumpable = true;  
        }
    }
    public void update () {
        if (Game.upkeypressed) {this.jump();}
        this.gravity();
        posX += velX; posY += velY;
    }
    public void render(Graphics g) {
        g.setColor(Color.GREEN); g.fillRect((int) this.posX, (int) this.posY, (int) WIDTH, (int) HEIGHT);
    }}
