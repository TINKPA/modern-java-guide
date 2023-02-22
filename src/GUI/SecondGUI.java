package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

class Canvas extends JPanel {
    // https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
    // https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html#constructor_summary
    // The default constructor will do for now.

    // Remember the rules for
    // when we're given a default constructor
    // and constructor chaining:
    // by not defining a constructor,
    // Canvas is given a default constructor
    // which calls JPanel's default constructor.

    // https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html#method_summary
    // methods inherited from class javax.swing.JComponent...
    // https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#paintComponent(java.awt.Graphics)

    @Override
    protected void paintComponent(Graphics g) {
        // "If you do not invoke super's implementation,
        // you must honor the opaque property...
        // If you do not honor the opaque property,
        // you will likely see visual artifacts."
        // It is simplest to invoke super's implementation!
        super.paintComponent(g);

        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#method_summary
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#setColor(java.awt.Color)
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html#field_summary
        g.setColor(Color.ORANGE);

        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#fillRect(int,%20int,%20int,%20int)
        // It is somewhat confusing that this method is marked as 'abstract'.
        // It is probably implemented by the operating system.
        // The most observant of you will have noticed the same above for setColor.
        g.fillRect(150, 100, 500, 200);

        // You can see that the rectangle is not positioned perfectly in the center. Why?
        // frame.setSize(800, 400) includes the title bar in the height of 400;
        // g.fillRect(150, 100, 500, 200) starts the rectangle 100 from *below* the title bar.
    }
}

class SecondGUI {
    public static void main(String[] args) {
        // Same as before...
        JFrame frame = new JFrame("My Second GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#getContentPane()
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html#method_summary
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html#add(java.awt.Component)
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
        // Canvas extends java.awt.JPanel which extends java.awt.Component
        // frame.getContentPane().add(new Canvas());

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
        // "As a conveniance 'add' and its variants, 'remove', and 'setLayout'
        // have been overridden to forward to the contentPane as necessary."

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#method_summary
        // methods inherited from class java.awt.Container...
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html#add(java.awt.Component)
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
        // Canvas extends java.awt.JPanel which extends java.awt.Component
        frame.add(new Canvas());

        // Same as before...
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}