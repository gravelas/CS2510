package uk.ac.nulondon;

public final class IntegerGrid {

    int row, col;
    int[][] arr;

    // Constructor that uses a row and col parameter to create a blank 2d array with those dimensions.
    public IntegerGrid(int row, int col) {
        this.row = row;
        this.col = col;
        arr = new int[row][col];
    }

    // Constructor that makes a deep copy of the passed array to store in the object.
    public IntegerGrid(int[][] arr) {
        this.arr = new int[arr.length][arr[0].length];
        for (int i = 0; i < this.arr.length; i++) {
            for (int j = 0; j < this.arr[0].length; j++) {
                this.arr[i][j] = arr[i][j];
            }
        }
        row = this.arr.length;
        col = this.arr[0].length;
    }

    // fills the objects array with values: i+j+s
    public void populate(int s) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = i+j+s;
            }
        }
    }

    // returns the amount of rows
    public int getRowSize() {
        return row;
    }

    // returns the amount of columns
    public int getColumnSize() {
        return col;
    }

    // changes the stored array to not have the row passed in the parameter.
    public void deleteRow(int r) {
        int[][] arr = new int[this.arr.length-1][this.arr[0].length];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != r) {
                arr[i] = this.arr[j];
            }
            else {
                i--;
                r=-1;
            }
            j++;
        }
        this.arr = arr;
    }

    // creates a string that contains the array in the properly formatted way.
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int[] array : this.arr) {
            for (int value : array) {
                returnString.append(value);
                returnString.append(" ");
            }
            returnString = new StringBuilder(returnString.substring(0, returnString.length() - 1));
            returnString.append("\n");
        }
        return returnString.toString();
    }
}

