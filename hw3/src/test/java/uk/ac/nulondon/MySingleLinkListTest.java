package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class MySingleLinkListTest {

    @Test
    void getAtTest() {
        MySingleLinkList<String> l =  new MySingleLinkList<>();
        l.add("hi");
        l.add("bye");
        l.add("cool");
        Assertions.assertThat(l.getAt(2)).isEqualTo("hi");
        Assertions.assertThat(l.getAt(0)).isEqualTo("cool");
        Assertions.assertThat(l.getAt(1)).isEqualTo("bye");
        Assertions.assertThat(l.getAt(100)).isEqualTo(null);
    }

    @Test
    void removeAtTest() {
        MySingleLinkList<String> l =  new MySingleLinkList<>();
        l.add("hi");
        l.add("bye");
        l.add("cool");
        Assertions.assertThat(l.removeAt(0)).isTrue();
        Assertions.assertThatIndexOutOfBoundsException().isThrownBy(() -> l.removeAt(200));
        Assertions.assertThat(l.toString()).isEqualTo("bye hi" + System.lineSeparator());
    }

    @Test
    void toStringTest() {
        MySingleLinkList<String> l =  new MySingleLinkList<>();
        l.add("hi");
        l.add("bye");
        l.add("cool");
        Assertions.assertThat(l.toString()).isEqualTo("cool bye hi" + System.lineSeparator());
        MySingleLinkList<String> p =  new MySingleLinkList<>();
        p.add("toodles");
        p.add("goodbye");
        p.add("hola");
        Assertions.assertThat(p.toString()).isEqualTo("hola goodbye toodles" + System.lineSeparator());
    }



}
