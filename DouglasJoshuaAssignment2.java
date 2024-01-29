import java.util.*;
import java.io.*;
/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 02.01.24
 * Assignment #2
 * Description: 
 */

public class DouglasJoshuaAssignment2 {
    public static void main(String[] args) throws IOException {
        
        File skisTxt = new File("Skis.txt");
        Scanner scanner = new Scanner(skisTxt);

        Ski[] skis = new Ski[scanner.nextInt()];
        int index = 0;

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

        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-25s%-10s\n", "Type", "Brand", "Description");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < skis.length; i++) {

            System.out.printf("%-15s%-25s%-15s\n", skis[i].getType(), skis[i].getBrand(), skis[i].description());
        }

        System.out.println();

        SkiShop skiShop = new SkiShop();
        skiShop.fillSkiShop(skis);
        skiShop.printSkiShopDetails();
    }
}

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

    public String description() {
            return type + " " + brand; // CHECK THIS
    }
}

class CrossCountry extends Ski {

    public CrossCountry(String brand) {

        super("Cross Country", brand);
    }

    @Override
    public String description() {
        return "Long & thin skis with binding that allows heal to be free";
    }
}

class Downhill extends Ski {

    public Downhill(String brand) {

        super("Downhill", brand);
    }

    @Override
    public String description() {
        return "All-mountain skis where width depends on skill level";
    }
}

class Snowboard extends Ski {

    public Snowboard(String brand) {
            
        super("Snowboard", brand);
    }

    @Override
    public String description() {
        return "Single board where both feet are secured onto board in two bindings";
    }
}

class SkiShop {

    private int numDownhill;
    private int numCrossCountry;
    private Ski[] skisForSale;

    public void fillSkiShop(Ski[] skis) {

        int index = 0;
        numDownhill = 0;
        numCrossCountry = 0;
            
        for (int i = 0; i < skis.length; i++) {

            if (skis[i] instanceof Downhill) {
                    
                numDownhill++;
            }

            if (skis[i] instanceof CrossCountry) {
                    
                numCrossCountry++;
            }
        }

        skisForSale = new Ski[numCrossCountry + numDownhill];

        for (int i = 0; i < skis.length; i++) {

            if (skis[i] instanceof Downhill || skis[i] instanceof CrossCountry) {
                skisForSale[index] = skis[i];
                index++;
            }
        }
    }

    public void printSkiShopDetails() {

        int numDownhill = 0;
        int numCrossCountry = 0;

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
