package Playground;

import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("My First Frame");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
