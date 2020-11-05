package programmers;

/**
 * 
 * @author TAEK
 * @category 부분합
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 월간 코드 챌린지 시즌1 > 풍선 터트리기 <br>
 * 
 * @since 2020-11-05
 * 
 */

public class 풍선터뜨리기 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] a = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
		System.out.println(s.solution(a));
	}
	
	static class Solution {
	    public int solution(int[] a) {
	        int answer = 0;
	        int len = a.length;
	        int[] left = new int[len];
	        int[] right = new int[len];
	        for(int i=0;i<len;i++) {
	        	left[i] = a[i];
	        	right[i] = a[i];
	        }
	        for(int i=1;i<len;i++) {
	        	left[i] = Math.min(left[i-1], left[i]);
	        }
	        for(int i=len-2;i>=0;i--) {
	        	right[i] = Math.min(right[i+1],right[i]);
	        }
	        answer = 2;
	        for(int i=1;i<len-1;i++) {
	        	if(a[i]>left[i-1] && a[i]>right[i+1]) continue;
	        	answer++;
	        }
	        
	        return answer;
	    }
	}
}
