package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_3190 {

	private static int N;
	private static int K;
	private static int[][] map;
	private static int L;
	private static Pair[] command;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,-1,0,1};
	private static boolean[][] visited;
	private static ArrayList<Snake> snake;
	private static int time;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); // 보드크기
		map = new int[N][N];
		st = new StringTokenizer(br.readLine()," ");
		K = Integer.parseInt(st.nextToken()); // 사과 갯수
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 1; // 사과
		}
		st = new StringTokenizer(br.readLine()," ");
		L = Integer.parseInt(st.nextToken()); //방향 횟수
		command = new Pair[L];
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine()," ");
			command[i] = new Pair(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0));
		}
		time = 0;
		solve();
		System.out.println(time+1); // 이동하고 게임끝난것이기 때문
	}
	static void solve() {
		
		visited = new boolean[N][N];
		snake = new ArrayList<Snake>();
		snake.add(new Snake(0,0,3));
		visited[0][0] = true; //start
		int moveDir = 0;
		while(true) {
			
			Snake head = snake.get(snake.size()-1);
			int nx = head.x+dx[head.dir];
			int ny = head.y+dy[head.dir];
			
			if(nx<0||nx>N-1||ny<0||ny>N-1) return;
			if(visited[nx][ny]) return; // 부딪히면 끝
			snake.add(new Snake(nx,ny,head.dir));
			visited[nx][ny] = true;
			if(map[nx][ny]==0) { //사과 없으면
				Snake last = snake.remove(0);
				visited[last.x][last.y] = false;
			}else {
				map[nx][ny] = 0;
			}
			
			time++;
			if(moveDir<L && command[moveDir].time==time) { // 방향 바꾸기
				if(command[moveDir].dir=='L') { //왼쪽
					head.dir=(head.dir+1)%4;
				}else if(command[moveDir].dir=='D') {//오른쪽
					head.dir=head.dir-1;
					if(head.dir<0) head.dir = 3;
				}
				snake.get(snake.size()-1).dir = head.dir;
				moveDir++;
			}
		}
	}
	static class Pair{
		int time;
		char dir;
		public Pair(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}
	static class Snake{
		int x,y;
		int dir;
		public Snake(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}

}
