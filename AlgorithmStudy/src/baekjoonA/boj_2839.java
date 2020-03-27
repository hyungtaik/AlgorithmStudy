package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2839 {

	private static int TC;
	private static int M;
	private static LinkedList<Integer> q;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken()); // Test case 개수
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," "); // 줄바꿈
			M = Integer.parseInt(st.nextToken()); // 목표 값
			
			q = new LinkedList<Integer>(); // bfs을 위한 linkedlist할당
			
			visited = new boolean[M+1]; // 이미 방문한 정수 체크
			q.add(0); // 0 부터 시작 bfs탐색
			visited[0] = true; // 0 시작점으로 visited 체크
			System.out.println(bfs()); // 결과값  return
		}
	}
	
	// bfs 탐색으로 최소시간 찾기
	static int bfs() {
		int count = 0; // 전체 count를  0으로 초기화
		while(!q.isEmpty()) { // q가 비어질대까지 반복
			int len = q.size();         // linkedlist 사이즈를 할당하여 
			for(int i=0;i<len;i++) {    // 사이즈만큼 for문 돌리고 count수 증가 
				int temp = q.poll(); // 해당 정수 poll
				if(temp == M) { // M일 경우 return
					return count; // bfs end
				}
				int fiveNext = temp+5; // 5kg 추가
				if(fiveNext <= M && !visited[fiveNext]) { // M까지 범위내에 있으면서 방문한 적이 없을 경우
					q.add(fiveNext); // q에 추가하고
					visited[fiveNext] = true; // 방문 처리
				}
				int threeNext = temp+3; // 3kg 추가
				if(threeNext <= M && !visited[threeNext]) { // M까지 범위내에 있으면서 방문한 적이 없을 경우
					q.add(threeNext); // q에 추가하고
					visited[threeNext] = true; // 방문 처리
				}
			}
			count++; // 한 턴이 끝났으므로 +1
		}
		return -1; // 위에서 return 되지 않았으면 갈 수 없는 것
	}

}
