package programmers;

public class SkillCheckTestLevel2_1 {

	public static void main(String[] args) {
	}
	 public int solution(int n) {
	        int ans = 0;
	        while(true) {
	        	if(n == 0) break;
	        	if(n%2 == 1) {
	        		n--;
	        		ans++;
	        	}else {
	        		n/=2;
	        	}
	        }
	        return ans;
	    }
}
