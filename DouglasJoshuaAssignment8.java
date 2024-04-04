/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 04.04.2024
 * Assignment #8
 * Description: This program decodes messages encrpted with a route cipher inside of a text file.
 * Through the use of scanners, arraylists, queues and Iterators, the program breaks down the
 * secret message, places the values in a 2D array, itterates through the array given the information
 * in the keys file, and displays the deciphered messages to the user.
 */
import java.util.*;
import java.io.*;

public class DouglasJoshuaAssignment8 {

    public static void main(String[] args) throws IOException {
        
        // Opening Files for Reading
        File queueMessage = new File("queueMessage.txt");
        File listMessage = new File("listMessage.txt");
        File queueKey = new File("queueKey.txt");
        File listKey = new File("listKey.txt");

        Scanner queueMsgScanner = new Scanner(queueMessage);
        Scanner listMsgScanner = new Scanner(listMessage);
        Scanner queueKeyScanner = new Scanner(queueKey);
        Scanner listKeyScanner = new Scanner(listKey);

        // Collecting messages and keys from scanners
        String listMessageString = listMsgScanner.nextLine();
        String listKeysString = listKeyScanner.nextLine();
        String queueMessageString = queueMsgScanner.nextLine();
        String queueKeyString = queueKeyScanner.nextLine();

        // Getting dimensions for 2D array from listKeys.txt, and queueKeys.txt
        int listRowSize = listKeyScanner.nextInt();
        int listColSize = listKeyScanner.nextInt();
        int listRouteStartingRow = listKeyScanner.nextInt();
        int listRouteStartingCol = listKeyScanner.nextInt();

        int queueRowSize = queueKeyScanner.nextInt();
        int queueColSize = queueKeyScanner.nextInt();
        int queueRouteStartingRow = queueKeyScanner.nextInt();
        int queueRouteStartingCol = queueKeyScanner.nextInt();

        // Filling arrays and queues with values from text files
        ArrayList<Character> listMessageArray = stringToCharArray(listMessageString);
        ArrayList<Character> listKeysArray = stringToCharArray(listKeysString);

        Queue<Character> queueMessageQueue = stringToCharQueue(queueMessageString);
        Queue<Character> queueKeysQueue = stringToCharQueue(queueKeyString);
        
        // Interators used to push values onto stack in unsramble method
        Iterator<Character> listMessageIterator = listMessageArray.iterator();
        Iterator<Character> listKeysIterator = listKeysArray.iterator();
        Iterator<Character> queueMessageIterator = queueMessageQueue.iterator();
        Iterator<Character> queueKeysIterator = queueKeysQueue.iterator();

        // Enables us to use unscramble method inside Decoder class
        Decoder listDecoder = new Decoder(listRowSize, listColSize, listRouteStartingRow, listRouteStartingCol);
        Decoder queueDecoder = new Decoder(queueRowSize, queueColSize, queueRouteStartingRow, queueRouteStartingCol);

        // Storing final secret messages
        String finalListMessage = listDecoder.unscramble(listMessageIterator, listKeysIterator);
        String finalQueueMessage = queueDecoder.unscramble(queueMessageIterator, queueKeysIterator);

        // Display secret messages to user
        System.out.print("---------------");
        System.out.printf("%-15s", "LIST SECRET MESSAGE");
        System.out.println("---------------");
        System.out.println(finalListMessage);
        System.out.print("---------------");
        System.out.printf("%-15s", "QUEUE SECRET MESSAGE");
        System.out.println("---------------");
        System.out.println(finalQueueMessage);

        // Best Practice: Closing Scanners to Avoid Memory Leaks
        queueMsgScanner.close();
        listMsgScanner.close();
        queueKeyScanner.close();
        listKeyScanner.close();
    }

    // Breaks string into characters and place into Character arrayList
    public static ArrayList<Character> stringToCharArray(String string) {

        ArrayList<Character> charArray = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            Character currentChar = string.charAt(i);
            charArray.add(currentChar);
        }

        return charArray;
    }

    // Breaks string into characters and places into Character queue
    public static Queue<Character> stringToCharQueue(String string) {
        
        Queue<Character> charQueue = new LinkedList<>();

        for (int i = 0; i < string.length(); i++) {
            Character currentChar = string.charAt(i);
            charQueue.add(currentChar);
        }

        return charQueue;
    }
}

// Class which contains 2D array and unscramble method to decode our secret messages
class Decoder {

    private char[][] messageArray;
    private int startingRow;
    private int startingCol;
    private DecodingStack stack;

    public Decoder(int numRows, int numCols, int startingRow, int startingCol) {

        messageArray = new char[numRows][numCols];

        stack = new DecodingStack();

        this.startingRow = startingRow;
        this.startingCol = startingCol;
    }

    // Unscrambles secret message using iterators
    public String unscramble (Iterator<Character> msgIterator, Iterator<Character> keyIterator) {

        int row = startingRow;
        int col = startingCol;

        // Adding message to messageArray
        int numRows = messageArray.length;
        int numColumns = messageArray[0].length;

        // Add message to 2D message array
        for (int i = numRows - 1; i >= 0; i--) {

            for (int z = 0; z < numColumns; z++) {

                messageArray[i][z] = msgIterator.next();
            }
        }

        // Push the first value onto the defined starting positions
        stack.push(messageArray[row][col]); 
        
        // Iterate through keys, move through 2D array and push values onto stack
        while (keyIterator.hasNext()) {
            
            char direction = keyIterator.next();

            switch (direction) {

                case 'f': col++;
                break;

                case 'b': col--;
                break;

                case 'u': row--;
                break;

                case 'd': row++;
                break;

                default: System.out.println("ERROR: INVALID KEYS");
            }

            stack.push(messageArray[row][col]);
        }

        // Assemble final message from characters on stack
        int size = stack.size();
        String finalMessage = "";

        for (int i = 0; i < size; i++) {

            char currentChar = stack.pop();
            finalMessage = finalMessage + currentChar;
        }

        return finalMessage;
    }

}

// Custom stack to ensure proper stack behavior
class DecodingStack {

    private Stack<Character> JCFstack;

    public DecodingStack() {
        JCFstack = new Stack<Character>();
    }

    public boolean isEmpty() {
        return JCFstack.isEmpty();
    }

    public int size() {
        return JCFstack.size();
    }

    public void push(char charToPush) {
        JCFstack.push(charToPush);
    }

    public char pop() {
        return JCFstack.pop();
    }
}
