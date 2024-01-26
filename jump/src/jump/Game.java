package jump;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;


public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;
    public static final int WinWidth = 750, WinHeight = 450;

    public int score = 0;

    private final Player player;

    private final Blocks blocks;

    private final Timer timer;
    private final Sound sound;

    public Game() { // - - - - - - - - VARIABLES at game start - - - - - - - - \\
        this.player = new Player(100,350);
        this.blocks = new Blocks(WinHeight);
        // this.blocks.generate(); // Generation for testing
        try {
            this.blocks.importLvdata("Level.txt");
        } catch (FileNotFoundException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        this.sound = new Sound("Endless_Night.wav");
        this.sound.play();

        this.timer = new Timer(1, this);
        this.timer.start();

        System.out.println("All Spawning/Variables successful");

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
    public void paint(Graphics g) { // draw and do crap, once per tick

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Draw GROUND
        g.setColor(Color.WHITE);
        g.fillRect(0, WinHeight, WINDOW_WIDTH, 15); // 0, 450

        // Draw Entities
        player.render(g, blocks);
        blocks.render(g, player);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Timer: " + timer.toString(), 12, 16);
        g.drawString("Score: " + score, 12, 26);
        g.drawString("player Y velocity: " + Math.floor(player.velY), 12, 36);
        g.drawString("Player Y: " + Math.floor(player.posY), 12, 46);
        g.dispose();

        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { // - - - - - - - - - - CONTROLS - - - - - - - - - - \\
        // Left arrow: 37
        switch(e.getKeyCode()) {
            case 38: // <- Jump
                player.jump();
                break;
            default: // Everything else
                break; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        repaint();
    }
    
}

