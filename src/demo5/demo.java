package demo5;

import java.util.Scanner;


public class demo {
    public static void main(String[] args) {

        System.out.println("输入产生式的个数");
        Scanner ip = new Scanner(System.in) ; 
        int n=ip.nextInt();


        char[][] grammar  = new char[n][];// 存放文法
        for (int i = 0; i < n; i++) {
            System.out.println("输入第" + (i + 1) + "个产生式");
            grammar[i] = in.next().toCharArray();
        }

        char[][] VN = new char[t][];
        char[][] VT = new char[t][];

        for (int i = 0; i < t - 1; i++) {
            String s = String.valueOf(grammar[i]);
            String[] d = s.split(":");
            a1 = d[0].toCharArray();
            a2 = d[1].toCharArray();
            VN[i] = a1;
            VT[i] = a2;
        }

    }

    public static boolean zeroJudge(char left[]) {
        int i = 0;
        while (left[i] != '\n') {
            if (left[i] < 65 || (left[i] > 90 && left[i] < 97) || left[i] > 122) { // 65-90为大写字母，97-122为小写字母
                System.out.println("含有非法符号，产生式不合法");
                return false;
            } else if ((left[i] >= 97 && left[i] <= 122)) { // a-z为终结符,A-Z为非终结符
                if (i == (left.length - 1)) {
                    System.out.println("左侧全为终结符，不合法产生式");
                    return false;
                }
            } else if (left[i] >= 65 && left[i] <= 90) {
                // 合法产生式
                break;
            }
            i++;
        }
        return true;
    }

    
}
