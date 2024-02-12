package uk.ac.nulondon;

public final class App {
    public App() {

    }

    public boolean startsWithY(String s) {
        return "Y".equalsIgnoreCase(s.substring(0, 1));
    }

    public String bingoWord(String word) {
        return word.substring(0, 1).toUpperCase() + " " + word.length();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
