package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_5557 {

	private static int N;
	private static ArrayList<Integer> list;
	private static long[][] dp;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		dp = new long[N][21];
		dp[0][list.get(0)] = 1;
        for(int i = 1; i < N-1; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i-1][j] > 0){
                	int sum = j+list.get(i);
                	if(sum <= 20){                       
                		dp[i][sum] += dp[i-1][j];
                	}
                	
                	int sub = j-list.get(i);
                    if(sub >= 0 ){
                        dp[i][sub] += dp[i-1][j];                       
                    }
                }
            }
        }
        System.out.println(dp[N-2][list.get(N-1)]);

	}
}
