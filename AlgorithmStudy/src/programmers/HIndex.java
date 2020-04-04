package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HIndex {
	public static void main(String[] args) {
		int[] c = {3,0,6,1,5};
		System.out.println(solution2(c));
	}
	
	public static int solution2(int[] citations) {
		int answer = 0;
		Arrays.sort(citations);
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<citations.length;i++) {
			list.add(citations[i]);
		}
		Collections.reverse(list);
		
		for(int i=0;i<list.size();i++) {
			citations[i] = list.get(i);
		}
		int pivot = citations[0];
		L:for(;pivot>=0;pivot--) {
			int count = 0;
			for(int i=0;i<list.size();i++) {
				if(citations[i]>=pivot) {
					count++;
				}else {
					break;
				}
			}
			if(count >= pivot) {
				if(list.size()-count <= pivot) {
					answer = pivot;
					break L;
				}
			}
		}
		return answer;
	}
	
	class Solution {
		public int solution(int[] citations) {
			int answer = 0;
			Arrays.sort(citations);
			ArrayList<Integer> list = new ArrayList<>(citations.length);
			Collections.reverse(list);
			
			for(int i=0;i<list.size();i++) {
				citations[i] = list.get(i);
			}
			int pivot = citations[0];
			for(;pivot>=0;pivot--) {
				int count = 0;
				for(int i=0;i<list.size();i++) {
					if(citations[i]>=pivot) {
						count++;
					}else {
						break;
					}
				}
				if(count == pivot) {
					answer = count;
					break;
				}
			}
			return answer;
		}
	}
}
