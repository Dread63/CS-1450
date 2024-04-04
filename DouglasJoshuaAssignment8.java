import java.util.*;
import java.io.*;

public class DouglasJoshuaAssignment8 {
    public static void main(String[] args) throws IOException {
        
        File queueMessage = new File("queueMessage.txt");
        File listMessage = new File("listMessage.txt");
        File queueKey = new File("queueKey.txt");
        File listKey = new File("listKey.txt");
        Scanner queueMsgScanner = new Scanner(queueMessage);
        Scanner listMsgScanner = new Scanner(listMessage);
        Scanner queueKeyScanner = new Scanner(queueKey);
        Scanner listKeyScanner = new Scanner(listKey);


        String listMessageString = listMsgScanner.nextLine();
        String listKeysString = listKeyScanner.nextLine();

        // Gettings dimensions for 2D array from listKeys.txt
        int listRowSize = listKeyScanner.nextInt();
        int listColSize = listKeyScanner.nextInt();
        int routeStartingRow = listKeyScanner.nextInt();
        int routeStartingCol = listKeyScanner.nextInt();

        // Filling arrays with values from text files
        ArrayList<Character> listMessageArray = stringToCharArray(listMessageString);
        ArrayList<Character> listKeysArray = stringToCharArray(listKeysString);
        
        Iterator<Character> listMessageIterator = listMessageArray.iterator();
        Iterator<Character> listKeysIterator = listKeysArray.iterator();

        Decoder decoder = new Decoder(listRowSize, listColSize, routeStartingRow, routeStartingCol);

        String finalMessage = decoder.unscramble(listMessageIterator, listKeysIterator);

        System.out.println(finalMessage);
    }

    public static ArrayList<Character> stringToCharArray(String string) {

        ArrayList<Character> charArray = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            Character currentChar = string.charAt(i);
            charArray.add(currentChar);
        }

        return charArray;
    }
}

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

    public String unscramble (Iterator<Character> msgIterator, Iterator<Character> keyIterator) {

        int row = startingRow;
        int col = startingCol;

        // Adding message to messageArray
        int numRows = messageArray.length;
        int numColumns = messageArray[0].length;

        for (int i = numRows - 1; i >= 0; i--) {

            for (int z = 0; z < numColumns; z++) {

                messageArray[i][z] = msgIterator.next();
            }
        }

        stack.push(messageArray[row][col]); 
        
        while (keyIterator.hasNext()) {
            
            char direction = keyIterator.next();

            if (direction == 'f') {
                col++;
            }

            if (direction == 'b') {
                col--;
            }

            if (direction == 'u') {
                row--;
            }

            if (direction == 'd') {
                row++;
            }

            stack.push(messageArray[row][col]);
        }

        int size = stack.size();
        String finalMessage;

        for (int i = 0; i < size; i++) {

            char currentChar = stack.pop();
            finalMessage = finalMessage + currentChar;
        }


        return finalMessage;
    }

}

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
