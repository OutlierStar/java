package demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class demo1 {
    public static void main(String[] args) {
        File fname = new File("./src/demo1/data.csv");
        InputStreamReader fr = null;
        BufferedReader br = null;

        try {
            fr = new InputStreamReader(new FileInputStream(fname), "UTF-8");
            br = new BufferedReader(fr);
            String rec = null;
            String[] argsArr = null;

            String[][] s = new String[50][5];
            int count = 0;
            while ((rec = br.readLine()) != null) {
                argsArr = rec.split(",");
                for (int i = 0; i < argsArr.length; i++) {
                    s[count][i] = argsArr[i];
                }
                if (valid(s[count][2]) && valid(s[count][3]) && valid(s[count][4])) {
                    
                } else {
                    count--;
                }
                count++;
            }

            Student[] stu = new Student[count - 1];
            for (int i = 1; i < count; i++) {
                stu[i - 1] = new Student();
                stu[i - 1].setId(s[i][0]);
                stu[i - 1].setName(s[i][1]);
                stu[i - 1].setYuWenScore(Float.parseFloat(s[i][2]));
                stu[i - 1].setShuXueScore(Float.parseFloat(s[i][3]));
                stu[i - 1].setYingYuScore(Float.parseFloat(s[i][4]));
                stu[i - 1].countAllScore();
            }
            // for (int i = 0; i < count-1; i++) {
            // System.out.println(i + "-" +stu[i]);
            // }
            Comparator<Student> comp = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if (o1.getAllScore() < o2.getAllScore()) {
                        return 1;
                    } else if (o1.getAllScore() > o2.getAllScore()) {
                        return -1;
                    }
                    return 0;
                }
            };
            Arrays.sort(stu, comp);

            System.out.print("次序  ");
            // System.out.print("学号 ");
            System.out.print("姓名  ");
            System.out.print("语文成绩  ");
            System.out.print("数学成绩  ");
            System.out.print("英语成绩  ");
            System.out.println("总成绩  ");
            for (int i = 0; i < 10; i++) {
                System.out.print((i + 1) + "    ");
                // System.out.print(stu[i].getId() + " ");
                System.out.print(stu[i].getName() + "   ");
                System.out.print((int) stu[i].getYuWenScore() + "         ");
                System.out.print((int) stu[i].getShuXueScore() + "        ");
                System.out.print((int) stu[i].getYingYuScore() + "        ");
                System.out.println((int) stu[i].getAllScore() + "       ");

                // File f = new File("./src/demo1/"+stu[i].getId() + stu[i].getName() + ".txt");
                // FileOutputStream fos = new FileOutputStream(f);
                // OutputStreamWriter dos = new OutputStreamWriter(fos,"UTF-8");
                // dos.write("学号：" + stu[i].getId() + "\r\n");
                // dos.write("姓名：" + stu[i].getName() + "\r\n");
                // dos.write("语文成绩：" + (int) stu[i].getYuWenScore() + "\r\n");
                // dos.write("数学成绩：" + (int) stu[i].getShuXueScore() + "\r\n");
                // dos.write("英语成绩：" + (int) stu[i].getYingYuScore() + "\r\n");
                // dos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null)
                    fr.close();
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public static boolean valid(String str) {
        String regex = "^(100|[1-9][0-9]{0,1}|0)$";
        if (str.matches(regex)) {
            return true; // 验证通过
        }
        return false;
    }

}
