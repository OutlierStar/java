package demo10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class main {

    static char[] VN = { 'E', 'Z', 'T', 'Y', 'F' }; // 存放非终结符
    static char[] VT = { 'i', '+', '*', '(', ')', '#' }; // 存放终结符
    static char[][][] LLTABLE = new char[5][6][5]; // 预测分析表
    public static void main(String[] args) {
        /*
         * 文法G[S]
         * E->TE'
         * E'->+TE'|ε
         * T->FT'
         * T'->*FT'|ε
         * F->i|(E)
         * 
         * 为了便于操作,E'替换为Z,T'替换为Y
         * 可得
         * E->TZ
         * Z->+TZ|ε
         * T->FY
         * Y->*FY|ε
         * F->i|(E)
         * 
         */

        ArrayList<String> gs = new ArrayList<String>();// 存放文法
        gs.add("E:TZ");
        gs.add("Z:+TZ");
        gs.add("Z:ε");
        gs.add("T:FY");
        gs.add("Y:*FY");
        gs.add("Y:ε");
        gs.add("F:i");
        gs.add("F:(E)");

        int n = gs.size();
        char[][] grammar = new char[n][];// 存放文法
        for (int i = 0; i < n; i++) {
            grammar[i] = gs.get(i).toCharArray();
        }

        // E
        LLTABLE[0][0] = "TZ".toCharArray();
        LLTABLE[0][1] = "".toCharArray();
        LLTABLE[0][2] = "".toCharArray();
        LLTABLE[0][3] = "TZ".toCharArray();
        LLTABLE[0][4] = "".toCharArray();
        LLTABLE[0][5] = "".toCharArray();

        // Z 即E'
        LLTABLE[1][0] = "".toCharArray();
        LLTABLE[1][1] = "+TZ".toCharArray();
        LLTABLE[1][2] = "".toCharArray();
        LLTABLE[1][3] = "".toCharArray();
        LLTABLE[1][4] = "ε".toCharArray();
        LLTABLE[1][5] = "ε".toCharArray();

        // T
        LLTABLE[2][0] = "FY".toCharArray();
        LLTABLE[2][1] = "".toCharArray();
        LLTABLE[2][2] = "".toCharArray();
        LLTABLE[2][3] = "FY".toCharArray();
        LLTABLE[2][4] = "".toCharArray();
        LLTABLE[2][5] = "".toCharArray();

        // Y 即T'
        LLTABLE[3][0] = "".toCharArray();
        LLTABLE[3][1] = "ε".toCharArray();
        LLTABLE[3][2] = "*FY".toCharArray();
        LLTABLE[3][3] = "".toCharArray();
        LLTABLE[3][4] = "ε".toCharArray();
        LLTABLE[3][5] = "ε".toCharArray();

        // F
        LLTABLE[4][0] = "i".toCharArray();
        LLTABLE[4][1] = "".toCharArray();
        LLTABLE[4][2] = "".toCharArray();
        LLTABLE[4][3] = "(E)".toCharArray();
        LLTABLE[4][4] = "".toCharArray();
        LLTABLE[4][5] = "".toCharArray();

        System.out.println("\ti" + "\t+" + "\t*" + "\t(" + "\t)" + "\t#");

        // 打印预测分析表
        int p = 0;
        for (char[][] cs : LLTABLE) {
            System.out.print(VN[p++]);
            for (char[] cs2 : cs) {
                System.out.print("\t");
                System.out.print(cs2);
            }
            System.out.println();
        }

        String str = "i+i*i";
        String str1 = "i*i+i";
        analyzs(str); // 打印i+i*i分析过程
        analyzs(str1); // 打印i*i+i分析过程
    }

    public static int getindexl(String string) { // 从VN中查询
        for (int i=0;i<VN.length;i++) {
            if(VN[i] == string.charAt(0)){
                return i;
            }
        }
        System.out.println("fail" );
        return -1;
    }

    public static int getindexr(String string) { // 从VT中查询
        for (int i=0;i<VT.length;i++) {
            if(VT[i] == string.charAt(0)){
                return i;
            }
        }
        System.out.println("fail" );
        return -1;
    }

    public static String get(String left,String right) { // 从预测分析表查询
        int l = getindexl(left);
        int r = getindexr(right);

        try {
            char[] tmp = LLTABLE[l][r];
            String res = String.valueOf(tmp);
            return res;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("存在不合法字符");
        }
        return "";
        
    }

    public static void analyzs(String str) {
        System.out.println(str + "的分析过程:");
        Queue<String> queue = new LinkedList<>();// 句子拆分存于队列
        for (int i = 0; i < str.length(); i++) {
            String t = str.charAt(i) + "";
            if (i + 1 < str.length() && (str.charAt(i + 1) == '\'' || str.charAt(i + 1) == '’')) {
                t += str.charAt(i + 1);
                i++;
            }
            queue.offer(t);
        }
        queue.offer("#");// "#"结束
        // 分析栈
        Stack<String> stack = new Stack<>();
        stack.push("#");// "#"开始
        stack.push("E");// 初态为开始符号
        boolean isSuccess = false;
        int step = 1;
        while (!stack.isEmpty()) {
            String left = stack.peek();
            String right = queue.peek();

            // 分析成功
            if (left.equals(right) && right.equals("#")) {
                isSuccess = true;
                System.out.println((step++) + "\t#\t#\t" + "分析成功");
                break;
            }

            // 匹配栈顶和当前符号，均为终结符号，消去
            if (left.equals(right)) {
                String stackStr = String.join("", stack.toArray(new String[stack.size()]));
                String queueStr = String.join("", queue.toArray(new String[queue.size()]));
                System.out.println((step++) + "\t" + stackStr + "\t" + queueStr + "\t匹配成功" + left);
                stack.pop();
                queue.poll();
                continue;
            }

            // 从预测表中查询
            if (get(left , right).length() > 0) {
                String stackStr = String.join("", stack.toArray(new String[stack.size()]));
                String queueStr = String.join("", queue.toArray(new String[queue.size()]));
                System.out.println((step++) + "\t" + stackStr + "\t" + queueStr + "\t用" + left + "→"
                        + get(left , right) + "," + right + "逆序进栈");
                stack.pop();
                String tmp = get(left , right);
                for (int i = tmp.length() - 1; i >= 0; i--) {// 逆序进栈
                    String t = "";
                    if (tmp.charAt(i) == '\'' || tmp.charAt(i) == '’') {
                        t = tmp.charAt(i - 1) + "" + tmp.charAt(i);
                        i--;
                    } else {
                        t = tmp.charAt(i) + "";
                    }
                    if (!t.equals("ε"))
                        stack.push(t);
                }
                continue;
            }

            break;
        }
        if (!isSuccess)
            System.out.println((step++) + "\t#\t#\t" + "分析失败");
    }

}
