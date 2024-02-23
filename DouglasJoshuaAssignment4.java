/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 02.13.24
 * Assignment #4
 * Description: This assignment is designed to practice utilizing 2D arrays and array lists as well as use the collections utility class
 * and Comparable interface in java. This program uses a PinBallMachine class to create a pinball machine object which contains a 2D 
 * playing field array. This array will be filled with target objects which implement the Comparable interface and override the compareTo
 * method. The program will display the initial playing field values, play a game using a given text file, and display the targets with the
 * most amount of hits to least amount of hits at the end.
 */
import java.io.*;
import java.util.*;

public class DouglasJoshuaAssignment4 {
    public static void main(String[] args) throws IOException {
        
        File pinballMachineTargets = new File("PinballMachineTargets.txt");
        File play = new File ("Play.txt");
        Scanner targetScanner = new Scanner(pinballMachineTargets);

        int numRows = targetScanner.nextInt();
        int numColumns = targetScanner.nextInt();

        PinballMachine pinballMachine = new PinballMachine(numRows, numColumns);

        // While there are targets left to read, add them to the playing field
        while (targetScanner.hasNextLine()) {

            // Initializing variables to ensure proper iteration through the file's contents
            int row = targetScanner.nextInt();
            int column = targetScanner.nextInt();
            String targetType = targetScanner.next();
            int id = targetScanner.nextInt();
            int points = targetScanner.nextInt();

            Target target = new Target(targetType, id, points);

            pinballMachine.addTargetToPlayingField(row, column, target);
        }
        
        // Display the loaded pinball machine
        pinballMachine.displayPlayingField();;

        // Play the game using play text file
        pinballMachine.play(play);

        // Printing targets from most hits to least
        printTargetsByHits(pinballMachine);

        // Close scanner to ensure no data leak
        targetScanner.close();
    }

    // Print targets from most hits to least
    public static void printTargetsByHits(PinballMachine pinballMachine) {

        ArrayList<Target> targetsArrayList = new ArrayList<>();

        for (int i = 0; i < pinballMachine.getNumRows(); i++) {

            for (int j = 0; j < pinballMachine.getNumColumns(); j++) {

                Target currentTarget = pinballMachine.getTarget(i, j);

                if (currentTarget != null) {
                    targetsArrayList.add(currentTarget);
                }  
            }
        }

        // Sorting ArrayList using the overridden method compareTo in the Target class
        Collections.sort(targetsArrayList);

        System.out.println("****************************************************************");
        System.out.println("PINBALL MACHINE TARGET HIT REPORT (From Most Hits to Least Hits)");
        System.out.println("****************************************************************");
        System.out.printf("%-14s%-10s%-8s%-10s", "Type", "Id", "Points", "Number Hits\n");
        System.out.println("----------------------------------------------------------------");

        // To string method to print type, id, points, numTimesHit
        for (int i = 0; i < targetsArrayList.size(); i++) {
            System.out.println(targetsArrayList.get(i).toString());
        }
    }

}

// Class which stores playing field 2D array
class PinballMachine {
    
    private int numRows;
    private int numColumns;
    private Target[][] playingField;

    public PinballMachine(int numRows, int numColumns) {

        this.numRows = numRows;
        this.numColumns = numColumns;

        playingField = new Target[numRows][numColumns];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void addTargetToPlayingField(int row, int column, Target target) {

        playingField[row][column] = target;
    }

    public Target getTarget(int row, int column) {

        if (playingField[row][column] == null) {
            return null;
        }
        else {
            return playingField[row][column];
        }
        
    }

    // Used to display initial playing field to user
    public void displayPlayingField() {

        System.out.println("Set up targets in pinball machine..");
        System.out.println();

        for (int i = 0; i < numColumns; i++) {

            if (i == 0) {
                System.out.printf("%15s", "Column " + i);
            }
            else {
                System.out.printf("%10s", "Column " + i);
            }
        }

        System.out.println();

        for (int i = 0; i < numRows; i++) {
            
            System.out.println();
            System.out.printf("%s%-5s", "Row", i);

            for (int j = 0; j < numColumns; j++) {

                if (playingField[i][j] == null) {
                    System.out.printf("%-10s", "-----");
                }

                else {
                    System.out.printf("%-10s", playingField[i][j].getType());
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    // Play game based on input from file
    public void play(File playFileName) throws IOException {

        Scanner playScanner = new Scanner(playFileName);

        int score = 0;

        System.out.println("----------------------------------------");
        System.out.println("Simulate Pinball Game");
        System.out.println("----------------------------------------");

        System.out.printf("%-15s%-10s%-10s%-10s\n", "Target Hit", "ID", "Points", "Score");
        System.out.println("----------------------------------------");

        // While there is still a line in the file, increment the Hits, get the points, and display the result
        while (playScanner.hasNextLine()) {

            int row = playScanner.nextInt();
            int column = playScanner.nextInt();

            // Ensure operations are not done on empty spaces
            if (playingField[row][column] != null) {

                playingField[row][column].incrementHits();
                score += playingField[row][column].getPoints();

                System.out.printf("%-15s%-10d%-10d%-10d\n", playingField[row][column].getType(), playingField[row][column]
                .getId(), playingField[row][column].getPoints(), score);
            }
        }
        System.out.println();
        System.out.println();

        // Close scanner to avoid data leak
        playScanner.close();
    }
}

// Target class which defines objects stored in the playingField array and implements Comparable
class Target implements Comparable<Target> {

    private String type;
    private int id;
    private int points;
    private int numTimesHit;

    public Target(String type, int id, int points) {

        this.type = type;
        this.id = id;
        this.points = points;

        numTimesHit = 0;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getNumTimesHit() {
        return numTimesHit;
    }

    public void incrementHits() {
        numTimesHit++;
    }

    public String toString() {
        return String.format("%-10s\t%d\t%d\t%d", type, id, points, numTimesHit);
    }

    // Check to see which target has the higher number of hits
    @Override
    public int compareTo(Target otherTarget) {

        if (this.numTimesHit < otherTarget.getNumTimesHit()) {
            return 1;
        }
        else if (this.numTimesHit > otherTarget.getNumTimesHit()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}