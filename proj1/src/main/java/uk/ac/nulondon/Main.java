package uk.ac.nulondon;

import javax.swing.*;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ComputedPixelImage image = new ComputedPixelImage();
        try {
            image.fromFileImage("src/main/resources/lobster.png");
        } catch (Exception e) {
            System.out.println("Image not loaded");
        }

        JFrame frame = new JFrame();
        frame.setContentPane(new DisplayPanel(frame, image));
        frame.setVisible(true);
        frame.pack();
    }
}
