package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 3. 666을 한번에 검색  - time: 116MS
public class boj_1436_3 {
	static int N; // 숫자

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int count = 0;
		int index = 666;
		while (count < N) {
			int num = index; // 숫자를 / % 가공하기 위해 새로운 변수 사용
			for (;num>0; num /= 10) {// num의 자리수만큼 반복
				if (num % 1000 == 666) {
					count++;
					break;
				}
			}index++;
		}
		System.out.println(index - 1);
	}

}
