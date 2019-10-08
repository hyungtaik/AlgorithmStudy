package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class naver01 {
	public static String[] solution(String[] record) {
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<record.length;i++) {
			if(record[i] == "DELETE") {
				list.remove(list.size()-1);
			}else if(record[i] == "SAVE") {
				set.addAll(list);
			}else {
				String[] temp = record[i].split(" ");
				if(temp[0].equals("RECEIVE")) {
					list.add(temp[1]);
				}
			}
		}
		String[] answer = set.toArray(new String[set.size()]);
		String[] temp = list.toArray(new String[list.size()]);
		for(int i=0;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
		System.out.println(answer.length);
		return answer;
	}

	public static void main(String... args) {
		String[] str1 = { "RECEIVE abcd@naver.com", "RECEIVE zzkn@naver.com", "DELETE", "RECEIVE qwerty@naver.com",
				"SAVE", "RECEIVE QwerTY@naver.com" };
		String[] str2 = { "RECEIVE abcd@naver.com", "RECEIVE zzkn@naver.com", "DELETE", "RECEIVE qwerty@naver.com",
				"SAVE", "SAVE", "DELETE", "RECEIVE QwerTY@naver.com", "SAVE" };
//		solution(str1);
		System.out.println(solution(str1));
//		System.out.println(solution(str2));
	}

}
