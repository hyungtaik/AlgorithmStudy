package programmers;

/**
 * 
 * @author TAEK
 * @category Array
 * 
 * @see 프로그래머스 : 주식가격
 * 
 * @since 2020-09-05
 * 
 */

public class 주식가격 {

	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };
		Solution s = new Solution();
		int[] result = new int[prices.length];
		result = s.solution(prices);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	static class Solution {
		public int[] solution(int[] prices) {
			int[] answer = new int[prices.length];
	        int len = answer.length;
	        for (int i = 0; i < len; i++) {
	            for (int j = i+1; j < len; j++) {
	                if (prices[i] > prices[j]) {
	                    answer[i] = j-i;
	                    break;
	                }
	                if (j==len-1) answer[i] = j-i;
	            }
	        }
	        return answer;
		}
	}
}
