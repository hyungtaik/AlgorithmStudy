package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj13460 {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N,M;
	static boolean[][][][] checked;
	static char[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		System.out.println(N+", "+M);
		// Red, Blue 위치 변수
		
		map = new char[N][M];
		checked = new boolean[10][10][10][10];

		point pt = new point();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'R') {
					pt.rx = i;
					pt.ry = j;
				} else if (map[i][j] == 'B') {
					pt.bx = i;
					pt.by = j;
				}
			}
		}
		System.out.println(bfs(pt));

	}

	public static int bfs(point p) {
		Queue<point> q = new LinkedList<point>();
		int result = 0;
		q.add(p);

		while (!q.isEmpty()) {
			point pt = q.poll();
			checked[pt.rx][pt.ry][pt.bx][pt.by] = true;

			if (pt.count >= 10) {
				return -1;
			}
	
			for (int i = 0; i < 4; i++) {
				int curBx = pt.bx;
				int curBy = pt.by;

				while (map[curBx + dx[i]][curBy + dy[i]] != '#') {
					curBx += dx[i];
					curBy += dy[i];
					if (map[curBx][curBy] == 'O') {
						break;
					}
				}

				int curRx = pt.rx;
				int curRy = pt.ry;

				while (map[curRx + dx[i]][curRy + dy[i]] != '#') {
					curRx += dx[i];
					curRy += dy[i];
					if (map[curRx][curRy] == 'O') {
						break;
					}
				}

				if (map[curBx][curBy] == 'O')
					continue;

				if (map[curRx][curRy] == 'O')
					return pt.count + 1;

				if (curRx == curBx && curRy == curBy) {
					switch (i) {
						case 0: // 서
							if(pt.by<pt.ry) {
								curRy += 1;
							}else {
								curBy += 1;
							}
							break;
						case 1://동
							if(pt.by < pt.ry) {
								curBy -= 1;
							}else {
								curRy -= 1;
							}
							break;
						case 2://북
							if(pt.bx < pt.rx) {
								curRx += 1; 
							}else {
								curBx += 1;
							}
							break;
						case 3://남
							if(pt.bx < pt.rx) {
								curBx -=1;
							}else {
								curRx -= 1;
							}
							break;
					}
				}
				if(!checked[curRx][curRy][curBx][curBy]) {
					q.add(new point(curRx,curRy,curBx,curBy,pt.count+1));
				}
			}
		}
		return -1;
	}
	static class point {
		int rx, ry, bx, by, count;

		public point() {
			this.rx = 0;
			this.ry = 0;
			this.bx = 0;
			this.by = 0;
			this.count = 0;
		}

		public point(int rx, int ry, int bx, int by, int count) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.count = count;

		}
	}
}
