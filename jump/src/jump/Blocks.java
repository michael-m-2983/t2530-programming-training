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

    private static final int Width = 20, Height = 20;

    // Blocks 2D Array
    // the internal array settings: posX, posY, type
    // NOTHING: 0
    // SPIKE: 1
    // BLOCK: 2
    public double block[][] = new double[1000][3];
    // Level 2D Array
    // posX and posY
    // To get a block detail use level[x][y] = thing
    public double level[][] = new double[1000][10];
    public int blocks;
    public double defY;

    public Blocks(int defY) { // setup and nothing
        this.defY = defY; // 450
    }

    public void collision(double posX, double posY, double t, Player p) { // COLLISION - - - - - - - - - - - -
        // quick player variables
        int blocktype = (int) Math.floor(t/10);
        //     p.posX // Player left x
        //     p.posY // Player top Y
        double pby = p.posY+Player.HEIGHT; // player bottom y
        double prx = p.posX+Player.WIDTH; // player right x

        switch (blocktype) { // BLOCK COLLISION
            case 2:
                // TESTED: Block X and Y is left bottom

                double brx = posX+Width; // Block Right X
                double bty = posY-Height;// Block Top Y
                if (prx > posX &&     // Is player right  X > left block collision
                    p.posX < brx &&   // Is player (left) x < right block collision
                    pby > bty &&      // Is player bottom y > top block collision
                    p.posY < posY   // Is player (top)  y < bottom block collision
                ) {
                    if ((p.gravity==1 && pby > bty+5) || p.gravity==-1 && p.posY < posY-10) { // Is player bottom y not too low
                        System.out.println("- - - - - - - ");
                        System.out.println(" > you died < "); // DEATH
                        System.out.println("- - - - - - - ");
                        System.exit(1);
                    } else {
                        p.velY = 0;
                        p.posY -= p.gravity; 
                        if (p.gravity==1){p.posY = bty-Player.HEIGHT;} else {p.posY=posY;}
                        p.jumpable = true;
                        // System.out.println("block.collision:inblock");
                    }
                }
                break;
            case 1:  // SPIKE COLLISION - - - -
                // Player Y at ground is 430
        
                double clx = posX+6; // collision left X
                double cty = posY-Height+6; // collision top y 450-20-6 = 424 bottom y (posY) is 430
                double crx = posX+Width-7; // collision right x
                if ( // CURRENTLY WORKING
                    prx > clx &&     // Is player right  X > left spike collision
                    p.posX < crx &&  // Is player (left) x < right spike collision
                    pby > cty &&     // Is player bottom y > top spike collision
                    p.posY < posY    // Is player (top)  y < bottom spike collision
                    ) {
                        System.out.println("- - - - - - - "); System.out.println(" > you died < ");
                        System.out.println("- - - - - - - "); System.exit(1);}
                break;
            case 3:  // ORB collision
                // A bigger hitbox is used
                if (prx > posX-3 &&          // Left collision
                    p.posX < posX+Width+3 && // Right collision
                    pby > posY-Height-3 &&   // Top collision
                    p.posY < posY+3          // Bottom collision
                ) {p.orbcontact = 1;}
                break;
            case 4: // PAD collision
                // A little small hitbox
                if (
                    prx > posX+3 &&
                    p.posX < posX+Width-3 &&
                    pby > posY-(Height/4) &&
                    p.posY < posY
                ) {
                    p.posY -= p.gravity*4;
                    switch ((int)t) {
                        case 40:
                            p.velY = -5.7;
                            break;
                        case 41:
                            p.velY = -3.8;
                            break;
                        case 42:
                            p.velY = -6.7;
                            break;
                        case 43:
                            p.gravity *= -1;
                            p.velY = p.gravity*5.7;
                            break;
                        default:break;
                    }
                }
                break;
            default:break;
        }
    }

    public void generate() { // This generates a test-used pattern or something.
        
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
            blocks = i;
        }
        
        System.out.println("spikes.generation:complete");
        //System.out.println(Arrays.deepToString(block));
    }

    public void importLvdata(String file) throws FileNotFoundException { // This imports the level.txt file.
        Scanner sc = new Scanner(new File(file)); // I use Scanner.
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {lines.add(sc.nextLine());}
        String[] arr = lines.toArray(new String[0]); // The scanner output goes to String array arr[x][v]
        //System.out.println(Arrays.deepToString(arr));

        int arrstrlen = arr[0].length();
        for (int gridx = 0; gridx < arrstrlen; gridx++) { // Converting to a 2D Array: Level[x][y]
            for (int gridy=0;gridy<arr.length;gridy++) {
                int tgridy = -(gridy-9);
                double curchar = arr[gridy].charAt(gridx)-48;
                double ibtype;
                // If non-numerical characters must be replaced with a double, do it here.
                // A~Z: 17~42, a~z: 49~75, 0~9: 0~9
                // qwer is orbs, asdf is pads
                switch ((int) curchar) {
                    case 0:ibtype=0;break;
                    case 1:ibtype=10;break;
                    case 2:ibtype=20;break;
                    case 49: // a
                        ibtype=40;
                        break;
                    case 67: // s
                        ibtype=41;
                        break;
                    case 52: // d
                        ibtype=42;
                        break;
                    case 54: // f
                        ibtype=43;
                        break;
                    default:
                        System.out.println(curchar + " Converted into tens.");
                        ibtype=curchar*10;
                        break;
                } 
                // 1 is Spike, 2 is Block, 3 is Orbs, 4 is Pads
                // #1 = Pink, #2 = Red #3 = Blue
                level[gridx][tgridy] = ibtype;
        }}
        //System.out.println(Arrays.deepToString(level));

        int blockn=0;
        for (int lvx=0;lvx < level.length;lvx++) { // Converting Level[x][y] to Block[n][v] Format
            for (int lvy=0;lvy < 10;lvy++) {
                if (!(level[lvx][lvy] == 0.0)) {
                    block[blockn][0] = 20*lvx;
                    block[blockn][1] = 450-(20*lvy);
                    block[blockn][2] = level[lvx][lvy];
                    blockn+=1;
                }
            }
        }
        blocks = blockn; // keeps track of how many blocks has been imported
        System.out.println(Arrays.deepToString(block));
        System.out.println("Leveldata import complete with "+blocks+" blocks");
    }
    public void render(Graphics g, Player p) { // RENDERING - - - - - - - - - - - - - - - - - - - - - - - - -
        for (int i = 0; i < blocks; i++) {
            block[i][0] -= 2;
            if (!(block[i][2] == 0) && block[i][0] < Game.WinWidth && block[i][0] > -40) {
                this.collision(block[i][0], block[i][1], block[i][2], p); // Collision
                int blockx = (int) block[i][0]; int blocky = (int) block[i][1];
                Double blockt = block[i][2]; char subblockt = blockt.toString().charAt(1);
                int Pblockt = (int) Math.floor(blockt/10);
                //System.out.println(subblockt);

                switch (subblockt) { // Color
                    case '0':
                        g.setColor(Color.yellow);
                        break;
                    case '1':
                        g.setColor(Color.pink);
                        break;
                    case '2':
                        g.setColor(Color.red);
                        break;
                    case '3':
                        g.setColor(Color.cyan);
                        break;
                    default:break;
                }
                if (Pblockt==1) {        // SPIKE drawing
                        g.setColor(Color.red);
                        g.drawPolygon(
                            new int[] {blockx, (int) blockx+Width, (int) (blockx+Width/2)}
                            ,new int[] {blocky, blocky, blocky-Height},3);
                } else if (Pblockt==2) { // BLOCK drawing
                        g.setColor(Color.white);
                        g.drawRect(blockx, (int) blocky-Height, (int) Width, (int) Height);
                } else if (Pblockt==3) { // ORB Drawing
                    g.fillOval(blockx+1, blocky-Height+1, Width-1, Height-1);
                } else if (Pblockt==4) { // PAD drawing
                    g.fillArc(blockx, blocky-(Height/4), Width, Height/2, 180, -180);
                }}

                if (block[blocks-1][0] < -20) { // WINNING
                    System.out.println("- - - - - - - - - - - - - - - - -");
                    System.out.println("You made it to the end. Congrats.");
                    System.out.println("- - - - - - - - - - - - - - - - -");
                    System.exit(1);
            }
        }
    }
}
