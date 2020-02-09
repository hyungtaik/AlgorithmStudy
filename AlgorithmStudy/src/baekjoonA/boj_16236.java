package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16236 {

	private static int N;
	private static int[][] map;
	private static int[] dx = { -1, 0, 0, 1 }; // �� �� �� ��
	private static int[] dy = { 0, -1, 1, 0 };
	private static int count;
	private static int weight; // ����� ũ��
	private static int liveTime;
	private static Queue<Fish> q;
	private static LinkedList<Fish> fish;

	private static int[][] distance; // �Ÿ� �迭

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // ���� ũ��
		map = new int[N][N]; // map ����
		count = 0;
		weight = 2; // �ʱ� ũ�� 2
		liveTime = 0; // ���� �ð�

		q = new LinkedList<Fish>(); // ��� ��ġ ���Ḯ��Ʈ
		// ����� �ִ� ���Ḯ��Ʈ

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					if (map[i][j] == 9) {
						q.add(new Fish(i, j, 0)); // ��� ��ġ ����
						map[i][j] = 0;
					}
				}
			}
		}

		bfs();
		System.out.println(liveTime);
	}

	static void bfs() {
		while (true) {
			fish = new LinkedList<Fish>();
			distance = new int[N][N]; // �Ÿ� �迭 �ʱ�ȭ
			while (!q.isEmpty()) {
				Fish shark = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = shark.x + dx[i];
					int ny = shark.y + dy[i];
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
						continue;

					if (map[nx][ny] <= weight && distance[nx][ny] == 0) {
						distance[nx][ny] = distance[shark.x][shark.y] + 1;
						if (map[nx][ny] >= 1 && map[nx][ny] <= 6 && map[nx][ny] < weight) {
							fish.add(new Fish(nx, ny, distance[nx][ny]));
							q.add(new Fish(nx, ny, distance[nx][ny]));
							continue;
						}
						q.add(new Fish(nx, ny, distance[nx][ny]));
					}
				}
			}

			if (fish.size() == 0) {
				return;
			}

			Fish start = fish.get(0);
			for (int i = 1; i < fish.size(); i++) {
				if (start.dist > fish.get(i).dist) {
					start = fish.get(i);
				}
				if (start.dist == fish.get(i).dist) {
					if (start.x > fish.get(i).x) {
						start = fish.get(i);
						continue;
					} else if (start.x == fish.get(i).x && start.y > fish.get(i).y)
						start = fish.get(i);
				}
			}
			map[start.x][start.y] = 0;
			liveTime += start.dist; // �̵� �Ÿ� ���
			count += 1; // ���� �����
			if (weight == count) {
				weight++;
				count = 0;
			}
			q.add(new Fish(start.x, start.y, weight));
		}

	}

	static class Fish {
		int x;
		int y;
		int dist; // �Ÿ�

		public Fish(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

}
