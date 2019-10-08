package programmers;

import java.util.Arrays;
import java.util.HashSet;

import java.util.Set;


public class naver2 {
	public static void main(String[] args) {
		System.out.println(solution(1));
	}


	public static long solution(int n) {
		
		Set<Integer> set = new HashSet<>();
		
		int result = 1;
		//È½¼ö
		for(int j=1;j<=9;j++) {
			result = j;
			for(int i=j+1;i<=9;i++) {
//				j¸¸Å­ °öÇØ³ª°£´Ù
				result *= i;
				set.add(result);
			}
		}
//		String[] answer = set.toArray(new String[set.size()]);
		Integer[] ans = set.toArray(new Integer[set.size()]);
		
		Arrays.sort(ans);
		
		for(int i=0;i<ans.length;i++) {
			System.out.println(ans[i]);
		}
		
		long answer = ans[n-1];
		
		return answer;
	}

}
