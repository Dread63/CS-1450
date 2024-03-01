/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 03.01.24
 * Assignment #5
 * Description: This assignment is designed to utilize both the java collection framework stack, and a custom made generic stack. The program
 * will fill different stacks with values read from a file, and perform manipulations on the stack to practice utilizing stacks and their methods.
 * Stacks will be sorted, merged together, analyzed to find the second largest value in the stack, and displayed to show the duplicate values 
 * within the stacks. This program intentionally avoids using other vector methods to build a solid foundation with stack manipulation in java.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DouglasJoshuaAssignment5 {
    
    public static void main(String[] args) throws IOException {
        
        // PART 1

        int[] values = {10, 27, 19, 45, 4, 2, 82, 49};

        Stack<Integer> jcfStack = new Stack<>();

        // Fill jcf stack with values from array
        for (int i = 0; i < values.length; i++) {
            jcfStack.add(values[i]);
        }

        // Printing stack details
        int secondLargest = findSecondLargest(jcfStack);
        System.out.println("Stack Values and Second Largest Value");
        System.out.println("-------------------------------------");
        printStack(jcfStack);
        System.out.println();
        System.out.printf("%s%d\n\n\n", "Second Largest Value in Stack: ", secondLargest);

        // PART 2

        // Initializing files which contain values to fill stacks
        File numbers1 = new File("numbers1.txt");
        File numbers2 = new File("numbers2.txt");
        File mountains1 = new File("mountains1.txt");
        File mountains2 = new File("mountains2.txt");

        // Initializing generic stacks to be manipualted throughout the code
        GenericStack<Integer> numStack1 = new GenericStack<>();
        GenericStack<Integer> numStack2 = new GenericStack<>();
        GenericStack<String> stringStack1 = new GenericStack<>();
        GenericStack<String> stringStack2 = new GenericStack<>();

        // Fill stacks with values from files
        fillIntStack(numbers1, numStack1);
        fillIntStack(numbers2, numStack2);
        fillStringStack(mountains1, stringStack1);
        fillStringStack(mountains2, stringStack2);

        // Display stack values
        System.out.println("Numbers Stack 1 - filled with values from numbers1.txt");
        System.out.println("------------------------------------------------------");
        printStack(numStack1);
        System.out.println();

        System.out.println("Numbers Stack 2 - filled with values from numbers2.txt");
        System.out.println("------------------------------------------------------");
        printStack(numStack2);
        System.out.println();

        // Merge stack 1 and 2 into new stack
        GenericStack<Integer> mergedIntegers = new GenericStack<>();
        mergeStacks(numStack1, numStack2, mergedIntegers);

        // Display details of merged stack
        System.out.println("Merged Stack - largest value on top");
        System.out.println("-----------------------------------");
        printStack(mergedIntegers);
        System.out.println();

        System.out.println("Details about duplicate values");
        System.out.println("------------------------------");
        displayDuplicateCount(mergedIntegers);
        System.out.println();

        // Repeat all manipulations on string stacks
        System.out.println("String Stack 1 - filled with values from mountains1.txt");
        System.out.println("-------------------------------------------------------");
        printStack(stringStack1);
        System.out.println();

        System.out.println("String Stack 2 - filled with values from mountains2.txt");
        System.out.println("-------------------------------------------------------");
        printStack(stringStack2);
        System.out.println();
        
        GenericStack<String> mergedStrings = new GenericStack<>();
        mergeStacks(stringStack1, stringStack2, mergedStrings);

        System.out.println("Merged Stack - largest value on top");
        System.out.println("-----------------------------------");
        printStack(mergedStrings);
        System.out.println();

        System.out.println("Details about duplicate values");
        System.out.println("------------------------------");
        displayDuplicateCount(mergedStrings);
        System.out.println();

    }

    // Find the second largest value in an integer stack
    public static int findSecondLargest(Stack<Integer> stack) {

        Stack<Integer> tempStack = new Stack<>();

        int size = stack.size();

        int largest = 0;
        int secondLargest = 0;

        for (int i = 0; i < size; i++) {

            int topValue = stack.pop();

            if (topValue > largest) {
                secondLargest = largest;
                largest = topValue;
            }

            tempStack.push(topValue);
        }

        for (int i = 0; i < size; i++) {

            stack.push(tempStack.pop());
        }

        return secondLargest;
    }

    // Print integer values in a stack and restore stack to orignal state (used in part 1)
    public static void printStack (Stack<Integer> stack) {

        Stack<Integer> tempStack = new Stack<>();

        int size = stack.size();

        for (int i = 0; i < size; i++) {

            int topValue = stack.pop();

            System.out.println(topValue);

            tempStack.push(topValue);
        }

        for (int i = 0; i < size; i++) {

            stack.push(tempStack.pop());
        }
    }

    // Fill a stack with integers
    public static void fillIntStack(File file, GenericStack<Integer> stack) throws IOException {

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {

            int stackValue = fileReader.nextInt();

            stack.push(stackValue);
        }

        fileReader.close();
    }

    // Fill a stack with strings
    public static void fillStringStack(File file, GenericStack<String> stack) throws IOException {

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {

            String stackValue = fileReader.nextLine();

            stack.push(stackValue);
        }

        fileReader.close();
    }

    // Print the values of a generic stack
    public static <E> void printStack (GenericStack<E> stack) {

        Stack<E> tempStack = new Stack<>();

        int size = stack.getSize();

        for (int i = 0; i < size; i++) {

            E topValue = stack.pop();

            System.out.println(topValue);

            tempStack.push(topValue);
        }

        for (int i = 0; i < size; i++) {

            stack.push(tempStack.pop());
        }
    }

    // Merge two generic stacks, placing values in order from least to greatest
    public static <E extends Comparable<E>> void mergeStacks (GenericStack<E> stack1, GenericStack<E> stack2, GenericStack<E> mergedStack) {

        while (!stack1.isEmpty() && !stack2.isEmpty()) {

            E top1 = stack1.peek();
            E top2 = stack2.peek();

            if (top1.compareTo(top2) >= 0) {

                mergedStack.push(stack2.pop());
            }

            else {

                mergedStack.push(stack1.pop());
            }
        }

        while (!stack1.isEmpty()) {

            mergedStack.push(stack1.pop());
        }

        while (!stack2.isEmpty()) {

            mergedStack.push(stack2.pop());
        }
    }

    // Find all duplicate values in a stack and display count for each
    public static <E extends Comparable<E>> void displayDuplicateCount (GenericStack<E> duplicatesStack) {

        GenericStack<E> tempStack = new GenericStack<>();

        while (!duplicatesStack.isEmpty()) {

            E top = duplicatesStack.pop();

            int duplicateCount = 1;

            int size = duplicatesStack.getSize();

            boolean duplicateDetected = false;

            for (int i = 0; i < size; i++) {

                E currentValue = duplicatesStack.pop();

                if (top.compareTo(currentValue) == 0) {
                    duplicateCount++;
                    duplicateDetected = true;
                }

                else {
                    tempStack.push(currentValue);
                }
            }

            while (!tempStack.isEmpty()) {
                
                duplicatesStack.push(tempStack.pop());
            }

            if (duplicateDetected) {
                System.out.println("The value " +  top + " appears " + duplicateCount + " times on the duplicate stack"); 

            }
        }
    }
}

// Generic stack using an ArrayList as storage
class GenericStack<E> {

    private ArrayList<E> stackData;

    public GenericStack() {
        stackData = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stackData.isEmpty();
    }

    public int getSize() {
        return stackData.size();
    }

    public E peek() {
        // stackData.size() - 1 ensures we return the last value in the array to emulate stack-like behavior
        return stackData.get(stackData.size() - 1);
    }

    public E pop() {
        return stackData.remove(stackData.size() - 1);
    }

    public void push(E value) {
        stackData.add(value);
    }
}
