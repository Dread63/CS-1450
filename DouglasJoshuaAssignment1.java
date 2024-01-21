import java.util.*;
import java.io.*;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 1.25.24
 * Assignment #1
 * Description: 
 */

public class DouglasJoshuaAssignment1 {

    public static void main(String[] args) {
        
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
        PrintWriter resultsFile = new PrintWriter(arrayData);

        for (int i=0; i < size1Array.length; i++) {

            resultsFile.println(size1Array[i]);
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
}
