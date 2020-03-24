package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_17837 {

	private static int N;
	private static int K;
	private static int[][] map;
	private static Chess[] chess;
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {1,-1,0,0};
	private static int result;
	private static int[][][] check;
	private static ArrayList<Integer> q;
	private static int turn;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		chess = new Chess[K+1];
		check = new int[N][N][K];
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			chess[i] = new Chess(x,y,dir);
			check[x][y][0] = i; // 번호 1~K
		}
		result = -1;
		solve();
		System.out.println(result);
	}
	
	static void solve() {
		turn = 1;
		while(turn<=1000) {
			for(int t=1;t<=K;t++) { // 체스 말 갯수 만큼 순서대로 for문 돌린다.
				Chess temp = chess[t];
				int x = temp.x;
				int y = temp.y;
				int dir = temp.dir;
				
				boolean index = false;
				q = new ArrayList<Integer>();
				for(int i=0;i<K;i++) { // 3차원에서 현재 위치에서 나보다 위에 있는 것들 꺼내서 q에 저장
					if(check[x][y][i]==0) break;
					if(check[x][y][i]==t) { //자기 자신
						index = true;
					}
					if(index) {
						q.add(check[x][y][i]);
						check[x][y][i] = 0;
					}
				}
				
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				
				// 1. 범위 벗어 났을 때  -> 범위 벗어난것이 방향을 바꾼다고해서 또 범위를 벗어날 수 없음 (N >=4)
				if(nx<0||nx>N-1||ny<0||ny>N-1) {
					if(dir == 1 || dir == 3) { // 방향 반대로
						dir--;
					}else {
						dir++;
					}
					nx = x+dx[dir];
					ny = y+dy[dir];
					chess[t].dir = dir; // 미리 방향을 저장해준다.
					
					// 1.1 파란색을 마주쳤을 경우
					if(map[nx][ny] ==2 ) {
						for(int i=0;i<K;i++) {
							if(check[x][y][i]!=0) continue;
							int tmp = q.remove(0);
			 				check[x][y][i] = tmp;
							chess[tmp].x = x;
							chess[tmp].y = y;
							if(q.size() == 0) break;
						}
					}else { //1.2 흰색이나 빨간색 마주쳤을 경우
						go(nx,ny,map[nx][ny]);
					}
				}else if(map[nx][ny] == 0) { // 2. 흰색 마주쳤을 경우
					go(nx,ny,0);
				}else if(map[nx][ny]==1 ) { // 3. 빨간색 마주쳤을 경우
					go(nx,ny,1);
				}else { // 3. 파란색을 마주쳤을 경우
					if(dir == 1 || dir ==3) { // 방향 반대로
						dir--;
					}else {
						dir++;
					}
					nx = x+dx[dir];
					ny = y+dy[dir];
					chess[t].dir = dir;
					
					// 3.1 다음 경로가 => 범위 벗어나지않고 파란색이 아닌경우
					if((nx>=0 && nx<= N-1 && ny>=0 && ny<= N-1) && map[nx][ny] !=2) {
						go(nx,ny,map[nx][ny]);
					}else { // 3.2 다음 경로가 => 범위가 벗어나거나 파란색일 경우
						for(int i=0;i<K;i++) {
							if(check[x][y][i]!=0) continue;
							int tmp = q.remove(0);
			 				check[x][y][i] = tmp;
							chess[tmp].x = x;
							chess[tmp].y = y;
							if(q.size() == 0) break;
						}
					}
				}
				// go 함수에서 result 값이 변동이 됬다면 0보다 커진다(result 초기값:-1)
				if(result>0) {
					return;
				}
			}
			// 1000번 까지 반복
			turn++;
		}
	}
	static void go(int nx,int ny, int color) {
		if(color == 0) { // 아래부터 저장
			for(int i=0;i<K;i++) {
				if(check[nx][ny][i]!=0) continue;
				int temp = q.remove(0);
 				check[nx][ny][i] = temp;
				chess[temp].x = nx;
				chess[temp].y = ny;
				if(q.size() == 0) break;
			}
		}else if(color == 1) { // 위부터 저장
			for(int i=0;i<K;i++) {
				if(check[nx][ny][i]!=0) continue;
				int size = q.size()-1;
				int temp = q.remove(size);
				check[nx][ny][i] = temp;
				chess[temp].x = nx;
				chess[temp].y = ny;
				if(q.size() == 0) break;
			}
		}
		
		// 이동한 후 체스 4번째 칸의 수가 0보다 크다면 체스가 들어간 것으로 간주하고 턴 횟수 저장하고 리턴
		if(check[nx][ny][3]!=0) {
			result = turn;
			return;
		}
	}
	static class Chess{
		int x,y;
		int dir;
		public Chess(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}