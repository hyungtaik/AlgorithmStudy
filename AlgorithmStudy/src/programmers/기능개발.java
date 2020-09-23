package programmers;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category Queue
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 스택/큐 > 기능개발 <br>
 * 
 * @since 2020-09-23
 * 
 */

public class 기능개발 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		s.solution(progresses, speeds);
		
	}
	
	static class Solution {
	    private LinkedList<Pair> q;
		private boolean[] visited;
		private ArrayList<Integer> list;

		public int[] solution(int[] progresses, int[] speeds) {
	        int[] answer = {};
	 
	        int n = progresses.length;
	        q = new LinkedList<Pair>();
	        for(int i=0;i<n;i++) {
	        	q.add(new Pair(progresses[i],speeds[i]));
	        }
	        list = new ArrayList<Integer>();
	        while(!q.isEmpty()) {
	        	int len = q.size();
	        	visited = new boolean[len];
	        	int count = 0;
	        	for(int i=0;i<len;i++) {
	        		Pair temp = q.poll();
	        		int nextProg = temp.progress + temp.speed;
	        		if(nextProg>=100) {
	        			if(i==0) {
	        				visited[i] = true;
	        				count++;
	        			}else if(visited[i-1]){
	        				visited[i] = true;
	        				count++;
	        			}else {
	        				q.add(new Pair(nextProg,temp.speed));
	        			}
	        		}else {
	        			q.add(new Pair(nextProg,temp.speed));
	        		}
	        	}
	        	if(count>0)	list.add(count);
	        }
	        answer = new int[list.size()];
	        for(int i=0;i<list.size();i++) {
	        	answer[i] = list.get(i);
	        	System.out.println(answer[i]);
	        }
	        return answer;
	    }
	}
	static class Pair{
		int progress;
		int speed;
		public Pair(int progress, int speed) {
			super();
			this.progress = progress;
			this.speed = speed;
		}
	}

}
