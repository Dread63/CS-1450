/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 03.21.24
 * Assignment #7
 * Description: This program simulates an escape room game where each player will be awarded a score at the end and the winner
 * will be determined based on the score. The primpary goal of this assignment is to practice using and creating a custom
 * priority queue and to properly implement the compareTo method. Additionally, this code provides practice with creating different
 * concrete classes and methods which will be used to manipulate data and interact with other object types throughout the program. 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DouglasJoshuaAssignment7 {
    
    public static void main(String[] args) throws IOException{
        
        GameController gameController = new GameController();
        Game game = new Game();

        File players = new File("Players.txt");
        Scanner fileReader = new Scanner(players);

        System.out.println("Game Controller: Moving Players Into Game:");
        System.out.println("-------------------------------------------------------------------------------------");

        // Creating players and filling the waitingToPlayQ via the movePlayerIntoGame() function
        while (fileReader.hasNext()) {

            int ranking = fileReader.nextInt();
            String university = fileReader.next();
            String name = fileReader.next();

            Player newPlayer = new Player(name, ranking, university);

            gameController.movePlayerIntoGame(game, newPlayer);
        }

        // Close reader to avoid any data leakage
        fileReader.close();

        // Simulating the game by passing our game object to simulateGame()
        System.out.println();
        System.out.println("Game Controller: Starting Game - moving players waiting to play into the escape room:");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Player", "University", "Score", "Current Leader");
        System.out.println("-------------------------------------------------------------------------------------");
        gameController.simulateGame(game);

        // Displaying the the players and their standing after game ends
        System.out.println();
        System.out.println("Game Controller: Escape Room Results");
        System.out.println("------------------------------------");
        System.out.printf("%-15s%-15s%-15s\n", "Player", "University", "Score");
        System.out.println("------------------------------------");
        gameController.displayResults(game);

        // Prove the game is over by check if both waitingQ and resultsQ in the game object are empty
        System.out.println();
        System.out.println("Game Controller: Proving Game Is Over....");
        System.out.println("-----------------------------------------");

        if (gameController.isGameOver(game) == true) {
            System.out.println("The game is over and everyone had a great time!");
        }

        else {
            System.out.println("Oops! Something went wronog and the game is stuck running!");
        }
    }
}

// Represents one player of the escape room
class Player implements Comparable<Player> {

    private String name;
    private int ranking;
    private String university;
    private int score;

    public Player(String name, int ranking, String university) {
        this.name = name;
        this.ranking = ranking;
        this.university = university;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getUniversity() {
        return university;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return String.format("%s\t%s\t\t%d", name, university, score);
    }

    // Compares player's scores: returns 1 if passed player has higher score, and -1 if passed player has lower score
    @Override
    public int compareTo(Player otherPlayer) {
        
        if (this.getScore() > otherPlayer.getScore()) {
            return -1;
        }

        else if (this.getScore() < otherPlayer.getScore()) {
            return 1;
        }

        else {
            return 0;
        }
    }

}

// Class to represent the escape room which users play the game inside of
class EscapeRoom {

    // Return a hash of the key. Key can be any length.
	// Returns an integer >= 0
	private int hash(String key) {
		
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
         hash += key.charAt(i);
         hash += (hash << 10);
         hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
         
        return Math.abs(hash);
         
     } // hash
     
     // Calulates players score and returns as int
     public int tryToEscape(String playerName, int playerRanking) {

        String key = playerName + playerRanking;

        // Calculates the hash and bounds it in the range of 0-100 via the modulo operator
        int score = hash(key) % 101;

        return score;
     }
}

// Class to represent the the overarching game which contains the queues and escape room
class Game {

    private Queue<Player> waitingToPlayQ;
    private PriorityQueue resultsQ;
    private EscapeRoom escapeRoom;

    public Game() {

        waitingToPlayQ = new LinkedList<>();
        resultsQ = new PriorityQueue();
        escapeRoom = new EscapeRoom();
    }

    public boolean isWaitingToPlayQEmpty() {
        return waitingToPlayQ.isEmpty();
    }

    public void addPlayerToWaitingToPlayQ (Player player) {
        waitingToPlayQ.offer(player);
    }

    public Player removePlayerFromWaitingToPlayQ() {
        return waitingToPlayQ.remove();
    }

    public boolean isResultsQEmpty() {
        return resultsQ.isEmpty();
    }

    public void addPlayerToResultsQ(Player player) {
        resultsQ.offer(player);
    }

    public Player removePlayerFromResultsQ() {
        return resultsQ.remove();
    }

    public Player peekResultsQ() {
       return resultsQ.peek();
    }

    // Allows the game object to use the tryToEscape function in the EscapeRoom class
    public int tryToEscape(String playerName, int playerRanking) {
        
        return escapeRoom.tryToEscape(playerName, playerRanking);
    }
}

// Class to define our custom behaviors of a priority queue
class PriorityQueue {

    // ArrayList used to store all data
    private ArrayList<Player> players;

    public PriorityQueue() {

        players = new ArrayList<>();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public Player peek() {

        // Ensures players arrayList isn't empty to avoid index out of bounds errors
        if (!players.isEmpty()) {
            return players.get(0);
        }

        // If arrayList is empty, return null to the function call
        else {
            return null;
        }
    }

    // Collections.sort is used to ensure our ArrayList orders data like a priority queue
    public void offer(Player player) {

        players.add(player);
        Collections.sort(players);
    }

    public Player remove() {

        // Ensures players ArrayList isn't empty before removing topPlayer to avoid index out of bounds errors
        if (!players.isEmpty()) {
            Player topPlayer = players.get(0);
            players.remove(0);
            return topPlayer;
        }

        else {
            return null;
        }
    }
}

// Class used to manipulate the players and game together
class GameController {

    // Fill queue of players waiting to enter escape room
    public void movePlayerIntoGame (Game game, Player player) {

        game.addPlayerToWaitingToPlayQ(player);
        System.out.println("Moved into game: " + player.getName());
    }

    // Simulates players in game by removing them from the waitingQ, collecting their score, setting the score, and calculating
    // the highest score to display current leader
    public void simulateGame (Game game) {

        String currentLeader = " ";
        int highestScore = 0;

        while (!game.isWaitingToPlayQEmpty()) {

            Player currentPlayer = game.removePlayerFromWaitingToPlayQ();

            int score = game.tryToEscape(currentPlayer.getName(), currentPlayer.getRanking());

            currentPlayer.setScore(score);

            // Finding leaders score and name to display throughout game
            if (score > highestScore) {
                highestScore = score;
                currentLeader = currentPlayer.getName();
            }

            // Send player to resultsQ once game is over for them
            game.addPlayerToResultsQ(currentPlayer);

            System.out.printf("%-15s%-15s%-15s%-15s\n", currentPlayer.getName(), currentPlayer.getUniversity(), currentPlayer.getScore(), currentLeader);
        }
    }

    // Display the players and their scores in order from highest to least
    public void displayResults (Game game) {

        while (!game.isResultsQEmpty()) {

            Player currentPlayer = game.removePlayerFromResultsQ();

            System.out.printf("%-15s%-15s%-15s\n", currentPlayer.getName(), currentPlayer.getUniversity(), currentPlayer.getScore());
        }
    } 

    // Check if the game ended properly by seeing if both the waitingQ and resultsQ are empty
    public boolean isGameOver(Game game) {

        if (game.isWaitingToPlayQEmpty() == true && game.isResultsQEmpty() == true) {
            return true;
        }

        else {
            return false;
        }
    }
}