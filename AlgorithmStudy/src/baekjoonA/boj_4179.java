package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 4179번: 불! <br>
 *      메모리: 73372 KB <br>
 *      시간: 524 ms
 * @since 2020-09-05
 * 
 */

public class boj_4179 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static LinkedList<Pair> person;
	private static LinkedList<Pair> fire;
	private static boolean[][] visited;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		person = new LinkedList<Pair>();
		fire = new LinkedList<Pair>();
		visited = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'J') {
					person.add(new Pair(i,j));
					map[i][j] = '.';
					visited[i][j] = true;
				}else if(map[i][j] == 'F') {
					fire.add(new Pair(i,j));
				}
			}
		}
		int result = bfs();
		if(result<0) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(result+1);
		}
	}
	static int bfs() {
		// fire 갱신 -> 지훈이 갱신
		int count = 0;
		Pair first = person.poll();
		if(first.x == 0 || first.x == R-1 || first.y == 0 || first.y == C-1) {
			return 0;
		}
		person.add(first);
		while(!person.isEmpty()) {
			count++;
			int len = fire.size();
			for(int i=0;i<len;i++) {
				Pair temp = fire.poll();
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if(nx<0||nx>R-1||ny<0||ny>C-1 || map[nx][ny] == '#' || map[nx][ny]=='F') continue;
					map[nx][ny] = 'F';
					fire.add(new Pair(nx,ny));
				}
			}
			if(!checkMap()) return -1;
			
			len = person.size();
			for(int i=0;i<len;i++) {
				Pair temp = person.poll();
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if(nx<0||nx>R-1||ny<0||ny>C-1 || map[nx][ny] == '#' || map[nx][ny]=='F') continue;
					if(visited[nx][ny]) continue;
					
					if((nx == 0) ||(ny==0) || (nx == R-1) || (ny==C-1)) {
						return count;
					}
					visited[nx][ny] = true;
					person.add(new Pair(nx,ny));
				}
			}
		}
		return -1;
	}
	
	// 가장자리 체크하는 함수 구현
	static boolean checkMap() {
		for(int i=0;i<R;i++) {
			if(map[i][0] == '.' || map[i][C-1] == '.') return true; 
		}
		for(int j=0;j<C;j++) {
			if(map[0][j]=='.' || map[R-1][j] == '.') return true;
		}
		
		return false;
	}
	
	static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
