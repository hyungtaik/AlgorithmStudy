package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17406 {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static Pair[] rotate;
	private static int[] arr;
	private static boolean[] visited;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		rotate = new Pair[K];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rotate[i] = new Pair(r,c,s);
		}
		arr = new int[K];
		visited = new boolean[K];
		min = Integer.MAX_VALUE;
		
		combi(0);
		
		System.out.println(min);
	}
	static void combi(int count) {
		if(count == K) {
			go();
			return;
		}
		for(int i=0;i<K;i++) {
			if(visited[i]) continue;
			arr[count] = i;
			visited[i] = true;
			combi(count+1);
			visited[i] = false;
		}
	}
	static void go() {
		int[][] tmap = copy();
		for(int i=0;i<K;i++) {
			Pair temp = rotate[arr[i]];
			int sx = temp.r - temp.s;
			int sy = temp.c - temp.s;
			int ex = temp.r + temp.s;
			int ey = temp.c + temp.s;
			rotation(sx,sy,ex,ey,tmap);
		}
		for(int i=1;i<=N;i++) {
			int sum = 0;
			for(int j=1;j<=M;j++) {
				sum+=tmap[i][j];
			}
			min = Math.min(min,sum);
		}
	}
	
	
	private static void rotation(int sx, int sy, int ex, int ey, int[][] tmap) {
		int l1 = ex - sx-1;
		int l2 = ey - sy-1;
		int len = Math.min(l1,l2);
		while(len-->0) {
			if(sx==ex && sy==ey) {
				break;
			}
			int temp2 = tmap[sx][ey];
			int temp3 = tmap[ex][sy];
			int temp4 = tmap[ex][ey];
			
			
			for(int i=ey;i>sy;i--) { // 맨위
				tmap[sx][i] = tmap[sx][i-1];
			}
			for(int i=ex;i>sx+1;i--) { // 맨오른쪽
				tmap[i][ey] = tmap[i-1][ey];
			}
			tmap[sx+1][ey] = temp2;
			
			for(int i=sy;i<ey-1;i++) { // 맨 아래
				tmap[ex][i] = tmap[ex][i+1];
			}
			tmap[ex][ey-1] = temp4;
			
			for(int i=sx;i<ex-1;i++) { // 맨왼쪽
				tmap[i][sy] = tmap[i+1][sy];
			}
			tmap[ex-1][sy] = temp3;
			
			sx++;
			sy++;
			ex--;
			ey--;
		}
	}
	static int[][] copy() {
		int[][] copyarr = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				copyarr[i][j] = map[i][j];
			}
		}
		return copyarr;
	}
	
	static class Pair{
		int r,c,s;

		public Pair(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
