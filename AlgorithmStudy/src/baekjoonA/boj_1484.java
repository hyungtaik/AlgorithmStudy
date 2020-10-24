package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category ��������
 * 
 * @see ���� 1484��: ���̾�Ʈ <br>
 *      �޸�: 12972 KB <br>
 *      �ð�: 96 ms
 * @since 2020-10-24
 * 
 */

public class boj_1484 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int cur = 1;
		int next = 2;
		boolean flag = false;
		while(cur < next) {
			int result = (next*next) - (cur*cur);
			if(result == n) {
				flag = true;
				System.out.println(next);
			}
			if(result < n) {
				next++;
			}else {
				cur++;
			}
		}
		
		if(!flag) {
			System.out.println(-1);
		}
	}

}
