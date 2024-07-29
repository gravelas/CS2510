package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageData {
    PixelNode topLeftCorner;
    PixelNode bottomRightCorner;
    HashMap<Coordinate, PixelNode> imageMap;
    Deque<Deque<PixelNode>> queue;
    Deque<Deque<PixelNode>> undoStack;
    int width, height, realWidth, tempNumber;

    public ImageData(String filePath) throws IOException {
        imageMap = new HashMap<>();
        BufferedImage image = ImageIO.read(new File(filePath));
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Coordinate gridLocation = new Coordinate(x, y);
                imageMap.put(gridLocation, new PixelNode(new Color(image.getRGB(x, y), true), gridLocation, imageMap));
            }
        }
        width = image.getWidth();
        realWidth = width;
        height = image.getHeight();
        topLeftCorner = imageMap.get(new Coordinate(0, 0));
        bottomRightCorner = imageMap.get(new Coordinate(width-1, height-1));
        updateAll();
        queue = new ArrayDeque<>();
        undoStack = new ArrayDeque<>();
        tempNumber = 0;
    }

    public void updateAll() {
        long startTime = System.nanoTime();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imageMap.get(new Coordinate(x, y)).setEnergyOfNode();
                imageMap.get(new Coordinate(x, y)).calculateBlueScore();
                imageMap.get(new Coordinate(x, y)).calculateEnergyScore();
            }
        }
        long endTime = System.nanoTime() - startTime;
        System.out.println(endTime/1000000000.0 + "s");
    }

    public void debug() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!imageMap.get(new Coordinate(x, y)).isRemoved) {
                    StringBuilder score = new StringBuilder(String.valueOf(imageMap.get(new Coordinate(x, y)).energy));
                    while (score.length() < 3) {
                        score.append(" ");
                    }
                    System.out.print(score + ", ");
                }
            }
            System.out.println();
        }
    }

    public void highlightBluestSeam() {
        if (queue.isEmpty() && realWidth > 1) {
            PixelNode parentNode = imageMap.get(new Coordinate(0, height - 1));
            Deque<PixelNode> bestSeam = new ArrayDeque<>();
            int bestBlueScore = Integer.MIN_VALUE;
            PixelNode bestNode = parentNode;
            while (parentNode != null) {
                while (parentNode.isRemoved) {
                    parentNode = parentNode.getRight();
                }
                if (parentNode.getBlueScore() > bestBlueScore) {
                    bestNode = parentNode;
                    bestBlueScore = parentNode.getBlueScore();
                }
                parentNode = parentNode.getRight();
            }
            while (bestNode != null) {
                bestNode.setBlued(true);
                bestSeam.add(bestNode);
                bestNode = bestNode.maxBlueScore();

            }
            queue.add(bestSeam);
        }
    }

    public void highlightLowestEnergySeam() {
        if (queue.isEmpty() && realWidth > 1) {
            PixelNode parentNode = imageMap.get(new Coordinate(0, height - 1));
            Deque<PixelNode> bestSeam = new ArrayDeque<>();
            int bestEnergyScore = Integer.MAX_VALUE;
            PixelNode bestNode = parentNode;
            while (parentNode != null) {
                while (parentNode.isRemoved) {
                    parentNode = parentNode.getRight();
                }
                if (parentNode.getEnergyScore() < bestEnergyScore) {
                    bestNode = parentNode;
                    bestEnergyScore = parentNode.getEnergyScore();
                }
                parentNode = parentNode.getRight();
            }
            while (bestNode != null) {
                bestNode.setReded(true);
                bestSeam.add(bestNode);
                bestNode = bestNode.minEnergyScore();

            }
            queue.add(bestSeam);
        }
    }

    public BufferedImage getBufferedImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            int x = 0;
            PixelNode currentNode = imageMap.get(new Coordinate(0,y));
            if (currentNode.isRemoved) {
                currentNode.getRight();
                x--;
            }
            while(currentNode != null) {
                if (x >= 0) {
                    if (currentNode.isBlued) {
                        image.setRGB(x, y, Color.BLUE.getRGB());
                    } else if (currentNode.isReded) {
                        image.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        image.setRGB(x, y, currentNode.getColor());
                    }
                }
                currentNode = currentNode.getRight();
                x++;
            }
        }
        return image;
    }

    public void saveImage(String fileName) throws IOException {
        ImageIO.write(getBufferedImage(), "png", new File(fileName));
    }

    public void saveImage() throws IOException {
        ImageIO.write(getBufferedImage(), "png", new File("src/main/resources/temp/" + tempNumber + ".png"));
        tempNumber++;
    }

    public void removeSeam() {
        if (!queue.isEmpty()) {
            undoStack.push(queue.peekLast());
            for (PixelNode currentNode : queue.removeLast()) {
                currentNode.setReded(false);
                currentNode.setBlued(false);
                currentNode.setRemoved(true);
            }
            updateAll();
            realWidth--;
        }
    }

    public void insertSeam() {
        if (!undoStack.isEmpty() && queue.isEmpty()) {
            for (PixelNode currentNode : undoStack.pop()) {
                currentNode.setRemoved(false);
            }
            updateAll();
            realWidth++;
        }
    }
}


