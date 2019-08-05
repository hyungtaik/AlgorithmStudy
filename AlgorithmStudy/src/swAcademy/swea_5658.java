package swAcademy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import java.io.InputStreamReader;

import java.io.BufferedReader;



public class swea_5658 {
	private static int[] deleteRefeat(int arr[]) {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++) {
			ar.add(arr[i]);
		}
		HashSet<Integer> ar2 = new HashSet<Integer>(ar);
		ArrayList<Integer> result = new ArrayList<Integer>(ar2);
		int ans[] = new int[result.size()];
		for(int i=0;i<result.size();i++) {
			ans[i] = result.get(i);
//			System.out.println(ans[i]);
		}
		return ans;
	}
	
	private static int sortA(int arr[],int K) {
		int result = 0;
		int temp;
		
		for(int i=0;i<arr.length-1;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]>arr[i]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		result = arr[K-1];
		return result;
	}
	
	
	private static int hexToDecimal(char ch) {
		if (ch >= '0' && ch <= '9')
            return (int) (ch - '0');
        else
            return (int) (ch - 'A' + 10);
	}
	
	private static String rotate(String temp) {
		
		char[] temp2 = new char[temp.length()];
		for(int j=0;j<temp2.length;j++) {
			temp2[j] = temp.charAt(j);
		}
		char last = temp.charAt(temp.length()-1);
		for(int j=temp2.length-1;j>0;j--) {
			temp2[j] = temp2[j-1];
		}
		temp2[0] = last;
		String result= String.valueOf(temp2);
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N,K;
		int result = 0;
		int token = Integer.parseInt(br.readLine());
		for(int i=1;i<=token;i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int B = N/4;
			int arr[] = new int[4*B];
			int num = 0;
			String temp = br.readLine();
			
			
			for(int n=0;n<B;n++) {
//				System.out.println("==============="+n);
				if(n>0) {
					temp = rotate(temp);
				}
//				System.out.println(temp+"********************");
				for(int j=0;j<N;j+=B) {
					String hex = temp.substring(j, j+B);
//					System.out.println("***********"+hex);
					int sum=0;
					int mul=1;
					for (int k = hex.length()-1; k >=0; k--) {
		                int decimal = hexToDecimal(hex.charAt(k));
		                sum+=decimal*mul;
		                mul*=16;
		            }
//					System.out.println("("+sum+")");
					arr[num++]= sum;
				}
			}
		
//			System.out.println("³¡³²?");
			arr = deleteRefeat(arr);
			result = sortA(arr, K);
			
			System.out.println("#"+i+" "+result);
			
			
		}
		
	}

}
