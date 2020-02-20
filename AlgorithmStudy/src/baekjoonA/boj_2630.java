package com.ssafy.subset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2630 {

	private static int N;
	private static int[][] map;
	private static int blue;
	private static int white;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int count = 0;
		blue = 0;
		white = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					count++;
			}
		}
		if (count == N * N) {
			blue = 1;
			white = 0;
		} else if (count == 0) {
			blue = 0;
			white = 1;
		} else {
			solve(0, 0, N);
		}
		System.out.println(white);
		System.out.println(blue);
	}

	static void solve(int sx, int sy, int size) {
		int len = size / 2;
		int count = 0;
		for (int i = sx; i < sx + size; i++) { // 1
			for (int j = sy; j < sy + size; j++) {
				if (map[i][j] == 1) {
					count++;
				}
			}
		}
		if (count == size * size) { // 전부 파란색
			blue++;
		} else if (count == 0) { // 전부 흰색
			white++;
		} else {
			solve(sx, sy, len);
			solve(sx, sy + len, len);
			solve(sx + len, sy, len);
			solve(sx + len, sy + len, len);
		}
	}

}
