package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 세그먼트 트리
 * 
 * @see 백준 2042번: 구간 합 구하기 <br>
 *      메모리: 128148 KB <br>
 *      시간: 1388 ms
 * @since 2020-11-08
 * 
 */

public class boj_2042 {

	private static int N,M,K;
	private static long[] arr;
	private static long[] tree;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		tree = new long[N*4];
		init(1,N,1);
		
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				long diff = c - arr[b];
				arr[b] = c;
				update(1, N, 1, b, diff);
			}else {
				System.out.println(sum(1,N,1,b,(int)c));
			}
		}
	}
	static long init(int start,int end, int node) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		int mid = (start + end)/2;
		
		return tree[node] = init(start,mid,node*2) + init(mid+1,end,node*2+1);
	}
	static void update(int start,int end, int node, int index, long diff) {
		if(index<start || index > end) return;
		
		tree[node] += diff;
		if(start == end) return;
		
		int mid = (start+end)/2;
		
		update(start,mid,node*2,index,diff);
		update(mid+1,end,node*2+1,index,diff);
 	}
	static long sum(int start, int end, int node, int left, int right) {
		if(left > end || right < start) return 0;
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		
		return sum(start,mid,node*2,left,right) + sum(mid+1,end,node*2+1, left,right);
	}
}
