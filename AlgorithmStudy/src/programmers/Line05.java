package programmers;

import java.util.*;

public class Line05 {
	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		if (N < 1 || N > 24 || M < 1 || M > 24) {
			System.out.println("fail");
			return;
		}
		Pair pair = new Pair(sc.nextInt(), sc.nextInt(), 0);
		
		if (pair.x < 0 || pair.x > M || pair.y < 0 || pair.y > N) {
			System.out.println("fail");
			return;
		}
		
		int min = pair.x + pair.y;
		int pathNum = 0;
		
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, 0));
		
		boolean check[][] = new boolean[N][M];

		while (!q.isEmpty()) {
			Pair p = q.remove();
			if (p.x == pair.x && p.y == pair.y && p.temp == min) {
				pathNum++;
			}
			
			check[p.x][p.y] = true;

			for (int i = 0; i < 4; i++) {
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				int temp = p.temp + 1;
				if (temp > min)	continue;

				if (x >= 0 &&  x <= M && y >= 0 && y <= N) {
					if (check[x][y])
						continue;
					q.add(new Pair(x, y, temp));
				}
			}
		}
		System.out.println(min);
		System.out.println(pathNum);

	}
}

class Pair {
	int x, y, temp;

	Pair(int x, int y, int temp) {
		this.x = x;
		this.y = y;
		this.temp = temp;
	}

}