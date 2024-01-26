package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
// do not use arraylist, it is garbage
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Blocks {

    private static final int BaseWidth = 20, Height = 20;

    // Blocks 2D Array
    // the internal array settings: posX, posY, type
    // NOTHING: 0
    // SPIKE: 1
    // BLOCK: 2
    public double block[][] = new double[1000][3];
    // Level 2D Array
    // posX and posY
    // To get a block detail use level[x][y] = thing
    public int level[][] = new int[1000][10];
    public int blocks;
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
            double brx = posX+BaseWidth; // Block Right X
            double bty = posY-Height;    // Block Top Y
            if (
                prx > posX &&     // Is player right  X > left block collision
                p.posX < brx &&   // Is player (left) x < right block collision
                pby > bty         // Is player bottom y > top block collision
            ) {
                if (pby > bty+5) {// Is player bottom y not too low
                    System.out.println("- - - - - - - ");
                    System.out.println(" > you died < "); // DEATH
                    System.out.println("- - - - - - - ");
                    System.exit(1);
                } else {
                    p.velY = 0;
                    p.posY -= 1;
                    p.posY = bty-Player.HEIGHT;
                    p.onground = true;
                    // System.out.println("block.collision:inblock");
                }
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
                    System.out.println("- - - - - - - ");
                    System.out.println(" > you died < "); // DEATH
                    System.out.println("- - - - - - - ");
                    System.exit(1);
                }
        }}

    public void generate() {
        block[0][0] = 750; block[0][1] = defY; block[0][2] = 0;

        for(int i=1; i<100; i++) {
            block[i][1] = defY;// block[i][0] = block[i-1][0]+20; block[i][2]=0;

            
            // test build: 2 11112 repeat
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
        //System.out.println(Arrays.deepToString(block));
    }
    public void importLvdata(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        String[] arr = lines.toArray(new String[0]);
        //System.out.println(Arrays.deepToString(arr));

        int arrstrlen = arr[0].length();
        for (int gridx = 0; gridx < arrstrlen; gridx++) {
            for (int gridy=0;gridy<arr.length;gridy++) {
                int tgridy = -(gridy-9);
                level[gridx][tgridy] = arr[gridy].charAt(gridx)-48;
        }}

        System.out.println("level: " + level[0].length);

        int blockn=0;
        for (int lvx=0;lvx < level.length;lvx++) {
            for (int lvy=0;lvy < 10;lvy++) {
                if (!(level[lvx][lvy] == 0.0)) {
                    block[blockn][0] = 20*lvx;
                    block[blockn][1] = 450-(20*lvy);
                    block[blockn][2] = level[lvx][lvy];
                    blockn+=1;
                }
            }
        }
        blocks = blockn;
        //System.out.println(Arrays.deepToString(block));
        System.out.println("Leveldata import complete");
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

            if (block[blocks-1][0] < 20) { // WINNING
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("You made it to the end. Congrats.");
                System.out.println("- - - - - - - - - - - - - - - - -");
                
                System.exit(1);
            }}}}
