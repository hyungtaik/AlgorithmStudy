package swAcademy;

import java.util.Scanner;

public class q3431 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int l;
		int u;
		int x;
		for (int i = 0; i < tc; i++) {
			int result = 0;

			l = sc.nextInt();
			u = sc.nextInt();
			x = sc.nextInt();
			if (u < x) {
				result = -1;
			}
			if (l > x) {
				result = l - x;
			}
			if (l <= x && x <= u) {
				result = 0;
			}
			System.out.println("#" + (i + 1) + " " + result);

		}
	}
}
