package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
// do not use arraylist, it is garbage

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;

    // Blocks 2D Array
    // the internal array settings: posX, posY, type
    // type: spike or block, 0 or 1
    public double block[][] = new double[120][3];
    public double defY;

    public Spikes(int defY) { // setup and nothing
        this.defY = defY; // 450
    }

    public void collision(double posX, double posY, Player p) {
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
        double crx = posX+BaseWidth-7; // collision right x
        // quick player variables
        double pby = p.posY+Player.HEIGHT; // player bottom y
        double prx = p.posX+Player.WIDTH; // player right x
        if ( // CURRENTLY WORKING
            prx > clx &&     // Is player right  X > left spike collision
            p.posX < crx &&  // Is player (left) x < right spike collision
            pby > cty &&     // Is player bottom y > top spike collision
            p.posY < posY    // Is player (top)  y < bottom spike collision
            ) {
                System.out.println(" - > you died < -");
                System.exit(1);
            }

    }

    public void generate() {
        block[0][0] = 750;

        for(int i=1; i<100; i++) {
            block[i][1] = defY;

            double RandomInt = Math.floor(Math.random()*8);
            switch ((int) RandomInt) {
                case 1:
                    block[i][0] = block[i-1][0] + 6*20;
                    break;
                case 2:
                    block[i][0] = block[i-1][0] + 20;
                    i+=1;
                    block[i][0] = block[i-1][0] + 4*20;
                    
                    break;
                case 3:
                    block[i][0] = block[i-1][0] + 20;
                    i+=1;
                    block[i][0] = block[i-1][0] + 20;
                    i+=1;
                    block[i][0] = block[i-1][0] + 5*20;
                    
                    break;
                case 4:
                    block[i][0] = block[i-1][0] + 4*20;
                    i+=1;
                    block[i][0] = block[i-1][0] + 5*20;
                    break;
                case 5:
                    block[i][0] = block[i-1][0] + 7*20;
                    i+=1;
                    block[i][0] = block[i-1][0] + 4*20;
                    break;
                default:
                    block[i][0] = block[i-1][0] + 8*20;
                    break;
            // nothing back here
            }
        
        }
        System.out.println("spikes.generation:complete");
    }

    public void render(Graphics g, Player p) {

        for (int i = 0; i < block.length; i++) {
            block[i][0] -= 2; 
            this.collision(block[i][0], block[i][1], p);


            // spike drawing
            g.setColor(Color.RED);
            g.fillPolygon(
                new int[] {(int) block[i][0], (int) block[i][0]+BaseWidth, (int) (block[i][0]+BaseWidth/2)}
                ,new int[] {(int) block[i][1], (int) block[i][1], (int) block[i][1]-Height}
                ,3
            );
            // block drawing
            // g.setColor(Color.YELLOW);
            // g.fillRect((int) posXs[i], (int) posY-Height, (int) BaseWidth, (int) Height);

            if (block[99][0] < 0) { // WINNING
                System.out.println("You made it to the end. Congrats.");
                System.exit(1);
            }
        }
    }
}
