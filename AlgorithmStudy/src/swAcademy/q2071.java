package swAcademy;

import java.util.*;

public class q2071 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int num;
		for (int i = 0; i < tc; i++) {
			int sum = 0;
			for (int j = 0; j < 10; j++) {
				num = sc.nextInt();
				sum += num;
			}
		
				
			System.out.format("#%d %.0f \n", (i + 1), sum/10.0);
		}
		

		}
	}
