package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16236 {

	private static int N;
	private static int[][] map;
	private static int[] dx = { -1, 0, 0, 1 }; // 상 좌 우 하
	private static int[] dy = { 0, -1, 1, 0 };
	private static int count;
	private static int weight; // 물고기 크기
	private static int liveTime;
	private static Queue<Fish> q;
	private static LinkedList<Fish> fish;

	private static int[][] distance; // 거리 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 공간 크기
		map = new int[N][N]; // map 정보
		count = 0;
		weight = 2; // 초기 크기 2
		liveTime = 0; // 생존 시간

		q = new LinkedList<Fish>(); // 상어 위치 연결리스트
		// 물고기 있는 연결리스트

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					if (map[i][j] == 9) {
						q.add(new Fish(i, j, 0)); // 상어 위치 정보
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
			distance = new int[N][N]; // 거리 배열 초기화
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
			liveTime += start.dist; // 이동 거리 계산
			count += 1; // 먹은 물고기
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
		int dist; // 거리

		public Fish(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

}
