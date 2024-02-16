package uk.ac.nulondon;

import java.util.LinkedList;

public class ComputedPixelImageStacker {
    Pixel[][] pixels;
    int width;
    int height;
    LinkedList<String> operations;
    public ComputedPixelImageStacker(Pixel[][] pixels, int width, int height, LinkedList<String> operations) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.operations = operations;

    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LinkedList<String> getOperations() {
        return operations;
    }
}
