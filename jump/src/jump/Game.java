package jump;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;
    public static final int WinWidth = 750, WinHeight = 450;

    public int score = 0;

    private final Player player;

    private final Spikes spawner;
    private final Spike spike;

    private final Timer timer;

    // bricks

    public Game() { // - - - - - - - - VARIABLES - - - - - - - - \\
        this.player = new Player(
            100
            ,100
        );
        this.spike = new Spike(100, 100);
        this.spawner = new Spikes(
            WinHeight
        );
        this.timer = new Timer(1, this);
        this.timer.start();

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

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Draw GROUND
        g.setColor(Color.WHITE);
        g.fillRect(0, WinHeight, WINDOW_WIDTH, 15); // 0, 450

        // Draw Entities
        player.render(g);
        spike.render(g);
        spawner.render(g);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Timer: " + timer.toString(), 12, 16);
        g.drawString("Score: " + score, 12, 26);
        g.drawString("VY: " + player.velY, 12, 36);
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
