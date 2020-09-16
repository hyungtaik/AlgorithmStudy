package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 19237번: 어른 상어 <br>
 *      메모리: KB <br>
 *      시간: ms
 * @since 2020-09-16
 * 
 */

public class boj_19237 {

	// 위 아래 왼 오
	private static int[] dx = { 0, -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, 0, -1, 1 };
	private static int N, M, K;
	private static int[][] map;
	private static Pair[] shark;
	private static trace[][] perfume;
	private static LinkedList<Pair> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 냄새 지속시간

		map = new int[N][N];
		perfume = new trace[N][N];
		shark = new Pair[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					shark[map[i][j]] = new Pair(i, j, -1, map[i][j]);
					perfume[i][j] = new trace(map[i][j], K);
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			int dir = Integer.parseInt(st.nextToken());
			shark[i].dir = dir;
		}

		q = new LinkedList<Pair>();
		// 위 아래 왼 오
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] up = new int[4];
			up[0] = Integer.parseInt(st.nextToken());
			up[1] = Integer.parseInt(st.nextToken());
			up[2] = Integer.parseInt(st.nextToken());
			up[3] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] down = new int[4];
			down[0] = Integer.parseInt(st.nextToken());
			down[1] = Integer.parseInt(st.nextToken());
			down[2] = Integer.parseInt(st.nextToken());
			down[3] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] left = new int[4];
			left[0] = Integer.parseInt(st.nextToken());
			left[1] = Integer.parseInt(st.nextToken());
			left[2] = Integer.parseInt(st.nextToken());
			left[3] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] right = new int[4];
			right[0] = Integer.parseInt(st.nextToken());
			right[1] = Integer.parseInt(st.nextToken());
			right[2] = Integer.parseInt(st.nextToken());
			right[3] = Integer.parseInt(st.nextToken());

			shark[i].up = new int[4];
			shark[i].up = up;
			shark[i].down = down;
			shark[i].left = left;
			shark[i].right = right;
			q.add(shark[i]);
		}

		int count = 0;
		int remain = M;
		while (remain > 1 && count < 1001) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("***********");
			int len = q.size();
			if (len == 1)
				break;

			// 상어들 이동하기
			for (int i = 1; i <= len; i++) {
				Pair temp = q.poll();
				int dir = temp.dir;
				int[] go = null;
				switch (dir) {
				case 1:
					go = temp.up;
					break;
				case 2:
					go = temp.down;
					break;
				case 3:
					go = temp.left;
					break;
				case 4:
					go = temp.right;
					break;
				}
				for (int j = 0; j < 4; j++) {
					int nx = temp.x + dx[go[j]];
					int ny = temp.y + dy[go[j]];
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
						continue;
					if (map[nx][ny] > 0 || perfume[nx][ny] != null) {
						if (perfume[nx][ny].num != map[temp.x][temp.y]) {
							continue;
						} else {
							dir = go[j];
							Pair p = new Pair(nx, ny, dir, temp.num);
							p.up = shark[temp.num].up;
							p.down = shark[temp.num].down;
							p.left = shark[temp.num].left;
							p.right = shark[temp.num].right;
							q.add(p);
							break;
						}
					} else {
						dir = go[j];
						Pair p = new Pair(nx, ny, dir, temp.num);
						p.up = shark[temp.num].up;
						p.down = shark[temp.num].down;
						p.left = shark[temp.num].left;
						p.right = shark[temp.num].right;
						q.add(p);
						break;

					}
				}
			}

			// 흔적 시간들 -1 하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (perfume[i][j] != null) {
						perfume[i][j].time--;
						if (perfume[i][j].time == 0)
							perfume[i][j] = null;
					}
					map[i][j] = 0;
				}
			}
			// 겹친애들중에서 젤 낮은 숫자만뺴고 다 지우기
			for (int i = 0; i < len; i++) {
				Pair temp = q.poll();
				if (map[temp.x][temp.y] != 0)
					continue;
				map[temp.x][temp.y] = temp.num;
				perfume[temp.x][temp.y] = new trace(temp.num, K);
				q.add(temp);
			}
			count++;

		}
		remain = q.size();
		if (remain != 1)
		{
			System.out.println(-1);
		} else {
			System.out.println(count);
		}
	}

	static class trace {
		int num;
		int time;

		public trace(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
	}

	static class Pair {
		int x, y;
		int dir;
		int num;
		int[] up, down, left, right;

		public Pair(int x, int y, int dir, int num) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.num = num;
		}
	}

}
