package programmers;

import java.util.ArrayList;

/**
 * 
 * @author TAEK
 * @category Trie
 * 
 * @see ���α׷��ӽ� : KAKAO BLIND RECRUITMENT ���� �˻� <br>
 * 
 * @since 2020-09-11
 * 
 */

public class KAKAO2020_����˻� {

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		Solution s = new Solution();
		int[] solution = s.solution(words, queries);
		for(int i=0;i<solution.length;i++) {
			System.out.println(solution[i]+" ");
		}
	}

	static class Solution {

		public int[] solution(String[] words, String[] queries) {
			int[] answer = new int[queries.length];
			
			// ���̻�(subffix)�� Ʈ���� ����
			Trie[] tries = new Trie[10001]; 
			// ���λ�(prefix)�� Ʈ���� ����
			Trie[] rtries = new Trie[10001];
			
			// words ���� �� �з�
			for(String word : words) {
				int size = word.length();
				// ���̿� �ش��ϴ� Ʈ���� �����
				try {
					tries[size].insert(word.toCharArray());
				}catch(Exception e) {
					tries[size] = new Trie();
					tries[size].insert(word.toCharArray());
				}
				
				// �ݴ� ���ڵ� Ʈ���� ����
				word = (new StringBuffer(word)).reverse().toString();
				try {
					rtries[size].insert(word.toCharArray());
				}catch(Exception e) {
					rtries[size] = new Trie();
					rtries[size].insert(word.toCharArray());
				}
			}
			
			// trie �̿��Ͽ� �ش� ������ �´� ������ ã��
			for(int i=0; i<queries.length; ++i) {
				String query = queries[i];
				if(query.indexOf('?')==0) {
					// prefix
					try {
						query = (new StringBuffer(query)).reverse().toString();
						answer[i] = rtries[query.length()].search(query.toCharArray());
					}
					catch(Exception e) {
						continue;
					}
				}
				
				else {
					// suffix
					try {
						answer[i] = tries[query.length()].search(query.toCharArray());
					}
					catch(Exception e) {
						continue;
					}
				}
			}
			return answer;
	    }
		// Trie Node
		class Trie {

			int count;
			Trie[] childList;

			Trie() {
				childList = new Trie[26];
				count = 0;
			}

			void insert(char[] word) {
				Trie current = this;
				for (char no : word) {
					++current.count;
					if (current.childList[no - 'a'] != null) {
						current = current.childList[no - 'a'];
					} else {
						current.childList[no - 'a'] = new Trie();
						current = current.childList[no - 'a'];
					}
				}
			}

			int search(char[] query) {
				Trie current = this;
				for (char no : query) {
					if (no == '?') {
						return current.count;
					}

					if (current.childList[no - 'a'] != null) {
						current = current.childList[no - 'a'];
					} else {
						return 0;
					}
				}
				return current.count;
			}

		}
	}
	
}
