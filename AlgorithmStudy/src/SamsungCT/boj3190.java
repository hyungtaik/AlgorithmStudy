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
	static int L; // ���� ���⺯ȯ Ƚ��
	static Queue<pair> dir;
	static int[][] map; // ��ü ����
	static int[] dx = { 0, 1, 0, -1 }; //��������
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // ������ ũ��
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // ����� ����

		map = new int[N][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// ����� ��ġ - 1
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
		// �ڱ��ڽŰ� �ε����ų� ���� �ε����� ���� set
		// ����� 1, �ڱ���� 2, �ƹ��͵� ���°��� 0
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
			//������ �̵�
			head.x += moveX;
			head.y += moveY;
			tail2.add(new cur(head.x,head.y));
			if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || map[nextX][nextY] == 2) {
				break;
			}
			if(count == 0) map[0][0] = 2;
			// �̵��� ���� �ƹ��͵� ������
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
				// ������
				if (timeStamp.dir == 'D') {
					x++;
				}
				// ����
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
