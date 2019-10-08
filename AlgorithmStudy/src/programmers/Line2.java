package programmers;

import java.util.*;

public class Line2 {
	public static boolean perm(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i -= 1;
		}

		if (i <= 0) {
			return false;
		}

		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1]) {
			j -= 1;
		}

		int k = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = k;

		j = arr.length - 1;
		while (i < j) {
			k = arr[i];
			arr[i] = arr[j];
			arr[j] = k;
			i += 1;
			j -= 1;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());

		int N = st.countTokens();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int num = sc.nextInt();
		int check = 1;
		do {
			for (int i = 0; i < N; i++) {
				if (check == num) {
					System.out.print(arr[i]);
				}
			}
			check++;

		} while (perm(arr));
	}
}