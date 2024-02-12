package uk.ac.nulondon;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArrayListFuncs {
    private final List<int[]> records;

    public ArrayListFuncs(List<int[]> records) {
        this.records = records;
    }

    // reads a formatted csv file with the given file path and return s list of arrays.
    public static List<int[]> readFile(String fileName) {
        try (FileReader in = new FileReader(fileName);
             CSVParser parser = CSVFormat.DEFAULT.parse(in)) {
            return parser.stream().skip(1)
                    .map(CSVRecord::values)
                    .map(r -> Arrays.stream(r).mapToInt(Integer::parseInt).toArray())
                    .toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // returns the number of recorded events from records
    public int sumEvents() {
        int sum = 0;
        for (int[] record: records) {
            sum += record[3];
        }
        return sum;
    }

    // finds the month with the most records and returns it as an int.
    public int maxMonth() {
        int[] months = new int[12];
        for (int[] record: records) {
            months[record[0]-1] += record[3];
        }
        int bigMonth = 0;
        for (int i = 1; i < months.length; i++) {
            if (months[i] > months[bigMonth]) {
                bigMonth = i;
            }
        }
        return bigMonth+1;
    }

    // returns true if there are more records made within the nighttime hours, else returns false.
    public boolean nightHasMore() {
        int[] dayNight = new int[2];
        for (int[] record: records) {
            if (record[2] >= 6 && record[2] <= 19) {
                dayNight[0]+= record[3];
            }
            else {
                dayNight[1] += record[3];
            }
        }
        return dayNight[1]>dayNight[0];
    }

    // uses all the methods in this class with the provided csv file and prints them formatted.
    public static void main(String[] args) {
        ArrayListFuncs listOfRecords = new ArrayListFuncs(readFile("src/main/resources/Report2014.csv"));
        System.out.println("Total number of events: " + listOfRecords.sumEvents());
        System.out.println("Month with the most occurrences: " + listOfRecords.maxMonth());
        System.out.println("Night has more events: " + listOfRecords.nightHasMore());
    }

}
