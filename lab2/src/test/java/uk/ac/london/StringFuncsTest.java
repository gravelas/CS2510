package uk.ac.london;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringFuncsTest {

    StringFuncs myStringFunc = new StringFuncs();

    @Test
    void testStartsWithY1() {
        Assertions.assertThat(myStringFunc.startsWithY("Yes")).isTrue();
        Assertions.assertThat(myStringFunc.startsWithY("yes")).isTrue();
        Assertions.assertThat(myStringFunc.startsWithY("No")).isFalse();
    }

    @Test
    void testStartsWithY2() {
        Assertions.assertThat(myStringFunc.startsWithY2("goo")).isFalse();
        Assertions.assertThat(myStringFunc.startsWithY2("yoooo")).isTrue();
        Assertions.assertThat(myStringFunc.startsWithY("Yes")).isTrue();
    }

    @Test
    void testBingoWord1() {
        Assertions.assertThat(myStringFunc.bingoWord1("yoooo")).isEqualTo("Y5");
        Assertions.assertThat(myStringFunc.bingoWord1("hello")).isEqualTo("H5");
    }

    @Test
    void testBingoWord2() {
        Assertions.assertThat(myStringFunc.bingoWord2("toodles")).isEqualTo("T7");
        Assertions.assertThat(myStringFunc.bingoWord1("yoooo")).isEqualTo("Y5");
        Assertions.assertThat(myStringFunc.bingoWord1("hello")).isEqualTo("H5");
    }
}
