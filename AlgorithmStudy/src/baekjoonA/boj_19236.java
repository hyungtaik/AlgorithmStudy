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
 *      메모리: KB <br>
 *      시간: ms
 * @since 2020-09-14
 * 
 */

public class boj_19236 {
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	private static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
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
				fishes[num] = new Fish(i, j, num, dir);
			}
		}
		max = Integer.MIN_VALUE;
		int num = map[0][0];
		int dir = fishes[num].dir;
		// 상어가 -1
		map[0][0] = -1;
		visited = new boolean[17];
		visited[num] = true;
		dfs(0, 0, dir, num);
		System.out.println(max);
	}

	static void dfs(int x, int y, int dir, int num) {
		System.out.println(num);
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

		for (int i = 1; i <= 3; i++) {
			System.out.println(i + "!@#");
			int nx = x + dx[dir] * i;
			int ny = y + dy[dir] * i;

			if (nx < 0 || nx > 3 || ny < 0 || ny > 3 || map[nx][ny] == 0) {
				break;
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

			Fish f = fishes[i]; // 현재 움직일 물고기
//			System.out.println("현재 움직이는 물고기 번호 : " + i + " / 현재 물고기의 이동 방향 : " + f.d);
			int dir = f.dir;
			int nx = f.x, ny = f.y;
			boolean flag = false;

//			int[] changeDir = {0,2,3,4,5,6,7,8,1};

			for (int j = 1; j < 8; j++) {
				nx = f.x + dx[dir];
				ny = f.y + dy[dir];

				if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
					if (map[nx][ny] == -1) {
						dir = (dir + j) % 8;
						continue;
					}
					if (map[nx][ny] == 0 || map[nx][ny] != -1) {
						flag = true;
						break;
					}
				} else {
					dir = (dir + j) % 8;
				}
			}

			if (!flag)
				continue;

			int temp = map[nx][ny];
			map[nx][ny] = map[f.x][f.y];
			map[f.x][f.y] = temp;

			// 값 갱신
			fishes[i] = new Fish(nx, ny, temp, dir); // 현재 움직일 물고기

			if (temp != 0) { // 빈칸이 아니면 갱신
				fishes[temp] = new Fish(f.x, f.y, temp, fishes[temp].dir);
			}

		}
	}

	static class Fish implements Comparable<Fish> {
		int x, y, num, dir;

		public Fish(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}
	}

}
