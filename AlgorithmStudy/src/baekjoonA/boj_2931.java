package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS + 시뮬레이션
 * 
 * @see 백준 2931번 : 가스관<br>
 * 		메모리 : 13056 KB
 * 		시간 : 80 ms
 * 
 * @since 2020-08-30
 * 
 */

public class boj_2931 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static LinkedList<Point> q;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static HashMap<Character, boolean[]> pipe;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		pipe = new HashMap<Character, boolean[]>();
		pipe.put('|', new boolean[] { true, true, false, false });
		pipe.put('-', new boolean[] { false, false, true, true });
		pipe.put('+', new boolean[] { true, true, true, true });
		pipe.put('1', new boolean[] { false, true, false, true });
		pipe.put('2', new boolean[] { true, false, false, true });
		pipe.put('3', new boolean[] { true, false, true, false });
		pipe.put('4', new boolean[] { false, true, true, false });

		Point start = new Point();
		Point end = new Point();
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'M') {
					start = new Point(i, j);
				} else if (map[i][j] == 'Z') {
					end = new Point(i, j);
				}
			}
		}
		visited = new boolean[R][C];
		visited[start.x][start.y] = true;
		q = new LinkedList<Point>();
		for (int i = 0; i < 4; i++) {
			int nx = start.x + dx[i];
			int ny = start.y + dy[i];
			if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1)
				continue;
			if (map[nx][ny] == '.' || map[nx][ny] == 'Z')
				continue;
			q.add(new Point(nx, ny));
			visited[nx][ny] = true;
			break; // 하나의 블록만 인접
		}
		
		while (!q.isEmpty()) {
			Point temp = q.poll();
			char curPipe = map[temp.x][temp.y];
			if (curPipe == '.') { // 길이 없는 경우
				boolean[] findPipe = new boolean[4];
				for (int dir = 0; dir < 4; dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || !pipeCheck(map[nx][ny], dir)) {
						findPipe[dir] = false;
					} else {
						findPipe[dir] = true;
					}
				}
				System.out.println((temp.x+1) +" "+ (temp.y+1) +" "+ printPipe(findPipe));
				break;
			}
			boolean[] pipeArr = pipe.get(curPipe);
			for (int dir = 0; dir < 4; dir++) {
				int nx = temp.x + dx[dir];
				int ny = temp.y + dy[dir];
				if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || visited[nx][ny])
					continue;
				if (!pipeArr[dir])
					continue; // 이동할 수 없는 경우

				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}

	static char printPipe(boolean[] findPipe) {
		// 상, 하, 좌, 우
		if(findPipe[0]) {
			if(findPipe[1]) {
				if(findPipe[2]) {
					return '+';
				}else {
					return'|';
				}
			}else {
				if(findPipe[2]) {
					return '3';
				}else {
					return '2';
				}
			}
		}else {
			if(findPipe[1]) {
				if(findPipe[3]) {
					return '1';
				}else {
					return '4';
				}
			}else {
				return '-';
			}
		}
		
	}

	static boolean pipeCheck(char input, int dir) {
		switch (input) {
		case '|':
			if (dir == 0 || dir == 1)
				return true;
			return false;
		case '-':
			if (dir == 2 || dir == 3)
				return true;
			return false;
		case '+':
			return true;
		case '1':
			if (dir == 0 || dir == 2)
				return true;
			return false;
		case '2':
			if (dir == 1 || dir == 2)
				return true;
			return false;
		case '3':
			if (dir == 1 || dir == 3)
				return true;
			return false;
		case '4':
			if (dir == 0 || dir == 3)
				return true;
			return false;
		default:
			return false;
		}
	}

	static class Point {
		int x, y;

		public Point() {
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
