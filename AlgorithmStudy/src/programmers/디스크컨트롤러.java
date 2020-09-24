package programmers;

import java.util.PriorityQueue;

/**
 * 
 * 
 * @author TAEK
 * @category PriorityQueue
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 힙 > 디스크 컨트롤러 <br>
 * 
 * @since 2020-09-24
 * 
 */

public class 디스크컨트롤러 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] jobs = {{0,3},{1,9},{2,6}};
		System.out.println(s.solution(jobs));
	}

	static class Solution {

		private PriorityQueue<Pair> pq;

		public int solution(int[][] jobs) {
			int answer = 0;

			pq = new PriorityQueue<Pair>();
			for (int i = 0; i < jobs.length; i++) {
				pq.add(new Pair(jobs[i][0],jobs[i][0], jobs[i][1]));
			}

			int sum = 0;
			while(!pq.isEmpty()) {
				Pair temp = pq.poll();
				int start = temp.start + temp.distance;
				int num = start - temp.point;
				sum += num;
				int len = pq.size();
				for(int i=0;i<len;i++) {
					Pair p = pq.poll();
					if(p.start < start) p.start = start;
					pq.add(p);
				}
			}
			answer = sum/jobs.length;
			return answer;
		}
	}

	static class Pair implements Comparable<Pair> {
		int point;
		int start, distance;

		public Pair(int point, int start, int distance) {
			super();
			this.point = point;
			this.start = start;
			this.distance = distance;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.start == o.start)
				return this.distance - o.distance;
			return this.start - o.start;
		}
	}
}
