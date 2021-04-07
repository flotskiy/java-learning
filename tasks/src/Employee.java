import java.util.*;

public class Employee implements Comparable<Employee> {
    String name;
    int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee employee) {
        if (getSalary() > employee.getSalary()) {
            return -1;
        }
        if (getSalary() < employee.getSalary()) {
            return 1;
        }
        return getName().compareTo(employee.getName());
    }

    public static void main(String[] args) {

        List<Employee> staffList = createStaff();

        System.out.println("-- TreeSet test --");
        Set<Employee> staffSet = new TreeSet<>(staffList);
        testPrint(staffSet);

        System.out.println("-- List sorting test --");
        System.out.println("Before:");
        testPrint(staffList);
        System.out.println("After:");
        sortBySalaryAndAlphabet(staffList);
        testPrint(staffList);
    }

    public static List<Employee> createStaff() {
        List<Employee> staff = new ArrayList<>();
        staff.add(new Employee("Иванов Иван", 75000));
        staff.add(new Employee("Петров Петр", 100000));
        staff.add(new Employee("Сидоров Олег", 99000));
        staff.add(new Employee("Джавов Василий", 75000));
        staff.add(new Employee("Краснозвездов Семен", 75000));
        staff.add(new Employee("Петров Александр", 75000));
        staff.add(new Employee("Алферов Роман", 109000));
        staff.add(new Employee("Федоров Андрей", 300009));
        staff.add(new Employee("Васильев Василий", 14000));
        staff.add(new Employee("Штрафов Артем", 75000));
        return staff;
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort(
                Comparator.comparingInt(Employee::getSalary).reversed()
                        .thenComparing(Employee::getName)
        );
    }

    private static void testPrint(Collection<Employee> list) {
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
        System.out.println();
    }
}
