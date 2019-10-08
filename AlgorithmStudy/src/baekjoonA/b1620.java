package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class b1620 {
	private static boolean isInteger(String question) {
		try {
			Integer.parseInt(question);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	static int N;
	static int M;
	static String pokemon;
	
	static String[] question;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		StringBuilder builder = new StringBuilder(M);
		
		
		Map<String,String> map = new HashMap<>();
		
		for(int i = 0;i<N;i++) {
			pokemon = br.readLine();
			String num = Integer.toString(i+1);
			map.put(pokemon, num);
			map.put(num, pokemon);
		}
		
		for(int i=0;i<M;i++) {
			String question = br.readLine();
			builder.append(map.get(question)+"\n");
		}
		
		System.out.println(builder);
		
	}

}
