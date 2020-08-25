package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션 + BFS
 * 
 * @see 백준 2638번: 치즈 <br>
 * 		메모리: 33164 KB <br>
 * 		시간: 216 ms
 * @since 2020-08-25
 * 
 */
class Point{
	int x,y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class boj_2638 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static boolean[][] check;
	private static LinkedList<Point> list;
	private static LinkedList<Point> q;
	private static LinkedList<Point> delete;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		
		map = new int[N][M];
		check = new boolean[N][M];
		list = new LinkedList<Point>();

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) list.add(new Point(i,j));
			}
		}
		
		// 초기 색칠 (공기)
		q = new LinkedList<Point>();
		q.add(new Point(0,0));
		check[0][0] = true;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int dir=0;dir<4;dir++) {
				int nx = temp.x + dx[dir];
				int ny = temp.y + dy[dir];
				
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if((map[nx][ny] == 1) || check[nx][ny]) continue;
				check[nx][ny] = true;
				q.add(new Point(nx,ny));
			}
		}
		
		// 1 중에서 색칠된 부분 2개의 변이 맞닿아있으면 queue에 넣기 -> queue에서 꺼내서 0으로 만들고 색칠 (반복)
		int time = 0;
		while(!list.isEmpty()) {
			
			time++;
			int len = list.size();
			delete = new LinkedList<Point>();
			for(int i=0;i<len;i++) {
				Point temp = list.poll();
				int count = 0;
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if(check[nx][ny]) count++;
					
					if(count == 2) {
						delete.add(temp);
						break;
					}
				}
				if(count < 2) {
					list.add(temp);
				}
			}
			
			// 지워질 칸을 0으로 만들고, 공기로 색칠해준다.
			for(int i=0;i<delete.size();i++) {
				Point temp = delete.poll();
				map[temp.x][temp.y] = 0;
				check[temp.x][temp.y] = true;
				delete.add(temp);
			}
			
			// 새롭게 공기들을 색칠해준다.
			while(!delete.isEmpty()) {
				Point temp = delete.poll();
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					
					if(nx<0||nx>N||ny<0||ny>M) continue;
					if((map[nx][ny] == 1) || check[nx][ny]) continue;
					check[nx][ny] = true;
					delete.add(new Point(nx,ny));
				}
			}
			
		}
		System.out.println(time);
	}
}
