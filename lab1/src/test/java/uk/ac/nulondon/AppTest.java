package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class AppTest {
    App a;

    public AppTest() {
        a = new App();
    }
    @Test
    void helloTest() {
        Assertions.assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    void testStartWithY() {
        Assertions.assertThat(a.startsWithY("yes!")).isTrue();
        Assertions.assertThat(a.startsWithY("no...")).isFalse();
        Assertions.assertThat(a.startsWithY("yippee!")).isTrue();
    }

    @Test
    void testBingoWord() {
        Assertions.assertThat(a.bingoWord("bingo")).isEqualTo("B 5");
        Assertions.assertThat(a.bingoWord("Win")).isEqualTo("W 3");
    }
}
