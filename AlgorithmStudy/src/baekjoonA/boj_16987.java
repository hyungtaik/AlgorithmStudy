package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16987 {

	private static int N;
	private static int max;
	private static Egg[] egg;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		egg = new Egg[N];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int hp = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			egg[i] = new Egg(hp, weight);
		}

		solve(0,0);
		System.out.println(max);
	}

	// 칠 계란이 없는 경우
	static void solve(int index,int eggCount) {
		if (index == N) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (egg[i].hp <= 0) {
					count++;
				}
			}
			max = Math.max(max, count);
			return;
		}
		if(N-eggCount == 1 || egg[index].hp <= 0) {
			solve(index+1,eggCount);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (i == index)
				continue;
			if (egg[i].hp <= 0)
				continue;
			egg[index].hp -= egg[i].weight;
			egg[i].hp -= egg[index].weight;
			int temp = 0;
			if(egg[i].hp <= 0 ) temp++;
			if(egg[index].hp <= 0 ) temp++;
			
			solve(index + 1,eggCount+temp);
			egg[index].hp += egg[i].weight;
			egg[i].hp += egg[index].weight;
		}

	}

	static class Egg {
		int hp;
		int weight;

		public Egg(int hp, int weight) {
			super();
			this.hp = hp;
			this.weight = weight;
		}

	}

}
