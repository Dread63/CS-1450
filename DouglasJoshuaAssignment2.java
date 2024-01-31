import java.util.*;
import java.io.*;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 02.01.24
 * Assignment #2
 * Description: This program is designed to review the manipulation of objects, inheritance, and polymorphism. The code creates a ski parent class with subclasses Cross-Country,
 * Downhill, and Snowboard. Then, these skis will be place into a polymorphic array and storted into the "ski shop" depending on their type. Lastly, the code will print the 
 * details of the ski shop in a nicely formatted table for the end user.
 */

public class DouglasJoshuaAssignment2 {

    public static void main(String[] args) throws IOException {
        
        // Declaring neccisary variables
        File skisTxt = new File("Skis.txt");
        Scanner scanner = new Scanner(skisTxt);
        Ski[] skis = new Ski[scanner.nextInt()];
        int index = 0;

        // Reading from the skis.txt file until hitting a blank line
        while (scanner.hasNextLine()) {

            String type = scanner.next();
            String brand = scanner.next() + " " + scanner.nextLine();

            if (type.equals("snowboard")) {

                skis[index] = new Snowboard(brand);
                index++;
            }

            if (type.equals("cross-country")) {

                skis[index] = new CrossCountry(brand);
                index++;
            }

            if (type.equals("downhill")) {

                skis[index] = new Downhill(brand);
                index++;
            }
        }

        // Printing ski type, brand, and description using a for loop
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-25s%-10s\n", "Type", "Brand", "Description");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < skis.length; i++) {

            System.out.printf("%-15s%-25s%-15s\n", skis[i].getType(), skis[i].getBrand(), skis[i].description());
        }

        System.out.println();

        // Passing data to skiShop to fill and print for the user
        SkiShop skiShop = new SkiShop();
        skiShop.fillSkiShop(skis);
        skiShop.printSkiShopDetails();
    }
}

// Ski parent class with constructor and getters
class Ski {

    private String type;
    private String brand;

    public Ski(String type, String brand) {
        this.type = type;
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    // This method will be overriden by subclasses
    public String description() {
        return type + " " + brand;
    }
}

// Child class of ski with unique constructor and description
class CrossCountry extends Ski {

    public CrossCountry(String brand) {

        super("Cross Country", brand); // Call to parent "ski" constructor
    }

    @Override
    public String description() {
        return "Long & thin skis with binding that allows heal to be free";
    }
}

// Child class of ski with unique constructor and description
class Downhill extends Ski {

    public Downhill(String brand) {

        super("Downhill", brand); // Call to parent "ski" constructor
    }

    @Override
    public String description() {
        return "All-mountain skis where width depends on skill level";
    }
}

// Child class of ski with unique constructor and description
class Snowboard extends Ski {

    public Snowboard(String brand) {
            
        super("Snowboard", brand); // Call to parent "ski" constructor
    }

    @Override
    public String description() {
        return "Single board where both feet are secured onto board in two bindings";
    }
}

// Class which will be filled with only downhill and cross country skis
class SkiShop {

    // Ints to county skis, array where those skis will be stored
    private int numDownhill;
    private int numCrossCountry;
    private Ski[] skisForSale;

    public void fillSkiShop(Ski[] skis) {

        int index = 0;
        numDownhill = 0;
        numCrossCountry = 0;
         
        // For loop to determine the proper length of the skisForSale array
        for (int i = 0; i < skis.length; i++) {

            if (skis[i] instanceof Downhill) {
                    
                numDownhill++;
            }

            if (skis[i] instanceof CrossCountry) {
                    
                numCrossCountry++;
            }
        }

        skisForSale = new Ski[numCrossCountry + numDownhill]; // Length of array initialized

        // For loop to fill skisForSale with only the downhill and crosscountry skis
        for (int i = 0; i < skis.length; i++) {

            if (skis[i] instanceof Downhill || skis[i] instanceof CrossCountry) {
                skisForSale[index] = skis[i];
                index++;
            }
        }
    }

    // Print what skis are for sale
    public void printSkiShopDetails() {

        int numDownhill = 0;
        int numCrossCountry = 0;

        // Tell customer how many of each are available
        for (int i = 0; i < skisForSale.length; i++) {

            if (skisForSale[i] instanceof Downhill) {
                numDownhill++;
            }

            if (skisForSale[i] instanceof CrossCountry) {
                numCrossCountry++;
            }  
        }

        System.out.println("----------------------------Ski Shop---------------------------");
        System.out.println("Downhill skis available: " + numDownhill);
        System.out.println("Cross-country skis available: " + numCrossCountry);
        
        // Print the type, brand, and description of each ski
        for (int i = 0; i < skisForSale.length; i++) {

            if (skisForSale[i] instanceof Downhill) {
                System.out.println(skisForSale[i].getType() + " --- " + skisForSale[i].getBrand());
            }
        }

        for (int i = 0; i < skisForSale.length; i++) {

            if (skisForSale[i] instanceof CrossCountry) {
                System.out.println(skisForSale[i].getType() + " --- " + skisForSale[i].getBrand());
            }
        }
    }
}
