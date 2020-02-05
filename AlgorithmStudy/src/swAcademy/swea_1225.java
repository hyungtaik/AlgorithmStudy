package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1225 {
	static int[] arr; // 암호 배열
	static Queue<Integer> q; // 암호 큐

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			arr = new int[8];
			int tcase = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			q = new LinkedList<Integer>();
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			L:while (true) {
				int index = 1;
				for (int i = 0; i < 5; i++) {
					int temp = q.poll();
					temp -= index++;
					if (temp <= 0) {
						temp = 0;
						q.offer(temp);
						break L;
					}
					q.offer(temp);
				}
			}
			System.out.print("#" + tcase);
			while (!q.isEmpty())
				System.out.print(" " + q.poll());
			System.out.println();
		}
	}

}
