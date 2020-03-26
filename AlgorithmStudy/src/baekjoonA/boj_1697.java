package baekjoonA;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class boj_1697 {

	private static int start;
	private static int end;
	private static int min;
	private static LinkedList<Integer> q;
	private static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt();
		end = sc.nextInt();
		
		min = end-start;
		q = new LinkedList<Integer>();
		visited = new boolean[100001];
		
		visited[start] = true;
		if(start == end){
			min = 0;
		}else if(start == 0) {
			visited[1] = true;
			q.add(1);
			bfs();
			min++;
		}else {
			q.add(start);
			bfs();
		}
		System.out.println(min);
	}
	static void bfs() {
		int count = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int i=0;i<len;i++) {
				int cur = q.poll();
				if(cur == end) {
					min = count;
					return;
				}
				int temp = cur*2;
				if(temp>=0 && temp<=100000 && !visited[temp]) { 
					visited[temp] = true;
					q.add(temp);
				}
				temp = cur+1;
				if(temp>=0 && temp<=100000 && !visited[temp]) { 
					visited[temp] = true;
					q.add(temp);
				}
				temp = cur-1;
				if(temp>=0 && temp<=100000 && !visited[temp]) { 
					visited[temp] = true;
					q.add(temp);
				}
			}
			count++;
		}
	}
	

}
