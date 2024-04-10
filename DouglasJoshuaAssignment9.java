import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DouglasJoshuaAssignment9 {
    
    public static void main(String[] args) throws IOException {
        
        File itinerary = new File("ParisItinerary.txt");
        Scanner fileReader = new Scanner(itinerary);

        ItineraryLinkedList singleLinkedList = new ItineraryLinkedList();
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        while (fileReader.hasNext()) {

            String line = fileReader.nextLine();
            Scanner lineScanner = new Scanner(line);
            int stop = lineScanner.nextInt();
            String type = lineScanner.next();
            String name = lineScanner.next();
            String activity = lineScanner.nextLine();

            Destination newDestination = new Destination(stop, type, name, activity);

            singleLinkedList.addByStopNumber(newDestination);
            lineScanner.close();
        }
        fileReader.close();

        singleLinkedList.printList();
        System.out.println("REMOVING ADVENTURES");
        singleLinkedList.removeDestination("adventure");
        singleLinkedList.printList();
    }
}

// Represents one destination (stop) in the itinerary
class Destination {

    private int stop;
    private String type;
    private String name;
    private String activity;

    public Destination (int stop, String type, String name, String activity) {

        this.stop = stop;
        this.type = type;
        this.name = name;
        this.activity = activity;  
    }

    public int getStop() {
        return stop;
    }

    public String getType() {
        return type;
    }

    public String getname() {
        return name;
    }

    public String getActivity() {
        return activity;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String toString() {
        
        return String.format("%-2d%-25s%-20s%s", stop, name, type, activity);
    }
}

class ItineraryLinkedList {

    private Node head;

    public void addByStopNumber(Destination destinationToAdd) {

        Node current = head;
        Node tempNode = new Node(destinationToAdd);

        if (head == null || destinationToAdd.getStop() < head.destination.getStop()) {

            tempNode.next = head;
            head = tempNode;
        }

        else {

            while (current.next != null && destinationToAdd.getStop() > current.next.destination.getStop()) {

                current = current.next;
            }

            tempNode.next = current.next;
            current.next = tempNode;
        }         

    }

    public Destination removeDestination(String typeToRemove) {

        Node current = head;
        Node previous = null;
        boolean found = false;

        while (current != null && !found) {

            if (current.destination.getType().equals(typeToRemove)) {
                found = true;
            }

            else {
                previous = current;
                current = current.next;
            }
        }

        if (found) {

            // Deleting the first node if previous is null
            if (previous == null) {
                head = current.next;
                
            }

            else {
                // Breaking the link
                previous.next = current.next;
            }

            return current.destination;
        } 

        // Return null if no match is found
        return null;
    }

    reverse() {

    }

    public void printList() {

        Node current = head;
        while (current != null) {
            System.out.println(current.destination);
            current = current.next;
        }
    }

    private static class Node {

        private Destination destination;
        private Node next;

        public Node(Destination destination) {
            this.destination = destination;
            next = null;
        }
    }
}

class DoubleLinkedList {

    private Node head;
    private Node tail;

    public void addDestination (Destination destination) {

    }

    public void printListBackwards() {

    }

    private static class Node {

        private Destination destination;
        private Node next;
        private Node previous;

        public Node(Destination destination) {
            this.destination = destination;
            next = null;
            previous = null;
        }
    }
}
