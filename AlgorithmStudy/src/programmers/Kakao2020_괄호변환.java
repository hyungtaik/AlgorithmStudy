package programmers;

import java.util.Stack;

/**
 * 
 * @author TAEK
 * @category �ùķ��̼� (���)
 * 
 * @see ���α׷��ӽ� : KAKAO BLIND RECRUITMENT ��ȣ ��ȯ <br>
 * 
 * @since 2020-09-08
 * 
 */

public class Kakao2020_��ȣ��ȯ {

	public static void main(String[] args) {
		Solution s = new Solution();
		String p = "()))((()";
		System.out.println(s.solution(p));
	}

	static class Solution {
		public String solution(String p) {
			String answer = "";
			// ���ڿ� �и�
			// �ùٸ� ��ȣ ���ڿ�, �������� ��ȣ ���ڿ�
			answer = checkFunc(p);

			return answer;
		}
	}

	static String checkFunc(String s) {
		if (s.length() == 0)
			return s;

		int len = s.length();
		Stack<Character> stack = new Stack<>();
		int checkNum = 0;
		int pushNum = 0;
		int popNum = 0;
		boolean check = true;
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '(') {
				pushNum++;
				stack.push('(');
			} else {
				popNum++;
				if (stack.isEmpty()) {
					check = false;
				} else {
					stack.pop();
				}
			}
			if (pushNum == popNum) {
				checkNum = i + 1;
				break;
			}
		}
		String u = s.substring(0, checkNum);
		String v = s.substring(checkNum, len);
		if (check) {
			return u + checkFunc(v);
		}
		else {
			String temp = "(";
			temp += checkFunc(v);
			temp += ")";
			for (int i = 1; i < u.length()-1; i++) {
				if (u.charAt(i) == '(') {
					temp += ")";
				} else {
					temp += "(";
				}
			}
			return temp;
		}
	}
}
