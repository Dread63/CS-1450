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

        File players = new File("players.txt");
        Scanner fileReader = new Scanner(players);

        while(fileReader.hasNext()) {

            
        }
    }
}

// Represents one player of the escape room
class Player implements Comparable {

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

    

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}

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
     
     public int tryToEscape(String playerName, int playerRanking) {

        String key = playerName + playerRanking;

        int score = hash(key) % 101;

        return score;
     }
}

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

    public int tryToEscape(String playerName, int playerRanking) {
        
        return escapeRoom.tryToEscape(playerName, playerRanking);
    }
}

class PriorityQueue {

    private ArrayList<Player> players;

    public PriorityQueue() {

        players = new ArrayList<>();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public Player peek() {

        if (!players.isEmpty()) {
            return players.get(0);
        }

        else {
            return null;
        }
    }

    public void offer(Player player) {

        players.add(player);
        Collections.sort(players);
    }

    public Player remove() {

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

class GameController {

    public void movePlayerIntoGame (Game game, Player player) {

        game.addPlayerToWaitingToPlayQ(player);
        System.out.println("Moved into game: " + player.getName());
    }

    public void simulateGame (Game game) {

        String currentLeader = " ";
        int highestScore = 0;

        while(!game.isWaitingToPlayQEmpty()) {

            Player currentPlayer = game.removePlayerFromWaitingToPlayQ();

            int score = game.tryToEscape(currentPlayer.getName(), currentPlayer.getRanking());

            currentPlayer.setScore(score);

            if (score > highestScore) {
                highestScore = score;
                currentLeader = currentPlayer.getName();
            }

            game.addPlayerToResultsQ(currentPlayer);

            System.out.print(currentPlayer.getName() + currentPlayer.getUniversity() + currentPlayer.getScore() + currentLeader);
        }
    }
}