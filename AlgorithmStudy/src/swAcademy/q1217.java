package swAcademy;

import java.util.Scanner;

public class q1217 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 10; i++) {
			int tc = sc.nextInt();
			int num = sc.nextInt();
			int snum = sc.nextInt();
			int result = 1;
			for(int j=0;j<snum;j++) {
				result=result*num;
			}
			System.out.println("#"+tc+" " + result);
		}
	}
}
