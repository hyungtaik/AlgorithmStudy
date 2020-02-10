import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17143 {

	private static int R;
	private static int C;
	private static int M;
	private static Shark[][] map;
	private static int result;
	private static int[] dx = {0,-1,1,0,0};
	private static int[] dy = {0,0,0,1,-1};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로
		M = Integer.parseInt(st.nextToken()); // 상어의 수
		map = new Shark[R+1][C+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r,c,s,d,z);
			
		}
		result = 0; // 잡은 갯수
		int man = 1; // 열 정보
		for(;man <= C;man++) { // 낚시왕이 열만큼 이동
			for(int i=1;i<R+1;i++) {
				if(map[i][man] != null) {
					result+= map[i][man].z;
					map[i][man] = null;
					break;
				}
			}
			moveShark();
			
		}
		System.out.println(result);
	}

	static void moveShark() {
		Queue<Shark> q = new LinkedList<Shark>();
		for(int i=1;i<R+1;i++) {
			for(int j=1;j<C+1;j++) {
				if(map[i][j]!=null) {
					Shark temp = map[i][j];
					int nx = i;
					int ny = j;
					
					for(int k=0;k<temp.s;k++) {
						nx+=dx[temp.d];
						ny+=dy[temp.d];
						if(nx <1||nx>R || ny < 1 || ny > C) {
							nx-=dx[temp.d];
							ny-=dy[temp.d];
							if(temp.d%2 == 0) temp.d -=1;
							else{
								temp.d +=1;
							}
							nx +=dx[temp.d];
							ny +=dy[temp.d];
						}
					}
					q.offer(new Shark(nx,ny,temp.s,temp.d,temp.z));
				}
			}
		}
		map = new Shark[R+1][C+1];
		while(!q.isEmpty()) {
			Shark temp = q.poll();
			if(map[temp.r][temp.c]!=null) {
				if(temp.z > map[temp.r][temp.c].z) {
					map[temp.r][temp.c] = temp;
				}
			}else {
				map[temp.r][temp.c] = temp;
			}
			
		}
	}
	
	
	static class Shark{
		int r,c,s,d,z; // r,c 는 상어의 위치   s 는 속력, d는 이동방향,   z는 크기

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		
		
	}
}
