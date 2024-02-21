import java.util.ArrayList;

public class DouglasJoshuaAssignment4 {
    public static void main(String[] args) {
        
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

            for (int j = 0; j < numColumns; i++) {

                if (playingField[i][j] == null) {
                    System.out.printf("%5s", "-----");
                }

                else {
                    System.out.printf("%5s", playingField[i][j].get);
                }
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