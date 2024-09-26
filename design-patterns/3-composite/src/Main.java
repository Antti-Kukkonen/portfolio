public class Main {
    public static void main(String[] args) {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        Organization organization = new Organization("organization");

        Department department = new Department("department");

        Employee employee = new Employee("employee1", 1000.0);
        Employee employee2 = new Employee("employee2", 2000.0);

        organization.add(department);
        department.add(employee);
        department.add(employee2);

        organization.execute();
    }
}
