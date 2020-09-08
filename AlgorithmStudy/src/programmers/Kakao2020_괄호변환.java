package programmers;

import java.util.Stack;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션 (재귀)
 * 
 * @see 프로그래머스 : KAKAO BLIND RECRUITMENT 괄호 변환 <br>
 * 
 * @since 2020-09-08
 * 
 */

public class Kakao2020_괄호변환 {

	public static void main(String[] args) {
		Solution s = new Solution();
		String p = "()))((()";
		System.out.println(s.solution(p));
	}

	static class Solution {
		public String solution(String p) {
			String answer = "";
			// 문자열 분리
			// 올바른 괄호 문자열, 균형잡힌 괄호 문자열
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
