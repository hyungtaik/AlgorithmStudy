package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category Disjoint-Set
 * 
 * @see 백준 1717번: 집합의 표현 <br>
 *      메모리: 52620 KB <br>
 *      시간: 1448 ms
 * @since 2020-10-29
 * 
 */

public class boj_1717 {

	private static int n;
	private static int m;
	private static boolean[][] map;
	private static int[] parent;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		makeSet();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(input == 1) {
				a = find(a);
				b = find(b);
				if(a == b) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}else {
				disjoint(a,b);
			}
		}
	}
	static void makeSet() {
		parent = new int[n+1];
		for(int i=0;i<=n;i++) {
			parent[i] = i;
		}
	}
	static void disjoint(int a,int b) {
		a = find(a);
		b = find(b);
		if(a == b) return;
		parent[b] = a;
	}
	static int find(int num) {
		if(num == parent[num]) return num;
		return parent[num] = find(parent[num]);
	}
}
