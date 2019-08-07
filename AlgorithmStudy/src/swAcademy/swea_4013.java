package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_4013 {
	static int K; //회전수
	static int[][] tooth;
	
	static class Pair{
		private int num,rotate;
		public Pair(int num, int rotate) {
			this.num = num;
			this.rotate = rotate;
		}
	}
	
	static void compare(int num,int rDir) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(num,rDir));
		boolean[] checkList = new boolean[4];
		checkList[num] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			if(p.num>0) {
				//왼쪽 검사
				if((checkList[p.num-1]!=true) && (tooth[p.num][6] != tooth[p.num-1][2])) {
					q.add(new Pair(p.num-1,p.rotate*-1));
					checkList[p.num-1] = true;
				}
			}
			if(p.num<3) {
				//오른쪽 검사
				if((checkList[p.num+1]!=true) && (tooth[p.num][2] != tooth[p.num+1][6])) {
					q.add(new Pair(p.num+1,p.rotate*-1));
					checkList[p.num+1] = true;
				}
			}
			rotateT(p.num,p.rotate);
		}
	}
	
	static void rotateT(int num,int rDir){
//		System.out.println((num+1)+"에서 회전한다");
		//시계방향
		if(rDir>0) {
			int temp = tooth[num][7];
			for(int j=7;j>0;j--) {
				tooth[num][j] = tooth[num][j-1];
			}
			tooth[num][0] = temp;
			
		}
		//반시계 방향
		else {
			int temp = tooth[num][0];
			for(int j=0;j<7;j++) {
				tooth[num][j] = tooth[num][j+1];
			}
			tooth[num][7] = temp;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int token = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=token;tc++) {
			K = Integer.parseInt(br.readLine());
			tooth = new int[4][8];
//			rotate = new int[K+1];
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					tooth[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int rDir = Integer.parseInt(st.nextToken());
//				System.out.println(num+"__________"+rDir);
				compare(num-1,rDir);
			}
	
			int score = (tooth[0][0]*1) + (tooth[1][0]*2) + (tooth[2][0]*4) + (tooth[3][0]*8);
			
			System.out.println("#"+tc+" "+score);
			
		}

	}

}
