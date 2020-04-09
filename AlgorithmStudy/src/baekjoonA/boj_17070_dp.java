package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17070_dp {
	private static int N;
	private static int[][] map;
	private static int count;
	private static int[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new int[N][N][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				for(int k=0;k<4;k++) {
					visited[i][j][k] = 0;
				}
			}
		}
		
		dp(0, 1, 1);
		count = visited[0][1][1];
		if(count == -1) count = 0;
		System.out.println(count);
	}

	static int dp(int x, int y, int shape) {
		if (x == N - 1 && y == N - 1) {
			return visited[x][y][shape] = 1;
		}
		if (visited[x][y][shape]>0) {
			return visited[x][y][shape];
		}
		else if( visited[x][y][shape] == -1) {
			return 0;
		}else {
			
			if (shape == 1) {
				int nx = x;
				int ny = y + 1;
				boolean check = false;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,1);
					if(temp >0) visited[x][y][shape] += temp;
				}
				nx = x + 1;
				ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny-1] == 0
						&& map[nx - 1][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,3);
					if(temp >0) visited[x][y][shape] += temp;
				}
				if( check) {
					return visited[x][y][shape];
				}else {
					return visited[x][y][shape] = -1;
				}
				
			} else if (shape == 2) {
				int nx = x + 1;
				int ny = y;
				boolean check = false;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,2);
					if(temp >0) visited[x][y][shape] += temp;
				}
				nx = x + 1;
				ny = y + 1;
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny - 1] == 0
						&& map[nx - 1][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,3);
					if(temp >0) visited[x][y][shape] += temp;
				}
				if( check) {
					return visited[x][y][shape];
				}else {
					return visited[x][y][shape] = -1;
				}
			} else  {
				int nx = x;
				int ny = y + 1;
				boolean check = false;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,1);
					if(temp >0) visited[x][y][shape] += temp;
				}
				nx = x + 1;
				ny = y;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,2);
					if(temp >0) visited[x][y][shape] += temp;
				}
				nx = x + 1;
				ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny - 1] == 0
						&& map[nx - 1][ny] == 0) {
					check = true;
					int temp  = dp(nx,ny,3);
					if(temp >0) visited[x][y][shape] += temp;
				}
				if( check) {
					return visited[x][y][shape];
				}else {
					return visited[x][y][shape] = -1;
				}
			}
		}
	}

	
}
