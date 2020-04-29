package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_모의_5658_보물상자비밀번호 {

	private static int T;
	private static int N;
	private static int K;
	private static HashSet<Integer> set;
	private static LinkedList<Integer> q;
	private static ArrayList list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String input = br.readLine();
			q = new LinkedList<Integer>();
			for(int i=0;i<N;i++) {
				String temp = input.charAt(i)+"";
				q.add(Integer.parseInt(temp,16));
//				System.out.println(q.get(i));
			}
			
			set = new HashSet<Integer>();
			int rotate = N/4; // 한면에 있는 숫자갯수 회전횟수
			for(int i=0;i<rotate;i++) {
				int sum = 0;
				int pivot = rotate-1;
				for(int j=0;j<N;j++) {
					int temp = (int)Math.pow(16, pivot);
					temp*= q.get(j);
					sum+=temp;
					pivot--;
					if((j+1)%rotate == 0 && j!=0) {
						set.add(sum);
						pivot = rotate-1;
						sum = 0;
					}
				}
				int first = q.poll();
				q.add(first);
			}
			list = new ArrayList<Integer>(set);
			Collections.sort(list,Collections.reverseOrder());
			
			System.out.println("#"+tc+" "+list.get(K-1));
			
		}
	}

}
