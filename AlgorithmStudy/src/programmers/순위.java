package programmers;

import java.util.Arrays;

/**
 * 
 * @author TAEK
 * @category 플로이드 와샬
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > 그래프 > 순위 <br>
 * 
 * @since 2020-09-21
 * 
 */

public class 순위 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 5;
		int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};
		System.out.println(s.solution(n, results));
	}
	static class Solution {
	    private int[][] map;
		private int max;
		private boolean[] checked;

		public int solution(int n, int[][] results) {
	        int answer = 0;
	        max = 101;
	        map = new int[n+1][n+1];
	        for(int i=0;i<=n;i++) {
	        	Arrays.fill(map[i], max);
	        }
	        
	        for(int i=0;i<=n;i++) {
	        	map[i][i] = 0;
	        }
	        
	        for(int i=0;i<results.length;i++) {
	        	int winner = results[i][0];
	        	int loser = results[i][1];
	        	map[winner][loser] = 1;
	        }
	        
	        for(int i=1;i<=n;i++) {
	        	for(int j=1;j<=n;j++) {
	        		for(int k=1;k<=n;k++) {
	        			if(map[j][k] > map[j][i] + map[i][k]) { // j -> i -> k
	        				map[j][k] = map[j][i] + map[i][k];
	        			}
	        		}
	        	}
	        }
	        checked = new boolean[n+1];
	        for(int i=1;i<=n;i++) {
	        	for(int j=1;j<=n;j++) {
	        		if(i==j) continue;
	        		if(map[i][j] == max && map[j][i] == max) {
	        			checked[i] = true;
	        			break;
	        		}
	        	}
	        }
	        for(int i=1;i<=n;i++) {
	        	if(!checked[i]) answer++;
	        }
	        
	        return answer;
	    }
	}

}
