package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerGridLLTest {

    MySingleLinkList<MySingleLinkList<Integer>> rows = new MySingleLinkList<>();
    MySingleLinkList<Integer> col1 = new MySingleLinkList<>();
    MySingleLinkList<Integer> col2 = new MySingleLinkList<>();
    MySingleLinkList<Integer> col3 = new MySingleLinkList<>();
    IntegerGridLL l;

    public IntegerGridLLTest() {
        col3.add(3);
        col3.add(2);
        rows.add(col3);
        col2.add(1);
        col2.add(2);
        col2.add(3);
        rows.add(col2);
        col1.add(5);
        col1.add(4);
        col1.add(3);
        col1.add(2);
        rows.add(col1);
        l = new IntegerGridLL(rows);
    }

    @Test
    void rowSizeTest() {
        Assertions.assertThat(l.getRowSize()).isEqualTo(3);
    }

    @Test
    void columnSizeTest() {
        Assertions.assertThat(l.getColumnSize(0)).isEqualTo(4);
        Assertions.assertThat(l.getColumnSize(1)).isEqualTo(3);
        Assertions.assertThat(l.getColumnSize(2)).isEqualTo(2);
    }

    @Test
    void deleteRowTest() {
        l.deleteRow(1);
        Assertions.assertThat(l.toString()).isEqualTo("2 3 4 5" + System.lineSeparator() + "2 3" + System.lineSeparator());
    }

    @Test
    void toStringTest() {
        Assertions.assertThat(l.toString()).isEqualTo("2 3 4 5" + System.lineSeparator() + "3 2 1" + System.lineSeparator() + "2 3" + System.lineSeparator());
    }
}
