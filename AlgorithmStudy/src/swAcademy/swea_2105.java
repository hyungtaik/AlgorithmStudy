package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2105 {
	
	static int N;
	static int[][] map;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static boolean[][] visited;
	static boolean[] visitNum;
	static int sx,sy,score;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int token = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=token;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			score = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N+1][N+1];
			visitNum = new boolean[101];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sx = i;
					sy = j;
					visited[i][j] = true;
					visitNum[map[i][j]] = true;
					
					solution(sx,sy,0,1);
					
					visited[i][j] = false;
					visitNum[map[i][j]] = false;
					
				}
			}
			
			if(score<4) {
				score = -1;
			}
			System.out.println("#"+tc+" "+score);
		}
	}
	static void solution(int x,int y,int dir,int count) {
		if(dir == 4) {
			return;
		}
		//dir은 0,1,2,3 - dx dy 돌리기 위해
		//count 횟수 세기
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		if(nx<0 || ny<0 || nx>=N || ny>=N) {
			return;
		}
		//이미 방문했거나 디저트 종류의 수가 같을경우
		if(visited[nx][ny] || visitNum[map[nx][ny]]) {
			if(nx == sx && ny == sy) {
				score = Math.max(count, score);
			}
			return;
		}
		
		//진행
		visited[nx][ny] = true;
		visitNum[map[nx][ny]] = true;
		solution(nx,ny,dir,count+1);
		solution(nx,ny,dir+1,count+1);
		visited[nx][ny] = false;
		visitNum[map[nx][ny]] = false;
		
	}
}
