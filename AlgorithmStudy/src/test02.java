import java.util.Scanner;

public class test02 {
	static int max = 0;
	static int[] arr;
	static int n;
	static int pt=0;
	static int size = 0;
	static void compare(int i,int count) {
		if(i<n) {
			if(arr[i] < arr[i+n]) {
				compare(i+n,count+1);
			}
			if(i==0) {
				if(arr[i] < arr[i+1]) {
					compare(i+1,count+1);
				}
			}
			else if(i==n-1) {
				if(arr[i] < arr[i-1]) {
					compare(i-1,count+1);
				}
			}else {
				if(arr[i] < arr[i-1]) {
					compare(i-1,count+1);
				}
				if(arr[i]< arr[i+1]) {
					compare(i+1,count+1);
				}
			}
		}
		else if(i>=size-n) {
			if(arr[i]< arr[i-n]) {
				compare(i-n,count+1);
			}
			if(i==size-n) {
				if(arr[i]< arr[i+1]) {
					compare(i+1,count+1);
				}
			}
			else if(i==(size-1)) {
				if(arr[i] < arr[i-1]) {
					compare(i-1,count+1);
				}
			}else {
				if(arr[i] < arr[i-1]) {
					compare(i-1,count+1);
				}
				if(arr[i] < arr[i+1]) {
					compare(i+1,count+1);
				}
			}
		}
		else {
			if(arr[i]< arr[i-n]) {
				compare(i-n,count+1);
			}
			if(arr[i] < arr[i+n]) {
				compare(i+n,count+1);
			}
			if((i/n)==0) {
				if(arr[i] < arr[i+1]) {
					compare(i+1,count+1);
				}
			}else if((i%n)==(n-1)) {
				if(arr[i]< arr[i-1]) {
					compare(i-1,count+1);
				}
			}else {
				if(arr[i] < arr[i-1]) {
					compare(i-1,count+1);
				}
				if(arr[i] < arr[i+1]) {
					compare(i+1,count+1);
				}
			}
		}
		if(max<count) {
			max = count;
		}
		
		if(i==pt && pt!=size-1) {
			pt +=1;
			compare(pt,1);
		}
		return;
	}
	
	
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			n = sc.nextInt();
			size = n*n;
			max = 0;
			pt = 0;
			arr = new int[size];
			for(int i=0;i<size;i++) {
					arr[i] = sc.nextInt();
			}
			compare(pt,1);
			System.out.println("#" + (tc + 1) + " " + max);
		}

	}

}
