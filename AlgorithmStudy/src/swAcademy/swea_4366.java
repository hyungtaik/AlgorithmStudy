package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
1
11010
210
*/
public class swea_4366 {
	private static int TC;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++) {
			char[] two = br.readLine().toCharArray();
			char[] three = br.readLine().toCharArray();
			
			int result = 0;
			
			L:for(int i=0; i<two.length; i++) {
				if(two[i] == '0') {
					two[i] = '1';
				}else {
					two[i] = '0';
				}
				for(int j=0; j<three.length; j++) {
					char tmp = three[j];
					
					for(char k='0'; k<='2'; k++) {
						if(tmp!=k) {
							three[j] = k;
							String temp1 = String.valueOf(two);
							String temp2 = String.valueOf(three);
							
							if(Integer.parseInt(temp1, 2)==Integer.parseInt(temp2, 3)) {
								result = Integer.parseInt(temp1, 2);
								break L;
							}
						}
					}
					three[j] = tmp;
				}
				if(two[i] == '0') {
					two[i] = '1';
				}else {
					two[i] = '0';
				}
			}
			System.out.println("#"+tc+" "+result);
		}

	}
}
