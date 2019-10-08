package programmers;

/**
 * Created By ds on 21/09/2019.
 * 10:20
 */

/**
 * 주어진 메일 주소 중, 규격에 맞는 주소는 몇 개인지 구하려 합니다.
 * 규격에 맞는 메일은 이름@도메인이름.탑레벨도메인 형시이다.
 * 이름은 영문 소문자와 . 로만 구성된다.
 * 도메인이름은 영문 소문자로만 구성된다.
 * 탑 레벨 도메인은 com, net, org 중 하나이다.
 * <p>
 * 규격에 맞는 주소는 몇개인지 반환해라
 */

import java.util.*;

public class dp01 {

 
	 public static void main(String... args) {
	        String[] str1 = {"d@co@m.com", "a@abc.com", "b@def.com", "c@ghi.net"};
	        String[] str2 = {"abc.def@x.com", "abc", "abc@defx", "abc@defx.xyz"};
	        System.out.println(solution(str1));
	        System.out.println(solution(str2));
	    }

    public static int solution(String[] emails) {
        int answer = 0;
        for (int i=0;i<emails.length;i++) {
            String[] temp = emails[i].split("@");
            
            if(temp.length != 2) {
            		System.out.println("pass  "+temp[0]);
            		continue;
            }
            String name = temp[0];

            boolean tf = false;
            if(check(name)) {
                tf = true;
            }

            String[] temp2 = temp[1].split("\\.");
            if(temp2.length != 2) continue;

            String doName = temp2[0];
            boolean tf2 = false;
            if(check2(doName)) {
                tf2 = true;
            }

            String topDo = temp2[1];
            boolean tf3 = false;
            if(topDo.equals("com")||topDo.equals("net")||topDo.equals("org")) {
            		tf3=true;
            }

            if (tf && tf2 && tf3) answer++;
        }
        return answer;
    }

   public static boolean check( String temp) {
    		boolean tf=true;
    		for(int i=0;i<temp.length();i++) {
    
    			if(Character.isLowerCase(temp.charAt(i))||(temp.charAt(i))=='.') {
    				
    			}else {
    				tf=false;
    			}
    			
    		}
    		System.out.println("check"+tf);
        return tf;
    }

   public static boolean check2(String temp) {
		boolean tf=true;
		for(int i=0;i<temp.length();i++) {

			if(!Character.isLowerCase(temp.charAt(i))) {
				tf=false;
			}
			
		}
		System.out.println("check2"+tf);
		return tf;
    }
}