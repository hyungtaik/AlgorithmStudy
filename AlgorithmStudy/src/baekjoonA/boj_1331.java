package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 1331번: 나이트 투어
 * 		메모리: 12936 KB <br>
 * 		시간: 80 ms
 * @since 2020-08-16
 * 
 */

public class boj_1331 {
	
	static int[] dx = {-2,-2,-1,-1,1,1,2,2};
	static int[] dy = {-1,1,-2,2,-2,2,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] map = new boolean[6][6];
		String input = br.readLine();
		int col = input.charAt(0) - 'A';
		int row = 5 - (input.charAt(1) - '1');
		int startC = col;
		int startR = row;
		
		map[row][col] = true;
		boolean valid = true;
		for(int i=1;i<36;i++) {
			input = br.readLine();
			int nextCol = input.charAt(0) - 'A';
			int nextRow = 5 - (input.charAt(1) - '1');
			boolean check = false;
			for(int j=0;j<8;j++) {
				int nx = row + dx[j];
				int ny = col + dy[j];
				
				if(nx == nextRow && ny == nextCol) {
					row = nx;
					col = ny;
					check = true;
					break;
				}
			}
			
			if(!check || map[nextRow][nextCol]) {
				valid = false;
				break;
			}
			map[row][col] = true;
			
			if(i==35) {
				valid = false;
				for(int j=0;j<8;j++) {
					int nx = row + dx[j];
					int ny = col + dy[j];
					
					if(nx == startR && ny == startC) {
						valid = true;
						break;
					}
				}
			}
		}
		
		System.out.println(valid?"Valid":"Invalid");
	}

}
