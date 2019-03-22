import java.util.*;
//ArrayList¸¦ È°¿ë
public class Q11718 {
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<>();
		while(a.hasNextLine()) {
			String input = a.nextLine();
			if(input.startsWith(" ")||input.endsWith(" ")||input.length()>100||input.isEmpty()) {
				break;
			}
			words.add(input);
		}
		for(int i=0;i<words.size();i++) {
			System.out.println(words.get(i));
		}
	}
}
