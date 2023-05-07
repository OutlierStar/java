package demo6;

public class Student {
    private String name;
    private double grade = 0;
    private int flag = 0;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void addGrade(double grade) {
        this.grade += grade;
        flag++;
    }

    public void clear() {
        this.grade=0;
        this.flag=0;        
    }

    public double getGrade() {
        return grade / flag;
    }

}
