package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1261 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static boolean[][] visited;
	private static int min;
	private static int[][] memo;
	private static PriorityQueue<Node> pq;
	private static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		map = new int[N][M]; 
		for(int i=0;i<N;i++) {
			String temp = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]= temp.charAt(j)-48;
			}
		}
		visited = new boolean[N][M];
		min = Integer.MAX_VALUE;
		
		dist = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		pq = new PriorityQueue<Node>();
		bfs();
		System.out.println(dist[N-1][M-1]);
		
	}
	static void bfs() {
		pq.add(new Node(0, 0, 0));
		dist[0][0] = 0;
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			if(temp.x == N-1 && temp.y == M-1) {
				min = Math.min(min,temp.cost);
				return;
			}
			for(int i=0;i<4;i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(nx <0||nx>N-1||ny<0||ny>M-1||visited[nx][ny]) continue;
				if(dist[nx][ny]> dist[temp.x][temp.y] + map[nx][ny]) {
					dist[nx][ny] = dist[temp.x][temp.y] + map[nx][ny];
					pq.add(new Node(nx,ny,dist[nx][ny]));
					visited[nx][ny] = true;
				}
			}
			
		}
	}
	
	static class Node implements Comparable<Node>{
		int x,y,cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

}
