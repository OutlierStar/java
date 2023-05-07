package demo7;

import java.util.*;
public class DFA2 {
	char startState;
	char[] test;
	char[] state;
	char[][] transTable;
	char[] endState;
	public DFA2() {
		startState = '0';
		test = new char[] {'a','b','c','d'};
		state = new char[] { '1', '2', '3','4', '5', '6','7' };
		transTable = new char[][]{{'3','2',' ',' '},
							     {'4','2',' ',' '},
							     {' ','6','3','5'},
							     {' ','7','3','5'},
							     {'4',' ',' ',' '},
							     {' ','6',' ',' '},
							     {' ','6',' ',' '}};
		endState = new char[] { '6','7' };
	}
	private char traning(char nowS, char nextChar) {
		int m = -1, n = -1;
		for (int i = 0; i < state.length; i++) {
			if (nowS == state[i]) {
				m = i;
				break;
			}
		}
		for (int i = 0; i < test.length; i++) {
			if (nextChar == test[i]) {
				n = i;
				break;
				
			}
		}
		if (n == -1 || m == -1) {
			return '0';
		}
		return transTable[m][n];
	}
	public static void main(String[] args) {
		DFA2 dfa = new DFA2();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入字符串");
		String inputStr = scanner.nextLine();
		String inputStrs[] = inputStr.trim().split("//s");
		char currState = dfa.startState;
		for (int i = 0; i < inputStrs.length; i++) {
			for (int j = 0; j < inputStrs[i].length(); j++) {
				currState = dfa.traning(currState, inputStrs[i].charAt(j));
				if(currState=='0'){
					System.out.println("no");
					System.exit(0);
				}
			}
			boolean flag=false;
			for(int k=0;k<dfa.endState.length;k++){
				if(currState==dfa.endState[k]){
					flag=true;
					break;
				}
			}
			if(flag){
				System.out.println("yes");
			}else{
				System.out.println("no");
			}
		}
		scanner.close();
	}
}


