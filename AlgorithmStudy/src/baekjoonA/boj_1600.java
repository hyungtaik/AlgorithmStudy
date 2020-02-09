package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1600¹ø ¸»ÀÌ µÇ°íÇÂ ¿ø¼þÀÌ
public class boj_1600 {

	private static int K;
	private static int W;
	private static int H;
	private static int[][] map; // ¸Ê
	private static boolean[][][] check; // check ¸Ê
	private static int[] dx = { -1, 1, 0, 0 }; // »óÇÏÁÂ¿ì
	private static int[] dy = { 0, 0, -1, 1 };
	
	private static int[] hx = { -2,-1,-2,-1,1,2,1,2 }; // horse »óÇÏÁÂ¿ì
	private static int[] hy = { -1,-2,1,2,-2,-1,2,1 };
	
	
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		K = Integer.parseInt(st.nextToken()); // ¸»Ã³·³ ¶Ù´Â È½¼ö
		st = new StringTokenizer(br.readLine(), " ");

		W = Integer.parseInt(st.nextToken()); // °¡·Î
		H = Integer.parseInt(st.nextToken()); // ¼¼·Î
		map = new int[H][W];
		check = new boolean[H][W][K+1];
		result = Integer.MAX_VALUE;

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = bfs();
		System.out.println(result);
	}

	// bfs·Î Â¥º¼°Í
	static int bfs() {
		Queue<test> q = new LinkedList<test>();
		q.add(new test(0, 0, 0, K));
		while (!q.isEmpty()) {
//			System.out.println("ddd");
			test temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int count = temp.count;
			check[x][y][count] = true;
			if(x == H -1 && y == W -1) {
				return temp.move;
			}
			if(result <= temp.move) continue;
			
			if (count > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = x + hx[i];
					int ny = y + hy[i];
					if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1 || map[nx][ny] == 1)
						continue;
					if(check[nx][ny][count-1] == true) continue;
					q.add(new test(nx, ny, temp.move + 1, count - 1));
					check[nx][ny][count-1] = true;
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1 || map[nx][ny] == 1)
					continue;
				if(check[nx][ny][count] == true) continue;
				q.add(new test(nx, ny, temp.move + 1, count));
				check[nx][ny][count] = true;
			}
		}
		return -1;
		
	}

	static class test {
		int x, y;
		int move;
		int count; // K»ç¿ë Ä«¿îÆ®

		public test(int x, int y, int move, int count) {
			super();
			this.x = x;
			this.y = y;
			this.move = move;
			this.count = count;
		}

	}

}
