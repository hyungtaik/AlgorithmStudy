package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1786 {

	private static String T;
	private static String P;
	private static int[] pi;
	private static int count;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		
		pi = getPi(); // 패턴 찾기
		count = 0; // P가 몇번 나오는지 세기
		
		sb = new StringBuilder();
		KMP();
		System.out.println(count);
		System.out.println(sb.toString());
	}
	private static void KMP() {
		int plen = P.length();
		int tlen = T.length();
		char[] text = T.toCharArray();
		char[] patt = P.toCharArray();
		
		for(int i=0,j=0;i<tlen;i++	) {
			while(j>0 && text[i]!=patt[j]) {
				j = pi[j-1];
			}
			
			if(text[i] == patt[j]) {
				if(j==plen-1) {
					sb.append(i-(plen-1)+1).append("\n");
					count++;
					j = pi[j];
				}else {
					j++;
				}
			}
		}
		
	}
	static int[] getPi() {
		int len = P.length();
		
		int[] pi = new int[len];
		char[] pattern = P.toCharArray();
		
		int j = 0;
		for(int i=1;i<len;i++) {
			while(j>0 && pattern[i]!=pattern[j]) {
				j = pi[j-1];
			}
			if(pattern[i]==pattern[j]) {
				++j;
				pi[i] = j;
			}
		}
		
		return pi;
	}

}
