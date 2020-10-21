package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category �ùķ��̼�
 * 
 * @see ���� 20055��: �����̾� ��Ʈ ���� �κ� <br>
 *      �޸�: 15080 KB <br>
 *      �ð�: 252 ms
 * @since 2020-10-21
 * 
 */

public class boj_20055 {

	private static int N;
	private static int K;
	private static int[] rail;
	private static boolean[] obj;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		rail = new int[2*N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<2*N;i++) {
			rail[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());;
	}
	
	static int solve() {
		// ���� ���� ����
		obj = new boolean[N];
		int cnt = 1;
		while(true) {
			// ȸ��
			rotate();
			// �̵�
			move();
			// ž��
			boarding();
			// ������ 0�ΰ� üũ
			if(check()) {
				break;
			}
			cnt++;
		}
		return cnt;
	}
	static void print() {
		for(int i=0;i<2*N;i++) {
			System.out.print(rail[i]+" ");
		}System.out.println();
		
		for(int i=0;i<N;i++) {
			System.out.print(obj[i]+" ");
		}System.out.println();
	}
	
	static boolean check() {
		int count = 0;
		for(int i=0;i<2*N;i++) {
			if(rail[i] == 0) count++;
		}
		if(count >= K) return true;
		else return false;
	}
	
	static void boarding() {
		if(rail[0]>0 && !obj[0]) {
			obj[0] = true;
			rail[0] --;
		}
	}
	
	static void move() {
		for(int i=N-2;i>=0;i--) {
			if(obj[i]) {
				if(!obj[i+1] && rail[i+1]>0) {
					obj[i+1] = true;
					obj[i] = false;
					rail[i+1]--;
					if((i+1) == (N-1)) {
						obj[i+1] = false;
					}
				}
			}
		}
	}
	
	static void rotate() {
		int temp = rail[2*N -1];
		for(int i=2*N-1;i>=1;i--) {
			rail[i] = rail[i-1];
		}
		rail[0] = temp;
		
		for(int i=N-1;i>=1;i--) {
			obj[i] = obj[i-1];
		}
		obj[0] = false;
		if(obj[N-1]) obj[N-1] = false; 
	}

}
