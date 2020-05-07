package programmers;

public class Kakao2019_징검다리건너기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// [2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };

		System.out.println(Solution.solution(stones, 3));
	}

	static class Solution {

		public static int solution(int[] stones, int k) {
			int answer = 0;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < stones.length; i++) {
				min = Math.min(min, stones[i]);
				max = Math.max(max, stones[i]);
			}
			int mid = 0;
			while (min <= max) {
				mid = (max + min) / 2;
				boolean check = true;

				// 체크
				int[] temp = stones.clone();
				for (int i = 0; i < temp.length; i++) {
					temp[i] -= mid;
				}
				int flag = 0;
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] < 0) {
						flag++;
						if (flag >= k) {
							check = false;
							break;
						}
					} else {
						flag = 0;
					}
				}

				if (check) {
					answer = Math.max(answer, mid);
					min = mid + 1;
				} else {
					max = mid - 1;
				}
			}
			return answer;
		}
		
		 public int solution2(int[] stones, int k) {
		        int answer = Integer.MAX_VALUE;


		        for(int i = 0; i<=stones.length-k; i++){
		            int temp = i;
		            int stone = stones[i];
		            for(int j = i; j<i+k; j++){
		                if(stones[j] > stone){
		                    stone = stones[j];
		                    temp = j;
		                }
		            }
		            if(answer > stone){
		                answer = stone;
		            }
		            i = temp;

		        }
		        return answer;
		    }
	}
}
