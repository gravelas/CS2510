package uk.ac.nulondon;

public class Coordinate {
    int x;
    int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getTopLeft() {
        return new Coordinate(x-1, y-1);
    }

    public Coordinate getTopMiddle() {
        return new Coordinate(x, y-1);
    }

    public Coordinate getTopRight() {
        return new Coordinate(x+1, y-1);
    }

    public Coordinate getLeft() {
        return new Coordinate(x-1, y);
    }

    public Coordinate getRight() {
        return new Coordinate(x+1, y);
    }

    public Coordinate getBottomLeft() {
        return new Coordinate(x-1, y+1);
    }

    public Coordinate getBottomMiddle() {
        return new Coordinate(x, y+1);
    }

    public Coordinate getBottomRight() {
        return new Coordinate(x+1, y+1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate c = (Coordinate) obj;
            return x == c.x && y == c.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return (x + ", " + y);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
