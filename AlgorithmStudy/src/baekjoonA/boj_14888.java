package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888 {
	static int N; // 수열의 크기
	static int[] arr; // 수열
	static int[] cal; // 계산 배열 0: 더하기 1: 빼기 2: 곱하기 3: 나누기

	static int max, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		arr = new int[N];
		cal = new int[4];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
		solve(0, 0);
		System.out.println(max);
		System.out.println(min);
	}

	static void solve(int index, int temp) {
		if (index == N - 1) {
			max = Math.max(max, temp);
			min = Math.min(min, temp);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (cal[i] == 0)
				continue;
			cal[i]--;
			int e = temp; //미리 저장

			temp = calculate(arr[index], arr[index + 1], i);
			int tmp = arr[index + 1]; //미리 저장
			arr[index + 1] = temp; //변경
			solve(index + 1, temp);
			arr[index + 1] = tmp; //복귀
			temp = e; //복귀
			cal[i]++;
		}
	}

	static int calculate(int a, int b, int what) {
		int result = 0;
		switch (what) {
		case 0:
			result = a + b;
			break;
		case 1:
			result = a - b;
			break;
		case 2:
			result = a * b;
			break;
		case 3:
			if (a < 0) {
				result = Math.abs(a) / b;
				result *= -1;
			} else {
				result = a / b;
			}
			break;
		}
		return result;
	}
}
