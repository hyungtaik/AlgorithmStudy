package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 3. 666�� �ѹ��� �˻�  - time: 116MS
public class boj_1436_3 {
	static int N; // ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int count = 0;
		int index = 666;
		while (count < N) {
			int num = index; // ���ڸ� / % �����ϱ� ���� ���ο� ���� ���
			for (;num>0; num /= 10) {// num�� �ڸ�����ŭ �ݺ�
				if (num % 1000 == 666) {
					count++;
					break;
				}
			}index++;
		}
		System.out.println(index - 1);
	}

}
