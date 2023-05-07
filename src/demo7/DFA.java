package demo7;

import java.util.Scanner;

public class DFA {

    int[][] dfa;// 转换关系矩阵

    public DFA() {
        dfa = new int[][] { { 1, 2, 3, 4, 5, 7, 8, 9, 10 },
                { 1, 1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 2, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, 6, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1 }
        };
    }

    public int judge(String inputStr) {
        String str = inputStr.trim();// 去除空格
        int nowstates = 0;

        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (Character.isLetter(tmp)) {
                // System.out.println(tmp+":isLetter");
                nowstates = dfa[nowstates][0];
            } else if (Character.isDigit(tmp)) {
                // System.out.println(tmp+":isDigit");
                nowstates = dfa[nowstates][1];
            } else {
                // System.out.println(tmp);
                switch (tmp) {
                    case '=':
                        nowstates = dfa[nowstates][2];
                        break;
                    case '+':
                        nowstates = dfa[nowstates][3];
                        break;
                    case '*':
                        nowstates = dfa[nowstates][4];
                        break;
                    case ',':
                        nowstates = dfa[nowstates][5];
                        break;
                    case '(':
                        nowstates = dfa[nowstates][6];
                        break;
                    case ')':
                        nowstates = dfa[nowstates][7];
                        break;
                    case ';':
                        nowstates = dfa[nowstates][8];
                        break;
                    default:
                        return -1;
                }
            }

        }
        return nowstates;
    }

    public static void main(String[] args) {

        DFA dfa = new DFA();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串");
        String inputStr = scanner.nextLine();
        int res = dfa.judge(inputStr);
        if (res >= 0 && res <= 10) {
            System.out.println("可识别");
        } else {
            System.out.println("不可识别");
        }
        // System.out.println("res:"+res);
        scanner.close();
    }
}
