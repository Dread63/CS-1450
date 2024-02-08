import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DouglasJoshuaAssignment3 {
    public static void main(String[] args) throws IOException {
        
        ArrayList<Employee> apollo = new ArrayList<Employee>();

        File employeeInfo = new File("employees.txt");

        Scanner fileReader = new Scanner(employeeInfo);

        int numEmployees = fileReader.nextInt(); // Defines how many employee objects are expected to be put into apollo array
        
        // Filling apollo array with each employee and their given names/abilities
        for (int i = 0; i < numEmployees; i++) {

            // Defining lines and their values to avoid if statements moving pointer
            String role = fileReader.next();
            String name = fileReader.next();
            int designAbility  = fileReader.nextInt();
            int developAbility = fileReader.nextInt();
            int testAbility = fileReader.nextInt();
            int managerAbility = fileReader.nextInt();

            if (role.equals("arc")) {

                Architect architectEmployee = new Architect(name, designAbility);
                apollo.add(architectEmployee);
            }

            else if (role.equals(("qae"))) {
                
                QAEngineer qaEngineer = new QAEngineer(name, testAbility);
                apollo.add(qaEngineer);
            }

            else if (role.equals("swe")) { 

                SWEngineer swEngineer = new SWEngineer(name, developAbility, testAbility);
                apollo.add(swEngineer);
            }

            else if (role.equals("swm")) {
                
                TeamManager manager = new TeamManager(name, managerAbility);
                apollo.add(manager);
            }
        }

        // Printing initial apollo team to user
        System.out.println("------------------------------------------------");
        System.out.printf("%10s%10s%10s%10s%10s%10s%10s\n", "Role", "Name", "Manage", "Design", "Develop", "Test", "Total");
        System.out.println("------------------------------------------------");
        displayEmployees(apollo, true);

        // Start of spartan team building
        System.out.println();
        System.out.println("Building Spartans Team....");
        System.out.println();

        findBestDeveloperAndTester(apollo); // Used to take best employees from apollo and place in spartan
        ArrayList<Employee> spartan = buildTeam(apollo, 5); // Array list for new team, created using buildTeam

        // Display spartan team information
        System.out.println("Employees transferred to Spartans Team");
        System.out.println("----------------------------------------");
        System.out.printf("%10s%10s%10s\n", "Role", "Name", "Duties");
        System.out.println("----------------------------------------");
        displayEmployees(spartan, false);
        System.out.println();
        
        // Display changes in apollo team after losing employees
        System.out.println("Employees remaining on Apollo Team");
        System.out.println("----------------------------------------");
        System.out.printf("%10s%10s%10s\n", "Role", "Name", "Duties");
        System.out.println("----------------------------------------");
        displayEmployees(apollo, false);
    }

    // Method to display information about employee objects depending on a boolean flag showAbilities
    public static void displayEmployees(ArrayList<Employee> employees, boolean showAbilities) {

        for (int i = 0; i < employees.size(); i++) {

            if (showAbilities == true) {
                System.out.printf("%10s%10s", employees.get(i).getRole(), employees.get(i).getName());
                displayAbilities(employees.get(i));
            }

            else {
                System.out.printf("%10s%10s%10s\n", employees.get(i).getRole(), employees.get(i).getName(), employees.get(i).duties());
            }
        }
    }

    // Displaying employees abilities by up-casting to their interfaces
    public static void displayAbilities(Employee employee) {

        int designAbility = 0;
        int developAbility = 0;
        int testAbility = 0;
        int managerAbility = 0;
        int total = 0;

        // Upcasts employee to interface if an instance of interface in order to access abstract methods
        if (employee instanceof Designer) {
            designAbility = ((Designer)employee).design();
        }

        if (employee instanceof Developer) {
            developAbility = ((Developer)employee).develop();
        }

        if (employee instanceof Tester) {
            testAbility = ((Tester)employee).test();
        }

        if (employee instanceof Manager) {
            managerAbility = ((Manager)employee).manage();
        }

        total = designAbility + developAbility + testAbility + managerAbility;

        System.out.printf("%10d%10d%10d%10d%10d\n", designAbility, developAbility, testAbility, managerAbility, total);
    }
    
    // Move defined amount of best employees from one team to a new team
    public static ArrayList<Employee> buildTeam(ArrayList<Employee> employees, int numEmployeesNeeded) {

        ArrayList<Employee> newTeam = new ArrayList<Employee>();

        for (int i = 0; i < numEmployeesNeeded; i++) {
            
            int bestEmployee = findBestDeveloperAndTester(employees);
            newTeam.add(employees.get(bestEmployee)); // Adding best employee by referencing index of employees array list
            employees.remove(employees.get(bestEmployee)); // Removing best employee from previous team by referencing index of employees array list
        }

        return newTeam;
    }

    // Finds the object inside of an array list that has the highest combined develop ability and test ability
    public static int findBestDeveloperAndTester(ArrayList<Employee> employees) {

        int highestSkillIndex = 0;
        int highestSkill = 0;
        int developAbility = 0;
        int testAbility = 0;

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i) instanceof Developer) {

                developAbility = ((Developer)employees.get(i)).develop(); // Upcasts to Developer to acces .develop() method
            }
            
            if (employees.get(i) instanceof Tester) {

                testAbility = ((Tester)employees.get(i)).test(); // Upcasts to Tester to ascces .test() method
            }

            int totalSkill = developAbility + testAbility;

            if (totalSkill > highestSkill) {
                highestSkill = totalSkill;
                highestSkillIndex = i;
            }
        }

        return highestSkillIndex;
    }
}

// Abstract super class of all employees encorcing the requirement of a name and role
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

// Concrete subclass of employee with capabilities of a developer and tester
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

// Concrete subclass of employee which has the abilities of a tester
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

// Concrete subclass of employee which has the abilities of a designer
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

// Concrete subclass of employee which has the capabilities of a manager
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

// Interfaces which require each class implementing them to have defined methods
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