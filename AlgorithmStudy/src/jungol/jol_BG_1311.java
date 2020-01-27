package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_BG_1311 {
	static char color;
	static int[] num;
	static int max1,max2,index1,index2; //같은 숫자 빈도수 1등 

	// R:0, B:1, Y:2, G:3
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		color = ' ';
		char temp = ' ';
		num = new int[5];
		int count = 0;
		max1 = 0;max2 = 0;index1 = 0;index2 = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			if (i == 0) {
				color = st.nextToken().charAt(0);
				count++;
			}else {
				temp = st.nextToken().charAt(0);
			}
			if (temp == color) {
				count++;
			}
			num[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(scoring(count));
	}

	static int scoring(int count) {
		int score = 0;
		int seq = sortArr(); // 정렬됬으니 제일 마지막 인덱스 제일 큰 수
		if(count==5) {
			if(seq ==1) { // 1.
				return score = 900+num[4];
			}else { // 4.
				return score = 600+num[4];
			}
		}else { // 5.
			if(seq == 1) {
				return score = 500 + num[4];
			}
			maxNum();
			// 같은 수 2장
			if (max1 == 2) {
				if(max2 ==2) { // 7.
					return score = index1*10 + index2 + 300;
				}else { // 8.
					return score = 200 + index1;
				}
			} else if (max1 == 3) {
				if(max2 ==2) { // 3.
					return score = index1*10 + index2 + 700;
				}else { // 6.
					return score = index1 + 400;
				}
			} else if (max1 == 4) { //2.
				return score = 800 + index1;
			} else { // 9.
				return score = 100 + num[4];
			}
		}

	}
	static int sortArr() {
		//정렬
		for(int i=0;i<4;i++) {
			int temp=0;
			for(int j=i+1;j<5;j++) {
				if(num[i]>num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;;
				}
			}
		}
		int first = num[0];
		boolean seq = true; // 순차적
		for(int i=1;i<5;i++) {
			if(first+1 == num[i]) {
				first++;
			}else {
				seq = false;
				break;
			}
		}
		if(seq == true) {
			return 1; //순차적
		}else {
			return 0; //비순차적
		}
	}
	static void maxNum() {
		
		int[] temp = new int[10];
		for(int i=0;i<5;i++) {
			temp[num[i]]++;
		}
		for(int i=1;i<10;i++) {
			if(max1<=temp[i]) {
				index1 = i; // 숫자
				max1 = temp[i];
			}
		}
		for(int i=1;i<index1;i++) {
			if(temp[i]==max1 && max1 != 2) continue;
			if(max2<=temp[i]) {
				index2 = i; // 숫자
				max2 = temp[i];
			}
		}

	}
	
}
