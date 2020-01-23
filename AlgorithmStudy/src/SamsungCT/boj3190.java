package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj3190 {
	static class cur {
		int x;
		int y;

		cur(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class pair {
		int num;
		char dir;

		pair(int num, char dir) {
			this.num = num;
			this.dir = dir;
		}
	}

	static int N, K;
	static int L; // 뱀의 방향변환 횟수
	static Queue<pair> dir;
	static int[][] map; // 전체 지도
	static int[] dx = { 0, 1, 0, -1 }; //동남서북
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 보드의 크기
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 사과의 개수

		map = new int[N][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 사과의 위치 - 1
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x-1][y-1] = 1;
		}
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());

		dir = new LinkedList<pair>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			dir.add(new pair(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}

		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		cur head = new cur(0, 0);
		cur tail = new cur(0, 0);

		List<cur> tail2 = new ArrayList<cur>();
		tail2.add(new cur(0,0));
		// 자기자신과 부딪히거나 벽에 부딪힐때 게임 set
		// 사과는 1, 자기몸음 2, 아무것도 없는것은 0
		int x = 0;

		int moveX = dx[x];
		int moveY = dy[x];
		
		int nextX = 0;
		int nextY = 0;
		
		pair timeStamp = dir.poll();

		while (true ) {
			
//			System.out.println("*******");
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			//방향대로 이동
			head.x += moveX;
			head.y += moveY;
			tail2.add(new cur(head.x,head.y));
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || map[nextX][nextY] == 2) {
				break;
			}
			if(count == 0) map[0][0] = 2;
			// 이동한 곳이 아무것도 없을때
			if (map[head.x][head.y] == 0) {
				map[head.x][head.y] = 2;
				tail = tail2.remove(0);
				map[tail.x][tail.y] = 0;
				
//				tail.x += moveX;
//				tail.y += moveY;
			} else if (map[head.x][head.y] == 1) {
				map[head.x][head.y] = 2;
				
			} 
			count++;
			if (count == timeStamp.num) {
				// 오른쪽
				if (timeStamp.dir == 'D') {
					x++;
				}
				// 왼쪽
				else if (timeStamp.dir == 'L') {
					x--;
				}
				if (x > 3)
					x = 0;

				if (x < 0)
					x = 3;
				
				moveX = dx[x];
				moveY = dy[x];
				
				if (!dir.isEmpty())
					timeStamp = dir.poll();
			}
//			System.out.println("*******");
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			nextX = head.x + moveX;
			nextY = head.y + moveY;
//			System.out.println(nextX+","+nextY+","+count);
			
		}

		return count+1;
	}
}
