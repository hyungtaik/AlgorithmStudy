import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금_김형택 {

	private static int TC;
	private static int[] pick;
	private static int[] set;
	private static int len;
	private static int max;
	private static int[] numbers;
	private static int check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			int count = Integer.parseInt(st.nextToken()); 
			len = str.length();
			numbers = new int[len]; 
			int[] copy = new int[len]; 
			int[] chk = new int[10];
			for (int i = 0; i < len; i++) {
				char temp = str.charAt(i);
				numbers[i] = temp - 48;
				copy[i] = numbers[i];
			}
			max = Integer.MIN_VALUE;
			check = 0;
			boolean checkNum = false;
			Arrays.sort(numbers);
			for(int i=0;i<numbers.length;i++) {
				chk[numbers[i]]++;
				if(chk[i]>1) {
					checkNum = true; //중복있음
					break;
				}
			}
			
			int i=0;
			int j=len-1;
			while(i<j) {
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
				i++;j--;
			}
			
			if(count>=len-1) {
				int l = len/2;
				int tcount = count-l;
				if((tcount%2) == 0 || checkNum) {
					max = calc(numbers);
				}else {
					int temp = numbers[len-2];
					numbers[len-2]= numbers[len-1];
					numbers[len-1] = temp;
					max = calc(numbers);
				}
			}else {
				solve(count,copy,max);
			}
			
			
			System.out.println("#" + tc + " " + max);
		}

	}

	static boolean check(int[] arr, int[] brr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != brr[i])
				return false;
		}
		return true;
	}

	static void solve(int count, int[] copy, int sum) {
		if (count == 0) {
			sum = calc(copy);
			max = Math.max(max, sum);
			return;
		}
		
		int[] pick = new int[len];
		pick[len - 1] = pick[len - 2] = 1;
		int[] set = new int[2];
		do {
			int[] tcopy = Arrays.copyOf(copy, len);
			for (int i = 0, j = 0; i < len; i++) {
				if (pick[i] == 1) {
					set[j++] = i;
				}
			}
			int temp = tcopy[set[0]];
			tcopy[set[0]] = tcopy[set[1]];
			tcopy[set[1]] = temp;

			if (count > 0)
				solve(count - 1, tcopy, sum);
			if (check == 1)
				return;

		} while (np(pick));

	}

	static int calc(int[] copy) {
		int sum = 0;
		for (int i = len - 1, j = 1; i >= 0; i--, j *= 10) {
			sum += copy[i] * j;
		}
		return sum;
	}

	static boolean np(int[] p) {
		int N = p.length;

		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;
		if (i == 0)
			return false;

		int j = N - 1;
		while (p[i - 1] >= p[j])
			--j;

		int temp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = temp;

		j = N - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}
		return true;
	}

}