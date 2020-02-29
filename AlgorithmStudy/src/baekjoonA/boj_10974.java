package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10974 {

	private static int num;
	private static int[] result;
	private static boolean[] visited;
	private static int[] arr;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		num = Integer.parseInt(st.nextToken());
		arr = new int[num];
		for(int i=0;i<num;i++) {
			arr[i] = i+1;
		}
		result = new int[num];
		visited = new boolean[num];
		sb = new StringBuilder();
		perm(0);
//		do {
//			for(int i=0;i<num;i++) {
//				sb.append(arr[i]);
//				sb.append(" ");
//			}
//			sb.append("\n");
//		} while (np(arr));
		
		System.out.println(sb);
	}
	static void perm(int count) {
		if(count == num) {
			for(int i=0;i<num;i++) {
				sb.append(result[i]);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0;i<num;i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[count] = arr[i];
				perm(count+1);
				visited[i] = false;
			}
		}
	}
	
	static boolean np(int[] arr) {
		int len = arr.length;
		int i = len-1;
		
		while(i>0 && arr[i-1]>=arr[i]) i--;
		if(i==0) return false;
		
		int j = len-1;
		while(arr[i-1] >= arr[j]) j--;
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = len-1;
		while(i<j) {
			temp = arr[i];
			arr[i]=arr[j];
			arr[j] = temp;
			i++;j--;
		}
		return true;
		
	}
	

}
