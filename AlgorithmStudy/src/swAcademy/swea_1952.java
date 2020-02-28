package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1952 {

	private static int TC;
	private static int result;
	private static int sum;
	private static int[] plan;
	private static int[] money;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			money = new int[4]; // 1일, 1달, 3달, 1년 요금 배열
			plan = new int[12]; // 1~12월까지 이용 계획
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				money[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			sum = 0;
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
				if (plan[i] > 0) {
					sum += plan[i];
				}
			}
			int start = 0;
			for (int i = 0; i < 12; i++) {
				if (plan[i] > 0) {
					start = i;
					break;
				}
			}
			result = money[3]; // 1년으로 계산했을때가 최대 Max

			dfs(start,0,sum);
			System.out.println("#" + tc + " " + result);
		}
	}

	static void dfs(int index,int ssum,int count) {
		if(ssum>result) {
			return;
		}
		
		if(count <= 0) {
			result = Math.min(result, ssum);
			return;
		}
		dfs(index+1,ssum+money[0]*plan[index],count-plan[index]);
		dfs(index+1,ssum+money[1],count-plan[index]);
		
		if(index <10) dfs(index+3,ssum+money[2],count-(plan[index]+plan[index+1]+plan[index+2]));
		else if(index == 10) dfs(index+2,ssum+money[2],count-(plan[index]+plan[index+1]));
		else dfs(index+1,ssum+money[2],count-plan[index]);
	
	}

}
