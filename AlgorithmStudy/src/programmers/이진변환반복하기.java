package programmers;

import java.util.Stack;

/**
 * 
 * @author TAEK
 * @category Queue
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 월간 코드 챌린지 시즌1 > 이진 변환 반복하기 <br>
 * 
 * @since 2020-11-11
 * 
 */

public class 이진변환반복하기 {
	class Solution {
	    public int[] solution(String s) {
	        int[] answer = new int[2];
	        
	        String temp = s;
	        int count = 0;
	        int cnt = 0;
	        int total = 0;
	        Stack<String> stack = new Stack<String>();
	        while(true) {
	        	if(temp.equals("1")) break;
	        	count = 0;
	        	for(int i=0;i<temp.length();i++) {
	        		if(temp.charAt(i)=='1') count++;
	        		else total++;
	        	}
	        	temp = "";
	        	stack.clear();
	        	while(count>0) {
	        		stack.push((count % 2)+"");
	        		count/=2; 
	        	}
	        	while(!stack.isEmpty()) {
	        		temp += stack.pop();
	        	}
	        	cnt++;
	        }
	        
	        answer[0] = cnt;
	        answer[1] = total;
	        return answer;
	    }
	}
}
