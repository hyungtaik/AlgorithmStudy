package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category LCS (DP)
 * 
 * @see 백준 9251번: LCS <br>
 *      메모리: 17288 KB <br>
 *      시간: 120 ms
 * @since 2020-09-20
 * 
 */

public class boj_9251 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first = br.readLine();
		String second = br.readLine();
		
		System.out.println(LCS(first,second));
	}
	static int LCS(String first, String second) {
		int count = 0;
		int max = Math.max(first.length(), second.length());
		if(max == second.length()) {
			String temp = second;
			second = first;
			first = temp;
		}
		// row : second , col : first
		int[][] map = new int[second.length()+1][first.length()+1];
		for(int i=1;i<=second.length();i++) {
			char temp = second.charAt(i-1);
			for(int j=1;j<=first.length();j++) {
				if(temp == first.charAt(j-1)) {
					map[i][j] = map[i-1][j-1]+1;
				}else {
					map[i][j] = Math.max(map[i][j-1], map[i-1][j]);
				}
			}
		}
		
		count = map[second.length()][first.length()];
		
		return count;
	}

}
