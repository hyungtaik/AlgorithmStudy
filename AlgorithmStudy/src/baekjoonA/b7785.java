package baekjoonA;

import java.io.InputStreamReader;
import java.util.*;
import java.sql.Array;
import java.io.BufferedReader;

public class b7785 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		
		Set<String> set = new HashSet<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String status = st.nextToken();
			
			if(status.equals("enter")) {
				set.add(name);
			}
			else {
				set.remove(name);
			}
		}
		
		String[] arr = set.toArray(new String[set.size()]);
		Arrays.sort(arr);
		for(int i=arr.length-1;i>=0;i--) {
			System.out.println(arr[i]);
		}
	}

}
