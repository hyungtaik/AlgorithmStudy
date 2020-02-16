package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485 {

	private static int N;
	private static int[][] map;
	private static int[][] dist;
	private static int result;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = 1;
		while(true) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			
			if(N==0) return;
			result = 0;
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			result = solve();
			System.out.println("Problem "+(tc++)+": "+result);
		}
	}
	static int solve() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		dist[0][0] = map[0][0];
		q.add(new Node(0, 0, map[0][0]));
		
		while(!q.isEmpty()) {
			
			Node n = q.poll();
			int x = n.x;
			int y = n.y;
			int cost = n.cost;
			if(dist[x][y]<cost) continue;
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;

				if(dist[nx][ny] > dist[x][y]+map[nx][ny] ) {
					dist[nx][ny] = dist[x][y]+map[nx][ny];
					q.add(new Node(nx,ny,dist[nx][ny]));
				}
				
			}
		}
		
		return dist[N-1][N-1];
	}
	static class Node implements Comparable<Node>{
		int x,y;
		int cost;
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