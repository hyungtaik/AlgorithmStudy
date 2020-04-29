package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_5557_test {

	private static int N;
	private static ArrayList<Integer> list;
	private static long cnt;
	private static Integer result;
	private static ArrayList<Pair>[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		result = list.get(N-1);
		cnt = 0;
		dp = new ArrayList[N-1];
		for(int i=0;i<N-1;i++) {
			dp[i] = new ArrayList<Pair>();
		}
		dp[0].add(new Pair(list.get(0),1));
		
		for(int i=1;i<N-1;i++) {
//			System.out.println("***********");
//			if(list.get(i)== 0) continue;
			for(int j=0;j<dp[i-1].size();j++) {
				long temp = dp[i-1].get(j).num;
				long sum = temp+list.get(i);
				if(sum >= 0 && sum<=20) {
					boolean flag = true;
					for(int k=0;k<dp[i].size();k++) {
						if(dp[i].get(k).num == sum) {
							dp[i].get(k).count+=dp[i-1].get(j).count;
							flag = false;
						}
					}
					if(flag) {
						dp[i].add(new Pair(sum,dp[i-1].get(j).count));
					}
				}
				long sub = temp-list.get(i);
				if(sub >= 0 && sub<=20 ) {
					boolean flag = true;
					for(int k=0;k<dp[i].size();k++) {
						if(dp[i].get(k).num == sub) {
							dp[i].get(k).count+=dp[i-1].get(j).count;
							flag = false;
						}
					}
					if(flag) {
						dp[i].add(new Pair(sub,dp[i-1].get(j).count));
					}
				}
			}
//			for(int j=0;j<dp[i].size();j++) {
//				System.out.println(dp[i].get(j));
//			}
		}
		for(int i=0;i<dp[N-2].size();i++) {
			if(dp[N-2].get(i).num == result) {
				cnt= dp[N-2].get(i).count;
				break;
			}
		}
		
		System.out.println(cnt);
	}
	static class Pair{
		long num,count;

		public Pair(long num, long count) {
			super();
			this.num = num;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Pair [num=" + num + ", count=" + count + "]";
		}
		
	}
}