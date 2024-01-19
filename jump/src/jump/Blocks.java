package jump;

import java.awt.Color;
import java.awt.Graphics;
// do not use arraylist, it is garbage
import java.util.Arrays;

public class Blocks {

    private static final int BaseWidth = 20, Height = 20;

    // Blocks 2D Array
    // the internal array settings: posX, posY, type
    // NOTHING: 0
    // SPIKE: 1
    // BLOCK: 2
    public double block[][] = new double[120][3];
    // Level 2D Array
    // posX and posY
    // To get a block detail use level[x][y] = thing
    public double level[][] = new double[100][100];
    public double defY;

    public Blocks(int defY) { // setup and nothing
        this.defY = defY; // 450
    }

    public void collision(double posX, double posY, double t, Player p) {
        // quick player variables
            double pby = p.posY+Player.HEIGHT; // player bottom y
            double prx = p.posX+Player.WIDTH; // player right x

        if (t==2) { // BLOCK COLLISION
            // block X and Y is bottom left

            // quick block variables
            double brx = posX+BaseWidth;
            double bty = posY-Height;
            if (
                prx > posX &&     // Is player right  X > left block collision
                p.posX < brx &&  // Is player (left) x < right block collision

                pby > bty &&     // Is player bottom y > top block collision
                p.posY < posY    // Is player (top)  y < bottom block collision
            ) {
                p.velY = 0;
                p.posY -= 1;
                p.posY = bty-Player.HEIGHT;
                p.onground = true;
                System.out.println("block.collision:inblock");
            }
        } else if (t==1) { // SPIKE COLLISION
            // checking for player collision by a smaller hitbox instead of scanning
            // hitbox should be (x+6,y),(x+w-6,y),(x+w-6,y+h-8),(x+6,y+h-8)
            // player hitbox is 20x20 left top is px

            // positive Y is down, negative is up
            // posX and posY is bottom left corner of spike
            // p.posX and pby is bottom left corner of player
            // Player Y at ground is 430
        
            // quick spike variables
            double clx = posX+6; // collision left X
            double cty = posY-Height+6; // collision top y 450-20-6 = 424 bottom y (posY) is 430
            double crx = posX+BaseWidth-7; // collision right x
            if ( // CURRENTLY WORKING
                prx > clx &&     // Is player right  X > left spike collision
                p.posX < crx &&  // Is player (left) x < right spike collision
                pby > cty &&     // Is player bottom y > top spike collision
                p.posY < posY    // Is player (top)  y < bottom spike collision
                ) {
                    System.out.println(" - > you died < -");
                    System.out.println(" > you died < "); // DEATH
                    System.exit(1);
                }
        }}

    public void generate() {
        block[0][0] = 750; block[0][1] = defY; block[0][2] = 0;

        for(int i=1; i<100; i++) {
            block[i][1] = defY;// block[i][0] = block[i-1][0]+20; block[i][2]=0;

            
            // testing stuff
            block[i][0] = block[i-1][0]+20; block[i][2] = 2;
            i+=1;

            block[i][0] = block[i-1][0]+20; block[i][1] = defY; block[i][2] = 1;
            i+=1;
            block[i][0] = block[i-1][0]+20; block[i][1] = defY; block[i][2] = 1;
            i+=1;
            block[i][0] = block[i-1][0]+20;block[i][1] = defY; block[i][2] = 1;
            i+=1;
            block[i][0] = block[i-1][0]+20; block[i][1] = defY; block[i][2] = 1;
              
        }
        System.out.println("spikes.generation:complete");
        System.out.println(Arrays.deepToString(block));
    }

    public void render(Graphics g, Player p) {

        for (int i = 0; i < block.length; i++) {
            block[i][0] -= 2; 
            this.collision(block[i][0], block[i][1], block[i][2], p);


            // spike drawing
            if (block[i][2] == 1) {
                g.setColor(Color.RED);
                g.fillPolygon(
                    new int[] {(int) block[i][0], (int) block[i][0]+BaseWidth, (int) (block[i][0]+BaseWidth/2)}
                    ,new int[] {(int) block[i][1], (int) block[i][1], (int) block[i][1]-Height}
                    ,3
                );
            } else if (block[i][2] == 2) {
                // block drawing
                g.setColor(Color.YELLOW);
                g.fillRect((int) block[i][0], (int) block[i][1]-Height, (int) BaseWidth, (int) Height);
            }

            if (block[100][0] < 0) { // WINNING
                System.out.println("You made it to the end. Congrats.");
                
                System.exit(1);
            }}}}
