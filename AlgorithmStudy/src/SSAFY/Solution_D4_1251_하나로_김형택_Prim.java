package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_Prim {

	private static int TC;
	private static int N;
	private static Pair[] map;
	private static double E;
	private static boolean[] visited;
	private static double[][] visit;
	private static double result;

	public static void main(String[] args) throws Exception{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new Pair[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				long x = Long.parseLong(st.nextToken());
				map[i] = new Pair(x,0);
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				long y = Long.parseLong(st.nextToken());
				map[i].y = y; 
			}
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			visited = new boolean[1000];
			visit = new double[N][N];
			
			for(int i=0;i<N;i++) {
				Pair p = map[i];
				for(int j=i+1;j<N;j++) {
					Pair t = map[j];
					visit[i][j] = visit[j][i] = E*(Math.pow(p.x - t.x,2)+Math.pow(p.y - t.y,2));
				}
			}
			result = 0.0;
			solve();
			System.out.println("#"+tc+" "+Math.round(result));
		}
	}
	
	static void solve() {
        ArrayList<Integer> selected = new ArrayList<Integer>(N);
        int index;
        double min;
        selected.add(0);
        visited[0] = true;
        for(int i = 0; i < N-1; i++) {
            min = Double.MAX_VALUE;
            index = -1;
            for(Integer v : selected) {
                for(int j = 0 ; j  < N; j++) { 
                    if(!visited[j] && visit[v][j] != 0 && visit[v][j] < min) {
                        min = visit[v][j];
                        index = j;
                    }
                }
            }
             
            if(index != -1) {
                result += min;         
                selected.add(index);    
                visited[index] = true; 
            }
        }
         
        Arrays.fill(visited, false);
	}
	
	
	static class Pair{
		long x,y;

		public Pair(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
