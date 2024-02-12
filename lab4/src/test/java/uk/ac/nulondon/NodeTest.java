package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class NodeTest {
    @Test
    void constructorTest() {
        Node<String> myNode = new Node<>("hello");
        Assertions.assertThat(myNode.element).isEqualTo("hello");
        Assertions.assertThat(myNode.prev).isEqualTo(null);
        Assertions.assertThat(myNode.next).isEqualTo(null);
    }
}
