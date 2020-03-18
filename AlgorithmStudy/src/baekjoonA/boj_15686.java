package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15686 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Pair> q;
	private static ArrayList<Pair> chicken;
	private static int[] numbers;
	private static boolean[] visited;
	private static int min;
	private static int[][][] memo;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		q = new ArrayList<Pair>();
		chicken = new ArrayList<Pair>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					q.add(new Pair(i,j)); //home
				}else if(map[i][j]==2) {
					chicken.add(new Pair(i,j)); //chicken
				}
			}
		}
		numbers = new int[M]; // 조합
		visited = new boolean[chicken.size()];
		memo = new int[N][N][chicken.size()]; // 이미 방문한 곳은 저장
		min = Integer.MAX_VALUE;
		combi(0,0);
		System.out.println(min);
		
	}
	// 조합
	static void combi(int index,int count) {
		if(count==M) {
			calc();
			return;
		}
		if(index==chicken.size()) {
			return;
		}
		for(int i=index;i<chicken.size();i++) {
			if(visited[i]) continue;
			numbers[count] = i;
			visited[i] = true;
			combi(i+1,count+1);
			visited[i]= false;
		}
	}
	static void calc() {
		int sum = 0;
		for(int j=0;j<q.size();j++) { // 집
			int x = q.get(j).x;
			int y = q.get(j).y;
			int minTemp = Integer.MAX_VALUE;
			for(int i=0;i<M;i++) { //선별된 치킨집
				int num = numbers[i];
				Pair chk = chicken.get(num);
				if(memo[x][y][num]>0) {
					minTemp = Math.min(minTemp,memo[x][y][num]);
					continue;
				}else {
					int distance = Math.abs(x-chk.x)+Math.abs(y-chk.y);
					memo[x][y][num] = distance;
					minTemp = Math.min(minTemp,distance);
				}
			}
			sum+=minTemp;
		}
		min = Math.min(min,sum);
		
	}
	static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
