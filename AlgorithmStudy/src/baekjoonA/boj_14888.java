package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888 {
	static int N; // ������ ũ��
	static int[] arr; // ����
	static int[] cal; // ��� �迭 0: ���ϱ� 1: ���� 2: ���ϱ� 3: ������

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
			int e = temp; //�̸� ����

			temp = calculate(arr[index], arr[index + 1], i);
			int tmp = arr[index + 1]; //�̸� ����
			arr[index + 1] = temp; //����
			solve(index + 1, temp);
			arr[index + 1] = tmp; //����
			temp = e; //����
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
