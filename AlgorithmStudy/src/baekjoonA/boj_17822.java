package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_17822 {

	private static int N, M, T;
	private static int[][] map;
	private static LinkedList<Command> q;
	private static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q = new LinkedList<Command>();
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			q.add(new Command(x, d, k));
		}
		result = 0; // 맵 전체 합
		solve();
		System.out.println(result);
	}

	static void solve() {
		while (!q.isEmpty()) {
			Command temp = q.poll();
			rotate(temp.x, temp.d, temp.k); // 회전
			
			boolean check = removeSame(); // 인접한게 있으면 true, 없으면 false; 
			if (!check) { // 변한게 없으면 전부더하고 평균구해서 값 조정.
				check = calc();
			}
			if(!check) {
				result=0;
				return;
			}
		}
		result = calcMap(); // 전체 맵 값 더하기(결과값)
		return;
	}

	// d:0 = 시계방향(오른쪽) d:1 = 반시계방향(왼쪽)
	static void rotate(int x, int d, int k) {
		int go = 0;
		for (int i = 0; i < N; i++) {
			if ((i + 1) % x != 0)
				continue; // 원판번호 배수가 아니면 continue;
			
			go = k;
			while (go-- > 0) {
				if (d == 1) {
					int t = map[i][0];
					for (int j = 0; j < M - 1; j++) {
						map[i][j] = map[i][j + 1];
					}
					map[i][M - 1] = t;
				} else { // d = 0
					int t = map[i][M - 1];
					for (int j = M - 1; j > 0; j--) {
						map[i][j] = map[i][j - 1];
					}
					map[i][0] = t;
				}
			}
		}
	}

	static boolean removeSame() {
		int count = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M ; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (nx < 0 || nx > N - 1)
							continue;
						
						if (ny < 0)
							ny = M-1;
						else if (ny > M - 1)
							ny = 0;
						if(visited[nx][ny]) continue;
						
						if (map[i][j] == map[nx][ny]) {
							visited[i][j] = true;
							visited[nx][ny] = true;
							count++;
						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					map[i][j] = -1;
			}
		}

		if (count > 0)
			return true;
		else
			return false;
	}
	static boolean calc() {
		int count = 0;
		double total = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]>0) {
					count++;
					total+=map[i][j];
				}
			}
		}
		if(count==0) {
			return false;
		}
		double avg = total/count;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]<=0) continue;
				double temp = map[i][j];
				if(temp>avg) {
					map[i][j]--;
				}else if(temp<avg) {
					map[i][j]++;
				}
			}
		}
		return true;
	}
	
	static int calcMap() {
		int total = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]>0)
				total+=map[i][j];
			}
		}
		return total;
	}

	static class Command {
		int x; // 원판 번호
		int d; // 방향
		int k; // 칸

		public Command(int x, int d, int k) {
			super();
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}
}
