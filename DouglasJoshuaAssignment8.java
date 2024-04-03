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

    private char messageArray[][];
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

        while (msgIterator.hasNext()) {
            messageArray[numRows-1][0] = 
        }

        while (keyIterator.hasNext()) {
            
            if (keyIterator.next() == 'f') {
                col++;
            }

            if (keyIterator.next() == 'b') {
                col--;
            }

            if (keyIterator.next() == 'u') {
                row++;
            }

            if (keyIterator.next() == 'd') {
                row--;
            }
        }
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
