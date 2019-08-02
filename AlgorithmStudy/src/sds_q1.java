import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class sds_q1 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./src/sample_input.txt"));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int i = 0; i < t; i++) {
			int max = 0;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(n>=1 && n<=10000 && 1<=k && k<=n) {
				int arr[] = new int[n];
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int temp = Integer.parseInt(st.nextToken());
					arr[j] = temp;
				}
				int a = 1;
				int trr[][] = new int[2][k + 1];
				for (int r = 0; r < n; r++) {
					int b = arr[r];
					if (a >= b && (b != 0)) {
						trr[0][a] += 1;
					}
					if (a < b) {
						a = b;
						trr[0][a] += 1;
					}
					if (b == 0) {
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
					if ((d == 1) && (trr[0][d]) == 0) {
						trr[0][d]++;
						trr[1][d]--;
					} else {
						if (trr[0][d] == 0) {
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
				br.readLine();
				System.out.println("#" + (i + 1) + " 예외 처리 조건입니다.");
			}
			

		}
		br.close();
	}

}
