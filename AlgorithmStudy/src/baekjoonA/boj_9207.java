package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category DFS (브루트포스)
 * 
 * @see 백준 9207번: 페그 솔리테어 <br>
 *      메모리: 16752 KB <br>
 *      시간: 124 ms
 * @since 2020-08-31
 * 
 */

public class boj_9207 {

	private static int TC;
	private static char[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int minNum;
	private static int minRoute;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		for(int tc=0;tc<TC;tc++) {
			map = new char[5][9];
			for(int i=0;i<5;i++) {
				String temp = br.readLine();
				for(int j=0;j<9;j++) {
					map[i][j] = temp.charAt(j);
				}
			}
			
			minNum = Integer.MAX_VALUE;
			minRoute = Integer.MAX_VALUE;
			
			dfs(0);
			System.out.println(minNum+" "+minRoute);
			br.readLine();
		}
	}
	static void dfs(int count) {
		
		int pin = 0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j] == 'o') {
					pin++;
				}
			}
		}
		if(minNum > pin) {
			minNum = pin;
			minRoute = count;
		}else if(minNum == pin) {
			minRoute = Math.min(minRoute, count);
		}
		
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j] == 'o') {
					for(int dir=0;dir<4;dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						
						if(nx<0||nx>=5||ny<0||ny>=9) continue;
						if(map[nx][ny] == 'o') {
							int nnx = nx+dx[dir];
							int nny = ny+dy[dir];
							if(nnx<0||nnx>=5||nny<0||nny>=9) continue;
							if(map[nnx][nny]=='.') {
								map[i][j] = '.';
								map[nnx][nny] = 'o';
								map[nx][ny] = '.';
								dfs(count+1);
								map[i][j] = 'o';
								map[nnx][nny] = '.';
								map[nx][ny] = 'o';
							}
						}
					}
				}
			}
		}
		
	}

}
