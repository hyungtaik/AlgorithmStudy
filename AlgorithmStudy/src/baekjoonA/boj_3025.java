package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3025 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static int N;
	private static StringBuilder sb;
	private static Pair[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new char[R][C]; // Map 초기화
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		memo = new Pair[C];
		for (int j = 0; j < C; j++) {
			memo[j] = new Pair(R, 1, j); // 1행부터 시작, 해당 열부터 시작
			memo[j].refresh(); // 가야할 위치 갱신
		}

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 명령 수행 횟수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int comm = Integer.parseInt(st.nextToken()) - 1; // 명령을 수행하는 열
			memo[comm].push(); // 미리 정해둔 위치에 둔다
			for(int j=0;j<C;j++) {
				memo[j].refresh(); // 모든 열을 돌면서 갱신해준다
			}
		}

		sb = new StringBuilder(); // map 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static class Pair{
		int[] col; // 해당 row에 대한 열의 위치
		int row; // 장애물의 위치
		
		public Pair(int size,int row,int i) {
			col = new int[size];
			this.row = row;
			col[0] = i;
		}
		
		// 해당 위치에 O을 push
		public void push() {
			map[row-1][col[row-1]] = 'O';
		}
		
		public void refresh() {
			while(true) {
				int cur = col[row-1];
				if(row>1 && map[row-1][cur]!='.') { // 가려는 위치가 갱신되서 못가게 되었을때 행을 위로 올려서 재검색
					row--;
				}
				else if(row==R) { // 바닥일때 break;
					break;
				}
				else if(map[row][cur]=='X') { // 장애물 만났다면 break;
					break;
				}else if(map[row][cur]=='.') { // 갈 수 있을때까지 계속 아래로
					col[row] = cur;
					row++;
				}else { // O 를 반났을 경우
					if(cur>0 && map[row][cur-1] == '.' && map[row-1][cur-1]=='.') { // 왼쪽 (현재위치에서 왼쪽, 왼쪽 위를 검색해야 바로 위에서 내려올때 가능한 루트가 된다)
						col[row++] = cur-1;
					}else if(cur+1<C && map[row][cur+1]=='.' && map[row-1][cur+1]=='.') { // 오른쪽 (현재위치에서 오른쪽, 오른쪽 위를 검색해야 바로 위에서 내려올때 가능한 루트가 된다)
						col[row++] = cur+1;
					}else { // 둘다 아닐때 그자리에서 break;
						break;
					}
				}
			}
		}
	}


	
}
