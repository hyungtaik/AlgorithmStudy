package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1194 {

	private static int N;
	private static int M;
	private static char[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int min;
	private static boolean[][][] visited;
	private static LinkedList<Pair> q;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][128];
		q = new LinkedList<Pair>();
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					q.add(new Pair(i,j,0,0));
					map[i][j] = '.';
					visited[i][j][0] = true;
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		bfs();
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
		
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			for(int dir=0;dir<4;dir++) {
				int nx = temp.x+dx[dir];
				int ny = temp.y+dy[dir];
				int key = temp.key;
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny][key]) continue;
				if(map[nx][ny]=='#') continue;
				if(map[nx][ny]=='1') {
					min = Math.min((temp.count+1), min);
					continue;
				}else if(map[nx][ny]=='.') {
					visited[nx][ny][key] = true;
					q.add(new Pair(nx,ny,temp.count+1,key));
				}else if(map[nx][ny]>='a' && map[nx][ny]<='f') {
					key |= 1 << map[nx][ny]-'a';
					visited[nx][ny][key] = true;
					q.add(new Pair(nx,ny,temp.count+1,key));
				}else if(map[nx][ny]>='A' && map[nx][ny]<='F') {
					if((key & 1<<map[nx][ny]-'A')>0) {
						visited[nx][ny][key] = true;
						q.add(new Pair(nx,ny,temp.count+1,key));
					}
					
				}
			}
		}
	}
	static class Pair {
		int x,y,count;
		int key;
		public Pair(int x, int y, int count, int key) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.key = key;
		}
	}

}
