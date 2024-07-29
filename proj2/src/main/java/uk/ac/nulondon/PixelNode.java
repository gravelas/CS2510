package uk.ac.nulondon;

import java.awt.*;
import java.util.HashMap;

public class PixelNode {
    Color c;
    int brightness;
    int energy;
    int blueScore;
    int energyScore;
    int hEnergy;
    int vEnergy;
    PixelNode bestBlueScoreAbove, bestEnergyScoreAbove;
    HashMap<Coordinate, PixelNode> grid;
    Coordinate gridLocation;
    boolean isBlued;
    boolean isReded;
    boolean isRemoved;


    public PixelNode(Color c, Coordinate gridLocation, HashMap<Coordinate, PixelNode> grid) {
        this.c = c;
        brightness = (c.getRed() + c.getGreen() + c.getBlue())/3;
        this.grid = grid;
        this.gridLocation = gridLocation;
        isBlued = false;
        isReded = false;
        isRemoved = false;
    }

    public PixelNode getTopLeft() {
        Coordinate topLeft = gridLocation.getTopLeft();
        if (grid.get(topLeft) == null) {
            return null;
        }
        while (grid.get(topLeft).isRemoved) {
            topLeft = topLeft.getRight();
            if (grid.get(topLeft) == this.getTopMiddle()) {
                return null;
            }
            if (grid.get(topLeft) == null) {
                return null;
            }
        }
        return grid.get(topLeft);
    }

    public PixelNode getTopMiddle() {
        Coordinate topMiddle = gridLocation.getTopMiddle();
        if (grid.get(topMiddle) == null) {
            return null;
        }
        if (getRight() != null) {
            while (grid.get(topMiddle).isRemoved) {
                topMiddle = topMiddle.getRight();
                if (grid.get(topMiddle) == null) {
                    return null;
                }
            }
        } else {
            while (grid.get(topMiddle).isRemoved) {
                topMiddle = topMiddle.getLeft();
                if (grid.get(topMiddle) == null) {
                    return null;
                }
            }
        }
        return grid.get(topMiddle);
    }

    public PixelNode getTopRight() {
        Coordinate topRight = gridLocation.getTopRight();
        if (grid.get(topRight) == null) {
            return null;
        }
        if (getRight() != null) {
            while(grid.get(topRight).isRemoved) {
                topRight = topRight.getRight();
                if (grid.get(topRight) == null) {
                    return null;
                }
            }
        } else {
            while (grid.get(topRight).isRemoved) {
                topRight = topRight.getLeft();
                if (grid.get(topRight) == null) {
                    return null;
                }
            }
        }

        return grid.get(topRight);
    }

    public PixelNode getLeft() {
        Coordinate left = gridLocation.getLeft();
        if (grid.get(left) == null) {
            return null;
        }
        while(grid.get(left).isRemoved) {
            left = left.getLeft();
            if (grid.get(left) == this) {
                return null;
            }
            if (grid.get(left) == null) {
                return null;
            }

        }
        return grid.get(left);
    }

    public PixelNode getRight() {
        Coordinate right = gridLocation.getRight();
        if (grid.get(right) == null) {
            return null;
        }
        while (grid.get(right).isRemoved) {
            right = right.getRight();
            if (grid.get(right) == null) {
                return null;
            }
        }
        return grid.get(right);
    }

    public PixelNode getBottomLeft() {
        Coordinate bottomLeft = gridLocation.getBottomLeft();
        if (grid.get(bottomLeft) == null) {
            return null;
        }
        while(grid.get(bottomLeft).isRemoved) {
            bottomLeft = bottomLeft.getRight();
            if (grid.get(bottomLeft) == this.getBottomMiddle()) {
                return null;
            }
            if (grid.get(bottomLeft) == null) {
                return null;
            }
        }
        return grid.get(bottomLeft);
    }

    public PixelNode getBottomMiddle() {
        Coordinate bottomMiddle = gridLocation.getBottomMiddle();
        if (grid.get(bottomMiddle) == null) {
            return null;
        }
        if (getRight() != null) {
            while (grid.get(bottomMiddle).isRemoved) {
                bottomMiddle = bottomMiddle.getRight();
                if (grid.get(bottomMiddle) == null) {
                    return null;
                }
            }
        } else {
            while (grid.get(bottomMiddle).isRemoved) {
                bottomMiddle = bottomMiddle.getLeft();
                if (grid.get(bottomMiddle) == null) {
                    return null;
                }
            }
        }
        return grid.get(bottomMiddle);
    }

    public PixelNode getBottomRight() {
        Coordinate bottomRight = gridLocation.getBottomRight();
        if (grid.get(bottomRight) == null) {
            return null;
        }
        if (getRight() != null) {
            while (grid.get(bottomRight).isRemoved) {
                bottomRight = bottomRight.getRight();
                if (grid.get(bottomRight) == null) {
                    return null;
                }
            }
        } else {
            while (grid.get(bottomRight).isRemoved) {
                bottomRight = bottomRight.getLeft();
                if (grid.get(bottomRight) == null) {
                    return null;
                }
            }
        }
        return grid.get(bottomRight);
    }

    public void setEnergyOfNode() {
        int topLeftBrightness;
        int topMiddleBrightness;
        int topRightBrightness;
        int bottomLeftBrightness;
        int bottomMiddleBrightness;
        int bottomRightBrightness;
        int leftBrightness;
        int rightBrightness;
        if (getTopLeft() == null) {
            topLeftBrightness = brightness;
        } else {
            topLeftBrightness = getTopLeft().getBrightness();
        }
        if (getTopMiddle() == null) {
            topMiddleBrightness = brightness;
        } else {
            topMiddleBrightness = getTopMiddle().getBrightness();
        }
        if (getTopRight() == null) {
            topRightBrightness = brightness;
        } else {
            topRightBrightness = getTopRight().getBrightness();
        }
        if (getBottomLeft() == null) {
            bottomLeftBrightness = brightness;
        } else {
            bottomLeftBrightness = getBottomLeft().getBrightness();
        }
        if (getBottomMiddle() == null) {
            bottomMiddleBrightness = brightness;
        } else {
            bottomMiddleBrightness = getBottomMiddle().getBrightness();
        }
        if (getBottomRight() == null) {
            bottomRightBrightness = brightness;
        } else {
            bottomRightBrightness = getBottomRight().getBrightness();
        }
        if (getLeft() == null) {
            leftBrightness = brightness;
        } else {
            leftBrightness = getLeft().getBrightness();
        }
        if (getRight() == null) {
            rightBrightness = brightness;
        } else {
            rightBrightness = getRight().getBrightness();
        }
        hEnergy = topLeftBrightness + 2 * leftBrightness + bottomLeftBrightness - (
                topRightBrightness + 2 * rightBrightness + bottomRightBrightness);
        vEnergy = topLeftBrightness + 2 * topMiddleBrightness + topRightBrightness - (
                bottomLeftBrightness + 2 * bottomMiddleBrightness + bottomRightBrightness);
        this.setEnergy((int) (Math.sqrt(Math.pow(hEnergy, 2) + Math.pow(vEnergy, 2))));
    }

    public int calculateBlueScore() {
        if (this.gridLocation.y == 0) {
            this.blueScore = this.getBlue();
            return this.getBlue();
        }
        int blueScoreLeft;
        int blueScoreRight;
        int blueScoreMiddle;
        if (getTopLeft() != null) {
            blueScoreLeft = getTopLeft().getBlueScore() + this.getBlue();
        }
        else {
            blueScoreLeft = Integer.MIN_VALUE;
        }
        if (getTopMiddle() != null) {
            blueScoreMiddle = getTopMiddle().getBlueScore() + this.getBlue();
        }
        else {
            blueScoreMiddle = Integer.MIN_VALUE;
        }
        if (getTopRight() != null) {
            blueScoreRight = getTopRight().getBlueScore() + this.getBlue();
        }
        else {
            blueScoreRight = Integer.MIN_VALUE;
        }
        this.blueScore = Math.max(blueScoreLeft, Math.max(blueScoreRight, blueScoreMiddle));
        if (blueScoreRight == blueScore) {bestBlueScoreAbove = getTopRight();};
        if (blueScoreMiddle == blueScore) {bestBlueScoreAbove = getTopMiddle();};
        if (blueScoreLeft == blueScore) {bestBlueScoreAbove = getTopLeft();};

        return blueScore;
    }

    public int getBlueScore() {
        return this.blueScore;
    }

    public PixelNode maxBlueScore() {
        return this.bestBlueScoreAbove;
    }

    public int calculateEnergyScore() {
        if (this.gridLocation.y == 0) {
            this.energyScore = this.getEnergy();
            return this.energy;
        }
        int energyScoreLeft;
        int energyScoreMiddle;
        int energyScoreRight;
        if (getTopLeft() != null) {
            energyScoreLeft = getEnergy() + getTopLeft().getEnergyScore();
        }
        else {
            energyScoreLeft = Integer.MAX_VALUE;
        }
        if (getTopMiddle() != null) {
            energyScoreMiddle = getEnergy() + getTopMiddle().getEnergyScore();
        }
        else {
            energyScoreMiddle = Integer.MAX_VALUE;
        }
        if (getTopRight() != null) {
            energyScoreRight = getEnergy() + getTopRight().getEnergyScore();
        }
        else {
            energyScoreRight = Integer.MAX_VALUE;
        }
        this.energyScore = Math.min(energyScoreLeft, Math.min(energyScoreMiddle, energyScoreRight));
        if (energyScore == energyScoreRight) {bestEnergyScoreAbove = getTopRight();}
        if (energyScore == energyScoreMiddle) {bestEnergyScoreAbove = getTopMiddle();}
        if (energyScore == energyScoreLeft) {bestEnergyScoreAbove = getTopLeft();}

        return energyScore;
    }

    public int getEnergyScore() {
        return this.energyScore;
    }

    public PixelNode minEnergyScore() {
        return bestEnergyScoreAbove;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getBlue() {
        return c.getBlue();
    }

    public int getColor() {
        return c.getRGB();
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public void setBlued(boolean b) {
        isBlued = b;
    }

    public void setReded(boolean r) {
        isReded = r;
    }

    public void setRemoved(boolean r) {
        isRemoved = r;
    }
}
