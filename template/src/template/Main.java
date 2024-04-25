package template;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setSize(Renderer.WindowWidth, Renderer.WindowHeight);
        window.setResizable(false);

        window.setTitle("Template 2D Renderer");
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(new Renderer());

        window.setVisible(true);
    }
}
