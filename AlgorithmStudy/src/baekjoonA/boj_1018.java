package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1018 {

	private static int N;
	private static int M;
	private static char[][] map;
	private static String input;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0;i<N;i++) {
			input = br.readLine();
			for(int j=0;j<M;j++){
				map[i][j] = input.charAt(j);
			}
		}
		min = Integer.MAX_VALUE;
		solve();
		System.out.println(min);
	}
	static void solve() {
		for(int i=0;i<=N-8;i++) {
			for(int j=0;j<=M-8;j++) {
				min = Math.min(min,check(i,j));
			}
		}
	}
	static int check(int x,int y) {
		char temp = map[x][y];
		int count = 0;
		for(int i=x;i<x+8;i++) {
			for(int j=y;j<y+8;j++) {
				if(temp!=map[i][j]) {
					count++;
				}
				if(temp == 'W') {
					temp = 'B';
				}else {
					temp = 'W';
				}
			}
			if(temp == 'W') {
				temp = 'B';
			}else {
				temp = 'W';
			}
		}
		
		char temp2 = map[x][y];
		if(temp2 == 'W') {
			temp2 = 'B';
		}else {
			temp2 = 'W';
		}
		int count2 = 0;
		for(int i=x;i<x+8;i++) {
			for(int j=y;j<y+8;j++) {
				if(temp2!=map[i][j]) {
					count2++;
				}
				if(temp2 == 'W') {
					temp2 = 'B';
				}else {
					temp2 = 'W';
				}
			}
			if(temp2 == 'W') {
				temp2 = 'B';
			}else {
				temp2 = 'W';
			}
		}
		
		
		return count>count2?count2:count;
	}

}
