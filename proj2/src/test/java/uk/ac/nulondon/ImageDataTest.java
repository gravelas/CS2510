package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageDataTest {

    @Test
    public void testFindBluestSeam() {
        ImageData image;
        try {image = new ImageData("src/main/resources/snowman.png"); }
        catch(Exception e) {throw new RuntimeException();}
    }
}
