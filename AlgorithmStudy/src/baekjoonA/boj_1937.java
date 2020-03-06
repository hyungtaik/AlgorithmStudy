package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1937 {

	private static int N;
	private static int[][] map;
	private static int max;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = -1;
			}
		}
		max = Integer.MIN_VALUE;
//		q = new LinkedList<Pair>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]==-1) {
//					System.out.println(i+"    "+j+"***********");
					dfs(i,j);
				}
			}
		}
		if(max == Integer.MIN_VALUE) {
			max =1;
		}
		System.out.println(max);
	}
	
	private static int dfs(int x,int y) {
		if(visited[x][y]!= -1) {
			return visited[x][y];
		}
		visited[x][y] = 1;
//		System.out.println(x+" = "+y);
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
			if(map[x][y]<map[nx][ny]) {
				int count = 1;
				count+=dfs(nx,ny);
				visited[x][y] = Math.max(visited[x][y],count);
				if(visited[x][y]>max) max = visited[x][y];
			}
		}
//		print();
//		System.out.println();
		return visited[x][y];
	}
	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(visited[i][j]+" ");
			}System.out.println();
		}
	}
}
