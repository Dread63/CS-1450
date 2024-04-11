import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 04.11.2024
 * Assignment #9
 * Description: This program is designed to demonstrate an understading of both single linked
 * lists and double linked lists. Using a text file, the program will create destination objects
 * given the attributes in the string and begin to place them into the single linked list. Then,
 * the program will print the list, move values out of the list into the double linked list, and
 * reverse the order of the destinatinos in both lists.
 */
public class DouglasJoshuaAssignment9 {
    
    public static void main(String[] args) throws IOException {
        
        // Initialize data used throughout code
        File itinerary = new File("ParisItinerary.txt");
        Scanner fileReader = new Scanner(itinerary);

        ItineraryLinkedList singleLinkedList = new ItineraryLinkedList();
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        // Create destinations until EOF reached
        while (fileReader.hasNext()) {

            // File reader collects whole line, lineScanner moves through single line
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


        // Display initial (unchanged) sorted itinerary
        System.out.println("\nSorted Paris Itinerary\n");
        
        singleLinkedList.printList();


        // Display itinerary with removed adventures using removeDestination()
        System.out.println("\nUpdated Itinerary With Adventures Removed\n");

        // While there are still destinations in the linked list
        boolean destinationsLeft = true;
        while (destinationsLeft) {
            
            // Remove from single linked list and add to double linked list
            Destination removedDestination = singleLinkedList.removeDestination("adventure");
            if (removedDestination != null) {
                doubleLinkedList.addDestination(removedDestination);
            }

            // No destinations left in single linked list
            else {
                destinationsLeft = false;
            }
        }

        singleLinkedList.printList();


        // Print removed adventures from single linked list in backwards order
        System.out.println("\nAdventure Destinations in Doubly Linked List - Printed Backwards\n");
        
        doubleLinkedList.printListBackwards();


        // Print single linked list in reverse order
        System.out.println("\nItinerary in Reverse Order\n");
        
        singleLinkedList.reverse();
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

    public String getName() {
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

    // Converts characteristics of destination into a string that can be printed
    public String toString() {
        
        return String.format("%-10d%-25s%-24s%s", stop, name, type, activity);
    }
}

// Singly linked list used to store destinations
class ItineraryLinkedList {

    private Node head;

    // Add destinations to the linked list in order of smallest stop value to largest stop value
    public void addByStopNumber(Destination destinationToAdd) {

        Node current = head;
        Node tempNode = new Node(destinationToAdd);

        // If linked list empty
        if (head == null || destinationToAdd.getStop() < head.destination.getStop()) {

            tempNode.next = head;
            head = tempNode;
        }

        // Find proper location and insert node into linked list
        else {

            // While not at end of list and new destination's stop is smaller than current destination's stop
            while (current.next != null && destinationToAdd.getStop() > current.next.destination.getStop()) {

                // Move forward
                current = current.next;
            }

            // Insert node
            tempNode.next = current.next;
            current.next = tempNode;
        }         

    }

    // Located node based on destinations type and remove from linked list
    public Destination removeDestination(String typeToRemove) {

        Node current = head;
        Node previous = null;
        boolean found = false;

        // While linked list not empty and desintation type not found
        while (current != null && !found) {

            if (current.destination.getType().equals(typeToRemove)) {
                found = true;
            }

            else {
                previous = current;
                current = current.next;
            }
        }

        // If type is found, break the node off from linked list
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

    // Reverse the order of values in the linked list until front of linked list reached
    public void reverse() {

        Node previous = null;
        Node current = head;
        Node nextNode;

        while (current != null) {

            // Preserve the pointer of the next node
            nextNode = current.next;
            // Reverse order of nodes
            current.next = previous;
            previous = current;
            // Move forward to continue reversing nodes
            current = nextNode;
        }

        head = previous;
    }

    // Print destinations inside linked list
    public void printList() {
        
        System.out.printf("%-10s%-25s%-25s%s\n%s\n", "Stop", "Destination Name", "Type", 
        "Activity", "--------------------------------------------------------------------------------------------------------------");

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

// Double linked list used to store the removed destinations from the single linked list
class DoubleLinkedList {

    private Node head;
    private Node tail;

    // Add destination to double linked list
    public void addDestination (Destination destination) {

        Node tempNode = new Node(destination);
        
        if (head == null) {
            head = tempNode;
            tail = tempNode;
        }

        else {
            tail.next = tempNode;
            tempNode.previous = tail;
            tail = tempNode;
        }
    }

    // Print double linked list in reverse order
    public void printListBackwards() {

        System.out.printf("%-10s%-25s%-25s%s\n%s\n", "Stop", "Destination Name", "Type", 
        "Activity", "--------------------------------------------------------------------------------------------------------------");

        // Start from end of double linked list
        Node currentNode = tail;

        // While not at the front of list, print current node's destination
        while (currentNode != null) {

            System.out.println(currentNode.destination.toString());

            currentNode = currentNode.previous;
        }
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
