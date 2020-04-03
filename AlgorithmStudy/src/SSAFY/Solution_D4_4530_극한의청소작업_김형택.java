package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_4530_극한의청소작업_김형택 {

	private static int TC;
	private static long start;
	private static long end;
	private static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			start = Long.parseLong(st.nextToken());
			end = Long.parseLong(st.nextToken());
			
			long tempStart = Math.abs(start);
			long tempEnd = Math.abs(end);
			long ts = toDigit(toNine(tempStart));
			long te = toDigit(toNine(tempEnd));
			result = 0L;
			if(start>0&&end>0) { 		// + +
				result = te - ts;
			}else if(start<0 && end >0) { // - +
				result = te + ts - 1;
			}else { 						// - - 
				result = ts - te;
			}
			System.out.println("#"+tc+" " +result);
		}
	}
	
	public static long toNine(long num) {
		String str = num+"";
		StringBuilder sb = new StringBuilder("");
		for(int i=0; i<str.length();i++) {
			if( (str.charAt(i)-'0') > 4 ) {
				sb.append(str.charAt(i)-'1');
			} else {
				sb.append(str.charAt(i)-'0');
			}
		}
		return Long.parseLong(sb.toString());
	}
	
	public static long toDigit(long num) {
		String str = num+"";
		Long calc = 0L;
		Long pivot = 0L;
		
		for(int i=str.length()-1; i>=0;i--) {
			calc += (str.charAt(i)-'0') * (long)Math.pow(9, pivot++);
		}
		return calc;
	}

}
