package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1808 {

	private static int TC;
	private static int result;
	private static int[] cal;
	private static int num;
	private static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			cal = new int[1000001];
			numbers = new int[10];
			for (int i = 0; i < 10; i++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					numbers[i] = 1;
					cal[i] = 1;
				}
			}

			result = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());

			result = solve(num);
			if (result == Integer.MAX_VALUE) {
				result = -2;
			}
			System.out.println("#" + tc + " " + (result+1));
		}
	}

	private static int solve(int num) {
		if (cal[num] != 0)
			return cal[num];

		cal[num] = check(num);

		for (int i = 1; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) {
				int num1 = solve(i);
				int num2 = solve(num / i);
				if(num1 != Integer.MAX_VALUE && num2 != Integer.MAX_VALUE) {
					cal[num] = Math.min(cal[num], num1 + num2 + 1);
				}
			}
		}
		return cal[num];
	}

	private static int check(int number) {
		int count = 0;
		while (number > 0) {
			int temp = number % 10;
			if (numbers[temp]==0) {
				return Integer.MAX_VALUE;
			}
			count++;
			number /= 10;
		}
		return count;
	}

}
