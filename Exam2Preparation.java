import java.util.*;

public class Exam2Preparation {
    public static void main(String[] args) {

        PriorityQueue<Customer> customers = new PriorityQueue<>();

        Customer edward = new Customer(100, "Edward", true);
        Customer marquise = new Customer(25, "Marquise", false);
        Customer velma = new Customer(1000, "Velma", true);
        Customer james = new Customer(13, "James", false);
        Customer robin = new Customer(70, "Robin", false);

        customers.offer(edward);
        customers.offer(marquise);
        customers.offer(velma);
        customers.offer(james);
        customers.offer(robin);

        Iterator<Customer> listCustomers = customers.iterator();

        while (listCustomers.hasNext()) {
            System.out.println(listCustomers.next().getName());
        }

        Kitchen myKitchen = new Kitchen(10, "Dirty");

        int numCustomers = customers.size();

        for (int i = 0; i < numCustomers; i++) {
            int count = 0;
            myKitchen.addDishes(count + 1);
        }

        String testString = "Happy";
        Integer testInt = 10;

        printValue(testString, testInt);

        int value = 1;

        Integer valueWrapped = Integer.valueOf(value);

        ArrayList<Character> myChars = new ArrayList<>();

        myChars.add('a');
        myChars.add('b');
        myChars.add('c');

        System.out.println(myChars.size());

        System.out.println(myChars);

    }

    public static <T> void printValue(T thing, T otherThing) {
        System.out.println(thing);
        System.out.println(otherThing);
    }

    public static <T extends Comparable<T>> T returnValue(T thing) {
        return thing;
    };
}

class CompanyStack<T> {

     private ArrayList<T> stackData;

    CompanyStack() {
    
        stackData = new ArrayList<>();
    }

    public void push(T x) {
        stackData.add(x);
    }

    public T pop() {
        T temp = stackData.remove(stackData.size() - 1);
        return temp;
    }

    public T peek() {
        return stackData.get(stackData.size() - 1);
    }

    public int getSize() {
        return stackData.size();
    }
}

class Customer implements Comparable<Customer> {

    private int money;
    private String name;
    private boolean hasMembership;

    Customer(int money, String name, boolean hasMembership) {
        this.money = money;
        this.name = name;
        this.hasMembership = hasMembership;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setMembership(boolean hasMembership) {
        this.hasMembership = hasMembership;
    }

    public void complain() {
        System.out.println("I've been waiting too long");
    }

    @Override
    public int compareTo(Customer o) {
        
        if (this.hasMembership && !o.hasMembership) {
             return 1; // This customer has a membership, so prioritize over the other customer

        } else if (!this.hasMembership && o.hasMembership) {
           
            return -1; // This customer doesn't have a membership, so deprioritize compared to the other customer

        } else {
            // Both customers have the same membership status, compare based on money
            return Integer.compare(o.money, this.money); // Higher money first
        }
    }
}

class Kitchen implements Dishes{

    private int workers;
    private String cleanliness;
    CompanyStack<Integer> dishes;

    Kitchen(int workers, String cleanliness) {

        this.workers = workers;
        this.cleanliness = cleanliness;
        dishes = new CompanyStack<>();
    }

    public void setCleanStatus(String cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getWorker() {
        return workers;
    }

    public String getCleanliness() {
        return cleanliness;
    }

    @Override
    public void cleanDishes() {
        System.out.println("Cleaning Dishes");
    }

    @Override
    public void sortDishes() {
        System.out.println("Sorting Dishes");
    }

    @Override
    public void addDishes(int dishes) {
        this.dishes.push(dishes);
    }
}

interface Dishes {

    void cleanDishes();

    void sortDishes();

    void addDishes(int dishes);
}