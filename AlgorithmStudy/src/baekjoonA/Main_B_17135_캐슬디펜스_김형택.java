import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_17135_캐슬디펜스_김형택 {
	private static int N;
	private static int M;
	private static int D;
	private static int[][] map;

	private static ArrayList<Enemy> elist;
	private static ArrayList<Enemy> bow;
	private static int Max;
	private static int[] arr;
	private static int[] set;
	private static int[][] copy;
	private static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		Max = Integer.MIN_VALUE;
		map = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr = new int[M];
		for (int i = M - 1; i >= M - 3; i--) {
			arr[i] = 1;
		}

		num = new int[M];
		for (int i = 0; i < M; i++) {
			num[i] = i;
		}

		arr = new int[M];
		for (int i = M - 1; i >= M - 3; i--) {
			arr[i] = 1;
		}

		set = new int[3];

		do {
			for (int i = 0, j = 0; i < M; i++) {
				if (arr[i] == 1) {
					set[j++] = num[i];
				}
			}
			elist = new ArrayList<>();
			copy = new int[N + 1][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
					if (map[i][j] == 1)
						elist.add(new Enemy(i, j));
				}
			}
			bow = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				copy[N][set[i]] = 2;
				bow.add(new Enemy(N, num[set[i]]));
			}
			gameStart(copy);
		} while (np(arr));

		System.out.println(Max);
	}

	static boolean np(int[] p) {
		int N = p.length;

		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;
		if (i == 0)
			return false;

		int j = N - 1;
		while (p[i - 1] >= p[j])
			--j;

		int temp = p[j];
		p[j] = p[i - 1];
		p[i - 1] = temp;

		j = N - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}

		return true;
	}

	static void gameStart(int[][] map) {
		int count = 0;

		while (elist.size() != 0) {
			ArrayList<Enemy> delete = new ArrayList<>();

			for (int i = 0; i < bow.size(); i++) {
				int[] dir = new int[elist.size()];
				int minDir = Integer.MAX_VALUE;

				for (int j = 0; j < elist.size(); j++) {
					dir[j] = (Math.abs(elist.get(j).x - bow.get(i).x)) + (Math.abs(elist.get(j).y - bow.get(i).y));
					if (minDir > dir[j])
						minDir = dir[j];
				}
				ArrayList<Enemy> check = new ArrayList<>();
				for (int j = 0; j < elist.size(); j++) {
					if (dir[j] == minDir) {
						if (dir[j] <= D) {
							check.add(elist.get(j));
						}
					}
				}
				if (check.size() == 0)
					continue;
				else if (check.size() == 1) {
					delete.add(check.get(0));
				} else if (check.size() >= 2) {
					int minX = Integer.MAX_VALUE;

					for (int j = 0; j < check.size(); j++) {
						if (minX > check.get(j).y)
							minX = check.get(j).y;
					}
					for (int j = 0; j < check.size(); j++) {
						if (check.get(j).y == minX) {
							delete.add(check.get(j));
						}
					}
				}

			}
			for (int i = 0; i < delete.size(); i++) {
				for (int j = 0; j < elist.size(); j++) {
					if (delete.get(i).x == elist.get(j).x && delete.get(i).y == elist.get(j).y) {
						elist.remove(j);
						count++;
						break;
					}
				}
			}
			int len = elist.size();
			int index = 0;
			while (len > 0) {
				if (elist.get(index).x != N - 1) {
					elist.add(new Enemy(elist.get(index).x + 1, elist.get(index).y));
				}
				elist.remove(index);
				len--;
			}
		}
		Max = Math.max(Max, count);
	}

	static class Enemy{
		int x;
		int y;

		public Enemy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}