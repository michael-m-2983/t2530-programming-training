package breakout;

import java.util.Scanner;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        double finalv = -0.2;
       
        JFrame window = new JFrame();

        window.setSize(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        window.setResizable(false);

        window.setTitle("Team 2530 Breakout Play number " + finalv );
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(new Game(finalv));

        window.setVisible(true);
    }
}