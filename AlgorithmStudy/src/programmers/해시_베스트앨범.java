package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 해시_베스트앨범 {

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(solution(genres, plays)));
	}

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		Map<String, Integer> gmap = new HashMap<String, Integer>();
		ArrayList<Pair2> list = new ArrayList<Pair2>();
		for (int i = 0; i < genres.length; i++) {
			gmap.put(genres[i], gmap.getOrDefault(genres[i], 0) + plays[i]);
			list.add(new Pair2(genres[i], plays[i], i));
		}
		Collections.sort(list);
		List<Integer> valueSetList = new ArrayList<>(gmap.values());
		Collections.sort(valueSetList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		for (Integer integer : valueSetList) {
			for (String str : gmap.keySet()) {
				if (gmap.get(str) == integer) { // 하나의 장르에서 검색
					int count = 0;
					for (int i = 0; i < list.size(); i++) {
						if (count == 2)
							break;
						if (list.get(i).genre.equals(str)) {
							list2.add(list.get(i).code);
							count++;
						}
					}
				}
			}
		}
		answer = new int[list2.size()];
		for (int i = 0; i < list2.size(); i++) {
			answer[i] = list2.get(i);
		}
		return answer;
	}

	static class Pair2 implements Comparable<Pair2> {
		String genre;
		int play;
		int code;

		public Pair2(String genre, int play, int code) {
			super();
			this.genre = genre;
			this.play = play;
			this.code = code;
		}

		@Override
		public int compareTo(Pair2 o) {
			if (this.genre.equals(o.genre)) {
				if (this.play == o.play) {
					return this.code - o.code;
				} else {
					return o.play - this.play;
				}
			}
			return this.genre.compareTo(o.genre);
		}
	}
}
