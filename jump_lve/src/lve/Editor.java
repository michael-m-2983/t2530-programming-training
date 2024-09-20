package lve;

import javax.swing.JPanel;
import javax.swing.Timer;

import lve.Block;
import lve.Blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Editor extends JPanel implements ActionListener, KeyListener{
    public static final int WinWidth=750,WinHeight=500;
    public static boolean KeyPressed[] = new boolean[100];
    public static double ScreenX = 0;

    public static final boolean LevelImport = true;

    private final Timer timer;

    private final Blocks blocks;
    private final Block block;

    public Editor() {
        this.blocks = new Blocks();
        this.block = new Block();

        if (LevelImport) {
            try {
                blocks.importLvdata("levelimport.txt");
                Block.placedblocks=blocks.blockc;
            } catch (FileNotFoundException e) {e.printStackTrace();}
        }

        this.timer = new Timer(1, this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
new Timer(1, this);
        this.timer.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }
    @Override
    public void paint(Graphics g) {
        // BG and Ground
        g.setColor(Color.black); g.fillRect(0, 0, WinWidth, WinHeight);
        g.setColor(Color.white); g.drawRect(-1, WinHeight-50, WinWidth, 15);

        // Running code
        block.actions(blocks, g);
        blocks.actions(g);

        // rendering
        // scores
        g.setColor(Color.white);
        g.drawString("ScreenX: "+ScreenX, 12, 12);
        g.drawString("Block X: "+block.posX, 12, 24);
        g.drawString("Block Y: "+block.posY, 12, 36);
        g.drawString("BlockType: "+block.blocktype, 12, 48);
        g.drawString("Block Rotation: "+block.dir, 12, 60);
        g.drawString("Block Count: "+blocks.blockc, 12, 72);


        g.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
        public void keyPressed(KeyEvent e) { // - - - - - - - - - - CONTROLS - - - - - - - - - - \\
            switch(e.getKeyCode()) { // A~Z 65~90 20~45
                case 37: // Left Key
                    block.posX-=10;
                    KeyPressed[0] = true; break;
                case 38: // Up Key
                    block.posY-=10;
                    KeyPressed[1] = true;break;
                case 39: // Right Key
                    block.posX+=10;
                    KeyPressed[2] = true;break;
                case 40: // Down Key
                    block.posY+=10;
                    KeyPressed[3] = true;break;
                case 49: // 1
                    ScreenX-=10;
                    KeyPressed[11]=true;break;
                case 51: // 3
                    ScreenX+=10;
                    KeyPressed[13]=true;break;

                case 65: // A key
                    KeyPressed[20]=true;break;
                case 68: // D key
                    KeyPressed[23]=true;break;
                case 32: // Space key
                    //block.addblock(blocks);
                    KeyPressed[4]=true;break;
                case 90: // Z key SType
                    KeyPressed[45]=true;break;
                case 67: // C key SType
                    KeyPressed[22]=true;break;
                case 81: // Q key Rotate
                    KeyPressed[31]=true;break;
                case 69: // E key Rotate
                    KeyPressed[24]=true;break;
                default: // Everything else
                    break; 
            }
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case 37: // Left Key
                    KeyPressed[0] = false;break;
                case 38: // Up Key
                    KeyPressed[1] = false;break;
                case 39: // Right Key
                    KeyPressed[2] = false;break;
                case 40: // Down Key
                    KeyPressed[3] = false;break;
                case 49: // 1
                    KeyPressed[11]=false;break;
                case 51: // 3
                    KeyPressed[13]=false;break;
                case 65: //A
                    KeyPressed[20]=false;break;
                case 68: //D
                    KeyPressed[23]=false;break;
                case 32: // Space key
                    KeyPressed[4]=false;break;
                case 90: //Z
                    KeyPressed[45]=false;break;
                case 67: // C
                    KeyPressed[22]=false;break;
                case 81: // Q key
                    KeyPressed[31]=false;break;
                case 69: // E key
                    KeyPressed[24]=false;break;
                default: // Everything else
                    break; 
            }
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start(); repaint();
    }
}
