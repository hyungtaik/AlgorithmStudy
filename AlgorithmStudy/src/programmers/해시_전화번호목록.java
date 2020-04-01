package programmers;

public class 해시_전화번호목록 {

	class Solution {
		public boolean solution(String[] phone_book) {
			boolean answer = true;
			int len = phone_book.length;
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (phone_book[i].equals(phone_book[j]))
						continue;
					if (phone_book[i].length() <= phone_book[j].length()
							&& phone_book[j].substring(0, phone_book[i].length()).equals(phone_book[i])) {
						answer = false;
						return false;
					}
				}
			}
			return answer;
		}
	}

	class Solution2 {
		public boolean solution(String[] phoneBook) {
			for (int i = 0; i < phoneBook.length - 1; i++) {
				for (int j = i + 1; j < phoneBook.length; j++) {
					if (phoneBook[i].startsWith(phoneBook[j])) {
						return false;
					}
					if (phoneBook[j].startsWith(phoneBook[i])) {
						return false;
					}
				}
			}
			return true;
		}
	}

}
