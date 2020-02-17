package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 조합 a e i o u 가 하나는 들어가되 자음도 2개는 들어간다
// 배열 다르게 해서 조합
public class boj_1759 {

	private static int L;
	private static int C;
	private static ArrayList<Character> list;
	private static char[] brr;
	private static int jaCount;
	private static int moCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		L = Integer.parseInt(st.nextToken()); // L개의 알파벳
		C = Integer.parseInt(st.nextToken()); // C개의 조합

		list = new ArrayList<Character>();
		
		moCount = 1;
		jaCount = 2;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			char input = st.nextToken().charAt(0);
			list.add(input); // 전체
		}
		Collections.sort(list);
		brr = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			brr[i] = list.get(i);
		}
		combi(new boolean[C+1], 0, 0);
	}

	static void combi(boolean[] visited, int index, int count) {
		if (index == C+1) {
			return;
		}
		if (count == L) {
			char[] result = new char[L];
			moCount = 1;
			jaCount = 2;
			for (int i = 0, idx=0; i < C; i++) {
				if (visited[i] == true) {
					if(brr[i] == 'a'||brr[i] == 'e'||brr[i] == 'o'||brr[i] == 'i'||brr[i] == 'u') {
						moCount--;
					}else {
						jaCount--;
					}
					result[idx++] = brr[i];
				}
			}
			if(moCount<=0 && jaCount <=0) {
				for(int i=0;i<L;i++) {
					System.out.print(result[i]);
				}System.out.println();
			}
			return;
		}
		
		visited[index] = true;
		combi(visited, index + 1, count + 1);
		visited[index] = false;
		combi(visited, index + 1, count);
	}

}
