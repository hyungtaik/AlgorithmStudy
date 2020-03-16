package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14890 {

	private static int N;
	private static int L;
	private static int[][] map;
	private static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 지도 크기
		L = Integer.parseInt(st.nextToken()); // 경사로 밑바닥
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = 0;
		for (int i = 0; i < N; i++) {
			searchRow(i);
			searchCol(i);
		}
		System.out.println(count);
	}
	// 행 검사
	static void searchRow(int index) {
		int start = map[index][0]; // 해당 행의 첫번째 열
		int len = 1;
		boolean check = true;
		for(int j=1;j<N;j++) {
			if(Math.abs(start-map[index][j])>1) return;
			
			if(start==map[index][j]) {
				len++;
				continue;
			}
			else if(start>map[index][j]) {
				int temp = j;
				int cnt = 0;
				while(temp<N) {
					if(map[index][j]!=map[index][temp]) break;
					cnt++;temp++;
				}
				if(cnt<L) {
					return;
				}else {
					start = map[index][j+L-1];
					j+=L-1;
					len = 0;
				}
			}else { // 오른쪽이 클때
				if(len<L) { // 길이 부족
					check=false;
					return;
				}else {
					start = map[index][j];
					len=1;	
				}
			}
		}
		if(check) {
			count++;
		}
	}
	// 열 검사
	static void searchCol(int index) {
		int start = map[0][index]; // 해당 열의 첫번째 행
		int len = 1;
		boolean check = true;
		for(int j=1;j<N;j++) {
			if(Math.abs(start-map[j][index])>1) return;
			
			if(start==map[j][index]) {
				len++;
				continue;
			}
			else if(start>map[j][index]) {
				int temp = j;
				int cnt = 0;
				while(temp<N) {
					if(map[j][index]!=map[temp][index]) break;
					cnt++;temp++;
				}
				if(cnt<L) {
					return;
				}else {
					start = map[j+L-1][index];
					j+=L-1;
					len = 0;
				}
			}else { // 오른쪽이 클때
				if(len<L) { // 길이 부족
					check=false;
					return;
				}else {
					start = map[j][index];
					len=1;	
				}
			}
		}
		if(check) {
			count++;
		}
	}
}
