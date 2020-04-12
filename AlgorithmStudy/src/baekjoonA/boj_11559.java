package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class boj_11559 {

	private static int[][] map;
	private static boolean[][] visited;
	private static LinkedList<Point> q;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static ArrayList<Point> list;
	private static ArrayList<Point> tlist;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[12][6];
		for(int i=0;i<12;i++) {
			String st = br.readLine();
			for(int j=0;j<6;j++) {
				char temp = st.charAt(j);
				int num = 0;
				if(temp == '.') {
				}else if(temp == 'R') {
					num = 1;
				}else if(temp == 'G') {
					num = 2;
				}else if(temp == 'B') {
					num = 3;
				}else if(temp == 'P') {
					num = 4;
				}else {
					num = 5;
				}
				map[i][j] = num;
			}
		}
		q = new LinkedList<Point>();
		list = new ArrayList<Point>();
		int count = 0;
		while(true) {
			visited = new boolean[12][6]; // 탐색용
			int changed = 0;
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(map[i][j] >0 && !visited[i][j]) {
						visited[i][j] =true;
						q.add(new Point(i,j,map[i][j]));
						changed += check(); // 4개 이상 있었다면 true, 없으면 false
					}
				}
			}
			if(changed>0) {
				removeColor();
				goDown();
				count++;
				list.clear();
			}else {
				break;
			}
		}
		System.out.println(count);
	}
	static int check() {
		int count = 0;
		tlist = new ArrayList<Point>();
		while(!q.isEmpty()) {
			count++;
			Point temp = q.poll();
			tlist.add(new Point(temp.x,temp.y,temp.color));
			for(int i=0;i<4;i++) {
				int nx = temp.x+dx[i];
				int ny = temp.y+dy[i];
				if(nx<0||nx>11||ny<0||ny>5) continue;
				if(visited[nx][ny]) continue;
				if(temp.color == map[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Point(nx,ny,map[nx][ny]));
				}
			}
		}
		if(count >= 4) {
			list.addAll(tlist);
			return 1;
		}else {
			return 0;
		}
	}
	static void removeColor() {
		for(int i=0;i<list.size();i++) {
			Point temp = list.get(i);
			map[temp.x][temp.y] = 0;
		}
		list.clear();
	}
	static void goDown() {
		for(int j=0;j<6;j++) {
			for(int i=11;i>=0;i--) {
				if(map[i][j]>0 ) {
					q.add(new Point(i,j,map[i][j]));
					map[i][j] = 0;
				}
			}
			int i=11;
			while(!q.isEmpty()) {
				map[i--][j] = q.poll().color;
			}
		}
	}
	
	static class Point{
		int x,y,color;

		public Point(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
	}

}
