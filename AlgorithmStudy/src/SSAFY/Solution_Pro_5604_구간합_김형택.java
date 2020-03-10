package SSAFY;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Pro_5604_구간합_김형택 {
	
	private static long start;
	private static long end;
	private static long result;
	private static long n;
	private static int TC; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Long.parseLong(st.nextToken());
			end = Long.parseLong(st.nextToken());
			result = 0L;
			n = 1;
			while(true) {
				solve(n);
				if(start == end) break;
				start /= 10;
				end /= 10;
				// 해당 자릿수만큼 갯수 세서 더해주기
				result+= 45*n*(end-start+1);
				n*=10;
			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
	// start는 0으로 end는 9로 맞춰주기 
	static void solve(long n) {
		while(start%10!=0) {
			calc(start,n);
			if(start == end) return;
			start++;
		}
		while(end%10!=9) {
			calc(end,n);
			if(start == end) return;
			end--;
		}
	}
	// 각 자리수 더해주기  / n: 자릿수  num: 수
	static void calc(long num,long n) {
		while(num>0) {
			result += (num%10)*n;
			num/=10;
		}
	}
	
}