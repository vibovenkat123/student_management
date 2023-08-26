package vaibhavvenkat.school_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Operations {
    public HashMap<Integer, Student> students_by_id = new HashMap<Integer, Student>();
    public void getOperation(Options.OPT opt) {
        switch (opt) {
            case CREATE -> create();
            case DELETE -> delete();
            case GET_ALL -> getAll();
            case CHANGE_GRADE -> changeGrade();
            case CHANGE_MONEY -> changeMoney();
            case GET_BY_ID -> getById();
            default -> System.err.println("Invalid option");
        };
    }

    private Integer getID() {
        Integer id = new InputReader().getIntInput("Enter id: ");
        if (id == null) {
            System.err.println("Enter a number for the id");
            return null;
        }
        if (!students_by_id.containsKey(id)) {
            System.err.println("No student with id " + id);
            return null;
        }
        return id;
    }

    public void getById() {
        Integer id = getID();
        if (id == null) {
            return;
        }
        Student student = students_by_id.get(id);
        System.out.printf("id: %d, name: %s, grade: %d, lunch money: %d, avg grade: %d\n", id, student.name, student.grade, student.lunch_money, student.avg_grade);
    }
    public void changeMoney() {
        Integer id = getID();
        if (id == null) {
            return;
        }
        for (Map.Entry<Integer, Student> entry : students_by_id.entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            if (key.equals(id)) {
                Integer money= new InputReader().getIntInput("Enter new money: ");
                if (money == null) {
                    System.err.println("Enter a number for the money");
                    return;
                }
                value.lunch_money = money;
                students_by_id.put(key, value);
                System.out.println("Changed money");
                return;
            }
        }
    }
    public void changeGrade() {
        Integer id = getID();
        if (id == null) {
            return;
        }
        for (Map.Entry<Integer, Student> entry : students_by_id.entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            if (key.equals(id)) {
                Integer grade = new InputReader().getIntInput("Enter new grade: ");
                if (grade == null) {
                    System.err.println("Enter a number for the grade");
                    return;
                }
                value.grade = grade;
                students_by_id.put(key, value);
                System.out.println("Changed grade");
                return;
            }
        }
    }

    public void getAll() {
        for (Map.Entry<Integer, Student> entry : students_by_id.entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            System.out.printf("id: %d, name: %s, grade: %d, lunch money: %d, avg grade: %d\n", key, value.name, value.grade, value.lunch_money, value.avg_grade);
        }
    }
    public void create() {
        InputReader rdr = new InputReader();
        String name = rdr.getInput("Enter name: ");
        Integer grade = rdr.getIntInput("Enter grade: ");
        if (grade == null) {
            System.err.println("Enter a number for the grade");
            return;
        }
        Student student = new Student();
        student.create(name, grade);
        students_by_id.put(student.id, student);
        System.out.printf("Created student with id %d\n", student.id);
    }
    public void delete() {
        InputReader rdr = new InputReader();
        Integer id = getID();
        if (id == null) {
            return;
        }
        Student student = students_by_id.get(id);
        if (student == null) {
            System.err.println("No student with id " + id);
            return;
        }
        boolean isDeleting = rdr.getInput(String.format("Are you sure you want to remove %s? (y/n): ", student.name)).equals("y");
        if (!isDeleting) {
            System.out.println("Not deleting");
            return;
        }
        students_by_id.remove(id);
    }
}
