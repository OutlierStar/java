package demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class demo1 {
    public static void main(String[] args){
    File fname = new File("data.csv");
	InputStreamReader fr = null;  
	BufferedReader br = null;  
	try {  
		fr = new InputStreamReader(new FileInputStream(fname));  
		br = new BufferedReader(fr);  
		String rec = null;  
		String[] argsArr = null;  
		
		
		
		String [][]s = new String[42][6];
		int count = 0;
		while ((rec = br.readLine()) != null) {  
			argsArr = rec.split(","); 
			for (int i = 0; i < argsArr.length; i++)
			{
				s[count][i] = argsArr[i];
			}
			count++;
		}
		
		Student[] stu = new Student[50];
		for(int i = 1; i < count; i++)
		{
			stu[i] = new Student();
            stu[i].setId(s[i][0]);
            stu[i].setName(s[i][1]);
            stu[i].setYuWenScore(Float.parseFloat(s[i][2]));
            stu[i].setShuXueScore(Float.parseFloat(s[i][3]));
            stu[i].setYuWenScore(Float.parseFloat(s[i][4]));
            
		}
		
		
		
		for(int i = 1; i < count; i++)
		{
			System.out.println(stu[i]);
		}
		
		
		for(int i = 1; i < count; i++)
		{
			File f=new File(stu[i].getId() + stu[i].getName() + ".txt");
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter dos = new OutputStreamWriter(fos);
			dos.write("学号：" + stu[i].getId() + "\r\n");
			dos.write("姓名：" + stu[i].getName() + "\r\n");
			dos.write("平时成绩：" + (int) stu[i].getPingShiScore() + "\r\n");
			dos.write("实验成绩：" + (int) stu[i].getShiYanScore() + "\r\n");
			dos.write("期末成绩：" + (int) stu[i].getQiMoScore() + "\r\n");
			dos.write("综合成绩：" + (int) stu[i].getZongHeScore() + "\r\n");
			
			dos.close();
        }
		 
	}
	catch (IOException e)
	{  
		e.printStackTrace();  
	}
	finally
	{  
		try
		{  
			if (fr != null)  
				fr.close();  
			if (br != null)  
				br.close();  
		}
		catch (IOException ex)
		{  
			ex.printStackTrace();  
		}  

    }
}
