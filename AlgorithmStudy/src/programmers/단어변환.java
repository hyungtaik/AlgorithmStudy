package programmers;

public class 단어변환 {

	private static boolean[] visited;
	private static int answer;
	private static int len;
	public static int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        len = begin.length();
        
        boolean check = false;
        for(int i=0;i<words.length;i++) {
        	if(target.equals(words[i])) {
        		check = true; // 단어안에 target이 있음
        		break;
        	}
        }
        if(check) {
        	visited = new boolean[words.length];
        	for(int i=0;i<words.length;i++) {
        		int count = 0;
        		for(int j=0;j<len;j++) {
        			if(begin.charAt(j)!=words[i].charAt(j)) {
        				count++;
        			}
        			if(count>1) {
        				break;
        			}
        		}
        		if(count == 1) {
        			visited[i] = true;
        			dfs(words[i],target,words,1);
        			visited[i] = false;
        		}
        	}
        }else {
        	answer = 0;
        }
        
        return answer;
    }
	static void dfs(String begin,String target, String[] words,int cnt) {
		if(begin.equals(target)) {
			answer = Math.min(answer,cnt);
			return;
		}
		
		for(int i=0;i<words.length;i++) {
    		if(visited[i]) continue;
			int count = 0;
    		for(int j=0;j<len;j++) {
    			if(begin.charAt(j)!=words[i].charAt(j)) {
    				count++;
    			}
    			if(count>1) {
    				break;
    			}
    		}
    		if(count == 1) {
    			visited[i] = true;
    			dfs(words[i],target,words,cnt+1);
    			visited[i] = false;
    		}
    	}
	}
}
