package demo15;

import java.util.Stack;

public class OPGParser1 {
    private String[][] opTable;
    private Stack<String> symbolStack;
    private Stack<Integer> stateStack;

    public OPGParser1(String[][] opTable) {
        this.opTable = opTable;
        this.symbolStack = new Stack<>();
        this.stateStack = new Stack<>();
    }

    public void parse(String input) {
        int i = 0;
        String s = input.substring(i, i + 1);
        symbolStack.push(s);
        stateStack.push(0);

        do {
            int state = stateStack.peek();
            String relation = getRelation(opTable[state][getSymbolType(s)], "<");

            if (relation.equals("<")) {
                i++;
                s = input.substring(i, i + 1);
                symbolStack.push(s);
                stateStack.push(Integer.parseInt(opTable[state][getSymbolType(s)]));
            } else if (relation.equals("=")) {
                i++;
                s = input.substring(i, i + 1);
                symbolStack.push(s);
                stateStack.pop();
                stateStack.push(Integer.parseInt(opTable[state][getSymbolType(s)]));
            } else if (relation.equals(">")) {
                String reduce = "";
                int backStep = 0;

                do {
                    backStep++;
                    reduce = symbolStack.pop() + reduce;
                } while (!getRelation(opTable[stateStack.get(stateStack.size() - backStep)][getSymbolType(symbolStack.peek())], ">").equals("<"));

                backStep--;

                String topSymbol = symbolStack.peek();

                stateStack.subList(stateStack.size() - backStep, stateStack.size()).clear();
                state = stateStack.peek();
                symbolStack.push("N");
                stateStack.push(Integer.parseInt(opTable[state][getSymbolType(topSymbol)]));
            } else {
                System.out.println("输入串不符合语法规则！");
                break;
            }
        } while (!s.equals("#") && !symbolStack.empty());

        if (symbolStack.peek().equals("N") && stateStack.size() == 2) {
            System.out.println("归约成功！");
        } else {
            System.out.println("归约失败！");
        }
    }

    private int getSymbolType(String s) {
        if (s.equals("+")) return 0;
        else if (s.equals("(")) return 1;
        else if (s.equals(")")) return 2;
        else if (s.equals("i")) return 3;
        else if (s.equals("#")) return 4;
        else return -1;
    }

    private String getRelation(String cell, String alt) {
        if (cell.equals("")) {
            return " ";
        } else if (cell.equals(alt)) {
            return "=";
        } else if (cell.equals("<") || cell.equals(">")) {
            return cell;
        } else {
            System.out.println("错误的表格内容： " + cell);
            return "";
        }
    }
}

