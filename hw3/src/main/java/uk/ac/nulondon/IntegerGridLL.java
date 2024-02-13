package uk.ac.nulondon;

public class IntegerGridLL {
    MySingleLinkList<MySingleLinkList<Integer>> l;
    public IntegerGridLL() {
        l = new MySingleLinkList<>();
    }
    public IntegerGridLL(MySingleLinkList<MySingleLinkList<Integer>> listToCopy) {
        l = new MySingleLinkList<>();
        for (int row = 0; row < listToCopy.length(); row++) {
            l.add(new MySingleLinkList<Integer>());
            for (int col = 0; col < listToCopy.getAt(row).length(); col++) {
                l.getAt(0).add(listToCopy.getAt(row).getAt(col));
            }
            l.getAt(0).reverse();
        }
        l.reverse();
    }

    /**
     * gets the number of rows
     * @return number of rows in 2d linked list
     */
    public int getRowSize() {
        return l.length();
    }

    /**
     * returns the size of the column from the provided row
     * @param r selected row
     * @return number of columns in selected row
     */
    public int getColumnSize(int r) {
        return l.getAt(r).length();
    }

    /**
     * deletes the specified row
     * @param r row to be deleted
     */
    public void deleteRow(int r) {
        Node<MySingleLinkList<Integer>> iter = l.head;
        for (int currentRow = 0; currentRow < r-1; currentRow++) {
            iter = iter.next;
        }
        iter.next = iter.next.next;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int row = 0; row < getRowSize(); row++) {
            for (int col = 0; col < getColumnSize(row); col++) {
                returnString.append(l.getAt(row).getAt(col));
                if (col != getColumnSize(row)-1) {
                    returnString.append(" ");
                }
            }
            returnString.append(System.lineSeparator());
        }
        return String.valueOf(returnString);
    }
}
