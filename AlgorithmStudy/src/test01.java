import java.util.Scanner;

public class test01 {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int max = 0;
			
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			if(n>=1 && n<=10000 && 1<=k && k<=n) {
				int arr[] = new int[n];
				for (int j = 0; j < n; j++) {
					arr[j] = sc.nextInt();
				}
				int a = 1;
				int trr[][] = new int[2][k + 1];
				for (int r = 0; r < n; r++) {
					int b = arr[r];
					if (a >= b && (b != 0)) {
						trr[0][a] += 1;
					}
					else if (a < b) {
						a = b;
						trr[0][a] += 1;
					}
					else if (b == 0) {
						trr[1][a] += 1;
						if (r != n - 1) {
							if (arr[r + 1] == a) {
								trr[0][a] += trr[1][a];
								trr[1][a] = 0;
							}
						}
					}
				}
				for (int d = 1; d <= k; d++) {
					if(trr[0][d] == 0) {
						if(d==1) {
							trr[0][d]++;
							trr[1][d]--;
						}else {
							trr[0][d]++;
							trr[1][d - 1]--;
							trr[1][d] = trr[1][d - 1];
							trr[1][d - 1] = 0;
						}
					}

				}
				for (int c = 1; c <= k; c++) {
					int result = trr[0][c] + trr[1][c] + trr[1][c - 1];
					if (max < result) {
						max = result;
					}
				}
				System.out.println("#" + (i + 1) + " " + max);
			}else {
				System.out.println("#" + (i + 1) + " 예외 처리 조건입니다.");
			}
			

		}
	}

}
