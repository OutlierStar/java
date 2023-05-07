package demo5;

import java.util.Scanner;

public class demo {
    public static void main(String[] args) {

        System.out.println("输入产生式的个数");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        char[][] grammar = new char[n][];// 存放文法
        for (int i = 0; i < n; i++) {
            System.out.println("输入第" + (i + 1) + "个产生式");
            grammar[i] = in.next().toCharArray();
        }
        in.close();

        judge(n, grammar);

    }

    public static void judge(int n, char[][] grammar) {
        int type = 0;
        char[][] VN = new char[n][]; // 文法左边
        char[][] VT = new char[n][]; // 文法右边

        for (int i = 0; i < n; i++) {
            String s = String.valueOf(grammar[i]);
            String[] d = s.split(":");
            VN[i] = d[0].toCharArray();
            VT[i] = d[1].toCharArray();
        }


        if(islegal(VN)){
            if(zeroJudge(VN)){
                if(oneJudge(VN, VT)){
                    type=1;
                    if (twoJudge(VN, VT)) {
                        type=2;
                        if (threeJudge(VN, VT)) {
                            type=3;
                        }
                    }
                }
            }else{
                System.out.println("左侧全为终结符,不符合0型文法");
                return;
            }
        }else{
            return;
        }

        switch (type) {

            case 0:
                System.out.println("0型文法");
                break;
            case 1:
                System.out.println("1型文法");
                break;
            case 2:
                System.out.println("2型文法");
                break;
            case 3:
                System.out.println("3型文法");
                break;
            default:
                break;
        }
    }

    public static boolean islegal(char[][] VN) { // 判断文法是否合法
        for (int j = 0; j < VN.length - 1; j++) {
            for(int i=0; i < VN[j].length; i++){
                if (VN[j][i] < 65 || (VN[j][i] > 90 && VN[j][i] < 97) || VN[j][i] > 122) { // 65-90为大写字母，97-122为小写字母
                    System.out.println("含有非法符号，产生式不合法");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean zeroJudge(char[][] VN) { // 判断文法是否为0型文法
        int flag = 0;// 左侧非终结符个数，即大写字母个数
        for (int j = 0; j < VN.length; j++) {
            for (int i = 0; i < VN[j].length; i++) {
                if ((VN[j][i] >= 65 && VN[j][i] <= 90)) { // a-z为终结符,A-Z为非终结符 65-90为大写字母，97-122为小写字母
                    flag++;
                }
            }
        }
        if (flag == 0) {
            return false;
        }
        return true;
    }

    public static boolean oneJudge(char[][] VN, char[][] VT) {
        // 式子右边可以有多个字符，但必须是有限个字符且左边长度必须小于右边
        for (int j = 0; j < VN.length; j++) {
            if (VN[j].length > VT[j].length) {
                return false;
            }
        }
        return true;
    }

    public static boolean twoJudge(char[][] VN, char[][] VT) {
        // 式子左边可以有多个字符，但必须有一个非终结符；
        for (int j = 0; j < VN.length; j++) {
            int flag=0;// 大写字母个数
            for (int i = 0; i < VN[j].length; i++) {
                if (VN[j][i] >= 65 && VN[j][i] <= 90) { // a-z为终结符,A-Z为非终结符 65-90为大写字母，97-122为小写字母
                    flag++;
                }
            }
            if (flag==0) {
                return false;
            }
        }
        return true;

    }

    public static boolean threeJudge(char[][] VN, char[][] VT) {
        for (int j = 0; j < VN.length; j++) {
            if ((1 == VN[j].length) && (VN[j][0] >= 65 && VN[j][0] <= 90)) {
                
            }else{
                return false;
            }

            if ((1 == VT[j].length) && (VT[j][0] >= 97 && VT[j][0] <= 122)) {
                
            }else if (2 == VT[j].length) {
                // if ((VT[j][0] >= 65 && VT[j][0] <= 90) && (VT[j][1] >= 97 && VT[j][1] <= 122)) {

                // }else{
                //     return false;
                // }
                if ((VT[j][0] >= 97 && VT[j][0] <= 122) && (VT[j][1] >= 65 && VT[j][1] <= 90)) {

                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;

    }

}
