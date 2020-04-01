package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class 해시_완주하지못한선수 {

	class Solution {
		public String solution(String[] participant, String[] completion) {
			String answer = "";
			HashMap<String,Integer> map = new HashMap<>();
			for (int i = 0; i < participant.length; i++) {
				String key = participant[i];
				if(map.containsKey(key)) {
					map.put(key,map.get(key)+1);
				}else {
					map.put(key, 1);
				}
			}
			for(int i=0;i<completion.length;i++) {
				String temp = completion[i];
				map.put(temp,map.get(temp)-1);
			}
			for(String str:map.keySet()) {
				if(map.get(str)!=0) {
					answer = str;
					break;
				}
			}
			return answer;
		}
	}

	class Solution2{
	    public String solution(String[] participant, String[] completion) {
	        String answer = "";
	        HashMap<String, Integer> hm = new HashMap<>();
	        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
	        for (String player : completion) hm.put(player, hm.get(player) - 1);

	        for (String key : hm.keySet()) {
	            if (hm.get(key) != 0){
	                answer = key;
	            }
	        }
	        return answer;
	    }
	}

}
