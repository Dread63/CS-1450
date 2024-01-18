
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

        System.out.println("Size 1 is " + size1);
        System.out.println("Size 2 is " + size2);

        int[] size1Array = generateNumbers(size1);
        int[] size2Array = generateNumbers(size2);
    }

    public static int[] generateNumbers(int size) {

        int[] randomNumArray = new int[size]; 

        for(int i=0; i<randomNumArray.length; i++) {

            int temp = 1 + (int)(Math.random() * 25);

            randomNumArray[i] = temp;
        }

        return randomNumArray;
    }
     
}
