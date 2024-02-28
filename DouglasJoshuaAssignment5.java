import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DouglasJoshuaAssignment5 {
    
    public static void main(String[] args) throws IOException {
        
        // Part 1
        int[] values = {10, 27, 19, 45, 4, 2, 82, 49};

        Stack<Integer> jcfStack = new Stack<>();

        for (int i = 0; i < values.length; i++) {
            jcfStack.add(values[i]);
        }

        int secondLargest = findSecondLargest(jcfStack);
        printStack(jcfStack);
        System.out.println();
        System.out.println("Second Largest Value in Stack");
        System.out.println(secondLargest);

        // Part 2

        File numbers1 = new File("numbers1.txt");
        File numbers2 = new File("numbers2.txt");
        File mountains1 = new File("mountains1.txt");
        File mountains2 = new File("mountains2.txt");

        GenericStack<Integer> numStack1 = new GenericStack<>();
        GenericStack<Integer> numStack2 = new GenericStack<>();
        GenericStack<String> stringStack1 = new GenericStack<>();
        GenericStack<String> stringStack2 = new GenericStack<>();

        fillIntStack(numbers1, numStack1);
        fillIntStack(numbers2, numStack2);
        fillStringStack(mountains1, stringStack1);
        fillStringStack(mountains2, stringStack2);

        printStack(numStack1);
        printStack(numStack2);
    }

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

    public static void fillIntStack(File file, GenericStack<Integer> stack) throws IOException {

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {

            int stackValue = fileReader.nextInt();

            stack.push(stackValue);
        }

        fileReader.close();
    }

    public static void fillStringStack(File file, GenericStack<String> stack) throws IOException {

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {

            String stackValue = fileReader.nextLine();

            stack.push(stackValue);
        }

        fileReader.close();
    }

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
}

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
        return stackData.get(stackData.size() - 1);
    }

    public E pop() {
        E topValue = stackData.get(stackData.size() - 1);
        stackData.remove(topValue);
        return topValue;
    }

    public void push(E value) {
        stackData.add(value);
    }
}
