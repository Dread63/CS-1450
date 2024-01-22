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

        int i = 0;
        while(i < size1Array.length) {
            resultingData.println(size1Array[i]);
            i++;
        }

        i = 0;
        while(i < size2Array.length) {
            resultingData.println(size2Array[i]);
            i++;
        }
        resultingData.close();

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
