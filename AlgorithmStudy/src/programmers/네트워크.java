package programmers;

public class 네트워크 {

	public static void main(String[] args) {
		int[][]	com = {{1,1,0},{1,1,0},{0,0,1}};
		System.out.println(solution(3,com));
	}
	static boolean[][] visited;
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n][n];
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		if(computers[i][j]==1 && !visited[i][j]) {
        			dfs(n,computers,i,j);
        			answer++;
        		}
        	}
        }
        return answer;
    }
	static void dfs(int n,int[][] computers, int x,int y) {
		visited[x][y] = true;
		for(int i=0;i<n;i++) {
			if(i==y || visited[x][i]) continue;
			if(computers[x][i]==1) {
				visited[x][i] = true;
				dfs(n,computers,i,i);
			}
		}
	}
	
}
