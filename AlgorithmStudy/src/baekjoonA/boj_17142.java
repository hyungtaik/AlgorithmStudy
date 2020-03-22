package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_17142 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Pair> virus;
	private static int[][] timeTable;
	private static boolean[][] visited;
	private static int[] cArray;
	private static LinkedList<Pair> vrs;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int total;
	private static int min;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		timeTable = new int[N][N];
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<Pair>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) { // 0:빈칸 1:벽 2:바이러스
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Pair(i,j));
				}
			}
		}
		cArray = new int[M];
		min = Integer.MAX_VALUE;
		timeTable = copyArr();
		if(total==0) {
			min = 0;
		}else {
			combi(0,0);
		}
		
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		
		System.out.println(min);
	}
	
	static int[][] copyArr(){
		total = 0;
		int[][] arr = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) { // -1:빈칸 -3:벽 -2:바이러스
				if(map[i][j]==1) { // 벽
					arr[i][j]=-3;
				}else if(map[i][j]==0) { // 빈칸
					total++;
					arr[i][j] = -1;
				}else { // 바이러스
					arr[i][j] = -2;
				}
			}
		}
		
		return arr;
	}
	
	static void combi(int index,int count) {
		
		if(count == M) {
			timeTable = copyArr();
			vrs = new LinkedList<Pair>();
			visited = new boolean[N][N];
			for(int i=0;i<M;i++) {
				Pair p = virus.get(cArray[i]);
				vrs.add(p);
				visited[p.x][p.y] = true;
				timeTable[p.x][p.y] = 0;
			}
			
			int result = bfs();
			
			if(result != -1) {
				min = Math.min(min, result);
			}
			return;
		}
		if(index ==virus.size()) return;
		cArray[count] = index;
		combi(index+1,count+1);
		combi(index+1,count);
	}
	static int bfs() {
		int count=0;
		while(!vrs.isEmpty()) {
			int len = vrs.size();
			if(min<count) return -1;
			for(int l=0;l<len;l++) {
				
				Pair temp = vrs.poll();
				int x = temp.x;
				int y = temp.y;
				for(int i=0;i<4;i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
					if(visited[nx][ny]) continue;
					if(timeTable[nx][ny] == -3) continue;
					vrs.add(new Pair(nx,ny));
					if(timeTable[nx][ny]==-1) {
						total--;
					}
					timeTable[nx][ny] = count+1;
					visited[nx][ny] = true;
					if(total ==0) {
						return count+1;
					}
				}
			}
			count++;
		}
		return -1;	
	}
	
	static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
