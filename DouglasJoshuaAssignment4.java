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

        while (targetScanner.hasNextLine()) {

            int row = targetScanner.nextInt();
            int column = targetScanner.nextInt();
            String targetType = targetScanner.next();
            int id = targetScanner.nextInt();
            int points = targetScanner.nextInt();

            Target target = new Target(targetType, id, points);

            pinballMachine.addTargetToPlayingField(row, column, target);
        }
        
        pinballMachine.displayPlayingField();;

        pinballMachine.play(play);

        printTargetsByHits(pinballMachine);

    }

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

        // Sorting arraylist using the overridden method compareTo in the Target class
        Collections.sort(targetsArrayList);

        for (int i = 0; i < targetsArrayList.size(); i++) {
            System.out.println(targetsArrayList.get(i).toString());
        }
    }

}

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

    public void displayPlayingField() {

        for(int i = 0; i < numRows; i++) {
            
            System.out.println();
            System.out.printf("%s%5s", "Row", i);

            for (int j = 0; j < numColumns; j++) {

                if (playingField[i][j] == null) {
                    System.out.printf("%5s", "-----");
                }

                else {
                    System.out.printf("%5s", playingField[i][j]);
                }
            }
        }
    }

    public void play(File playFileName) throws IOException {

        Scanner playScanner = new Scanner(playFileName);

        int score = 0;

        System.out.println("---------------------");
        System.out.println("Simulate Pinball Game");
        System.out.println("---------------------");

        System.out.printf("%-10s%-10s%-10s%-10s", "Target hit", "ID", "Points", "Score");
        System.out.println("---------------------");


        while (playScanner.hasNextLine()) {

            int row = playScanner.nextInt();
            int column = playScanner.nextInt();

            if (playingField[row][column] != null) {

                playingField[row][column].incrementHits();
                score += playingField[row][column].getPoints();
                System.out.printf("%-10s%-10d%-10d%-10d", playingField[row][column].getType(), playingField[row][column]
                .getId(), playingField[row][column].getPoints(), score);
            }
        }
    }
}

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

    public int compareTo(Target otherTarget) {

        if (this.numTimesHit < otherTarget.getNumTimesHit()) {
            return -1;
        }
        else if (this.numTimesHit < otherTarget.getNumTimesHit()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}