package programmers;

import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category Queue
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 스택/큐 > 프린터 <br>
 * 
 * @since 2020-09-22
 * 
 */

public class 프린터 {

	public static void main(String[] args) {
		Solution s = new Solution();
//		int[] priorities = {2,1,3,2};
//		int location = 2;
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		
		System.out.println(s.solution(priorities, location));
	}
	
	static class Solution {

		private LinkedList<Pair> q;

		public int solution(int[] priorities, int location) {
	        int answer = 0;
	        
	        int[] counts = new int[10];
	        for(int i=0;i<priorities.length;i++) {
	        	counts[priorities[i]]++;
	        }
	        
	        q = new LinkedList<Pair>();
	        for(int i=0;i<priorities.length;i++) {
	        	q.add(new Pair(i,priorities[i]));
	        }
	        
	        int count = 1;
	        while(!q.isEmpty()) {
	        	Pair temp = q.poll();
	        	boolean flag = true;
	        	for(int i=temp.rank+1;i<10;i++) {
	        		if(counts[i]>0) {
	        			flag =false;
	        			break;
	        		}
	        	}
	        	if(flag) {
	        		if(temp.num == location) {
	        			break;
	        		}
	        		counts[temp.rank]--;
	        		count++;
	        		continue;
	        	}
	        	q.add(temp);
	        }
	        answer = count;
	        return answer;
	    }
	}
	static class Pair{
		int num;
		int rank;
		public Pair(int num, int rank) {
			super();
			this.num = num;
			this.rank = rank;
		}
		
	}
	

}
