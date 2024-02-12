package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayListFuncsTest {

    ArrayListFuncs recordsTest1 = new ArrayListFuncs(ArrayListFuncs.readFile("src/main/resources/ReportTest1.csv"));
    ArrayListFuncs recordsTest2 = new ArrayListFuncs(ArrayListFuncs.readFile("src/main/resources/ReportTest2.csv"));


    @Test
    void testSumEvents() {
        Assertions.assertThat(recordsTest1.sumEvents()).isEqualTo(7);
        Assertions.assertThat(recordsTest2.sumEvents()).isEqualTo(5);
    }

    @Test
    void testMaxMonth() {
        Assertions.assertThat(recordsTest1.maxMonth()).isEqualTo(1);
        Assertions.assertThat(recordsTest2.maxMonth()).isEqualTo(3);
    }

    @Test
    void testNightHasMore() {
        Assertions.assertThat(recordsTest1.nightHasMore()).isTrue();
        Assertions.assertThat(recordsTest2.nightHasMore()).isFalse();
    }
}
