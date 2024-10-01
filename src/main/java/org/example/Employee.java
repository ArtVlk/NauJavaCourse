package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public Employee(){
        this.fullName = "";
        this.age = 0;
        this.department = "";
        this.salary = 0.0;
    }

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void run(){
        System.out.println("№3");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван Иванов", 30, "IT", 50000.0));
        employees.add(new Employee("Пётр Петров", 25, "HR", 45000.0));
        employees.add(new Employee("Михаил Михайлов", 35, "Finance", 60000.0));
        employees.add(new Employee("Алексей Алёшкин", 28, "Marketing", 55000.0));
        employees.add(new Employee("Роберт Робертов", 40, "Sales", 70000.0));

        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();

        sortedEmployees.forEach(System.out::println);
        System.out.println('\n');
    }
}
