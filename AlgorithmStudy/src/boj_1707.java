package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1707 {

	private static int TC;
	private static int V;
	private static int E;
	private static ArrayList<Integer>[] list;
	private static int[] check;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken()); // Test Case 개수
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수
			
			list = new ArrayList[V+1]; // 연결되어있는 정점 담을 list
			check = new int[V+1]; // 해당 정점에 대한 그룹을 넣을 배열
			
			for(int i=1;i<=V;i++) { // 모든 정점에 대한 arraylist 
				list[i] = new ArrayList<Integer>(); // 정수 arraylist로 할당
			}
			
			for(int i=0;i<E;i++) { // 간선에 대한 정점 정보를 list에 담는다.
				st = new StringTokenizer(br.readLine()," "); // 줄바꿈
				int a = Integer.parseInt(st.nextToken()); // 첫번째 정점 입력
				int b = Integer.parseInt(st.nextToken()); // 두번째 정점 입력
				list[a].add(b); // a정점에 b 정점 넣기  => 인접 
				list[b].add(a); // b정점에 a 정점 넣기  => 인접
			}
			boolean bipartiteGraph = true; // Yes or No를 알아내기 위한 변수
			
			for(int i=1;i<=V;i++) { // dfs 탐색을 통해 두 그룹으로 나눈다.(1과 2그룹)
				if(check[i]==0) {   // 인접한 정점끼리 서로 다른 그룹으로 배정하여 모든 정점을 두가지 그룹으로만 구별할 수 있게한다.
					dfs(i,1); // 1부터 시작하여 해당 i인덱스 정점부터 그룹핑
				}
			}
			for(int i=1;i<=V;i++) { // 모든 정점 방문하면서
				for(int j :list[i]) { // 정점에 연결된 정점들을 반복하며 본다.
					if(check[i]==check[j]) { // 인접 정점의 그룹이 같은 경우 이분그래프가 아니므로
						bipartiteGraph = false; // false하여 break한다.
						break;
					}
				}
			}
			if(bipartiteGraph) { // boolean타입 변수를 통해 이분그래프 체크
				System.out.println("YES"); // 이분그래프일 경우
			}else {
				System.out.println("NO"); // 이분그래프가 아닐 경우
			}
		}
	}
	// 깊이우선탐색으로 한단계 깊어질때마다 이전 단계랑 정점의 그룹을 다르게 지정해준다.
	static void dfs(int index, int count) {
		check[index] = count; // check배열을 통해 count 변수로 그룹을 나눈다.
		for(int i:list[index]) { // 해당 정점에 연결된 정점들을 찾아 그룹이 정해져있지 않은 경우 반대되는 그룹으로 배정
			if(check[i] == 0) { // 비어있지 않은 경우
				dfs(i,(count%2)+1); // 두개의 그룹으로 나누기 위해 사용 -> count == 1일때 (1%2)+1 = 2로 count에 할당
			}												// count == 2일때 (2%2)+1 = 1로 count에 할당
		}
	}

}
