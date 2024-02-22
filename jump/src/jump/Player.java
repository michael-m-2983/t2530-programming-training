package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public static final double WIDTH = 20, HEIGHT = 20;
    public double posX, posY; public double velX, velY;
    public int gravity = 1;
    public boolean jumpable;
    public int orbcontact;
    private boolean orbcooldown;

    public Player(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void jump() {
        if (orbcooldown && orbcontact==0) {
            orbcooldown = false;
        }
        if (Game.JumpKeyDown) {
            if (!(orbcontact==0) && !orbcooldown) {
                posY -= gravity;
                switch (orbcontact) {
                    case 30: // YELLOW
                        velY = gravity*-5; break;
                    case 31: // PINK
                        velY = gravity*-3.4; break;
                    case 32: // RED
                        velY = gravity*-6.7; break;
                    case 33: // CYAN
                        gravity*=-1;
                        velY = gravity*3; break;
                    default:break;
                }
                orbcooldown = true;
            } else if (jumpable && orbcontact==0) {
                posY -= gravity*2;
                velY = gravity*-3.8;
            }
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
            posY -= 1;
            posY = 430;
            jumpable = true;  
        }
    }
    public void update () {
        this.jump();
        this.gravity();
        posX += velX; posY += velY;
    }
    public void render(Graphics g) {
        g.setColor(Color.GREEN); g.fillRect((int) this.posX, (int) this.posY, (int) WIDTH, (int) HEIGHT);
    }}
