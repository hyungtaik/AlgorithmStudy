package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 2210번: 숫자판 점프 <br>
 *      메모리: 15628 KB <br>
 *      시간: 108 ms
 * @since 2020-10-15
 * 
 */

public class boj_2210 {

	private static String[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static HashSet<String> set;
	private static LinkedList<Pair> q;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		map = new String[5][5];
		
		for(int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				map[i][j] = st.nextToken();
			}
		}
		set = new HashSet<String>();
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				bfs(i,j);
			}
		}
		System.out.println(set.size());
	}
	static void bfs(int x,int y) {
		q = new LinkedList<Pair>();
		q.add(new Pair(x,y,map[x][y]));
		while(!q.isEmpty()){
			Pair temp = q.poll();
			for(int dir=0;dir<4;dir++) {
				int nx=temp.x+dx[dir];
				int ny=temp.y+dy[dir];
				if(nx<0||nx>4||ny<0||ny>4) continue;
				String str = temp.str + map[nx][ny];
				if(str.length() == 6) {
					set.add(str);
				}else {
					q.add(new Pair(nx,ny,str));
				}
			}
		}
	}
	static class Pair{
		int x,y;
		String str;
		
		public Pair(int x, int y, String str) {
			super();
			this.x = x;
			this.y = y;
			this.str = str;
		}
	}
}
