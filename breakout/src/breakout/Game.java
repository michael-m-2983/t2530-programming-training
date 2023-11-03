package breakout;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends JPanel implements ActionListener, KeyListener {
    private static Game INSTANCE;
    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;

    public int score = 0;

    public final Player player;

    public final Ball ball;

    public final Board board;

    private final Timer timer;

    // TODO: bricks

    public Game() {
        INSTANCE = this;
        this.player = new Player(WINDOW_WIDTH / 2 - Player.WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 5);

        this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 8);
        this.ball.velX = ThreadLocalRandom.current().nextFloat(-0.5f,0.5f);
        this.ball.velY = -1.4;

        this.board = new Board(10, 10);

        this.timer = new Timer(0, this);
        this.timer.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {

        // Check collision between ball and bricks

        board.checkCollisions(ball);

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw Bricks
        board.render(g);

        // Draw Player
        player.render(g);

        // Draw ball
        ball.render(g, player);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 12, 16);
        g.drawString("BX: " + ball.posX, 12, 30);
        g.drawString("BY: " + ball.posY, 12, 42);
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
            case 38: //down
                player.moveUp();
                break;
            case 40: //up
                player.moveDown();
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
    public static Game getInstance() {
        return INSTANCE;
    }
}
