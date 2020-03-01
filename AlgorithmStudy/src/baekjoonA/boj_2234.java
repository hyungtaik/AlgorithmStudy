package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2234 {
	private static int N;
	private static int M;
	private static Load[][] map;
	private static int[][] tMap;
	private static boolean[][] visited;
	private static LinkedList<Load> q;
	private static int[] dx = {0,0,1,-1}; // 동 서 남 북
	private static int[] dy = {1,-1,0,0};
	private static int max;
	private static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 열
		M = Integer.parseInt(st.nextToken()); // 행 
		map = new Load[M][N];
		tMap = new int[M][N];
		max = Integer.MIN_VALUE;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				boolean d=false,s=false,n=false,b = false;
				if(temp >= 8) {
					temp -=8;
					n = true;
				}if(temp >=4 ) {
					temp-=4;
					d = true;
				}if(temp>=2) {
					temp-=2;
					b = true;
				}if(temp >=1) {
					temp-=1;
					s = true;
				}
				map[i][j] = new Load(d,s,n,b,i,j);
			}
		}
		numbers = new int[M*N+1];
		int index = 1;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(tMap[i][j]==0) {
					visited = new boolean[M][N];
					visited[i][j] = true;
					bfs(map[i][j],index++);
				}
			}
		}
		visited = new boolean[M][N];
		int result = Integer.MIN_VALUE;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				for(int dir = 0;dir<4;dir++) {
					int nx = i+dx[dir];
					int ny = j+dy[dir];
					if(nx<0||nx>M-1||ny<0||ny>N-1) continue;
					if(tMap[i][j]!=tMap[nx][ny]) {
						int sum = numbers[tMap[i][j]]+numbers[tMap[nx][ny]];
						result = Math.max(result, sum);
					}
					visited[nx][ny] = true;
				}
			}
		}
		
		System.out.println(index-1);
		System.out.println(max);
		System.out.println(result);
	}
	// 가장 넓은 방 크기 구하기
	static void bfs(Load p,int index) {
		q = new LinkedList<Load>();
		q.add(p);
		while(!q.isEmpty()) {
			Load temp = q.poll();
			int x = temp.x;
			int y = temp.y;
//			System.out.println("동:"+temp.d+" 서:"+temp.s+" 남:"+temp.n+" 북:"+temp.b);
			if(!temp.d) {
				if(!visited[x+dx[0]][y+dy[0]]) {
					visited[x+dx[0]][y+dy[0]] = true;
					q.add(map[x+dx[0]][y+dy[0]]);
				}
			}if(!temp.s) {
				if(!visited[x+dx[1]][y+dy[1]]) {
					visited[x+dx[1]][y+dy[1]] = true;
					q.add(map[x+dx[1]][y+dy[1]]);
					
				}
			}if(!temp.n) {
				if(!visited[x+dx[2]][y+dy[2]]) {
					visited[x+dx[2]][y+dy[2]] = true;
					q.add(map[x+dx[2]][y+dy[2]]);
					
				}
			}if(!temp.b) {
				if(!visited[x+dx[3]][y+dy[3]]) {
					visited[x+dx[3]][y+dy[3]] = true;
					q.add(map[x+dx[3]][y+dy[3]]);
					
				}
			}
		}
		int count = 0;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) {
					count++;
					tMap[i][j] = index;
				}
			}
		}
		numbers[index] = count;
		max = Math.max(max, count);
	}
	
	
	static class Load{
		boolean d,s,n,b;
		int x,y;
		public Load(boolean d, boolean s, boolean n, boolean b, int x, int y) {
			super();
			this.d = d;
			this.s = s;
			this.n = n;
			this.b = b;
			this.x = x;
			this.y = y;
		}
		
		
	}
}
