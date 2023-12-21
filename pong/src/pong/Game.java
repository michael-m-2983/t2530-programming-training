package pong;

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

    public int score = 0;

    private final Player player;
    private final Player2 player2;

    private final Ball ball;

    private final Timer timer;

    // bricks

    public Game() {
        this.player = new Player(
            WINDOW_WIDTH / 2 - Player.WIDTH / 2, // 750/2 - 100/2 = 375 - 50 = 325 = X position of player
            450 // 500 - 10*5 = 500 - 50 = 450 = Y position of player
        );
        this.player2 = new Player2(
            WINDOW_WIDTH / 2 - Player2.WIDTH / 2, // 750/2 - 100/2 = 375 - 50 = 325 = X position of player
            3 // 10*5 = 50 = Y position of player
        );
        this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 8);
        // normal position at 400,400; right next to first brick: 65,25
        this.ball.posX = 400;
        this.ball.posY = 400;
        this.ball.velY = -2;
        this.ball.velX = -0.5;

        this.timer = new Timer(1, this);
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

        // Draw Player
        player.render(g);
        player2.render(g);

        // Draw ball
        ball.render(g, player, player2);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 12, 16);
        g.drawString("BX: " + Math.round(ball.posX), 12, 30);
        g.drawString("BY: " + Math.round(ball.posY), 12, 42);
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 37: // Left
                player.moveLeft();
                break;
            case 39: // Right
                player.moveRight();
                break;
            case 65: // A
                player2.moveLeft();
                break;
            case 68: // D
                player2.moveRight();
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
