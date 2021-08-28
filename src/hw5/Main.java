package hw5;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван", "Инженер", "ivanovivan@mailbox.com", "892312311", 30000, (byte) 30);
        employees[1] = new Employee("Петров Петр", "Программист", "petrovpetr@mailbox.com", "892312312", 40000, (byte) 40);
        employees[2] = new Employee("Андреев Андрей", "Начальник отдела GUO", "andreevandrei@mailbox.com", "892312313", 60000, (byte) 45);
        employees[3] = new Employee("Сергеев Сергей", "Повар", "sergeevsergei@mailbox.com", "892312314", 30000, (byte) 39);
        employees[4] = new Employee("Антонова Антонина", "Бухгалтер", "antonovatonya@mailbox.com", "892312315", 50000, (byte) 55);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() >= 40)
                employees[i].showDetails();
            System.out.println();
        }
    }
}
