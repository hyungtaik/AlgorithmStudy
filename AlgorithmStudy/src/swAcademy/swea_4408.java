import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_4408 {

	private static int TC;
	private static int N;
	private static int[][] map;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[3][201];
			max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int startX = Integer.parseInt(st.nextToken());
				int startY = 0;
				int endX = Integer.parseInt(st.nextToken());
				int endY = 0;
				// 홀수 짝수 나누기
				// X가 열, Y가 행
				if (startX % 2 == 0) { // 짝수
					startX = startX / 2 - 1;
					startY = 2;
				} else {
					startX /= 2;
					startY = 0;
				}
				map[startY][startX] = 8;

				if (endX % 2 == 0) { // 짝수
					endX = endX / 2 - 1;
					endY = 2;
				} else {
					endX /= 2;
					endY = 0;
				}

				map[endY][endX] = 9;
				if (startX < endX) {
					for (int j = startX; j <= endX; j++) {
						if (map[1][j] >= 1) {
							map[1][j]++;
						} else {
							map[1][j] = 1;
						}
					}
				} else {
					for (int j = startX; j >= endX; j--) {
						if (map[1][j] >0 ) {
							map[1][j]++;

						} else {
							map[1][j] = 1;
						}
					}
				}
//				for (int k = 0; k < 3; k++) {
//					for (int j = 0; j < 201; j++) {
//						System.out.print(map[k][j] + " ");
//					}System.out.println();
//				}
//				System.out.println("***** ");
			}
			

			
			for (int j = 0; j < 201; j++) {

				max = Math.max(max, map[1][j]);
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}
