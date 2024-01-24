import java.util.*;
import java.io.*;
import java.io.File;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 1.25.24
 * Assignment #1
 * Description: 
 */

public class DouglasJoshuaAssignment1 {

    public static void main(String[] args) throws IOException {
        
        int size1 = 1 + (int)(Math.random() * 10);
        int size2 = 1 + (int)(Math.random() * 10);

        System.out.println("The length of array 1 is " + size1);
        System.out.println("The length of array 2 is " + size2);

        int[] size1Array = generateNumbers(size1);
        int[] size2Array = generateNumbers(size2);
        Arrays.sort(size1Array);
        Arrays.sort(size2Array);
        printArrayData(size1Array);
        printArrayData(size2Array);

        
        File arrayData = new File("assignment1data.txt");
        PrintWriter resultingData = new PrintWriter(arrayData);

        arraySorter(size1Array);
        arraySorter(size2Array);

        System.out.println("Beggining file writing");

        writeDataToFile(size1Array, size2Array, resultingData);
        resultingData.close();
        System.out.println("DONE");

        Scanner scanner = new Scanner(arrayData);

        int[] noDuplicates = removeDuplicates(size1Array, size2Array, scanner);
        System.out.println("Printing array with no duplicates");
        for (int i = 0; i < noDuplicates.length; i++) {
            System.out.println(noDuplicates[i]);
        }
    }

    public static int[] generateNumbers(int size) {

        int[] randomNumArray = new int[size]; 

        for(int i=0; i<randomNumArray.length; i++) {

            int temp = 1 + (int)(Math.random() * 25);

            randomNumArray[i] = temp;
        }

        return randomNumArray;
    }

    public static void printArrayData(int[] array) {

        for (int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    } 
    
    public static void arraySorter(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            for (int z = 0; z < array.length - 1; z++) {

                if (array[z] > array[z + 1]) {

                    int temp = array[z];
    
                    array[z] = array[z + 1];
                    array[z + 1] = temp;
                }
            }
        }
    }

    public static void writeDataToFile(int[] array1, int[] array2, PrintWriter writer) {

        int i = 0;
        int z = 0;

        while (i < array1.length && z < array2.length) {

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
                if (array1.length < array2.length) {
                    System.out.println("Writing " + array1[i]);
                    writer.println(array1[i]);
                    i++;
                }
                
                if (array2.length > array1.length || array1.length == array2.length) {
                    System.out.println("Writing " + array2[z]);
                    writer.println(array2[z]);
                    z++;
                }
            }
        }

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

        writer.close();
    }

    public static int[] removeDuplicates(int[] array1, int[] array2, Scanner scanner) throws IOException {

        int previousLine = 0;
        int[] noDuplicates = new int[array1.length + array2.length];
        int i = 0;

        do {

            int currentLine = scanner.nextInt();

            if (currentLine != previousLine) {

                noDuplicates[i] = currentLine;
                previousLine = currentLine;
                i++;
            }

        } while (scanner.hasNextInt());

        scanner.close();

        int zeroCounter = 0;
        for (int z = 0; z < noDuplicates.length; z++) {

            if (noDuplicates[z] == 0) {
                zeroCounter++;
            }
        }

        int[] noDuplicatesNoZeros = new int[noDuplicates.length - zeroCounter];

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
