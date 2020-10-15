package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 2251번: 물통 <br>
 *      메모리: 21876 KB <br>
 *      시간: 92 ms
 * @since 2020-10-15
 * 
 */

public class boj_2251 {

	private static int A;
	private static int B;
	private static int C;
	private static LinkedList<Pair> q;
	private static boolean[][][] visited;
	private static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, C));
		list = new ArrayList<Integer>();
		visited = new boolean[A + 1][B + 1][C + 1];

		while (!q.isEmpty()) {
			Pair temp = q.poll();
			if (visited[temp.a][temp.b][temp.c])
				continue;
			if (temp.a == 0) {
				list.add(temp.c);
			}
			visited[temp.a][temp.b][temp.c] = true;

			// A로 이동
			// B -> A
			int num = temp.a + temp.b;
			if (num <= A) {
				q.add(new Pair(num, 0, temp.c));
			} else {
				q.add(new Pair(A, num - A, temp.c));
			}

			// C -> A
			num = temp.a + temp.c;
			if (num <= A) {
				q.add(new Pair(num, temp.b, 0));
			} else {
				q.add(new Pair(A, temp.b, num - A));
			}

			// B로 이동
			// A -> B
			num = temp.a + temp.b;
			if (num <= B) {
				q.add(new Pair(0, num, temp.c));
			} else {
				q.add(new Pair(num - B, B, temp.c));
			}

			// C -> B
			num = temp.b + temp.c;
			if (num <= B) {
				q.add(new Pair(temp.a, num, 0));
			} else {
				q.add(new Pair(temp.a, B, num - B));
			}

			// C로 이동
			// A -> C
			num = temp.a + temp.c;
			if (num <= C) {
				q.add(new Pair(0, temp.b, num));
			} else {
				q.add(new Pair(num - C, temp.b, C));
			}

			// B -> C
			num = temp.b + temp.c;
			if (num <= C) {
				q.add(new Pair(temp.a, 0, num));
			} else {
				q.add(new Pair(temp.a, num - C, C));
			}
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}

	}

	static class Pair {
		int a, b, c;

		public Pair(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

}
