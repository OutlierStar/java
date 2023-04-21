import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		int flag=4;
		char[] a1;
		char[] a2;
		String str;
        System.out.println("输入产生式的个数");
        Scanner ip = new Scanner(System.in) ; 
        int t=ip.nextInt();
        char[][] num = new char[t][];//定义数组
		char[][] left=new char[t][];
		char[][] right=new char[t][];
        //输入
        Scanner in = new Scanner(System.in); 
            for(int i=0;i<t;i++)
            {   System.out.println("输入第"+(i+1)+"个产生式");
            	num[i]=in.next().toCharArray(); 
            	}
        for(int i=0;i<t-1;i++)
        { 
        	String s=String.valueOf(num[i]);
        	String[]d=s.split(":");
        	a1=d[0].toCharArray(); 
        	a2=d[1].toCharArray(); 
        	left[i]=a1;
        	right[i]=a2;
        	if(zeroJudge(left[i]))
    		{
    			if(oneJudge(left[i],right[i]))
    			{
    				if(twoJudge(left[i],right[i]))
    				{
    					if(threeJudge(left[i],right[i]))
    					{
    						if(flag < 3)
    							;
    						else
    							flag = 3;
    					}
    					else{
    						if(flag < 2)
    							;
    						else
    							flag = 2;
    					}
    				}
    				else{
    					if(flag < 1)
    						;
    					else
    						flag = 1;
    				}
    			}
    			else{
    				flag = 0;
    			}
    		}
    	}
        
        judge(flag);

	}

	
	public static boolean zeroJudge(char left[])  {
		int i=0;
		while(left[i] != '\n'){
			if(left[i] < 65 || (left[i] > 90 && left[i] < 97) || left[i] > 122){
				System.out.println("含有非法符号，不合法产生式");
				return false;
			}
			else 
			if((left[i] >= 97 && left[i] <= 122)){
				if(i == (left.length-1)){
					System.out.println("左侧全为终结符，不合法产生式");
					return false;
				}
			}
			else if(left[i] >= 65 && left[i] <= 90){
				//合法产生式
				break;
			}
			i++;
		}
		return true;
	}

    public static boolean oneJudge(char left[],char right[])  {
    	if(right.length >= left.length)
    	{
    		return true;
    	}
    	return false;
    	
    }
    public static boolean twoJudge(char left[],char right[])  {
    	if((1 == left.length) && (left[0] >= 65 && left[0] <= 90))
    	{
    		return true;
    	}
    	return false;
    	
    }
    public static boolean threeJudge(char left[],char right[])  {
    	if((1 == right.length) && (right[0] >= 97 && right[0] <= 122))
    	{
    		return true;
    	}
    	if(2 == right.length){
    		if((right[0] >= 65 && right[0] <= 90) && (right[1] >= 97 && right[1] <= 122))
    		{
    			return true;
    		}
    		if((right[0] >= 97 && right[0] <= 122) && (right[1] >= 65 && right[1] <= 90))
    		{
    			return true;
    		}
    	}
    	return false;
    	
    }
    public static void judge(int flag)
    {
    	switch(flag)
    	{
    	case 0:
    		System.out.println("0型文法!");
    		break;
    	case 1:
    		System.out.println("1型文法!");
    		break;
    	case 2:
    		System.out.println("2型文法!");
    		break;
    	case 3:
    		System.out.println("3型文法!");
    		break;
    	}
    }
}




