package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14502 {

	static int N,M;
	static int[][] map;
	static List<Pair> virus;
	static int result;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		map = new int[N][M];
		int temp;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 2) {
					virus.add(new Pair(i,j));
				}else if(temp == 0) {
					result++;
				}
				
			}
		}
		count = 0;
		solve(0,0,0);
		System.out.println(count-3);
	}
	
	static void solve(int x,int y,int count) {
		if(count ==3) {
			check();
			return;
		}
		if(y>=M) {
			solve(x+1,0,count);
			return;
		}
		if(x>=N) return;
		
		if(map[x][y] == 0) {
			map[x][y] = 1;
			solve(x,y+1,count+1);
			map[x][y] = 0;
		}
		
		solve(x,y+1,count);
	}
	
	static void check() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] checked = new boolean[N][M];
		int checkNum = result;
		for(Pair p :virus) {
			q.add(p);
			checked[p.x][p.y] = true;
		}
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			for(int i=0;i<4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx < 0 || nx >N-1 || ny <0 || ny > M-1) continue;
				if(checked[nx][ny]==true) continue;
				
				if(map[nx][ny]==0) {
					checked[nx][ny] = true;
					q.add(new Pair(nx,ny));
					checkNum--;
				}
			}
		}
		count = Math.max(count, checkNum);
	}
	
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}

}
