package programmers;

/**
 * 
 * @author TAEK
 * @category 문자열
 * 
 * @see 프로그래머스 :  코딩테스트 연습> 2020 KAKAO BLIND RECRUITMENT > 문자열 압축 <br>
 * 
 * @since 2020-10-16
 * 
 */

import java.util.LinkedList;

public class KAKAO_2020_문자열압축 {

	public static void main(String[] args) {
		Solution temp = new Solution();
		temp.solution("ababcdcdababcdcd");
	}
	static class Solution {
		private LinkedList<Pair> q;
		private int min;
		public Solution() {
		}
	    public int solution(String s) {
	        int answer = 0;
	        int len = s.length();
	        int pivot = 1;
	        q = new LinkedList<Pair>();
	        min = Integer.MAX_VALUE;
	        while(pivot<=len/2) {
	        	String temp = s.substring(0, pivot);
	        	int count = 1;
	        	for(int i=pivot;i<len;i+=pivot) {
	        		
	        		if(i+pivot > len) continue;
	        		if(temp.equals(s.substring(i,i+pivot))) {
	        			count++;
	        		}else {
	        			q.add(new Pair(temp,count));
	        			temp = s.substring(i,i+pivot);
	        			count=1;
	        		}
	        		if(i+pivot+pivot > len) {
	        			q.add(new Pair(temp,count));
	        			break;
	        		}
	        	}
	        	int ll = len%pivot;
	        	if(ll!=0) {
	        		q.add(new Pair(s.substring(len-ll,len),1));
	        	}
	        	String result = "";
	        	while(!q.isEmpty()) {
	        		Pair tmp = q.poll();
	        		if(tmp.count>1) {
	        			result+=tmp.count+tmp.input;
	        		}else {
	        			result+=tmp.input;
	        		}
	        	}
	        	min = Math.min(min,result.length());
	        	pivot++;
	        }
	        if(min == Integer.MAX_VALUE) answer = len;
	        else {
	        	answer = min;
	        }
	        return answer;
	    }
	}
	static class Pair{
		String input;
		int count;
		public Pair(String input, int count) {
			super();
			this.input = input;
			this.count = count;
		}
		
	}
}
