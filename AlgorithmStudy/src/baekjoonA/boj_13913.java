
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_13913 {

	private static int N;
	private static int K;

	private static int[] map;
	private static LinkedList<Pair> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		map = new int[100001];
		Arrays.fill(map, -1);
		q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		int min = bfs();
		System.out.println(min);
		Stack<Integer> stack = new Stack<>();
//		stack.push(K);
		int k = K;
		for(int i=0;i<min;i++) {
			stack.push(map[K]);
			K = map[K];
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}System.out.println(k);
	}

	static int bfs() {
		q.add(new Pair(N, 0));
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			int tindex = temp.index;
			int tcount = temp.count;
			if (tindex < 0 || tindex > 100000)
				continue;
			if (tindex == K) {
				return tcount;
			} else {
				int[] dir = { tindex + 1, tindex - 1, tindex * 2 };
				for (int i = 0; i < 3; i++) {
					int nx = dir[i];
					if (nx < 0 || nx > 100000)
						continue;
					if (map[nx] == -1) {
						map[nx] = tindex;
						q.add(new Pair(nx, tcount + 1));
					}

				}
			}
		}
		return -1;
	}

	static class Pair {
		int index;
		int count;

		public Pair(int index, int count) {
			this.index = index;
			this.count = count;
		}

	}
}
