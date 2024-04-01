package uk.ac.nulondon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class MySingleLinkListTest {

    MySingleLinkList<String> emptyList = new MySingleLinkList<>();
    MySingleLinkList<Integer> oddList = new MySingleLinkList<>();
    MySingleLinkList<Integer> evenList = new MySingleLinkList<>();
    MySingleLinkList<String> racecarList = new MySingleLinkList<>();
    MySingleLinkList<String> fundiesList = new MySingleLinkList<>();
    MySingleLinkList<String> sunTuThSat = new MySingleLinkList<>();
    MySingleLinkList<String> monWedFri = new MySingleLinkList<>();

    @BeforeEach
    public void setUp() {
        // list of odd integers
        oddList.add(9);
        oddList.add(7);
        oddList.add(5);
        oddList.add(3);
        oddList.add(1);

        // list of even integers
        evenList.add(10);
        evenList.add(8);
        evenList.add(6);
        evenList.add(4);
        evenList.add(2);

        // racecar
        racecarList.add("r");
        racecarList.add("a");
        racecarList.add("c");
        racecarList.add("e");
        racecarList.add("c");
        racecarList.add("a");
        racecarList.add("r");

        // fundies
        fundiesList.add("s");
        fundiesList.add("e");
        fundiesList.add("i");
        fundiesList.add("d");
        fundiesList.add("n");
        fundiesList.add("u");
        fundiesList.add("f");
    }

    @Test
    void testIndexOf() {
        // Beginning of the list
        Assertions.assertThat(oddList.indexOf(1)).isEqualTo(0);

        // End of the list
        Assertions.assertThat(evenList.indexOf(10)).isEqualTo(4);

        // Middle of the list
        Assertions.assertThat(racecarList.indexOf("c")).isEqualTo(2);

        // Not in the list (should return last index)
        Assertions.assertThat(fundiesList.indexOf("z")).isEqualTo(6);
    }

    @Test
    void testInterleave() {
        // Sunday, Tuesday, Thursday, Saturday
        sunTuThSat.add("Sunday");
        sunTuThSat.add("Saturday");
        sunTuThSat.add("Thursday");
        sunTuThSat.add("Tuesday");

        // Monday, Wednesday, Friday
        monWedFri.add("Friday");
        monWedFri.add("Wednesday");
        monWedFri.add("Monday");

        // Days of the week, in reverse order
        String weekExpected = "Sunday Saturday Friday Thursday Wednesday Tuesday Monday";
        Assertions.assertThat(monWedFri.interleave(sunTuThSat.getHead()).toString()).isEqualTo(weekExpected);

        String intExpected = "10 9 8 7 6 5 4 3 2 1";
        Assertions.assertThat(oddList.interleave(evenList.getHead()).toString()).isEqualTo(intExpected);

        // Interleave with an empty list
        Assertions.assertThat(racecarList.interleave(emptyList.getHead()).toString()).isEqualTo(racecarList.toString());
    }

    @Test
    void testShiftByOne() {
        // Original: 1 3 5 7 9
        oddList.shiftByOne();
        Assertions.assertThat(oddList.toString()).isEqualTo("9 1 3 5 7");

        // Original: 2 4 6 8 10
        evenList.shiftByOne();
        Assertions.assertThat(evenList.toString()).isEqualTo("10 2 4 6 8");

        // Shift even list one more time
        evenList.shiftByOne();
        Assertions.assertThat(evenList.toString()).isEqualTo("8 10 2 4 6");
    }

    @Test
    void testPalindromeTrue() {
        Assertions.assertThat(racecarList.palindrome()).isTrue();

        // Empty list
        Assertions.assertThat(emptyList.palindrome()).isTrue();

        // Single item
        emptyList.add("one");
        Assertions.assertThat(emptyList.palindrome()).isTrue();
    }

    @Test
    void testPalindromeFalse() {
        Assertions.assertThat(oddList.palindrome()).isFalse();
        Assertions.assertThat(evenList.palindrome()).isFalse();
        Assertions.assertThat(fundiesList.palindrome()).isFalse();
    }

}
