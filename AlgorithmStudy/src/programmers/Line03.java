package programmers;

import java.util.*;

public class Line03 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int people[][] = new int[N][2];
		Comparator<toilet> comp = new Comparator<toilet>() {
			public int compare(toilet first, toilet second) {
				if(first.go>second.go) {
					return 1;
				}else {
					return -1;
				}
			}
		};
		PriorityQueue<toilet> q = new PriorityQueue<>(comp);
		for(int i=0;i<N;i++) {
			int go = sc.nextInt();
			int back = sc.nextInt();
			q.add(new toilet(go,back));
		}
		int result=1;
		
		toilet t = q.remove();
		ArrayList <Integer> min=new ArrayList<Integer>();
		
		min.add(t.back);
		
		while(!q.isEmpty()) {
			toilet next = q.remove();
			if(t.back<=next.go) {
				if(min.get(0)<=next.go) {
					min.set(0, next.go);
					Collections.sort(min);
					continue;
				}
				t=next;
				continue;
			}else {
				min.add(next.back);
				Collections.sort(min);
				result++;
			}
		}
		System.out.println(result);
	}
}
class toilet{
	int go,back;
	toilet(int go,int back){
		this.go=go;
		this.back=back;
	}
}
