package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 1347번: 미로 만들기 <br>
 * 		메모리: 13112 KB <br>
 * 		시간: 92 ms
 * @since 2020-08-17
 * 
 */

public class boj_1347 {

	private static int N;
	
	// 동 -> 남 -> 서 -> 북 
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String input = st.nextToken();
		
		// 0: 동 , 1: 남, 2: 서, 3: 북 
		Person hong = new Person(0,0,1);
		LinkedList<Person> list = new LinkedList<>();
		list.add(new Person(0,0,0));
		int maxX = 0;
		int maxY = 0;
		int minX = 0;
		int minY = 0;
		
		for(int i=0;i<N;i++) {
			char command = input.charAt(i);
			switch(command) {
			case 'R':
				hong.dir = (hong.dir + 1) % 4;
				break;
			case 'L':
				hong.dir = (hong.dir - 1);
				if(hong.dir<0) hong.dir = 3;
				break;
			case 'F':
				hong.x = hong.x + dx[hong.dir];
				maxX = Math.max(maxX, hong.x);
				minX = Math.min(minX, hong.x);
				
				hong.y = hong.y + dy[hong.dir];
				maxY = Math.max(maxY, hong.y);
				minY = Math.min(minY, hong.y);
				
				list.add(new Person(hong.x,hong.y,0));
				
				break;
			}
		}
		int row = Math.abs(maxX - minX)+1;
		int col = Math.abs(maxY - minY)+1;
		
		char[][] map = new char[row][col];
		
		while(!list.isEmpty()) {
			Person visit = list.poll();
			int x = visit.x + Math.abs(minX);
			int y = visit.y + Math.abs(minY);
			map[x][y] = '.';
		}
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j] != '.') {
					map[i][j] = '#';
				}
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
class Person{
	int x,y;
	int dir;
	public Person(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	
}