package swAcademy;

import java.util.Scanner;

public class q2072 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int num;
		for(int i=0;i<tc;i++) {
			int result = 0;
			for(int j=0;j<10;j++) {
				num = sc.nextInt();
				if(num %2 == 1 ) {
					result += num;
				}
			}
			System.out.println("#"+(i+1)+" " + result);
		}
		
	}

}
