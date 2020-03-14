package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7194_화섭이의미생물배양_김형택 {

	private static int TC;
	private static int s,t,a,b;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			if(b==1) {
				if((t-s)%a==0) min = (t-s)/a;
				else min = -1;
			}else {
				solve();
			}
			if(min == Integer.MAX_VALUE) min = -1;
			
			System.out.println("#"+tc+" "+min);
		}
	}
	static void solve()	{
		int multi = 1;
		int cnt = 0;
		while(t-s*multi>=0) {
			int temp = t-s*multi;
			if(temp%a==0) {
				int tempAdd = temp/a;
				int tempCnt = 0;
				int tempMulti = multi;
				while(tempMulti>0) {
					tempCnt+=tempAdd/tempMulti;
					tempAdd%=tempMulti;
					tempMulti/=b;
				}
				min = Math.min(min,cnt+tempCnt);
			}
			cnt++;
			multi*=b;
		}
	}
}
