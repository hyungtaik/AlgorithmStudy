package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* TEST CASE
[Input]
A1 A2 5
B
L
LB
RB
LT
-------------
[Output]
A1
A2 
*/

public class boj_1063 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		String king = st.nextToken();
		int kx = king.charAt(1) - '0';
		int ky = king.charAt(0) - 'A';

		String stone = st.nextToken();
		int sx = stone.charAt(1) - '0';
		int sy = stone.charAt(0) - 'A';

		int N = Integer.parseInt(st.nextToken());

		// 명령
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), "");
			String command = st.nextToken();
			int nx = 0;
			int ny = 0;
			switch (command) {
				case "R":
					nx = 0;
					ny = 1;
					break;
				case "L":
					nx = 0;
					ny = -1;
					break;
				case "B":
					nx = -1;
					ny = 0;
					break;
				case "T":
					nx = 1;
					ny = 0;
					break;
				case "RT":
					nx = 1;
					ny = 1;
					break;
				case "LT":
					nx = 1;
					ny = -1;
					break;
				case "RB":
					nx = -1;
					ny = 1;
					break;
				case "LB":
					nx = -1;
					ny = -1;
					break;
			}
			int tempX = kx+nx;
			int tempY = ky+ny;
			
			if(tempX<1||tempX>8||tempY<0||tempY>7) continue;
			
			if(tempX == sx && tempY == sy) {
				tempX = sx+nx;
				tempY = sy+ny;
				if(tempX<1||tempX>8||tempY<0||tempY>7) continue;
				sx = tempX;
				sy = tempY;
			}
			
			kx+=nx;
			ky+=ny;
			
		}
		
		// 킹의 마지막 위치
		king = (char)(ky+65)+""+(kx);
		System.out.println(king);

		// 돌의 마지막 위치
		stone = (char)(sy+65)+""+(sx);
		System.out.println(stone);
	}
}
