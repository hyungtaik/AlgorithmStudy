package baekjoonA;

/**
 * 
 * @author TAEK
 * @category DFS(백트래킹)
 * 
 * @see 백준 17136번: 색종이 붙이기 <br>
 *      메모리: 183548 KB <br>
 *      시간: 612 ms
 * @since 2020-10-17
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17136_second {

	private static int[][] map;
	private static int min;
	private static int[] count;
	private static boolean[][] visited;
	private static int sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		visited = new boolean[10][10];
		sum = 0;
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					visited[i][j] = true;
				}else {
					sum++;
				}
			}
		}
		min = Integer.MAX_VALUE;
		count = new int[5];
		Arrays.fill(count, 5);
		dfs(0,0,map);
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	
	static boolean check(int x,int y,int size,int[][] arr) {
		if(x+size>10 || y+size >10) return false;
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(arr[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}

	
	static int[][] copy(int x,int y, int size,int[][] arr){
		int[][] copyArr = new int[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(arr[i][j]>0) copyArr[i][j] = arr[i][j];
			}
		}
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				copyArr[i][j] = 0;
			}
		}
		return copyArr;
	}
	
	static void dfs(int chkCount,int cnt,int[][] tmap) {
		
		if(cnt>=min) {
			return;
		}
		if(chkCount == sum) {
			min = Math.min(min, cnt);
			return;
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(visited[i][j]) continue;
				if(tmap[i][j] == 1) {
					int index = 5;
					while(true) {
						if(check(i,j,index,tmap)) {
							break;
						}
						index--;
					}
				
					while(index>0) {
						if(count[index-1]==0) {
							index--;
							continue;
						}
						int[][] temp = copy(i,j,index,tmap);
						count[index-1]--;
						dfs(chkCount+(index*index),cnt+1,temp);
						count[index-1]++;
						index--;
					}
					
					return;
					
				}
			}
		}
	}

}
