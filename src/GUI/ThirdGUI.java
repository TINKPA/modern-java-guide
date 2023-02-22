import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

class Canvas extends JPanel {
    // Same as before...
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.ORANGE);
        g.fillRect(150, 100, 500, 200);
    }
}

class ThirdGUI {
    public static void main(String[] args) {
        // Same as before...
        JFrame frame = new JFrame("My Third GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Canvas());

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#getContentPane()
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Container.html#method_summary
        // methods inherited from class java.awt.Component...
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Component.html#setPreferredSize(java.awt.Dimension)
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Dimension.html
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Dimension.html#constructor_summary
        frame.getContentPane().setPreferredSize(new Dimension(800, 400));

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#method_summary
        // methods inherited from class java.awt.Window...
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Window.html#pack()
        frame.pack();

        // The rectangle is positioned perfectly in the center because
        // frame.getContentPane().setPreferredSize(new Dimension(800, 400))
        // does *not* include the title bar in the height of 400.

        // Same as before...
        frame.setVisible(true);
    }
}