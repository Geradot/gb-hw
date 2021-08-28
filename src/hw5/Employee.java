package hw5;

public class Employee {
    private String fullName;
    private String positionHeld;
    private String email;
    private String phone;
    private int salary;
    private byte age;

    public Employee(String fullName, String positionHeld, String email, String phone, int salary, byte age) {
        this.fullName = fullName;
        this.positionHeld = positionHeld;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public byte getAge() {
        return age;
    }

    public void showDetails() {
        System.out.println("ФИО: " + this.fullName);
        System.out.println("Должность: " + this.positionHeld);
        System.out.println("Email: " + this.email);
        System.out.println("Телефон: " + this.phone);
        System.out.println("Зарплата: " + this.salary);
        System.out.println("Возраст: " + this.age);
    }
}
