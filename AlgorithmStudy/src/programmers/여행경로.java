package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 여행경로 {

	public static void main(String[] args) {
		String[][] ticket = {{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},{"ATL","ICN"},{"ATL","SFO"}};
		System.out.println(Arrays.toString(solution(ticket)));
	}
	private static ArrayList<Pair> list;
	private static boolean[] visited;
	private static int len;
	private static String[] numbers;
	private static String[] answer;
	public static String[] solution(String[][] tickets) {
        list = new ArrayList<Pair>();
        for(int i=0;i<tickets.length;i++) {
        	list.add(new Pair(tickets[i][0],tickets[i][1]));
        }
        Collections.sort(list);
        len = tickets.length;
        answer = new String[len+1];
        for(int i=0;i<list.size();i++) {
    		Pair p = list.get(i);
        	if(p.start.equals("ICN")) {
        		numbers = new String[len+1];
        		numbers[0] = "ICN";
        		visited = new boolean[len];
        		visited[i] = true;
        		dfs(i,1);
        		visited[i] = false;
        	}
        }
        
        return answer;
    }
	static void dfs(int index,int count) {
		if(answer[len]!=null) return;
		if(count == len ) {
			if(answer[len]==null) {
				numbers[len] = list.get(index).end;
				for(int i=0;i<=len;i++) {
					answer[i] = numbers[i];
				}
			}
			return;
		}
		for(int i=0;i<list.size();i++) {
    		Pair p = list.get(i);
        	if(p.start.equals(list.get(index).end)) {
        		if(visited[i]) continue;
        		visited[i] = true;
        		numbers[count] = p.start;
        		dfs(i,count+1);
        		visited[i] = false;
        	}
        }
	}
	
	
	static class Pair implements Comparable<Pair>{
		String start,end;

		public Pair(String start, String end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.start.equals(o.start)) {
				return this.end.compareTo(o.end);
			}
			return this.start.compareTo(o.start);
		}
	}
}
