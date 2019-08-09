package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_5656 {
	static int N, W, H;
	static int[][] map;
	static int min;
	static Queue<Point> q = new LinkedList<>();

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] copy(int[][] map) {
		int[][] temp = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	static void solve(int[][] map, int count) {
		if (count == N) {
			int sum = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0) {
						sum++;
					}
				}
			}
			min = Math.min(min, sum);
			
			return;
		}

		for (int j = 0; j < W; j++) {
			int i = 0;

			for (; i < H; i++) {
				if (map[i][j] != 0)
					break;
			}
			if (i != H) {
				int[][] arr = copy(map);
				q.add(new Point(i, j));
				breakBrick(arr);
				solve(arr, count + 1);
			}
		}
	}

	static void breakBrick(int[][] map) {
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			int range = map[cur.x][cur.y] - 1;
			map[cur.x][cur.y] = 0;
			int sX = (cur.x-range < 0 ? 0 : cur.x-range);
			int sY = (cur.y-range < 0 ? 0 : cur.y-range);
			int eX = (cur.x+range >= H ? H-1 : cur.x+range);
			int eY = (cur.y+range >= W ? W-1 : cur.y+range);
			
			for(;sX<=eX;sX++) {
				if(map[sX][cur.y] == 0) continue;
				if(map[sX][cur.y] == 1) {
					map[sX][cur.y] = 0;
				}else {
					q.add(new Point(sX,cur.y));
				}
			}
			for(;sY<=eY;sY++) {
				if(map[cur.x][sY] == 0) continue;
				if(map[cur.x][sY] == 1) {
					map[cur.x][sY] = 0;
				}else {
					q.add(new Point(cur.x,sY));
				}
			}
			//블록 정리
			
		}
		for(int i=H-2;i>=0;i--) {
			for(int j=0;j<W;j++) {
				if(map[i][j] == 0) continue;
				int range = map[i][j];
				int temp = i;
				map[i][j] = 0;
				while(true) {
					if(temp+1 >= H || map[temp+1][j]!=0) break;
					temp++;
				}
				map[temp][j] = range;
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int token = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= token; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H + 1][W + 1];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve(map, 0);
			if(min == Integer.MAX_VALUE) min = 0;
			System.out.println("#" + tc + " " + min);
		}

	}

}
