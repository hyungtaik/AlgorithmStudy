package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 5014번: 스타트링크 <br>
 * 		메모리: 53832	 KB <br>
 * 		시간: 172 ms
 * @since 2020-08-26
 * 
 */
public class boj_5014 {

	private static int F;
	private static int S;
	private static int G;
	private static int U;
	private static int D;
	private static boolean[] checked;
	private static LinkedList<Integer> list;

	public static void main(String[] args) throws Exception{
		
		// F층 건물 - 스타트링크는 G층
		// 강호는 S층 -> G층
		// U , D 버튼만 존재 - 해당하는 층이 없을때는 이동 x
		// 버튼을 최소 몇 번 눌러야 G층에 도착하는지 - 갈 수 없다면 use the stairs 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] FSGUD = input.split(" ");
		
		F = Integer.parseInt(FSGUD[0]);
		S = Integer.parseInt(FSGUD[1]);
		G = Integer.parseInt(FSGUD[2]);
		U = Integer.parseInt(FSGUD[3]);
		D = Integer.parseInt(FSGUD[4]);
		
		checked = new boolean[F+1];
		checked[0] = true;
		checked[S] = true;
		list = new LinkedList<Integer>();
		list.add(S);
		int count = 0;
		boolean check = false;
		
		if(S == G) {
			System.out.println(count);
		}else {
			L:while(!list.isEmpty()) {
				int len = list.size();
				count++;
				for(int i=0;i<len;i++) {
					int temp = list.poll();
					checked[temp] = true;
					int upTemp = temp + U;
					
					if(upTemp == G) {
						check = true;
						break L;
					}else if(upTemp <F && !checked[upTemp]) {
						list.add(upTemp);
						checked[upTemp] = true;
					}
					
					int downTemp = temp - D;
					if(downTemp == G) {
						check = true;
						break L;
					}else if(downTemp > 0 && !checked[downTemp]) {
						list.add(downTemp);
						checked[downTemp] = true;
					}
				}
			}
			if(check) {
				System.out.println(count);
			}else {
				System.out.println("use the stairs");
			}
		}
		
	}

}
