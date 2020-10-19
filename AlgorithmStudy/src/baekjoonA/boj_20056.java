package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 20056번: 마법사 상어와 파이어볼 <br>
 *      메모리: 39768 KB <br>
 *      시간: 1392 ms
 * @since 2020-10-20
 * 
 */

public class boj_20056 {

	private static int N;
	private static int M;
	private static int K;
	private static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static ArrayList<Pair> list;
	private static int[][] map;
	private static LinkedList<Pair> q;
	private static LinkedList<Pair> command;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList<Pair>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int mass = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Pair(x, y, mass, s, d));
		}
		System.out.println(solve());
	}
	

	static int solve() {
		int time = 0;
		while (true) {
			map = new int[N][N];
			if(list.size() == 0) {
				return 0;
			}
			move();
			
			if (check()) {
				crash();
			}
			time++;
			if(list.size() == 0) {
				return 0;
			}
			if (time == K) {
				return calc();
			}
		}
	}
	static int calc() {
		int sum = 0;
		int len = list.size();
		for(int i=0;i<len;i++) {
			Pair temp = list.get(i);
			sum += temp.mass;
		}
		return sum;
	}
	static void move() {
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Pair temp = list.get(i);
			int nx = temp.x + dx[temp.d] * temp.s;
			int ny = temp.y + dy[temp.d] * temp.s;
			
			if(nx < 0) {
				while(true) {
					nx += N;
					if(nx >= 0 && nx <N) break;
				}
			}
			if(nx > N-1) {
				while(true) {
					nx -=  N;
					if(nx >= 0 && nx <N) break;
				}
			}
			if(ny < 0) {
				while(true) {
					ny += N;
					if(ny >= 0 && ny <N) break;
				}
			}
			if(ny > N-1) {
				while(true) {
					ny -= N;
					if(ny >= 0 && ny <N) break;
				}
			}
			
			temp.x = nx;
			temp.y = ny;
			
			list.set(i, temp);
		}
	}

	static boolean check() {
		int len = list.size();
		boolean crash = false;
		command = new LinkedList<Pair>();
		for (int i = 0; i < len; i++) {
			Pair temp = list.get(i);
			if (map[temp.x][temp.y] > 0) {
				crash = true;
			}
			map[temp.x][temp.y]++;
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>1) {
					command.add(new Pair(i,j,0,0,0));
				}
			}
		}
		
		return crash;
	}

	static void crash() {
		q = new LinkedList<Pair>();
		while(!command.isEmpty()) {
			Pair com = command.poll();
			int i = com.x;
			int j = com.y;
			
			int mass = 0;
			int speed = 0;
			boolean odd = false;
			boolean even = false;
			for(int k=0;k<list.size();k++) {
				Pair temp = list.get(k);
				if(temp.x == i && temp.y == j) {
					if(temp.d % 2 == 0) {
						even = true;
					}else {
						odd = true;
					}
					mass += temp.mass;
					speed += temp.s;
					list.remove(k);
					k--;
				}
			}
			mass /= 5;
			if(mass == 0) continue;
			speed /= map[i][j];
			if(odd && even) {
				for(int dir=1;dir<8;dir+=2) {
					q.add(new Pair(i,j,mass,speed,dir));
				}
			}else {
				// 0 2 4 6
				for(int dir=0;dir<8;dir+=2) {
					q.add(new Pair(i,j,mass,speed,dir));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			list.add(temp);
		}
	}

	static class Pair implements Comparable<Pair>{
		int x, y;
		int mass, s, d;

		public Pair(int x, int y, int mass, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.s = s;
			this.d = d;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.x == o.x) {
				return this.y-o.y;
			}
			return this.x - o.x;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", mass=" + mass + ", s=" + s + ", d=" + d + "]";
		}
		
	}

}
