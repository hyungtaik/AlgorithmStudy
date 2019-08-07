import java.util.Scanner;

public class test01_1 {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int max = 0;
			
			int n = sc.nextInt();
			int k = sc.nextInt();
			int arr[] = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j] = sc.nextInt();
			}
			if(arr[0]==0) {
				arr[0]=1;
			}
			if(arr[n-1]==0) {
				arr[n-1]=k;
			}
			
			int a = 1;
			int trr[] = new int[k + 1];
			int count = 0;
			for(int j=0;j<n;j++) {
				if(arr[j]==0) {
					count++;
				}
				if(arr[j]==a) {
					trr[a]++;
					trr[a]+=count;
					count=0;
				}
				if(arr[j]==a+1) {
					trr[a+1]++;
					trr[a]+=count;
					trr[a+1]+=count;
					count=0;
					a=a+1;
				}
				if(arr[j]>a+1) {
					trr[arr[j]]++;
					count = count-(arr[j]-a-1);
					trr[a]+=count;
					trr[arr[j]]+=count;
					count=0;
					a=arr[j];
					
				}
			}
			for(int j=1;j<k+1;j++) {
				if(max<trr[j]) {
					max = trr[j];
				}
			}
			System.out.println("#" + (i + 1) + " " + max);
		}
	}
}
