package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class message {
	int num, time;

	message(int num, int time) {
		this.num = num;
		this.time = time;
	}
}
public class Line01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int consumer = sc.nextInt();

		Queue<message> q = new LinkedList<message>();
		int carr[] = new int[consumer];

		for (int i = 0; i < num; i++) {
			q.add(new message(i, sc.nextInt()));
		}

		for (int i = 0; i < consumer; i++) {
			message temp = q.remove();
			carr[i] = temp.time;
		}
		Arrays.sort(carr);

		while (!q.isEmpty()) {
			message mem = q.remove();
			carr[0] += mem.time;
			Arrays.sort(carr);
		}
		int result = carr[consumer - 1];
		System.out.println(result);
	}
}
