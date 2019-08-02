import java.util.Scanner;

public class sds_q4 {
	static int max = 0;
	static int[][] arr;
	static int n;
	
	static void compare(int i,int j,int count) {
		if(i==0) {
			if(j==0) {
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
			}
			else if(j==n-1) {
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
			}else {
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
			}
		}
		else if(i==n-1) {
			if(j==0) {
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
			}
			else if(j==n-1) {
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
			}else {
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
			}
		}
		else {
			if(j==0) {
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
			}else if(j==n-1) {
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
			}else {
				if(arr[i][j] < arr[i+1][j]) {
					compare(i+1,j,count+1);
				}
				if(arr[i][j] < arr[i-1][j]) {
					compare(i-1,j,count+1);
				}
				if(arr[i][j] < arr[i][j-1]) {
					compare(i,j-1,count+1);
				}
				if(arr[i][j] < arr[i][j+1]) {
					compare(i,j+1,count+1);
				}
			}
		}
		if(max<count) {
			max = count;
		}
		return;
	}
	
	
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			n = sc.nextInt();
			max = 0;
			arr = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					compare(i, j, 1);
				}
			}
			System.out.println("#" + (tc + 1) + " " + max);
		}

	}

}
