package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 
 * @author TAEK
 * @category 트라이
 * 
 * @see 백준 14725번: 개미굴 <br>
 *      메모리: 17692 KB <br>
 *      시간: 200 ms
 * @since 2020-10-27
 * 
 */

public class boj_14725 {
	private static int N;
	private static int K;
	private static ArrayList<String> list;
	private static Trie root;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		root = new Trie();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<String>();
			for(int j=0;j<K;j++) {
				list.add(st.nextToken());
			}
			Trie trie = root;
			for(int j=0;j<list.size();j++) {
				String input = list.get(j);
				if(!trie.node.containsKey(input)) {
					trie.node.put(input, new Trie());
				}
				trie.isEnd = false;
				trie = trie.node.get(input);
			}
			trie.isEnd = true;
		}
		
		Trie trie = root;
		sb = new StringBuilder();
		dfs(trie,0);
		System.out.println(sb.toString());
	}
	static void dfs(Trie trie,int floor) {
		if(trie.isEnd) {
			return;
		}
		
		for(String key:trie.node.keySet()) {
			for(int j=0;j<floor;j++) {
				sb.append("--");
			}
			sb.append(key);
			sb.append("\n");
			dfs(trie.node.get(key),floor+1);
		}
	}
	static class Trie{
		boolean isEnd;
		TreeMap<String, Trie> node;
		
		public Trie() {
			isEnd = false;
			this.node = new TreeMap<>();;
		}
	}

}
