package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class ComputedPixelImage {

    Pixel[][] pixels;
    Stack<ComputedPixelImageStacker> previousIter;
    int width;
    int height;

    LinkedList<String> operations;

    public ComputedPixelImage() {
        pixels = new Pixel[0][0];
        width = 0;
        height = 0;
        operations = new LinkedList<>();
        previousIter = new Stack<>();
    }

    public void fromFileImage(String filePath) throws IOException {
        File imageFile = new File(filePath);
        BufferedImage image = ImageIO.read(imageFile);
        pixels = new Pixel[image.getWidth()][image.getHeight()];
        width = image.getWidth();
        height = image.getHeight();
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                Color pixel = new Color(image.getRGB(row, col), true);
                pixels[row][col] = new Pixel(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), pixel.getAlpha());
            }
        }
    }

    public void confirmOperations() {
        while (!operations.isEmpty()) {
            Pixel[][] copy = pixelsDeepCopy();
            previousIter.push(new ComputedPixelImageStacker(pixelsDeepCopy(), width, height, operationsDeepCopy()));
            int toRemove = Integer.parseInt(operations.pop().substring(1));
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    if (row != toRemove) {
                        if (row > toRemove) {
                            pixels[row-1][col] = copy[row][col];
                        }
                        if (row < toRemove) {
                            pixels[row][col] = copy[row][col];
                        }
                    }
                }
            }
            width--;
        }
    }

    public void undo() {
        if (!previousIter.isEmpty()) {
            pixels = previousIter.peek().getPixels();
            width = previousIter.peek().getWidth();
            height = previousIter.peek().getHeight();
            operations = previousIter.pop().getOperations();
        }
    }

    private int bluestColumn() {
        int[] blueOfColumn = new int[pixels.length];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                blueOfColumn[row] += pixels[row][col].getBlue();
            }
        }
        int maxBlueCol = 0;
        for (int i = 1; i < blueOfColumn.length; i++) {
            if (blueOfColumn[maxBlueCol] < blueOfColumn[i]) {
                maxBlueCol = i;
            }
        }
        return maxBlueCol;
    }

    private Pixel[][] pixelsDeepCopy() {
        Pixel[][] copyPixels = new Pixel[width][height];
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                copyPixels[row][col] = pixels[row][col].copy();
            }
        }
        return copyPixels;
    }

    private LinkedList<String> operationsDeepCopy() {
        LinkedList<String> copyOperations = new LinkedList<>();
        for (int i = 0; i < operations.size(); i++) {
            copyOperations.addLast(operations.get(i));
        }
        return copyOperations;
    }

    public void highlightBluest() {
        if (operations.isEmpty()) {
            previousIter.push(new ComputedPixelImageStacker(pixelsDeepCopy(), width, height, operationsDeepCopy()));
            for (int row = 0; row < pixels[bluestColumn()].length; row++) {
                pixels[bluestColumn()][row].setColor(0, 0, 255);
            }
            operations.add("b" + bluestColumn());
        }
    }

    public void highlightRandom() {
        if (operations.isEmpty()) {
            previousIter.push(new ComputedPixelImageStacker(pixelsDeepCopy(), width, height, operationsDeepCopy()));
            int randomCollumn = (int)(Math.random()*width);
            for (int row = 0; row < pixels[randomCollumn].length; row++) {
                pixels[randomCollumn][row].setColor(255, 0, 0);
            }
            operations.add("r" + randomCollumn);
        }
    }

    public Color getColorAt(int x, int y) {
        return new Color(pixels[x][y].getRed(), pixels[x][y].getGreen(), pixels[x][y].getBlue());
    }

    public BufferedImage getBufferedImage() {
        BufferedImage returnImage = new BufferedImage(width, height, TYPE_INT_ARGB);
        for (int row = 0; row < returnImage.getWidth(); row++) {
            for (int col = 0; col < returnImage.getHeight(); col++) {
                returnImage.setRGB(row, col, pixels[row][col].getColor().getRGB());
            }
        }
        return returnImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void saveImage() throws IOException {
        File newFile = new File("src/main/resources/newImage.png");
        ImageIO.write(getBufferedImage(), "png", newFile);
    }
}
