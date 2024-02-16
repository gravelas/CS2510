package uk.ac.nulondon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class DisplayPanel extends JPanel implements KeyListener {

    JLabel imageLabel;
    JFrame f;
    ComputedPixelImage image;

    public DisplayPanel(JFrame f, ComputedPixelImage image) {
        imageLabel = new JLabel("");
        this.image = image;
        this.f = f;
        imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
        this.setBounds(0, 0, image.getWidth(), image.getHeight());
        add(imageLabel);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    image.saveImage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'b') {
            image.highlightBluest();
            imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
        }
        if (e.getKeyChar() == 'u') {
            image.undo();
            imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));

        }
        if (e.getKeyChar() == 'd') {
            image.confirmOperations();
            imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
        }
        if (e.getKeyChar() == 'r') {
            image.highlightRandom();
            imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
        }
    }
}
