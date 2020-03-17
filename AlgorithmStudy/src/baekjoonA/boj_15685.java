package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15685 {

	private static int N;
	private static boolean[][] map;
	private static Dragon[] dragon;
	private static int x;
	private static int y;
	private static int d;
	private static int g;
	private static int count;
	private static ArrayList<Dragon> list;
	private static int[] dx = {1,0,-1,0,1,0,1}; //0 1 2 3 || 오른쪽 아래 오른쪽대각선아래 탐색
	private static int[] dy = {0,-1,0,1,0,1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		map = new boolean[101][101];
		dragon = new Dragon[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			dragon[i] = new Dragon(x, y, d, g);
		}
		count = 0;
		solve(); // map에 그리기

		check(); // 오른쪽 아래 오른쪽 아래 대각선 탐색
		System.out.println(count);
	}
	static void solve() {
		for(int i=0;i<N;i++) {
			list = new ArrayList<Dragon>();
			list.add(dragon[i]);
			Dragon first = dragon[i];
			int nx = first.x+dx[first.d];
			int ny = first.y+dy[first.d];
			map[first.x][first.y] = true;
			map[nx][ny] = true;
			
			for(int j=0;j<first.g;j++) { // 세대만큼 반복
				int len = list.size();
				for(int k=len-1;k>=0;k--) { // list에 담긴 수만큼
					int dir = (list.get(k).d +1)%4;
					list.add(new Dragon(nx,ny,dir,0));
					nx += dx[dir];
					ny += dy[dir];
					map[nx][ny] = true;
				}
			}
		}
	}
	static void check() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]) {
					boolean chk = false;
					for(int k=4;k<7;k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(!map[nx][ny]) {
							chk = true;
							break;
						}
					}
					if(!chk) count++;
					
				}
			}
		}
	}
	
	static class Dragon{
		int x,y,d,g;

		public Dragon(int x, int y, int d, int g) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}

}
