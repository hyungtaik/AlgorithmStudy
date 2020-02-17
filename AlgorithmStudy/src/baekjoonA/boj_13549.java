package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_13549 {

	private static int N;
	private static int K;
	private static LinkedList<Pair> q;
	private static boolean[] check;
	private static int len;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 수빈 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		q = new LinkedList<Pair>();
		check = new boolean[100001];
		int result = 0;
		if(N>=K) {
			result = N-K;
		}else {
			result = bfs();
		}
		System.out.println(result);
	}

	static int bfs() {
		q.add(new Pair(N, 0));
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			check[temp.index] = true;
			
			int[] dir = { temp.index * 2, temp.index - 1, temp.index + 1  };
			for (int j = 0; j < 3; j++) {
				if(dir[j]<0 || dir[j]>100000) continue;
				if(check[dir[j]]==true) continue;
				check[dir[j]] = true;
				
				if (j == 0) {
					if(dir[j] == K) {
						return temp.count;
					}
					q.add(new Pair(dir[j], temp.count));
				}else {
					if(dir[j] == K) {
						return temp.count+1;
					}
					q.add(new Pair(dir[j], temp.count + 1));
				}
			}
		}
		return 0;
	}
	
	
	static class Pair  {
		int index;
		int count;

		public Pair(int index, int count) {
			super();
			this.index = index;
			this.count = count;
		}

	}

}