package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DFS(백트래킹)
 * 
 * @see 백준 19236번: 청소년 상어 <br>
 *      메모리: 13112 KB <br>
 *      시간: 80 ms
 * @since 2020-09-15
 * 
 */

public class boj_19236 {
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	private static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	private static int max;
	private static boolean[] visited;
	private static int[][] map;
	static Fish[] fishes = new Fish[17];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				fishes[num] = new Fish(i, j, dir);
			}
		}
		max = Integer.MIN_VALUE;
		visited = new boolean[17];

		int num = map[0][0];
		int dir = fishes[num].dir;
		visited[num] = true;

		map[0][0] = -1; // 상어가 -1
		dfs(0, 0, dir, num);
		System.out.println(max);
	}

	static void dfs(int x, int y, int dir, int num) {
		// 물고기 이동
		Fish[] tempFishes = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			tempFishes[i] = fishes[i];
		}

		int[][] tempMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempMap[i][j] = map[i][j];
			}
		}

		moveFish();

		// 상어 이동
		for (int i = 1; i < 4; i++) {
			int nx = x + dx[dir] * i;
			int ny = y + dy[dir] * i;

			if (nx < 0 || nx > 3 || ny < 0 || ny > 3 || map[nx][ny] == 0) {
				continue;
			}
			int fishNum = map[nx][ny];
			visited[fishNum] = true;
			map[nx][ny] = -1;
			map[x][y] = 0;
			dfs(nx, ny, fishes[fishNum].dir, num + fishNum);

			visited[fishNum] = false;
			map[x][y] = -1;
			map[nx][ny] = fishNum;
		}

		// 물고기 원상 복구
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = tempMap[i][j];
			}
		}

		for (int i = 1; i <= 16; i++) {
			fishes[i] = tempFishes[i];
		}
		max = Math.max(max, num);

	}

	static void moveFish() {
		for (int i = 1; i <= 16; i++) {

			if (visited[i])
				continue;

			Fish fish = fishes[i];
			int nd = fish.dir;
			int nx = fish.x;
			int ny = fish.y;
			boolean flag = false;

			for (int j = 0; j < 8; j++) {
				nx = fish.x + dx[nd];
				ny = fish.y + dy[nd];

				if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
					if (map[nx][ny] == -1) {
						nd = (nd + 1) % 9;
						if (nd == 0)
							nd += 1;
						continue;
					}
					if (map[nx][ny] == 0 || map[nx][ny] != -1) {
						flag = true;
						break;
					}
				} else {
					nd = (nd + 1) % 9;
					if (nd == 0)
						nd += 1;
				}
			}

			if (!flag)
				continue;

			int temp = map[nx][ny];
			map[nx][ny] = map[fish.x][fish.y];
			map[fish.x][fish.y] = temp;

			fishes[i] = new Fish(nx, ny, nd);

			if (temp != 0) {
				fishes[temp] = new Fish(fish.x, fish.y, fishes[temp].dir);
			}

		}
	}

	static class Fish {
		int x, y, dir;

		public Fish(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}
