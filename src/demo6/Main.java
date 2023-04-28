package demo6;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
                  
    /* 
     * 输入代码,请保留  
     * 数组inputs对应控制台输入
     * inputs[0]: title-论文标题；
     * inputs[1]: edition-最终需要恢复的论文版本；
     * inputs[2-4]: contents-3个版本的论文内容；
     * inputs[5]: sequence，教师类型与评分次序，1-按字数评分的教师，2-按标题评分的教师
     *  */
    
    Scanner sc = new Scanner(System.in);
    String[] inputs = new String[6];
    for(int i=0;i<inputs.length;i++){
        inputs[i]=sc.nextLine();
    }
    sc.close();
    
    String title = inputs[0];        
    String edition = inputs[1];
    String[] contents = {inputs[2],inputs[3],inputs[4]};
    String sequence = inputs[5];
    
    /* 构建批阅职责链，设置教师批改策略 */

    // Handler handlers = new WordCountHandler();
    Chain chain = new Chain();

    String pattern = "\\d";

    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(sequence);
    while (m.find()) {
        try {
            Handler handler;
            if ( "1".equals(m.group()) ) {
                handler = new WordCountHandler();
            } else if ("2".equals(m.group())) {
                handler = new TitleCountHandler();
            }else{
                continue;
            }
            chain.addHandler(handler);
        } catch (Exception e) {
            // TODO: handle exception
            // System.out.println("ERROR: parseInputs error");
        }
    }

    /* 准备数据 */
    Student student = new Student();
    Document document = new Document(title);

    System.out.println("title: " + document.getTitle());
    /* 准备数据 */
    
    int i=1;
    for (String content : contents) {
        document.edit(content); //修改论文
        chain.handle(student,document); //评阅论文
        System.out.println("edition " + i + ", score: " + String.format("%.2f", student.getGrade()) );
        student.clear();
        document.backup();  //备份论文
        i++;
    }
    
    document.restore(Integer.parseInt(edition));  //恢复论文之前的版本
    chain.handle(student,document); //评阅论文
    System.out.println("edition " + Integer.parseInt(edition) + ", score: " +  String.format("%.2f", student.getGrade()) );
    }
}

abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handle(Student student, Document document);
}

class WordCountHandler extends Handler {
    private GradeStrategy strategy = new CharCountGradeStrategy();

    public void handle(Student student, Document document) {
        double grade = strategy.grade(student,document);
        student.addGrade(grade);
        if (successor != null) {
            successor.handle(student, document);
        }
    }
}

class TitleCountHandler extends Handler {
    private GradeStrategy strategy = new TitleCountGradeStrategy();

    public void handle(Student student, Document document) {
        double grade = strategy.grade(student,document);
        student.addGrade(grade);
        if (successor != null) {
            successor.handle(student, document);
        }
    }
}

class NullHandler extends Handler {
    public void handle(Student student, Document document) {
        if (successor != null) {
            successor.handle(student, document);
        }
    }
}

class Chain {
    private Handler head = new NullHandler();

    public void addHandler(Handler handler) {
        Handler tail = head;
        while (tail.successor != null) {
            tail = tail.successor;
        }
        tail.successor = handler;
    }

    public void handle(Student student, Document document) {
        head.handle(student, document);
    }
}

/**
 * 策略模式：定义评分策略接口
 */
interface GradeStrategy {
    /**
     * 计算评分
     * 
     * @param student  学生对象
     * @param document 论文对象
     * @param sequence 评分教师序列
     * @return 评分
     */
    public double grade(Student student, Document document);
}

/**
 * 具体策略类
 * 
 */

// 实现按照字数评分的策略
class CharCountGradeStrategy implements GradeStrategy {

    @Override
    public double grade(Student student, Document document) {
        double score = document.getContent().length();
        return score > 100 ? 100 : score;
    }

}

// 实现按照标题出现次数评分的策略
class TitleCountGradeStrategy implements GradeStrategy {

    @Override
    public double grade(Student student, Document document) {
        String title = document.getTitle().toLowerCase();
        String content = document.getContent().toLowerCase();
        int count = 0;
        int index = 0;
        while ((index = content.indexOf(title, index)) != -1) {
            count++;
            index += title.length();
        }
        double score = count * 20.0;
        return score > 100 ? 100 : score;
    }
}