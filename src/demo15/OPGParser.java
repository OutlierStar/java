package demo15;

import java.util.Stack;

public class OPGParser {
    private static final char N = 'N';

    public static void main(String[] args) {
        // 定义优先关系表

        char[][] precedenceTable = {
                // + * ^ i ( ) #
                /* + */ { '>', '<', '<', '<', '<', '>', '>' },
                /* * */ { '>', '>', '<', '<', '<', '>', '>' },
                /* ^ */ { '>', '>', '<', '<', '<', '>', '>' },
                /* i */ { '>', '>', '>', ' ', ' ', '>', '>' },
                /* ( */ { '<', '<', '<', '<', '<', '=', ' ' },
                /* ) */ { '>', '>', '>', ' ', ' ', '>', '>' },
                /* # */ { '<', '<', '<', '<', '<', ' ', '=' }
        };

        // 输入串
        String input = "i+i#";

        String input1 = "i+(i)#";
        // 执行OPG分析
        opgAnalysis(precedenceTable, input);
    }

    private static void opgAnalysis(char[][] precedenceTable, String input) {
        Stack<Character> opStack = new Stack<>();
        opStack.push('#');

        System.out.println("步骤\t\t\t符号栈\t\t\t输入串\t\t\t动作");

        int inputIndex = 0;
        char currentSymbol = input.charAt(inputIndex);
        char topOp = opStack.peek();
        // System.out.println(topOp);

        int p = 1;
        while (true) {

            if (isTerminal(topOp)) {
                // System.out.println("topOp="+topOp);

                char tmp = opStack.pop();
                topOp = opStack.peek();
                opStack.push(tmp);
            }
            
            char relation;
            if (topOp == 'N')
                relation = '<';
            else
                relation = getRelation(precedenceTable, topOp, currentSymbol);
            if (relation == '<') {
                // 进栈
                opStack.push(currentSymbol);
                inputIndex++;
                currentSymbol = input.charAt(inputIndex);

            } else if (relation == '>') {
                String reduction = "";
                do {
                    if (!isTerminal(topOp)) {
                        reduction = topOp + reduction;
                        opStack.pop();
                        topOp = opStack.peek();
                    }
                    if (topOp == 'N')
                        relation = '<';
                    else
                        relation = getRelation(precedenceTable, topOp, currentSymbol);
                } while (relation == '>');
                reduction = N + reduction;
                opStack.push(N);

                System.out.println("归约: " + reduction);
            } else if (relation == '=') {
                if (topOp == '#' && currentSymbol == '#') {
                    System.out.println("分析成功");
                    break;
                } else {
                    System.out.println("ERROR: 输入串未处理完毕");
                    break;
                }
            } else {
                System.out.println("ERROR: 无法确定优先关系");
                break;
            }

            System.out.println(
                    p + "\t\t\t" + opStack.toString() + "\t\t\t" + input.substring(inputIndex) + "\t\t\t");
            p++;
            topOp = opStack.peek();
            currentSymbol = input.charAt(inputIndex);
        }
    }

    private static boolean isTerminal(char symbol) {
        return symbol != '+' && symbol != '*' && symbol != '(' && symbol != ')' && symbol != 'i' && symbol != '#';
    }

    private static char getRelation(char[][] precedenceTable, char op1, char op2) {
        int row = getRowIndex(op1);
        int column = getColumnIndex(op2);
        return precedenceTable[row][column];
    }

    private static int getRowIndex(char op) {
        System.out.println("getRowIndex::::" + op);
        switch (op) {
            case '+':
                return 0;
            case '*':
                return 1;
            case '^':
                return 2;
            case 'i':
                return 3;
            case '(':
                return 4;
            case ')':
                return 5;
            case '#':
                return 6;
            default:
                return -1;
        }
    }

    private static int getColumnIndex(char op) {
        System.out.println("getColumnIndex::::" + op);
        switch (op) {
            case '+':
                return 0;
            case '*':
                return 1;
            case '^':
                return 2;
            case 'i':
                return 3;
            case '(':
                return 4;
            case ')':
                return 5;
            case '#':
                return 6;
            default:
                return -1;
        }
    }
}
