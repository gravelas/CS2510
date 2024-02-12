package uk.ac.nulondon;

import java.util.List;

public class MyList {

    private int[] list;
    public MyList(int[] arr) {
        list = new int[arr.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = arr[i];
        }
    }
}
