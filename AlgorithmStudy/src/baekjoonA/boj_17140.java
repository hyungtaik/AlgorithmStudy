package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_17140 {

	private static int k;
	private static int c;
	private static int r;
	private static int[][] map;
	private static ArrayList<Point> list;
	private static int max;
	private static int xL;
	private static int yL;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[101][101]; // 1~100
		for (int i = 0; i < 101; i++) {
			Arrays.fill(map[i], 0);
		}

		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		xL = 3;
		yL = 3;
		System.out.println(solve());
	}

	static int solve() {
		int time = 0;
		while (map[r][c] != k) {
			if (xL >= yL) { // R연산
				sortList(1); // 행에 고정값을 넣고 정렬
			} else { // C연산
				sortList(0); // 열에 고정값을 넣고 정렬
			}
			
			time++;
			if(time >100) {
				time = -1;
				break;
			}
//			System.out.println(time+ "xL: "+xL+"     yL: "+yL);
//			print();
		}
		return time;
	}
	static void print() {
		for (int i = 1; i <= xL; i++) {
			for (int j = 1; j <= yL; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}
		System.out.println("**************");
	}
	// num: 1 - 행 정렬, 2 - 열정렬 index: 해당 인덱스
	static void sortList(int num) {
		
		if(num==1) {
			int max = Integer.MIN_VALUE;
			for(int i=1;i<=100;i++) {
				list = new ArrayList<>();
				int[] temp = new int[101];
				// 1. 숫자와 빈도수 저장
				for(int j=1;j<=100;j++) {
					if(map[i][j]==0) continue;
					temp[map[i][j]]++;
				}
				// 2. 리스트에 요소 저장
				for(int j=1;j<=100;j++) {
					map[i][j] = 0; //미리 비워놓기
					if(temp[j]>0) list.add(new Point(j,temp[j]));
				}
				// 3. 정렬
				Collections.sort(list);
				// 4. 채워넣기 
				int listSize = list.size()*2;
				if(listSize >100) {
					listSize = 100;
				}
				max = Math.max(max, listSize);
				
				for(int len=1,idx=0;len<=listSize;len++) {
					map[i][len++] = list.get(idx).num;
					map[i][len] = list.get(idx++).count;
				}
			}
			yL = max;
		}else {
			int max = Integer.MIN_VALUE;
			for(int j=1;j<=100;j++) {
				list = new ArrayList<>();
				int[] temp = new int[101];
				// 1. 숫자와 빈도수 저장
				for(int i=1;i<=100;i++) {
					if(map[i][j]==0) continue;
					temp[map[i][j]]++;
				}
				// 2. 리스트에 요소 저장
				for(int i=1;i<=100;i++) {
					map[i][j] = 0; //미리 비워놓기
					if(temp[i]>0) list.add(new Point(i,temp[i]));
				}
				// 3. 정렬
				Collections.sort(list);
				// 4. 채워넣기 
				
				int listSize = list.size()*2;
				if(listSize >100) {
					listSize = 100;
				}
				max = Math.max(max, listSize);
				for(int len=1,idx=0;len<=listSize;len++) {
					map[len++][j] = list.get(idx).num;
					map[len][j] = list.get(idx++).count;
				}
			}
			xL = max;
		}
	}
	static class Point implements Comparable<Point> {
		int num;
		int count;
		
		public Point(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
		
		@Override
		public int compareTo(Point o) {
			if (this.count == o.count)
				return this.num - o.num;
			
			return this.count - o.count;
		}
		
	}

}
