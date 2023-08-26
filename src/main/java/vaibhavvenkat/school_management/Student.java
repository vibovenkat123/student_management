package vaibhavvenkat.school_management;

public class Student {
    public String name;
    public int grade;
    public int lunch_money;
    public Integer avg_grade;
    public int id;
    public void createInit(String name, int grade, int lunch_money, int avg_grade, int id) {
        this.name = name;
        this.grade = grade;
        this.lunch_money = lunch_money;
        this.avg_grade = avg_grade;
        this.id = id;
    }
    public void create(String name, int grade) {
        this.name = name;
        this.grade = grade;
        this.lunch_money = 0;
        this.avg_grade = null;
        this.id = (int) (Math.random() * 90000) + 10000;
    }
}
