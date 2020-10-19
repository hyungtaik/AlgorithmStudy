package programmers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author TAEK
 * @category Greedy
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 탐욕법 > 단속카메라 <br>
 * 
 * @since 2020-10-19
 * 
 */

public class 단속카메라 {

	static class Solution {
	    public int solution(int[][] routes) {
	        int answer = 1;
	        Arrays.sort(routes, new Comparator<int[]>(){
	            @Override
	             public int compare(int[] first, int[] second){
	            	if(first[0] == second[0]) return first[1] - second[1];
	                 return first[0] - second[0];
	             }
	         });
	         
	        int min = routes[0][1];
	        
	         for(int i = 1 ; i < routes.length-1 ; i++){
	             
	        	 min = Math.min(min,routes[i][1]);
	        	 
	        	 if(min < routes[i+1][0]) {
	        		 answer++;
	        		 min = routes[i+1][1];
	        	 }
	         }
	         return answer;
	    }
	}
}
