package programmers;

import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 그래프 > 가장 먼 노드 <br>
 * 
 * @since 2020-09-20
 * 
 */

public class 가장먼노드 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 6;
		int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
		System.out.println(s.solution(n, edge));
	}
	
	static class Solution {
	    private LinkedList<Integer> q;
		private boolean[] visited;

		public int solution(int n, int[][] edge) {
	        int answer = 0;
	        int len = edge.length;
	        boolean[][] map = new boolean[n+1][n+1];
	        for(int i=0;i<len;i++) {
	        	map[edge[i][0]][edge[i][1]] = true;
	        	map[edge[i][1]][edge[i][0]] = true;
	        }
	        q = new LinkedList<Integer>();
	        visited = new boolean[n+1];
	        visited[1] = true;
	        q.add(1);
	        while(!q.isEmpty()) {
	        	answer = q.size();
	        	for(int i=0;i<answer;i++) {
	        		int temp = q.poll();
	        		for(int j=1;j<=n;j++) {
	        			if(!map[temp][j]) continue;
	        			if(visited[j]) continue;
	        			q.add(j);
	        			visited[j] = true;
	        		}
	        	}
	        }
	        return answer;
	    }
	}

}
