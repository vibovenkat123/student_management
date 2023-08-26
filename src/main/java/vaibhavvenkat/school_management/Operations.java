package vaibhavvenkat.school_management;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Operations {
    private Connection conn;
    public HashMap<Integer, Student> students_by_id = new HashMap<Integer, Student>();
    public Operations() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:main.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, grade INTEGER, lunch_money INTEGER, avg_grade INTEGER)");
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student student = new Student();
                student.createInit(rs.getString("name"), rs.getInt("grade"), rs.getInt("lunch_money"), rs.getInt("avg_grade"), rs.getInt("id"));
                students_by_id.put(student.id, student);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database " + e.getMessage());
            System.exit(1);
        }
    }
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
                try {
                    PreparedStatement stmt = this.conn.prepareStatement("UPDATE students SET lunch_money = ? WHERE id = ?");
                    stmt.setInt(1, value.lunch_money);
                    stmt.setInt(2, value.id);
                    students_by_id.put(key, value);
                    System.out.println("Changed money");
                    return;
                } catch (SQLException e) {
                    System.err.println("Error updating database");
                    System.exit(1);
                }
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
                try {
                    PreparedStatement stmt = this.conn.prepareStatement("UPDATE students SET avg_grade = ? WHERE id = ?");
                    stmt.setInt(1, value.grade);
                    stmt.setInt(2, value.id);
                    students_by_id.put(key, value);
                    System.out.println("Changed grade");
                    return;
                } catch (SQLException e) {
                    System.err.println("Error updating database");
                    System.exit(1);
                }
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
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO students (id, name, grade, lunch_money, avg_grade) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, student.id);
            stmt.setString(2, student.name);
            stmt.setInt(3, student.grade);
            stmt.setInt(4, student.lunch_money);
            stmt.setInt(5, student.avg_grade);
            stmt.executeUpdate();
            students_by_id.put(student.id, student);
        } catch (SQLException e) {
            System.err.println("Error updating database");
            System.exit(1);
        }
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
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM students WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            students_by_id.remove(id);
        } catch(SQLException e) {
            System.err.println("Error updating database");
            System.exit(1);
        }
    }
}
