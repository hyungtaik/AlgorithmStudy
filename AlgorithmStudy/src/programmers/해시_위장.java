package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class 해시_위장 {
	// 조합문제
	private static HashMap<String, Integer> map;
	private static int answer;
	private static int size;
	private static boolean[] visited;
	private static int[] arr;
	class Solution {

		public int solution(String[][] clothes) {
			answer = 0;
			map = new HashMap<>();
			for (int i = 0; i < clothes.length; i++) {
				map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
			}
			arr = new int[map.size()];
			size = 0;

			for (String str : map.keySet()) arr[size++] = map.get(str);
			
			visited = new boolean[size];
			if (size == 30) {
				answer = all(30);
			} else {
				dfs(0, 0);
			}
			return answer;
		}
	}

	static int all(int num) {
		int result = 1;
		for(int i=0;i<num;i++) {
			result*=2;
		}
		return result-1;
	}

	static void dfs(int index, int count) {
		if (index == size) {
			if (count == 0)
				return;
			int sum = 1;
			for (int i = 0; i < size; i++) {
				if (visited[i]) {
					sum *= arr[i];
				}
			}
			answer += sum;
			return;
		}
		if (!visited[index]) {
			visited[index] = true;
			dfs(index + 1, count + 1);
			visited[index] = false;
		}
		dfs(index + 1, count);
	}

	class Solution2 {
	    public int solution(String[][] clothes) {
	        int answer = 1;
	        HashMap<String, Integer> map = new HashMap<>();
	        for(int i=0; i<clothes.length; i++){
	            String key = clothes[i][1];
	            if(!map.containsKey(key)) {
	                map.put(key, 1);
	            } else {
	                map.put(key, map.get(key) + 1);
	            }
	        }
	        Iterator<Integer> it = map.values().iterator();
	        while(it.hasNext()) {
	            answer *= it.next().intValue()+1;
	        }
	        return answer-1;
	    }
	}
	
}
