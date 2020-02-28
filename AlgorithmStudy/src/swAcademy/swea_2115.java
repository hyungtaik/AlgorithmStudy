package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_2115 {

	private static int TC;
	private static int C;
	private static int N;
	private static int M;
	private static int[][] map;
	private static int result;
	private static ArrayList<Pair> list;
	private static int len;
	private static boolean[] check;
	private static int tempSum;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			result = 0;
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			makeSet();
			len = list.size();
			check = new boolean[len];
			dfs(0,0);
			System.out.println("#"+tc+" "+result);
		}
	}
	
	static void makeSet() { // ¸ðµç Á¶ÇÕ »ý¼º
		list = new ArrayList<Pair>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {
				list.add(new Pair(i,j));
			}
		}
	}
	
	static void dfs(int index,int count) {
		if(count==2) {
			result = Math.max(result, solve());
			return;
		}
		if(index == len) {
			return;
		}
		check[index] = true;
		dfs(index+1,count+1);
		check[index] = false;
		dfs(index+1,count);
	}
	
	static int solve() {
		int first = 0;
		int second = 0;
		boolean flag = false;
		for(int i=0;i<len;i++) {
			if(check[i]==true) {
				if(!flag) {
					first = i;
					flag = true;
				}else {
					second = i;
					break;
				}
			}
		}
		Pair p1 = list.get(first);
				
		Pair p2 = list.get(second);
		if((p1.x == p2.x) && (p1.y+M-1 >= p2.y)) { // °ãÄ¥¶§
			return 0;
		}else {
			int sum = 0;
			tempSum = 0;
			int[] calc = new int[M];
			for(int i=p1.y,j=0;i<p1.y+M;i++) {
				calc[j++] = map[p1.x][i];
			}
			dfs2(calc,0,0,0);
			
			sum+=tempSum;
			
			for(int i=p2.y,j=0;i<p2.y+M;i++) {
				calc[j++] = map[p2.x][i];
			}
			tempSum = 0;
			dfs2(calc,0,0,0);
			sum+= tempSum;
			
			return sum;
		}
	}
	
	static void dfs2(int[] arr,int index,int sum,int ssum) {
		if(sum > C) {
			return;
		}
		if(index == M) {
			tempSum = Math.max(tempSum, ssum);
			return;
		}
		dfs2(arr,index+1,sum+arr[index],ssum+(arr[index]*arr[index]));
		dfs2(arr,index+1,sum,ssum);
	}
	static class Pair {
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
