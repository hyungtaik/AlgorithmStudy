package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea_4014 {
	static int N;
	static int x;
	static int[][] map;
//	static int[] check;
	static List<Integer> check;
	
	static List<Integer> solution(int[] arr) {
//		int[] result = new int[N];
		List<Integer> result = new ArrayList<Integer>();
		int temp = arr[0];
		int count = 0;
		int start = 0;
		for(int i=0;i<arr.length;i++) {
			if(temp == arr[i]) {
				count++;
			}else {
//				result[start++] = temp*10+count;
				result.add((temp*100)+count);
//				System.out.println(temp*10+count);
				temp = arr[i];
				count = 1;
			}
			if(i == arr.length-1) {
				result.add((temp*100)+count);
			}
			
		}
//		for(int i=0;i<result.size();i++) {
//			System.out.println("***"+result.get(i));
//		}
//		System.out.println(result);
//		System.out.println("************************");
		return result;
	}
	
	static int checkCount(List<Integer> check,int x) {
		int count = 0;
		int num = 0;
		boolean chk = true;
		for(int i=0;i<check.size()-1;i++) {
			int num1 = check.get(i);
			int num2 = check.get(i+1);
			int max = Math.max(num1, num2);
			int min = Math.min(num1, num2);
			if((max/100)-(min/100)!=1 || min%100 < x) {
				chk = false;
			}
			if((max/100)-(min/100)==1 || min%100 >=x) {
				if(min == num1) {
					check.set(i, check.get(i)-x);
				}
				else if(min == num2) {
					check.set(i+1, check.get(i+1)-x);
				}
			}
		}
		
		if(chk == true) {
//			System.out.println(check+"에서 체크됨");
			count = 1;
		}else {
			count = 0;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int token = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=token;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			int count = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] temp = new int[N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					temp[j] = map[i][j];
				}
				check = solution(temp);
				count+=checkCount(check,x);
			}
//			System.out.println("모든행 검색 결과:"+count);
			for(int j=0;j<N;j++) {
				for(int i=0;i<N;i++) {
					temp[i] = map[i][j];
				}
				check = solution(temp);
				count+=checkCount(check,x);
			}
//			System.out.println("모든열 검색 결과:"+count);
			System.out.println("#"+tc+" "+count);
		}

	}

}
