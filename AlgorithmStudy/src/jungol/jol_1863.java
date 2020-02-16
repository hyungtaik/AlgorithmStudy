import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class jol_1863 {

	private static int m;
	private static int n;
	private static int[] person;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 인원
		m = Integer.parseInt(st.nextToken()); // 쌍
		makeSet(n);
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			
			union(p1,p2);
		}
		// -1인 것을 체크
		int check = 0;
		for(int i=1;i<=n;i++) {
			if(person[i] == -1) check++;
		}
		
		System.out.println(check);
	}
	static void makeSet(int n) {
		person = new int[n+1]; // 각 정보
		Arrays.fill(person, -1);
	}
	static int findSet(int num) {
		if(person[num]== -1) return num;
		return person[num] = findSet(person[num]); 
	}
	static void union(int a,int b) {
		int first = findSet(a);
		int second = findSet(b);
		if(first != second) { // 다른 경우 합치기
			person[second] = first;
		}
	}
	

}
