package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public static final double WIDTH = 20, HEIGHT = 20;
    public double posX, posY; public double velX, velY;
    public int gravity = 1;
    public boolean jumpable;
    public int orbcontact;

    public Player(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void jump() {
        if (orbcontact==1) {
            posY -= gravity;
            velY = gravity*-4;
        } else if (jumpable) {
            posY -= gravity*2;
            velY = gravity*-3.8;
        }
    }
    public void gravity() {
        // Blocks gravity collision
        if ((posY < Game.WinHeight-20)) { // if not on the ground <430
            velY += gravity*0.18;
            jumpable = false;
            orbcontact = 0;
        } else if ((posY-1 > Game.WinHeight-20) && gravity==1) { // if under the ground >431
            velY = 0;
            posY -= gravity;
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
