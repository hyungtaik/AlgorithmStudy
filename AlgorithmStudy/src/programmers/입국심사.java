package programmers;

import java.util.Arrays;

/**
 * 
 * @author TAEK
 * @category 이분탐색
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 이분탐색 > 입국심사 <br>
 * 
 * @since 2020-10-09
 * 
 */

public class 입국심사 {

	public static void main(String[] args) {

	}
	class Solution {
	    public long solution(int n, int[] times) {
	        long answer = Long.MAX_VALUE;
	        
	        Arrays.sort(times);
	        long left = 0;
	        long right = times[times.length-1];
	        right*=n;
	        long mid = 0;
	        
	        while(left<=right) {
	        	mid = (left+right)/2;
	        	
	        	long sum = 0;
	        	for(int i=0;i<times.length;i++) {
	        		sum+= mid/times[i];
	        	}
	        	if(sum >= n) {
	        		if(answer>mid) {
	        			answer = mid;
	        		}
	        		right = mid-1;
	        	}else {
	        		left = mid+1;
	        	}
	        }
	        
	        return answer;
	    }
	    
	}
}
