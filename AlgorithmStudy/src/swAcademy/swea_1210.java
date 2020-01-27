package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1210 {
	static int tcase;
	static int result;
	static int[][] map; // ��ٸ� ��
	static int dx,dy; //������
	static boolean[][] checked;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1;tc<=10;tc++) {
			st = new StringTokenizer(br.readLine());
			tcase = Integer.parseInt(st.nextToken());
			result = 0; // ��� �ʱ�ȭ
			map = new int[100][100]; // ��ٸ� �ʱ�ȭ
			dx=0; dy=0; // ������ �ʱ�ȭ
			checked = new boolean[100][100]; // �湮 �� �ʱ�ȭ
			
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						checked[i][j] = true;
					}
					
					if(map[i][j]==2) {
						dx = i; // ��
						dy = j; // ��
					}
				}
			}
			solve(dx-1,dy);
			System.out.println("#"+tcase+" "+result);
		}
	}
	
	static void solve(int x,int y) {
		checked[x][y] = false; // �湮�� üũ
		if(x == 0) { //���� ��, �� ���� ����
			result = y;
			return;
		}
		if((y-1>=0) && checked[x][y-1]==true) { // ���� Ž��
			solve(x,y-1);
		}
		else if((y+1 < 100) && checked[x][y+1]==true) { // ������ Ž��
			solve(x,y+1);
		}else { // �׿� ���� �ö� Ž��
			solve(x-1,y);
		}
		return;
	}
}
