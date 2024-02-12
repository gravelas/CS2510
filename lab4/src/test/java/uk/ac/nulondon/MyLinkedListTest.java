package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {
    MyLinkedList<String> testList = new MyLinkedList<>();

    @Test
    void testAddFirstWithGetFirst() {
        testList.addFirst("hello");
        Assertions.assertThat(testList.getFirst()).isEqualTo("hello");
        testList.addFirst(null);
        Assertions.assertThat(testList.getFirst()).isEqualTo("hello");
    }

    @Test
    void testAddLastWithGetLast() {
        testList.addLast("goodbye");
        Assertions.assertThat(testList.getLast()).isEqualTo("goodbye");
        testList.addLast(null);
        Assertions.assertThat(testList.getLast()).isEqualTo("goodbye");
    }



    @Test
    void testToString() {
        testList.addFirst("hello");
        testList.addLast("goodbye");
        testList.addFirst("hola");
        testList.addLast("byebye");
        Assertions.assertThat(testList.toString()).isEqualTo("hola hello goodbye byebye");
    }

    @Test
    void testRemoveFirst() {
        testList.addFirst("hello");
        testList.removeFirst();
        Assertions.assertThat(testList.getFirst()).isEqualTo(null);
    }

    @Test
    void testRemoveLast() {
        testList.addLast("hello");
        testList.removeLast();
        testList.addLast("hello2");
        System.out.println(testList);

        System.out.println(testList);
        Assertions.assertThat(testList.getLast()).isEqualTo(null);
        System.out.println(testList.toString());
        testList.addLast("t");
        testList.addLast("q");
        Assertions.assertThat(testList.removeLast()).isEqualTo("q");
        Assertions.assertThat(testList.getLast()).isEqualTo("t");
    }

}
