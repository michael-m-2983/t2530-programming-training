package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
// do not use arraylist, it is garbage

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;

    public double posXs[] = new double[100];
    public int posY;

    public Spikes(int posY) { // setup and crap
        this.posY = posY;
    }

    public void collision(double posX, int posY, Player p) {
        // checking for player collision by a small hitbox
        // hitbox should be (x+6,y),(x+w-6,y),(x+w-6,y+h-8),(x+6,y+h-8)
        // player hitbox is 20x20 left top is px

        // 
        double clx = posX+6; // left X
        double cty = posY-Height-6; // top y
        double crx = posX+BaseWidth-6; // right x
        if (
            p.posX > clx &&
            p.posX < crx
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
