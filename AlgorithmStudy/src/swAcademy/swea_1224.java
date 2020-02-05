package swAcademy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class swea_1224 {
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for(int tc=1;tc<=10;tc++) {
        int N = Integer.parseInt(br.readLine()); 
        Stack<Character> stack = new Stack<Character>(); 
        String str = br.readLine();
        String temp = "";
       
        stack.push('(');
        for(int i=1;i<N;i++) {
            char ch = str.charAt(i); 
             
            if(0<=ch-'0'&&ch-'0'<=9) temp += ch; 
            else if(ch == ')') { // 괄호가 끝나면 
                while(stack.peek()!='(') temp += stack.pop();
                stack.pop(); // 여는 괄호 pop
            }
            else { // 연산자
                while(!stack.isEmpty() && getPrior(stack.peek(),true) >= getPrior(ch,false)) {
                    temp += stack.pop(); 
                }
                stack.push(ch); 
            }
        }
        while(!stack.isEmpty()) { temp += stack.pop(); }
         
        
        Stack<Integer> result = new Stack<Integer>();
        for(int i=0;i<temp.length();i++) {
            char op = temp.charAt(i);
            if(0<=op-'0'&&op-'0'<=9) result.push(op-'0');
            else {
                int n1 = result.pop();
                int n2 = result.pop();
                switch(op) {
                case '+': 
                    result.push(n2+n1);
                    break;
                case '-':
                    result.push(n2-n1);
                    break;
                case '*':
                    result.push(n2*n1);
                    break;
                case '/':
                    result.push(n2/n1);
                    break;
                }
            }
             
        }
        System.out.println("#"+tc+" "+result.peek());
        }
    }
     
    private static int getPrior(char ch, boolean isInStack) {
        if(ch=='*'||ch=='/') return 2;
        else if(ch=='+'||ch=='-') return 1;
        else return isInStack?0:3; 
    }
}
