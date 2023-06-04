package demo16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LR0Parser {
    private List<String> nonTerminals; // 非终结符列表
    private List<String> terminals; // 终结符列表
    private List<Production> productions; // 产生式列表
    private Map<Integer, Map<String, Action>> actionTable; // ACTION表
    private Map<Integer, Map<String, Integer>> gotoTable; // GOTO表
    private Stack<Integer> stack; // 状态栈
    private Stack<String> symbolStack; // 符号栈
    private String inputString; // 输入串

    public LR0Parser() {
        nonTerminals = new ArrayList<>();
        terminals = new ArrayList<>();
        productions = new ArrayList<>();
        actionTable = new HashMap<>();
        gotoTable = new HashMap<>();
        stack = new Stack<>();
        symbolStack = new Stack<>();
    }

    // 添加非终结符
    public void addNonTerminal(String nonTerminal) {
        nonTerminals.add(nonTerminal);
    }

    // 添加终结符
    public void addTerminal(String terminal) {
        terminals.add(terminal);
    }

    // 添加产生式
    public void addProduction(String productionString) {
        String[] parts = productionString.split(":");
        String leftHandSide = parts[0].trim();
        String[] rightHandSide = parts[1].trim().split("\\s+");
        productions.add(new Production(leftHandSide, Arrays.asList(rightHandSide)));
    }

    // 设置ACTION表的项
    public void setAction(int state, String symbol, Action action) {
        Map<String, Action> stateActions = actionTable.computeIfAbsent(state, k -> new HashMap<>());
        stateActions.put(symbol, action);
    }

    // 设置GOTO表的项
    public void setGoto(int state, String symbol, int nextState) {
        Map<String, Integer> stateGotos = gotoTable.computeIfAbsent(state, k -> new HashMap<>());
        stateGotos.put(symbol, nextState);
    }

    // 初始化语法分析器
    public void initialize() {
        stack.clear();
        symbolStack.clear();
        stack.push(0); // 初始状态入栈
    }

    // 执行LR(0)分析
    public void parse(String input) {
        inputString = input + "$"; // 在输入串末尾添加结束符$
        int ip = 0; // 输入串指针

        while (true) {
            int state = stack.peek(); // 当前状态
            String symbol = Character.toString(inputString.charAt(ip)); // 当前输入符号

            Action action = actionTable.get(state).get(symbol);
            if (action == null) {
                // 错误处理
                System.out.println("Error: Invalid action for state " + state + " and symbol " + symbol);
                break;
            }

            if (action.getType() == ActionType.SHIFT) {
                // 移进操作
                int nextState = action.getValue();
                stack.push(nextState);
                symbolStack.push(symbol);
                ip++;
            } else if (action.getType() == ActionType.REDUCE) {
                // 归约操作
                int productionIndex = action.getValue();
                Production production = productions.get(productionIndex);
                String leftHandSide = production.getLeftHandSide();
                int numSymbols = production.getRightHandSide().size();

                for (int i = 0; i < numSymbols; i++) {
                    stack.pop();
                    symbolStack.pop();
                }

                int currentState = stack.peek();
                String currentSymbol = leftHandSide;
                int nextState = gotoTable.get(currentState).get(currentSymbol);
                stack.push(nextState);
                symbolStack.push(leftHandSide);

                System.out.println("Reduce: " + production);
            } else if (action.getType() == ActionType.ACCEPT) {
                // 接受操作
                System.out.println("Accept");
                break;
            }
        }
    }

    public static void main(String[] args) {
        LR0Parser parser = new LR0Parser();

        // 设置非终结符
        parser.addNonTerminal("S");
        parser.addNonTerminal("A");

        // 设置终结符
        parser.addTerminal("a");
        parser.addTerminal("b");
        parser.addTerminal("c");
        parser.addTerminal("$");

        // 设置产生式
        parser.addProduction("S : A");
        parser.addProduction("A : a A");
        parser.addProduction("A : b");

        // 设置ACTION表
        parser.setAction(0, "a", new Action(ActionType.SHIFT, 2));
        parser.setAction(0, "b", new Action(ActionType.SHIFT, 3));
        parser.setAction(1, "$", new Action(ActionType.ACCEPT, -1));
        parser.setAction(2, "a", new Action(ActionType.SHIFT, 2));
        parser.setAction(2, "b", new Action(ActionType.SHIFT, 3));
        parser.setAction(2, "c", new Action(ActionType.REDUCE, 2));
        parser.setAction(3, "a", new Action(ActionType.REDUCE, 1));
        parser.setAction(3, "b", new Action(ActionType.REDUCE, 1));
        parser.setAction(3, "c", new Action(ActionType.REDUCE, 1));

        // 设置GOTO表
        parser.setGoto(0, "S", 1);
        parser.setGoto(0, "A", 4);
        parser.setGoto(2, "A", 5);
        parser.setGoto(3, "A", 6);

        // 初始化语法分析器
        parser.initialize();

        // 输入串进行LR(0)分析
        parser.parse("bccd");
    }
}

class Production {
    private String leftHandSide; // 产生式左部
    private List<String> rightHandSide; // 产生式右部

    public Production(String leftHandSide, List<String> rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public String getLeftHandSide() {
        return leftHandSide;
    }

    public List<String> getRightHandSide() {
        return rightHandSide;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(leftHandSide).append(" : ");
        for (String symbol : rightHandSide) {
            sb.append(symbol).append(" ");
        }
        return sb.toString().trim();
    }
}

enum ActionType {
    SHIFT,
    REDUCE,
    ACCEPT
}

class Action {
    private ActionType type;
    private int value;

    public Action(ActionType type, int value) {
        this.type = type;
        this.value = value;
    }

    public ActionType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
