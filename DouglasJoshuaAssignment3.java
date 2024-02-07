import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DouglasJoshuaAssignment3 {
    public static void main(String[] args) throws IOException {
        
        ArrayList<Employee> apollo = new ArrayList<Employee>();

        File employeeInfo = new File("employees.txt");

        Scanner fileReader = new Scanner(employeeInfo);

        int index = fileReader.nextInt();
        
        for (int i = 0; i <= index; i++) {

            if (fileReader.next().equals("arc")) {

                String name = fileReader.next();
                int designAbility  = fileReader.nextInt();

                Architect architectEmployee = new Architect(name, designAbility);
                apollo.add(architectEmployee);
                fileReader.nextLine();
            }

            else if (fileReader.next().equals(("qae"))) {

                String name = fileReader.next();
                fileReader.next();
                fileReader.next();
                int testAbility = fileReader.nextInt();
                fileReader.nextLine();

                QAEngineer qaEngineer = new QAEngineer(name, testAbility);
                apollo.add(qaEngineer);
            }

            else if (fileReader.next().equals("swe")) { 

                String name = fileReader.next();
                fileReader.next();
                int developAbility = fileReader.nextInt();
                int testAbility = fileReader.nextInt();
                SWEngineer swEngineer = new SWEngineer(name, developAbility, testAbility);
                apollo.add(swEngineer);

                fileReader.nextLine();
            }

            else if (fileReader.next().equals("swm")) {

                String name = fileReader.next();
                fileReader.next();
                fileReader.next();
                fileReader.next();
                int managerAbility = fileReader.nextInt();
                TeamManager manager = new TeamManager(name, managerAbility);
                apollo.add(manager);

                fileReader.nextLine();
            }
        }

        for (int i = 0; i < apollo.size(); i++) {
            System.out.println(apollo.get(i).getName());
        }
    }

    public static void displayEmployees(ArrayList<Employee> employees, boolean showAbilities) {

        System.out.println("------------------------------------------------");
        System.out.printf("%10s%10s%10s%10s%10s%10s%10s", "Role", "Name", "Manage", "Design", "Develop", "Test", "Total");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < employees.size(); i++) {

            if (showAbilities == true) {
                System.out.printf("%10s%10s", employees.get(i).getRole(), employees.get(i).getName());
                displayAbilities(employees.get(i));
            }

            else {
                System.out.printf("%10s%10s%10s", employees.get(i).getRole(), employees.get(i).getName(), employees.get(i).duties());
            }
        }
    }

    public static void displayAbilities(Employee employee) {

        if (employee instanceof Designer) {
            System.out.println()
        }
    }

    public static ArrayList<Employee> buildTeam(ArrayList<Employee> employees, int numEmployeesNeeded) {


    }

    public static int findBestDeveloperAndTester(ArrayList<Employee> employees) {

    }
}

abstract class Employee {

    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract String duties();
}

class SWEngineer extends Employee implements Developer, Tester {

    private int testAbility;
    private int developAbility;

    public SWEngineer(String name, int developAbility, int testAbility) {
        
        setName(name);
        setRole("SW Engineer");
        this.developAbility = developAbility;
        this.testAbility = testAbility;
    }

    @Override
    public String duties() {
        return("I develop, test, and debug code on a daily basis.");
    }

    @Override
    public int test() {
       return(testAbility);
    }

    @Override
    public int develop() {
        return(developAbility);
    }
    
}

class QAEngineer extends Employee implements Tester {

    private int testAbility;

    QAEngineer (String name, int testAbility) {

        setName(name);
        setRole("QA Engineer");
        this.testAbility = testAbility;
    }

    @Override
    public String duties() {
        return("I test code on a daily basis.");
    }

    @Override
    public int test() {
        return testAbility;
    }
    
}

class Architect extends Employee implements Designer {

    private int designAbility;

    Architect(String name, int designAbility) {

        setName(name);
        setRole("Architect");
        this.designAbility = designAbility;
    }

    @Override
    public String duties() {
        return("I design systems and interconnections between systems.");
    }

    @Override
    public int design() {
       return designAbility;
    }
    
}

class TeamManager extends Employee implements Manager {

    private int managerAbility;

    TeamManager(String name, int managerAbility) {

        setName(name);
        setRole("Manager");
        this.managerAbility = managerAbility;
    }

    @Override
    public String duties() {
        return("I manage a development team. It's like herding cats!");
    }

    @Override
    public int manage() {
        return managerAbility;
    }
    
}

interface Designer {

    int design();

}

interface Developer {

    int develop();
}

interface Tester {

    int test();
}

interface Manager {

    int manage();
}