package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
// do not use arraylist, it is garbage

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;

    public double posXs[] = new double[100];
    public int posY;

    public Spikes(int posY) { // setup and nothing
        this.posY = posY; // 450
    }

    public void collision(double posX, int posY, Player p) {
        // checking for player collision by a smaller hitbox instead of scanning
        // hitbox should be (x+6,y),(x+w-6,y),(x+w-6,y+h-8),(x+6,y+h-8)
        // player hitbox is 20x20 left top is px

        // positive Y is down, negative is up
        // posX and posY is bottom left corner of spike
        // p.posX and pby is bottom left corner of player
        // Player Y at ground is 430

        // quick spike variables
        double clx = posX+6; // collision left X
        double cty = posY-Height-6; // collision top y 450-20-6 = 424 bottom y (posY) is 430
        double crx = posX+BaseWidth-6; // collision right x
        // quick player variables
        double pby = p.posY+Player.HEIGHT; // player bottom y
        double prx = p.posX+Player.WIDTH; // player right x
        if ( // CURRENTLY WORKING
            prx > clx &&     // Is player right  X > left spike collision
            p.posX < crx &&  // Is player (left) x < right spike collision
            pby > cty &&     // Is player bottom y > top spike collision
            p.posY < posY    // Is player (top)  y < bottom spike collision
            ) {
                
            }

    }

    public void generate() {
        posXs[0] = 750;
        for(int i=1; i<100; i++) {
            posXs[i] = posXs[i-1] + Math.floor(Math.random()*3)*100;
        
        }
    }

    public void render(Graphics g, Player p) {


        g.setColor(Color.RED);
        for (int i = 0; i < posXs.length; i++) {
            posXs[i] -= 2; 
            this.collision(posXs[i], posY, p);



            g.fillPolygon(
                new int[] {(int) posXs[i], (int) posXs[i]+BaseWidth, (int) (posXs[i]+BaseWidth/2)}
                ,new int[] {(int) posY, (int) posY, (int) posY-Height}
                ,3
            );
            if (posXs[99] < 0) {
                // win message
            }
        }
    }
}
