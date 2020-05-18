package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_15997 {

	private static ArrayList<Pair> list;
	private static Percent[] map;
	private static int[] score;
	private static PriorityQueue<Pair2> pq;
	private static double[] result;
	private static int[][] nation;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 해당 국가에 대한 접근을 위해 인덱스를 넣어준다.
		list = new ArrayList<Pair>();
		for(int i=0;i<4;i++) {
			String nation = st.nextToken();
			list.add(new Pair(nation,i));
		}
		
		// dfs돌면서 모든 경기들에 대한 경우의 수를 구하기 위해 총 경기수를 담을 배열을 생성
		map = new Percent[6];
		for(int i=0;i<6;i++) {
			st = new StringTokenizer(br.readLine());
			String n1 = st.nextToken();
			String n2 = st.nextToken();
			int ni1=0;
			int ni2=0;
			// Percent 배열로 접근을 위해서 - 어차피 4번 돌음
			for(int j=0;j<list.size();j++) {
				if(list.get(j).name.equals(n1)) {
					ni1 = list.get(j).index;
				}else if(list.get(j).name.equals(n2)) {
					ni2 = list.get(j).index;
				}
			}
			
			double w = Double.parseDouble(st.nextToken());
			double d = Double.parseDouble(st.nextToken());
			double l = Double.parseDouble(st.nextToken());
			map[i] = new Percent(ni1,ni2,w,d,l);
		}
		score = new int[4];
		result = new double[4];
		Arrays.fill(result, 0.0);
		dfs(0,1);
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		System.out.println(result[3]);
	}
	static void dfs(int count,double per) {
		if(per==0) return;
		if(count == 6) {
			pq = new PriorityQueue<Pair2>();
			for(int i=0;i<4;i++) {
				pq.add(new Pair2(score[i],i));
			}
			nation = new int[2][4];
			for(int i=0;i<4;i++) {
				Pair2 temp = pq.poll();
				nation[0][i] = temp.score;
				nation[1][i] = temp.index;
			}
			// 다같을 경우 (모두 무승부)
			if(nation[0][0]==nation[0][1]&& nation[0][1]==nation[0][2]&& nation[0][2]==nation[0][3]) {
				for(int i=0;i<4;i++) {
					result[nation[1][i]]+=per*(1/2.0);
				}
				
			}// 3국가 같을 경우 (3국가가 무승부)  9333
			else if(nation[0][0]>nation[0][1] && nation[0][1]== nation[0][2] && nation[0][2]==nation[0][3] ) {
				result[nation[1][0]] +=per;
				for(int i=1;i<4;i++) {
					result[nation[1][i]]+=per*(1/3.0);
				}
				
			// 다들 1승씩 하고 서로 무승부 1승 2무 x 3국가 / 3패 1국가
			}else if(nation[0][0]== nation[0][1] && nation[0][1]== nation[0][2] ) { // 6660
				for(int i=0;i<3;i++) {
					result[nation[1][i]]+=per*(2/3.0);
				}
				
			}
			else if(nation[0][0]== nation[0][1]) { //6641
				result[nation[1][0]] +=per;
				result[nation[1][1]] +=per;
			}
			else if(nation[0][1]== nation[0][2]) { //7441
				result[nation[1][0]] +=per;
				result[nation[1][1]] +=per*(1/2.0);
				result[nation[1][2]] +=per*(1/2.0);
			}
			// 승점이 모두 다를 경우
			else { // 7640
				result[nation[1][0]] +=per;
				result[nation[1][1]] +=per;
			}
			return;
		}
		// 승
		score[map[count].first]+=3;
		dfs(count+1,per*(map[count].w));
		score[map[count].first]-=3;
		
		// 무
		score[map[count].first]+=1;
		score[map[count].second]+=1;
		dfs(count+1,per*(map[count].d));
		score[map[count].first]-=1;
		score[map[count].second]-=1;
		
		// 패
		score[map[count].second]+=3;
		dfs(count+1,per*(map[count].l));
		score[map[count].second]-=3;
	}
	
	static class Pair{
		String name;
		int index;
		public Pair(String name, int index) {
			super();
			this.name = name;
			this.index = index;
		}
	}
	static class Percent{
		int first,second; // 경기 치루는 국가 인덱스
		double w,d,l;
		public Percent(int first, int second, double w, double d, double l) {
			super();
			this.first = first;
			this.second = second;
			this.w = w;
			this.d = d;
			this.l = l;
		}
		
		@Override
		public String toString() {
			return super.toString();
		}
	}
	static class Pair2 implements Comparable<Pair2>{
		int score;
		int index;
		
		public Pair2(int score, int index) {
			super();
			this.score = score;
			this.index = index;
		}

		@Override
		public int compareTo(Pair2 o) {
			return o.score - this.score;
		}
		
		
	}
}
