package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14499 {
	private static class Dice {
		int top, bottom, east, west, south, north;

		public Dice() {
			this.top = 0;
			this.bottom = 0;
			this.east = 0;
			this.west = 0;
			this.south = 0;
			this.north = 0;
		}

		public void moveEast() {
			int temp = top;
			top = west;
			west = bottom;
			bottom = east;
			east = temp;
		}

		public void moveWest() {
			int temp = top;
			top = east;
			east = bottom;
			bottom = west;
			west = temp;
		}

		public void moveNorth() {
			int temp = top;
			top = south;
			south = bottom;
			bottom = north;
			north = temp;
		}

		public void moveSouth() {
			int temp = top;
			top = north;
			north = bottom;
			bottom = south;
			south = temp;
		}

		public void printTop() {
			System.out.println(top);
		}
	}

	static int[][] map;
	static int[] move;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int K, N, M, x, y;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		move = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}

		Dice dice = new Dice();
		int nx,ny;
		for (int i = 0; i < K; i++) {
			int direction = move[i]-1;
			nx = x+dx[direction];
			ny = y+dy[direction];
			
			if(0<=nx && nx <N && 0<= ny && ny < M) {
				if (direction == 0) {
					dice.moveEast();
				}
				else if (direction == 1) {
					dice.moveWest();
				}
				else if (direction == 2) {
					dice.moveNorth();
				}
				else if (direction == 3) {
					dice.moveSouth();
				}
				
				if(map[nx][ny]==0) {
					map[nx][ny] = dice.bottom;
				}else {
					dice.bottom = map[nx][ny];
					map[nx][ny] = 0;
				}
				
				dice.printTop();
				x = nx;
				y = ny;
			}
			
			
		}

	}

}
