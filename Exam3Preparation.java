import java.util.*;
import java.util.random.*;
public class Exam3Preparation {
    
    public static void main(String[] args) {
        
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            Random random = new Random();

            int randomNumber = random.nextInt(100) + 1;

            numbers.add(randomNumber);
        }

        Iterator numberReader = numbers.iterator();

        while (numberReader.hasNext()) {

            System.out.println(numberReader.next());
        }

        System.out.println("\nSORTED ARRAY\n");
        ArrayList<Integer> sortedInts = sortInts(numbers, numberReader);
        Iterator<Integer> sortedIterator = sortedInts.iterator();

        while (sortedIterator.hasNext()) {

            System.out.println(sortedIterator.next());
        }



        System.out.println("--------------------------------------------------------");
        System.out.println("LINKED LISTS");
        System.out.println("--------------------------------------------------------");
        LinkedList<Integer> myLinkedList = new LinkedList<>();

        myLinkedList.addToFront(4);
        myLinkedList.addToFront(6);
        myLinkedList.addToEnd(99);
        myLinkedList.addToIndex(101, 2);

        myLinkedList.printList();

        myLinkedList.removeFromIndex(2);

        myLinkedList.printList();

    }

    public static ArrayList<Integer> sortInts (ArrayList<Integer> array, Iterator arrayIterator) {

        ArrayList<Integer> sortedList = array;

        for (int i = 0; i < array.size(); i++) {

            int smallest = array.get(i);
            int smallestIndex = i;

            for (int j = i + 1; j < array.size(); j++) {

                if (array.get(j) < smallest) {

                    smallest = array.get(j);
                    smallestIndex = j;
                }
            }

            if (smallestIndex != i) {

                array.set(smallestIndex, array.get(i));
                array.set(i, smallest);
            }
        }

        return sortedList;
    }
}

class LinkedList <T> {

    private Node<T> head;

    public void printList() {

        Node<T> currentNode = head;

        while (currentNode != null) {

            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public void addToFront(T data) {

        Node<T> tempNode = new Node<>(data);

        // If list is empty, make tempNode the first node
        if (head == null) {
            head = tempNode;
        }

        else {

            tempNode.next = head;
            head = tempNode;
            
        }
    }

    public void addToEnd(T data) {

        Node<T> tempNode = new Node<>(data);
        Node<T> currentNode = head;

        if (head == null) {

            head = tempNode;
        }

        else {

            while (currentNode.next != null) {

                currentNode = currentNode.next;
            }
    
            currentNode.next = tempNode;
        }
    }

    public void addToIndex(T data, int index) {

        Node<T> tempNode = new Node<>(data);

        if (index == 0) {

            tempNode.next = head;
            head = tempNode;
        }

        else {

            Node<T> currentNode = head;
            int currentIndex = 0;

            while (currentIndex < index - 1 && currentNode.next != null) {

                currentNode = currentNode.next;
                currentIndex++;
            }

            tempNode.next = currentNode.next;
            currentNode.next = tempNode;
        }
    }

    public T removeFirst() {

        if (head == null) {
            return null;
        }
        
        else {

            Node<T> removedNode = head;
            head = head.next;

            return removedNode.data;
        }
    }

    public T removeFromEnd() {

        Node<T> currentNode = head;
        Node<T> previousNode = null;

        while (currentNode.next != null) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        T removedData = currentNode.data;
        previousNode.next = null;
        return removedData;
    }

    public T removeFromIndex(int index) {

        if (index == 0) {
            head = head.next;
        }

        int currentIndex = 0;
        Node<T> currentNode = head;
        Node<T> previousNode = null;

        while (currentIndex < index && currentNode != null) {

            previousNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        T removedData = currentNode.next.data;  
        previousNode.next = currentNode.next;
        
        return removedData;
    }

    private static class Node <T> {

        private T data;
        private Node<T> next;

        Node (T data) {
            this.data = data;
            next = null;
        }
    }
}
