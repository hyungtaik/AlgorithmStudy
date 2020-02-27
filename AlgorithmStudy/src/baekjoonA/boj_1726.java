package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1726 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static LinkedList<Pair> q;
	private static Pair end;
	private static int[] dx = { 0, 0, 0, 1, -1 }; // 1234
	private static int[] dy = { 0, 1, -1, 0, 0 }; // 동서남북
	private static boolean[][][] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken()); // 세로 행
		N = Integer.parseInt(st.nextToken()); // 가로 열
		map = new int[M][N];
		checked = new boolean[M][N][5];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		q = new LinkedList<Pair>();
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		checked[x - 1][y - 1][dir] = true;
		q.add(new Pair(x - 1, y - 1, dir, 0));
		st = new StringTokenizer(br.readLine(), " ");
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		end = new Pair(x - 1, y - 1, dir, 0);
		bfs();
	}

	private static void bfs() {

	while(!q.isEmpty()) {
		Pair curPair= q.poll();
		int curDir = curPair.dir;
		int curX = curPair.x;
		int curY = curPair.y;
		int curCnt = curPair.count;
		
		if(curX == end.x && curY == end.y && curDir == end.dir) {
			System.out.println(curCnt);
			return;
		}
		
		// 칸 이동 
		for(int i = 1; i <= 3; i++) { 
			int nextX = curX + dx[curDir] * i;
			int nextY = curY + dy[curDir] * i;
			
			if(nextX > M-1 || nextY > N-1 || nextX < 0 || nextY <0) {
				break;
			}else {
				if(map[nextX][nextY] == 0) { // 이동 가능 
					if(!checked[nextX][nextY][curDir]) {
						checked[nextX][nextY][curDir] = true;
						q.add(new Pair(nextX, nextY, curDir, curCnt+1));
					}
				} else { // 그 이상의 칸으로 이동 불가 
					break;
				}
			}
		}
		for(int i = 1; i <= 4; i++) { 
			if(curDir != i && !checked[curX][curY][i]) {
				checked[curX][curY][i] = true;
				if((curDir == 1 && i == 2 )|| (curDir == 2 && i == 1 ) || (curDir == 3 && i == 4) ||(curDir == 4 && i == 3)) {
					q.add(new Pair(curX, curY, i, curCnt+2));
				}else {
					q.add(new Pair(curX, curY, i, curCnt+1));
				}
			}
		}
	}
	}

	static class Pair {
		int x, y, dir, count;

		public Pair(int x, int y, int dir, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}
}

