package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_5607_조합_김형택 {

	private static int TC;
	private static int N;
	private static int R;
	private static long result;
	private static long[] fact;
	private static long nnr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			result = 0;
			fact = new long	[N+1];
			fact[0] = 1; 
			for(int i=1;i<=N;i++) { // 팩토리얼값 저장
				fact[i] = (fact[i-1]*i)%1234567891;
			}
//			System.out.println(Arrays.toString(fact));
			
			// n!(n-r)!
			nnr=  (fact[R]*fact[N-R])%1234567891;
			
			// (nnr)^p-2
			long temp = solve(1234567889);
			
			// n! x (n!(n-r)!)  %  p -> 합동을 이용해서 구하기
			result = (fact[N]*temp)%1234567891;
			System.out.println("#"+tc+" "+result);
		}
	}
	private static long solve(int mod) {
        if (mod == 0) {
        	return 1; // 제일 처음 
        }
        long a = solve(mod/2); // 2로 나눠가며 들어가기
        long aa = (a * a) % 1234567891;
        if (mod % 2 == 0) return aa; // 짝수일떄 
        else return (aa * nnr) % 1234567891; // 홀수일대 하나 더 곱해주기
    }
	

}
