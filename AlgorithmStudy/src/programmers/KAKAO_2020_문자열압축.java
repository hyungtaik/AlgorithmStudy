package programmers;

import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category 문자열 비교 (시뮬레이션)
 * 
 * @see 프로그래머스 : 문자열 압축
 * 
 * @since 2020-08-29
 * 
 */

public class KAKAO_2020_문자열압축 {

	public static void main(String[] args) {
		KAKAO_2020_문자열압축 Solution = new KAKAO_2020_문자열압축();
		System.out.println(Solution.solution("aabbaccc"));
	}

	private int min;
	private LinkedList<Pair> q;
	// 압축할 문자열 s가 매개변수로 주어질 때,
	// 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록 solution
	// 함수를 완성해주세요.
	public int solution(String s) {
		int answer = 0;
        int len = s.length();
        int pivot = 1; // 잘라지는 단위 개수
        q = new LinkedList<Pair>();
        min = Integer.MAX_VALUE;
        
        // 절반 이상이 넘어가면 압축하는 의미가 사라짐(Pair가 안맞음)
        while(pivot<=len/2) {
        	String temp = s.substring(0, pivot);	// 처음 단위로 자르기
        	int count = 1;						 	// 반복 갯수 세기
        	for(int i=pivot;i<len;i+=pivot) {		// 단위대로 이동
        		
        		if(temp.equals(s.substring(i,i+pivot))) {	// 같으면 count+1
        			count++;
        		}else {										// 다르면 이전 단위로 자른 문자를 q에 저장
        			q.add(new Pair(temp,count));
        			temp = s.substring(i,i+pivot);			// 새로운 문자 단위를 할당
        			count = 1;
        		}
        		
        		if(i+pivot+pivot > len) {			// 다음 체크할 문자열이 범위를 벗어나면 일단 q에 저장
        			q.add(new Pair(temp,count));	// 마지막인 경우 or 단위의 갯수가 안맞을 경우
        			break;
        		}
        	}
        	
        	// 단위의 갯수가 안맞아서 남아있는 것이 있을 경우 결과 String에 나머지를 붙여주기 위해 q에 저장
        	int remain = len%pivot;
        	if(remain!=0) {
        		q.add(new Pair(s.substring(len-remain,len),1));
        	}
        	
        	String result = "";
        	while(!q.isEmpty()) {	// q에서 꺼내서 결과 String에 차례대로 붙여주며 저장
        		Pair pair = q.poll();
        		if(pair.count>1) {
        			result+=pair.count+pair.input;
        		}else {
        			result+=pair.input;
        		}
        	}
        	min = Math.min(min,result.length());
        	pivot++;	// pivot 증가
        }
        
        if(min == Integer.MAX_VALUE) {
        	answer = len;
        } else {
        	answer = min;
        }
        return answer;
    }
	
	private class Pair{
		String input;
		int count;
		
		public Pair(String input, int count) {
			super();
			this.input = input;
			this.count = count;
		}
		
	}
}
