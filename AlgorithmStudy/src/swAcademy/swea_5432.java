package swAcademy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_5432 {

	private static int[][] map;
	private static int height;
	private static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			height = 0;
			result = 0;
			int len = input.length();
			for(int i=0;i<len;i++) {
				char temp = input.charAt(i);
				if(temp == '(') {
					if(input.charAt(i+1) == ')') {
						result += height; // ���̸�ŭ ������ �������
						i++;
					}else {
						height++; // ��� ���� �ö�
					}
				}else { // ')'
					result++; // ���������ϱ� +1
					height -- ; // �������� ���� ������
					
				}
			}
			result += height;
		
			System.out.println("#"+tc+" "+result);
		}
	}

}
