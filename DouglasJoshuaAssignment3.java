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
        
        for (int i = 0; i < index; i++) {

            String currentLine = fileReader.next();

            if (currentLine.equals("arc")) {

                String name = fileReader.next();
                int designAbility  = fileReader.nextInt();
                int developAbility = fileReader.nextInt();
                int testAbility = fileReader.nextInt();
                int managerAbility = fileReader.nextInt();

                Architect architectEmployee = new Architect(name, designAbility);
                apollo.add(architectEmployee);
            }

            else if (currentLine.equals(("qae"))) {

                String name = fileReader.next();
                int designAbility = fileReader.nextInt();
                int developAbility = fileReader.nextInt();
                int testAbility = fileReader.nextInt();
                int managerAbility = fileReader.nextInt();
                
                QAEngineer qaEngineer = new QAEngineer(name, testAbility);
                apollo.add(qaEngineer);
            }

            else if (currentLine.equals("swe")) { 

                String name = fileReader.next();
                int designAbility = fileReader.nextInt();
                int developAbility = fileReader.nextInt();
                int testAbility = fileReader.nextInt();
                int managerAbility = fileReader.nextInt();

                SWEngineer swEngineer = new SWEngineer(name, developAbility, testAbility);
                apollo.add(swEngineer);
            }

            else if (currentLine.equals("swm")) {

                String name = fileReader.next();
                int designAbility = fileReader.nextInt();
                int developAbility = fileReader.nextInt();
                int testAbility = fileReader.nextInt();
                int managerAbility = fileReader.nextInt();
                
                TeamManager manager = new TeamManager(name, managerAbility);
                apollo.add(manager);
            }
        }

        System.out.println("------------------------------------------------");
        System.out.printf("%10s%10s%10s%10s%10s%10s%10s\n", "Role", "Name", "Manage", "Design", "Develop", "Test", "Total");
        System.out.println("------------------------------------------------");
        displayEmployees(apollo, true);

        System.out.println();
        System.out.println("Building Spartans Team....");
        System.out.println();

        findBestDeveloperAndTester(apollo);
        ArrayList<Employee> spartan = buildTeam(apollo, 5);
        System.out.println("Employees transferred to Spartans Team");
        System.out.println("----------------------------------------");
        System.out.printf("%10s%10s%10s\n", "Role", "Name", "Duties");
        System.out.println("----------------------------------------");
        displayEmployees(spartan, false);
        System.out.println();
        
        System.out.println("Employees remaining on Apollo Team");
        System.out.println("----------------------------------------");
        System.out.printf("%10s%10s%10s\n", "Role", "Name", "Duties");
        System.out.println("----------------------------------------");
        displayEmployees(apollo, false);
    }

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

    public static void displayAbilities(Employee employee) {

        int designAbility = 0;
        int developAbility = 0;
        int testAbility = 0;
        int managerAbility = 0;
        int total = 0;

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
 
    public static ArrayList<Employee> buildTeam(ArrayList<Employee> employees, int numEmployeesNeeded) {

        ArrayList<Employee> newTeam = new ArrayList<Employee>();

        for (int i = 0; i < numEmployeesNeeded; i++) {
            
            int bestEmployee = findBestDeveloperAndTester(employees);
            newTeam.add(employees.get(bestEmployee));
            employees.remove(employees.get(bestEmployee));
        }

        return newTeam;
    }

    public static int findBestDeveloperAndTester(ArrayList<Employee> employees) {

        int highestSkillIndex = 0;
        int highestSkill = 0;
        int developAbility = 0;
        int testAbility = 0;

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i) instanceof Developer) {

                developAbility = ((Developer)employees.get(i)).develop();
            }
            
            if (employees.get(i) instanceof Tester) {

                testAbility = ((Tester)employees.get(i)).test();
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