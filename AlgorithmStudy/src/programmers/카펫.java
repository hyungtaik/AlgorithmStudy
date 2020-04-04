package programmers;

import java.util.ArrayList;

public class 카펫 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class Solution {
		private ArrayList<Integer> list;

		public int[] solution(int brown, int red) {
			int[] answer = {};
			answer = new int[2];
			int sum = brown + red;
			list = new ArrayList<Integer>();
			
			for(int i=1;i<=sum;i++) {
				if(sum%i==0) {
					list.add(i);
				}
			}
			int[] arr = new int[list.size()];
			for(int i=0;i<list.size();i++) {
				arr[i] = list.get(i);
			}
			int len = list.size();
			int first = 0;
			int second = 0;
			int index = len/2;
			int fdex = 0;
			int sdex=0;
			int go = 1;
			if(len%2 == 0) {
				first = arr[index];
				second = arr[index-1];
				fdex = index;
				sdex = index-1;
			}else {
				first = second = arr[index];
				fdex =sdex= index;
			}
			while(true) {
				if(first<=2 || second <=2) {
					first = arr[fdex+go];
					second = arr[fdex-go];
					go++;
					continue;
				}
				int inner = (first-2)*(second-2);
				if(inner == red) {
					answer[0] = first;
					answer[1] = second;
					break;
				}else {
					first = arr[fdex+go];
					second = arr[sdex-go];
					go++;
				}
			}
			
			return answer;
		}
	}
}
