package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public static final double WIDTH = 20, HEIGHT = 20;
    public double posX, posY;
    public double velX, velY;
    public boolean onground;
    public boolean blockcontact;

    public Player(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void jump() {
        if (onground) {
            posY -= 2;
            velY -= 2.4;
        }
    }
    public void gravity(Blocks b) {
        // Blocks gravity collision
        if (!blockcontact) {
            if ((posY < Game.WinHeight-20)) { // if not on the ground <430
                velY += 0.18;
                onground = false;
            } else { // if touching ground perfectly: 430
                velY = 0;
                onground = true;
            }
            if ((posY-1 > Game.WinHeight-20)) { // if under the ground >431
                velY = 0;
                posY -= 1;
                posY = 430;
                onground = true;
                
            }}}

    public void render(Graphics g, Blocks b) {
        // update
        this.gravity(b);
        posX += velX;
        posY += velY;

        g.setColor(Color.GREEN);
        g.fillRect((int) this.posX, (int) this.posY, (int) WIDTH, (int) HEIGHT);
    }}
