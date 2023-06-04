package demo16;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class main {
    static Map<String, myaction> actionTable = new HashMap<>();

    static Map<String,  Integer> gotoTable = new HashMap<>();

    static int[] productions = {1,2,2,2,1,2,1};

    static char[] left = {'S','E','E','A','A','B','B'};

    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        
        // 置ip指向输入串w的第一个符号
        int ip = 0;

        Stack<Integer> stack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();
        stack.push(0);
        symbolStack.push('#');
        // 令S为栈顶状态
        
        String inputString = "bccd#";
        // a是ip指向的符号
        
        System.out.println("\t步骤\t状态栈\t\t符号栈\t\t输入串\tACTION\tGOTO");
        int step = 1;
        while (true) {
            String outpuString = "";
            String stackoutpuString = "";
            String symbolStackoutpuString = "";
            for(int l = ip; l < inputString.length(); l++){
                outpuString+=inputString.charAt(l);
            }
            for (Integer integer : stack) {
                stackoutpuString+=integer;
            }
            for (Character character : symbolStack) {
                symbolStackoutpuString+=character;
            }
            System.out.print("\t"+step+"\t"+stackoutpuString+"\t\t"+symbolStackoutpuString+"\t\t"+outpuString+"\t");
            // System.out.println(stack.toString());// 状态栈
            // System.out.println(symbolStack.toString());// 符号栈

            char a = inputString.charAt(ip);
            int S = stack.peek();
            if (actionTable.get(""+S+a).istype()==1) {
                myaction Sj = actionTable.get(""+S+a);
                
                // PUSH j,a(进栈)
                stack.push(Sj.getJ());
                symbolStack.push(a);

                // ip前进(指向下一输入符号)
                ip++;
                System.out.println(""+Sj.getCh()+Sj.getJ()+"\t");
                // in.nextLine();
            } else if (actionTable.get(""+S+a).istype()==2) {
                myaction rj = actionTable.get(""+S+a);

                // 按产生式|e|的长度，将对应的符号和状态出栈

                // System.out.println("j:"+rj.getJ());
                int numSymbols = productions[rj.getJ()];

                for (int i = 0; i < numSymbols; i++) {
                    stack.pop();
                    symbolStack.pop();
                }
                
                int currentState = stack.peek();
                char currentSymbol = left[rj.getJ()];
                int nextState = gotoTable.get(""+currentState+currentSymbol);
                
                // 令当前栈顶状态为S'
                stack.push(nextState);
                symbolStack.push(currentSymbol);
                
                // System.out.println("r");
                System.out.println(""+rj.getCh()+rj.getJ()+"\t"+nextState);
                // in.nextLine();
            } else if (actionTable.get(""+S+a).isAcc()) {
                System.out.println("分析成功");
                return;
            }else {
                // 错误处理
                System.out.println("Error");
                break;
            }
            step++;
        }

    }

    public static void init() {
        myaction S2 = new myaction('S', 2);
        myaction S3 = new myaction('S', 3);
        myaction S4 = new myaction('S', 4);
        myaction S5 = new myaction('S', 5);
        // myaction S6 = new myaction('S',6);
        // myaction S7 = new myaction('S',7);
        // myaction S8 = new myaction('S',8);
        // myaction S9 = new myaction('S',9);
        myaction S10 = new myaction('S', 10);
        myaction S11 = new myaction('S', 11);

        myaction r1 = new myaction('r', 1);
        myaction r2 = new myaction('r', 2);
        myaction r3 = new myaction('r', 3);
        myaction r4 = new myaction('r', 4);
        myaction r5 = new myaction('r', 5);
        myaction r6 = new myaction('r', 6);

        myaction acc = new myaction('a', 0);

        actionTable.put("0a", S2);
        actionTable.put("0b", S3);
        actionTable.put("1#", acc);
        actionTable.put("2c", S4);
        actionTable.put("2d", S10);
        actionTable.put("3c", S5);
        actionTable.put("3d", S11);
        actionTable.put("4c", S4);
        actionTable.put("4d", S10);
        actionTable.put("5c", S5);
        actionTable.put("5d", S11);

        actionTable.put("6a", r1);
        actionTable.put("6b", r1);
        actionTable.put("6c", r1);
        actionTable.put("6d", r1);
        actionTable.put("6#", r1);

        actionTable.put("7a", r2);
        actionTable.put("7b", r2);
        actionTable.put("7c", r2);
        actionTable.put("7d", r2);
        actionTable.put("7#", r2);

        actionTable.put("8a", r3);
        actionTable.put("8b", r3);
        actionTable.put("8c", r3);
        actionTable.put("8d", r3);
        actionTable.put("8#", r3);

        actionTable.put("9a", r5);
        actionTable.put("9b", r5);
        actionTable.put("9c", r5);
        actionTable.put("9d", r5);
        actionTable.put("9#", r5);

        actionTable.put("10a", r4);
        actionTable.put("10b", r4);
        actionTable.put("10c", r4);
        actionTable.put("10d", r4);
        actionTable.put("10#", r4);

        actionTable.put("11a", r6);
        actionTable.put("11b", r6);
        actionTable.put("11c", r6);
        actionTable.put("11d", r6);
        actionTable.put("11#", r6);


        gotoTable.put("0E", 1);
        gotoTable.put("2A", 6);
        gotoTable.put("3B", 7);
        gotoTable.put("4A", 8);
        gotoTable.put("5B", 9);


    }

    

}

