package swAcademy;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_5650 {

	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 }; //동,서,북,남
	static int[] dy = { 1, -1, 0, 0 };
	static Point[][] worm; 
	static int max;
	
	//dir 0:동쪽으로 이동 1:서 2:남 3:북
	static void solution(int a,int b,int dir) {
		int mx = a;
		int my = b;
		int score = 0;
		while(true) {
			
//			System.out.println("점수:"+score);
//			System.out.println(mx+","+my);
			int nx = mx+dx[dir];
			int ny = my+dy[dir];
//			System.out.println(nx+","+ny);
//			System.out.println("방향:"+dir);
			
			if(map[nx][ny] == -1 || (nx == a && ny == b)) {
//				System.out.println("끝");
				break;
			}
			if(map[nx][ny] >= 6) {
				Point p = new Point();
				p = worm[0][map[nx][ny]-6];
				if((p.x == nx) && (p.y == ny)) {
					mx = worm[1][map[nx][ny]-6].x;
					my = worm[1][map[nx][ny]-6].y;
					
				}else {
					mx = p.x;
					my = p.y;
				}
				continue;
			}
				
			else if(map[nx][ny]>=1) {
				int block = map[nx][ny];
				dir = changeDirection(block, dir);
							
				score ++;
			}
			mx = nx;
			my = ny;
		}
		max = Math.max(max, score);
	}
	static int changeDirection(int block,int dir) {
		if(block == 1) {
//			System.out.println("direction:"+block);
			if(dir == 0) {dir = 1;}
			else if(dir == 1) {dir = 3;}
			else if(dir == 2) {dir = 0;}
			else if(dir == 3) {dir = 2;}
		}
		else if(block == 2) {
//			System.out.println("direction:"+block);
			if(dir == 0) {dir = 1;}
			else if(dir == 1) {dir = 2;}
			else if(dir == 2) {dir = 3;}
			else if(dir == 3) {dir = 0;}
		}
		else if(block == 3) {
//			System.out.println("direction:"+block);
			if(dir == 0) {dir = 2;}
			else if(dir == 1) {dir = 0;}
			else if(dir == 2) {dir = 3;}
			else if(dir == 3) { dir = 1;}
		}
		else if(block == 4) {
//			System.out.println("direction:"+block);
			if(dir == 0) {dir = 3;}
			else if(dir == 1) {dir = 0;}
			else if(dir == 2) {dir = 1;}
			else if(dir == 3) {dir = 2;}
		}
		else if(block == 5) {
//			System.out.println("direction:"+block);
			if(dir == 0) {dir = 1;}
			else if(dir == 1) {dir = 0;}
			else if(dir == 2) {dir = 3;}
			else if(dir == 3) {dir = 2;}
		}
		return dir;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int token = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<token;tc++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n+2][n+2];
			worm = new Point[2][n];
			max = 0;
			for(int i=0;i<n+2;i++) {
				map[0][i] = map[i][0] = map[n+1][i]=map[i][n+1]= 5;
			}
			for(int i=1;i<n+1;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<n+1;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>=6) {
						if(worm[0][map[i][j]-6] != null) {
							worm[1][map[i][j]-6] = new Point(i,j);
						}else {
							worm[0][map[i][j]-6] = new Point(i,j);
						}
					}
				}
			}
			for(int i=1;i<n+1;i++) {
				for(int j=1;j<n+1;j++) {
					if(map[i][j]==0) {
						for(int dir=0;dir<4;dir++) {
//							System.out.println(dir);
							solution(i,j,dir);
						}
					}
				}
			}
			System.out.println("#"+(tc+1)+" "+max);
		}
	}
}
