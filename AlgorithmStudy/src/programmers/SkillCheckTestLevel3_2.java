package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class SkillCheckTestLevel3_2 {

	public static void main(String[] args) {
		String[] uid = {"frodo","fradi","crodo","abc123","frodoc"};
		String[] bid = {"fr*d*","*rodo","******","******"};
		
		System.out.println(solution(uid,bid));
				
	}

	private static int[] banArr;
	private static boolean[] visited;
	private static int len;
	private static int N;
	private static boolean[] check;
	private static int answer;
	private static HashSet<String> set;

	public static int solution(String[] user_id, String[] banned_id) {
		answer = 0;
		len = banned_id.length;
		N = user_id.length;
				
		banArr = new int[banned_id.length];
		visited = new boolean[user_id.length];
		set = new HashSet<String>();
		combi(0,banned_id,user_id);
		
		return answer;
	}
	
	static void combi(int count,String[] banned_id,String[] user_id) {
		if(count == len) {
			int cnt = 0;
			check = new boolean[len];
			for(int j=0;j<len;j++) {
				String temp = user_id[banArr[j]]; // 조합된 결과
				L:for(int i=0;i<len;i++) { // 벤 목록
					if(check[i]) continue;
					String temp2 = banned_id[i];
					if(temp.length()!=temp2.length()) continue;
					boolean flag = true;
					for(int len=0;len<temp2.length();len++) {
						if(temp2.charAt(len)=='*') continue;
						if(temp.charAt(len)!=temp2.charAt(len)) {
							flag = false;
							break;
						}
					}
					if(flag) {
						check[i] = true;
						cnt++;
						break L;
					}
				}
			}
			if(cnt == len) {
				System.out.println(Arrays.toString(banArr));
				int[] arr = new int[len];
				String temp = "";
				for(int i=0;i<len;i++) {
					arr[i] = banArr[i];
				}
				Arrays.sort(arr);
				for(int i=0;i<len;i++) {
					temp+=arr[i]+"";
				}
				if(!set.contains(temp)) {
					set.add(temp);
					answer++;
				}
				return;
			}
			return;
		}
		for(int i=0;i<N;i++	) {
			if(visited[i]) continue;
			banArr[count] = i;
			visited[i] = true;
			combi(count+1,banned_id,user_id);
			visited[i] = false;
		}
	}
}
