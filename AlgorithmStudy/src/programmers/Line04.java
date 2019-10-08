package programmers;

import java.util.Scanner;

public class Line04 {
    public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		int seat[] = new int[N];
		
		int start=-1;
		int end=-1;
		
		int min=0;
		int max=-1;
		
		int maxStart=-1;
		int maxEnd=-1;
		
		for(int i=0;i<N;i++) {
			seat[i]=sc.nextInt();
			if(seat[i]==0) {
				if(min==0) {
					start=i;
				}
				min++;
				if(i==N-1) {
					if(max<min) {
						maxStart=start;
						max=min;
						maxEnd=i;
					}
				}				
			}else {
				if(min!=0) {
					end=i;
					if(max<min) {
						maxStart=start;
						max=min;
						maxEnd=end-1;
					}
					min=0;
				}
			}
		}
		if(maxEnd==N-1 || maxStart==0) {
			System.out.println(max);
			return;
		}
		if(max%2==0) {
			System.out.println(max/2);
		}else {
			System.out.println(max/2+1);
		}
	}
}