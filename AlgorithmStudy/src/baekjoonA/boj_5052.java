package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category Ʈ����
 * 
 * @see ���� 5052��: ��ȭ��ȣ ��� <br>
 *      �޸�: 110732 KB <br>
 *      �ð�: 392 ms
 * @since 2020-10-25
 * 
 */

public class boj_5052 {
	private static int TC;
	private static int N;
	private static Trie root;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=TC;tc++) {
			N = Integer.parseInt(br.readLine());
			
			root = new Trie();
			String[] input = new String[N];
			for(int i=0;i<N;i++) {
				input[i] = br.readLine();
				insert(input[i]);
			}
			
			boolean flag = true;
			for(int i=0;i<N;i++) {
				if(!search(input[i])) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
	static void insert(String input) {
		Trie trie = root;
		int len = input.length();
		
		for(int i=0;i<len;i++) {
			int index = input.charAt(i)-'0';
			
			if(trie.node[index] == null) {
				trie.node[index] = new Trie();
			}
			trie = trie.node[index];
		}
		trie.isEnd = true;
	}
	
	static boolean search(String input) {
		Trie trie = root;
		int len = input.length();
		
		for(int i=0;i<len;i++) {
			int index = input.charAt(i)-'0';
			
			if(trie.isEnd) {
				return false;
			}
			trie = trie.node[index];
		}
		return true;
	}
	
	static class Trie{
		boolean isEnd;
		Trie node[];
		
		public Trie() {
			isEnd = false;
			node = new Trie[10]; // �ִ� 10��
			for(int i=0;i<10;i++) {
				node[i] = null;
			}
		}
		
		
	}

}
