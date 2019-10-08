package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj12100 {

	static int N;
	static int[][] map;

	static int[] dx = { 0, 0, 1, -1 }; // 동서남북
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] cp;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(MAX);

	}

	public static void showMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void copy(int [][] temp, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	public static void move(int dir) {
		//	dir 동 서 남 북 - 0,1,2,3,
		// Queue에 담아 처리하고 다시 배열에 넣어주는 로직
		Queue<Integer> q = new LinkedList<>();
		// 동
		if (dir == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if(map[i][j]!=0) q.add(map[i][j]);
					map[i][j] = 0;
				}
				int idx = N-1;
				int data;
				while(!q.isEmpty()) {
					data = q.poll();
					if(map[i][idx]==0) map[i][idx] = data;
					else if(map[i][idx] == data) {
						map[i][idx]*=2;
						idx--;
					}else {
						map[i][--idx] = data;
					}
				}
			}
		}
		// 서
		if (dir == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <N; j++) {
					if(map[i][j]!=0) q.add(map[i][j]);
					map[i][j] = 0;
				}
				int idx = 0;
				int data;
				while(!q.isEmpty()) {
					data = q.poll();
					if(map[i][idx]==0) map[i][idx] = data;
					else if(map[i][idx] == data) {
						map[i][idx]*=2;
						idx++;
					}else {
						map[i][++idx] = data;
					}
				}
			}
		}
		
		// 남
		if (dir == 2) {
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--) {
					if(map[i][j]!=0) q.add(map[i][j]);
					map[i][j] = 0;
				}
				int idx = N-1;
				int data;
				while(!q.isEmpty()) {
					data = q.poll();
					if(map[idx][j]==0) map[idx][j] = data;
					else if(map[idx][j] == data) {
						map[idx][j]*=2;
						idx--;
					}else {
						map[--idx][j] = data;
					}
				}
			}
		}
		
		// 북
		if (dir == 3) {
			for (int j = 0; j < N; j++) {
				for (int i = 0; i <N; i++) {
					if(map[i][j]!=0) q.add(map[i][j]);
					map[i][j] = 0;
				}
				int idx = 0;
				int data;
				while(!q.isEmpty()) {
					data = q.poll();
					if(map[idx][j]==0) map[idx][j] = data;
					else if(map[idx][j] == data) {
						map[idx][j]*=2;
						idx++;
					}else {
						map[++idx][j] = data;
					}
				}
			}
		}
		
	}

	public static void checkMax(int[][] tmap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmap[i][j] > MAX) {
					MAX = tmap[i][j];
				}
			}
		}
	}

	public static void dfs(int depth) {

		int[][] tempMap = new int[N][N];
		
		// dfs 하면서  이전의 map 정보를 미리 저장할 수 있게 한다.
		copy(tempMap,map);

		if (depth == 5) {
			checkMax(tempMap);
			return;
		}

		for (int i = 0; i < 4; i++) {
			move(i);
			dfs(depth + 1);
			// 이전의 맵을 다시 map으로 초기화
			copy(map,tempMap);
		}
	}
}
