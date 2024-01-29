import java.util.*;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 1.25.24
 * Assignment #1
 * Description: This program is a practice on manipulating arrays. First, it will create to arrays of random length.
 * Then, it will fill the arrays with random numbers before sorting them. The two array will then be written to a
 * file in numerical order. Lastly, the program will read from the file to create a third array which will have
 * all values in numerical order and will remove any duplicates.
 */
import java.io.*;

public class DouglasJoshuaAssignment1 {

    public static void main(String[] args) throws IOException {
        
        // Size 1 and two determine the length of the arrays
        int size1 = 1 + (int)(Math.random() * 10);
        int size2 = 1 + (int)(Math.random() * 10);

        System.out.println("The length of array 1 is " + size1);
        System.out.println("The length of array 2 is " + size2);
        System.out.println("");

        int[] size1Array = generateNumbers(size1);
        int[] size2Array = generateNumbers(size2);

        // Sort and print array data for a baseline
        Arrays.sort(size1Array);
        Arrays.sort(size2Array);

        // Give the user baseline info
        System.out.println("First array with " + size1 + " random values");
        System.out.println("-------------------------------------------");
        printArrayData(size1Array);
        System.out.println("");

        System.out.println("Second array with " + size2 + " random values");
        System.out.println("-------------------------------------------");
        printArrayData(size2Array);
        System.out.println("");

        // File creation and writer instantiation
        File arrayData = new File("assignment1data.txt");
        PrintWriter resultingData = new PrintWriter(arrayData);

        System.out.println("Beggining file writing");
        System.out.println("----------------------");

        writeDataToFile(size1Array, size2Array, resultingData);
        resultingData.close(); // Close the writer to ensure data is saved to file properly

        Scanner scanner = new Scanner(arrayData); // Used to read file data into new array

        // New array with no duplicate numbers created by calling on removeDuplicates
        int[] noDuplicates = removeDuplicates(size1Array, size2Array, scanner);
        System.out.println("");
        System.out.println("Array with no duplicate values");
        System.out.println("------------------------------");
        printArrayData(noDuplicates);
    }

    // Fill size1array and size2array with random values between 1-25
    public static int[] generateNumbers(int size) {

        int[] randomNumArray = new int[size]; 

        for(int i = 0; i < randomNumArray.length; i++) {

            // Math.random() creates a random floating double between 0.0(inclusive) and 1.0(exclusive)
            // * 25 scales the random number from 0.0(inclusive) to 25.0(exclusive)
            // Lastly, (int) removes the decimal value, and 1+ changes range to 1(inclusive) to 25(inclusive)
            int temp = 1 + (int)(Math.random() * 25);

            randomNumArray[i] = temp;
        }

        return randomNumArray;
    }

    // Simple for loop to print all values in an array
    public static void printArrayData(int[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    } 

    // Write sorted data from two arrays into a file
    public static void writeDataToFile(int[] array1, int[] array2, PrintWriter writer) {

        // Counters to track values being compared in each array
        int i = 0;
        int z = 0;

        while (i < array1.length && z < array2.length) { // "While there are still numbers to be written"

            if (array1[i] < array2[z]) {
                System.out.println("Writing " + array1[i]);
                writer.println(array1[i]);
                i++;
            }
            else if (array2[z] < array1[i]) {
                System.out.println("Writing " + array2[z]);
                writer.println(array2[z]);
                z++;
            }
            else {

                // If the values are equal to each other, write one of them
                if (array1[i] <= array2[z]) {
                    System.out.println("Writing " + array1[i]);
                    writer.println(array1[i]);
                    i++;
                }
                
                if (array2[z] <= array1[i - 1]) {
                    System.out.println("Writing " + array2[z]);
                    writer.println(array2[z]);
                    z++;
                }
            }
        }

        // If there are any values left to write, finish
        while (i < array1.length) {
            System.out.println("Writing " + array1[i]);
            writer.println(array1[i]);
            i++;
        }

        while (z < array2.length) {
            System.out.println("Writing " + array2[z]);
            writer.println(array2[z]);
            z++;
        }

        writer.close(); // Close to ensure data is saved to file properly
    }

    // Remove all duplicate numbers from file and sort in an array
    public static int[] removeDuplicates(int[] array1, int[] array2, Scanner scanner) throws IOException {


        int previousLine = 0;
        int i = 0; // Used to itterate through noDuplicates
        int zeroCounter = 0; // Used to calculate proper length of final array
        int[] noDuplicates = new int[array1.length + array2.length];
        

        do { // Do-While was chosen since the first line has no value to compare to

            int currentLine = scanner.nextInt();

            if (currentLine != previousLine) {

                noDuplicates[i] = currentLine;
                previousLine = currentLine;
                i++;
            }

        } while (scanner.hasNextInt()); // As long as the scanner hasn't reach the end of the file

        scanner.close(); // Close to ensure no data leakage

        // Counting number of deleted values
        for (int z = 0; z < noDuplicates.length; z++) {

            if (noDuplicates[z] == 0) {
                zeroCounter++;
            }
        }

        // Final array with no duplicates and no zeros
        int[] noDuplicatesNoZeros = new int[noDuplicates.length - zeroCounter];

        // Fill the final array with proper values
        int index = 0;
        for (int z = 0; z < noDuplicatesNoZeros.length; z++) {

            if (noDuplicates[z] != 0) {
                noDuplicatesNoZeros[index] = noDuplicates[z];
                index++;
            }
        }

        return noDuplicatesNoZeros;
    }
}
