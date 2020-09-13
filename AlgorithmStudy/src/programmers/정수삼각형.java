package programmers;

/**
 * 
 * @author TAEK
 * @category DP (Bottom up)
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > DP > 정수 삼각형 <br>
 * 
 * @since 2020-09-13
 * 
 */

public class 정수삼각형 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] triangle = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
		System.out.println(s.solution(triangle));
	}
	static class Solution {
	    public int solution(int[][] triangle) {
	        int answer = 0;
	        int len = triangle.length;
	        int[][] result = new int[len][len];
	        
	        result[0][0] = triangle[0][0];
	        for(int i=1;i<len;i++) {
	        	result[i][0] = result[i-1][0]+triangle[i][0];
	        	result[i][i] = result[i-1][i-1]+triangle[i][i];
	        }
	        
	        
	        for(int i=1;i<len-1;i++) { // column
	        	for(int j=i+1;j<len;j++) {
	        		int max = Math.max(result[j-1][i-1], result[j-1][i]);
	        		result[j][i] = triangle[j][i] + max;
	        	}
	        	
	        }
	        answer = result[len-1][0];
	        
	        for(int i=1;i<len;i++) { 
	        	if(answer < result[len-1][i]) answer = result[len-1][i];
	        }
	        return answer;
	    }
	}

}
