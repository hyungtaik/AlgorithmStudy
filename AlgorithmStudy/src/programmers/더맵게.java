package programmers;

import java.util.PriorityQueue;

/**
 * 
 * @author TAEK
 * @category Heap
 * 
 * @see ���α׷��ӽ� : �ڵ��׽�Ʈ ���� > ����/ť > �� �ʰ� <br>
 * 
 * @since 2020-09-23
 * 
 */
public class ���ʰ� {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] scoville = {1,2,3,9,10,12};
		int K = 7;
		System.out.println(s.solution(scoville, K));
	}

	static class Solution {
		private PriorityQueue<Integer> pq;

		public int solution(int[] scoville, int K) {
	        int answer = 0;
	        pq = new PriorityQueue<Integer>();
	        for(int i=0;i<scoville.length;i++) {
	        	pq.add(scoville[i]);
	        }
	        int count = 0;
	        while( pq.peek()<K) {
	        	int first = pq.poll();
	        	int second = pq.poll();
	        	int result = first + second*2;
	        	if(pq.size()==0) {
	        		if(result >= K) {
	        			count++;
	        			break;
	        		}
	        		else {
	        			count = -1;
	        			break;
	        		}
	        	}
	        	pq.add(result);
	        	count++;
	        }
	        answer = count;
	        return answer;
	    }
	}
}
