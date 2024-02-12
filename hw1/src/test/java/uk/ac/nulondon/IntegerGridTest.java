package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class IntegerGridTest {
    IntegerGrid g = new IntegerGrid(3, 3);
    IntegerGrid f = new IntegerGrid(2, 4);

    @Test
    void testToString() {
        g.populate(2);
        Assertions.assertThat(g.toString()).isEqualTo("2 3 4\n3 4 5\n4 5 6\n");
    }

    @Test
    void testGetRowSize() {
        Assertions.assertThat(g.getRowSize()).isEqualTo(3);
        Assertions.assertThat(f.getRowSize()).isEqualTo(2);
    }

    @Test
    void testGetColumnSize() {
        Assertions.assertThat(g.getColumnSize()).isEqualTo(3);
        Assertions.assertThat(f.getColumnSize()).isEqualTo(4);
    }

    @Test
    void testDeleteRow() {
        g.populate(2);
        g.deleteRow(1);
        Assertions.assertThat(g.toString()).isEqualTo("2 3 4\n4 5 6\n");
    }
}
