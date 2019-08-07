import java.util.Scanner;
import java.util.LinkedList;

public class sds_q2 {
	//상, 하, 좌, 우
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	public static String maxBFS(int[][] graph, int i, int j, int n) {
		//
		LinkedList<Integer> qx = new LinkedList<Integer>();
		LinkedList<Integer> qy = new LinkedList<Integer>();
		LinkedList<Integer> cost = new LinkedList<Integer>();
		LinkedList<Integer> odd = new LinkedList<Integer>();
		System.out.println("**************************");
		qx.add(i);
		qy.add(j);
		cost.add(1);
		odd.add(0);
		if(graph[i][j]%2 != 0) {
			odd.add(1);
		}
		boolean[][] trr = new boolean[n][n];
		int minOdd = n*n;
		int maxCost = 0;
		int h=0;
		while (!qx.isEmpty()) {
//			System.out.println(h);
			h++;
			int x = qx.pollLast();
			int y = qy.pollLast();
			trr[x][y]=true;
			int co = cost.pollLast();
			int od = odd.poll();
			
			int count=0;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx < 0 || nx >= graph.length || ny < 0 || ny >= graph.length) {
					continue;
				} else {
					if(trr[nx][ny]==false) {
						qx.add(nx);
						qy.add(ny);
						cost.add(co + 1);
						odd.add(od);
						if(graph[nx][ny]%2 !=0) {
							odd.add(od+1);
						}
					}
				}
			}
			if(x==n-1 && y==n-1) {
				System.out.println("여기");
				if (od < minOdd) {
					minOdd = od;
					maxCost = co;
				}
			}
		}
		System.out.println(minOdd);
		String result = Integer.toString(minOdd)+","+Integer.toString(maxCost);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) throws NumberFormatException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();
		for (int s = 1; s <= tc; s++) {
			int n = sc.nextInt();
			int[][] graph = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					graph[i][j] = sc.nextInt();
				}
			}
			
			String str = maxBFS(graph,0,0,n);
			String[] max = str.split(",");
			int odd = Integer.parseInt(max[1])-Integer.parseInt(max[0]);

//			int max = 0;
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					int num = maxBFS(graph, i, j);
//					if (num > max)
//						max = num;
//				}
//			}
			System.out.println("#" + (s) + " " + max[0]+" "+ odd);
		}
		
	}
	

}
