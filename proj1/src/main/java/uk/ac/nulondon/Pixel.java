package uk.ac.nulondon;

import java.awt.*;

public class Pixel {
    int r,g,b,a;

    public Pixel(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getRed() {
        return r;
    }

    public int getBlue() {
        return b;
    }

    public int getGreen() {
        return g;
    }

    public int getAlpha() {
        return a;
    }

    public Color getColor() {
        return new Color(r, g, b, a);
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel copy() {
        return new Pixel(r,g,b,a);
    }
}
