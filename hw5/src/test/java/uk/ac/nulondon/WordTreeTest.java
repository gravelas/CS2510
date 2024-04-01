package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class WordTreeTest {
    @Test
    void toStringTest() {
        WordTree tree = new WordTree();
        tree.insert("cat");
        tree.insert("dog");
        tree.insert("car");
        System.out.println(tree);
    }
}
