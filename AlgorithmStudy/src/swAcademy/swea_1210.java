package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1210 {
	static int tcase;
	static int result;
	static int[][] map; // 사다리 맵
	static int dx,dy; //도착지
	static boolean[][] checked;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1;tc<=10;tc++) {
			st = new StringTokenizer(br.readLine());
			tcase = Integer.parseInt(st.nextToken());
			result = 0; // 결과 초기화
			map = new int[100][100]; // 사다리 초기화
			dx=0; dy=0; // 도착지 초기화
			checked = new boolean[100][100]; // 방문 맵 초기화
			
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						checked[i][j] = true;
					}
					
					if(map[i][j]==2) {
						dx = i; // 행
						dy = j; // 열
					}
				}
			}
			solve(dx-1,dy);
			System.out.println("#"+tcase+" "+result);
		}
	}
	
	static void solve(int x,int y) {
		checked[x][y] = false; // 방문시 체크
		if(x == 0) { //도착 시, 열 값을 저장
			result = y;
			return;
		}
		if((y-1>=0) && checked[x][y-1]==true) { // 왼쪽 탐색
			solve(x,y-1);
		}
		else if((y+1 < 100) && checked[x][y+1]==true) { // 오른쪽 탐색
			solve(x,y+1);
		}else { // 그외 위로 올라가 탐색
			solve(x-1,y);
		}
		return;
	}
}
