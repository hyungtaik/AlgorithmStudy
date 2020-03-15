package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15683 {

private static int N;
private static int M;
private static int[][] map;
private static ArrayList<Pair> list;
private static int lSize;
private static int min;
private static int[] dx = {-1,1,0,0};
private static int[] dy	= {0,0,-1,1}; // 상하좌우


/*
[tc]
4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0 

20
 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		list = new ArrayList<Pair>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0 && map[i][j]<6) list.add(new Pair(i,j,map[i][j]));
			}
		}
		lSize = list.size();
		min = Integer.MAX_VALUE;
		dfs(0,map);
		System.out.println(min);
	}
	static void dfs(int index,int[][] tMap) {
		if(index >= lSize) {
			min = Math.min(min, check(tMap));
			return;
		}
		
		int[][] temp = copy(tMap); //복사하고 시작
		int x = list.get(index).x;
		int y = list.get(index).y;
		int num = list.get(index).num;
		if(num==1) { 
			dfs(index+1,cctv(x,y,0,temp));
			dfs(index+1,cctv(x,y,1,temp));
			dfs(index+1,cctv(x,y,2,temp));
			dfs(index+1,cctv(x,y,3,temp));
		}else if(num==2) {
			int[][] temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,1,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,2,temp);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
			
		}else if(num==3) {
			int[][] temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,2,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,1,temp);
			temp2 = cctv(x,y,2,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,1,temp);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
		}else if(num==4) {
			int[][] temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,1,temp2);
			temp2 = cctv(x,y,2,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,1,temp);
			temp2 = cctv(x,y,2,temp2);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,1,temp2);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
			temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,2,temp2);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
		}else if(num==5) {
			int[][] temp2 = cctv(x,y,0,temp);
			temp2 = cctv(x,y,1,temp2);
			temp2 = cctv(x,y,2,temp2);
			temp2 = cctv(x,y,3,temp2);
			dfs(index+1,temp2);
		}
	}
	static int[][] cctv(int x,int y,int dir,int[][] arr) {
		int[][]	temp = copy(arr);
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		while(true) {
			if(nx<0||nx>N-1||ny<0||ny>M-1) break;
			if(temp[nx][ny]==6) break;
			temp[nx][ny] = 7;
			nx+=dx[dir];
			ny+=dy[dir];
		}
		return temp;
	}
	
	static int[][] copy(int[][] arr) {
		int[][] result = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				result[i][j] = arr[i][j];
			}
		}
		return result;
	}
	
	static int check(int[][] arr) {
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]==0)
				count++;
			}
		}
		return count;
	}
	
	static class Pair implements Comparable<Pair>{
		int x,y,num;

		@Override
		public int compareTo(Pair o) {
			if(this.x==o.x) return this.y - o.y;
			return this.x-o.x;
		}
		public Pair(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}
