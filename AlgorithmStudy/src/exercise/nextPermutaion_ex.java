import java.util.Arrays;

public class nextPermutaion_ex {

	// 넥퍼를 이용한 {1,4,2,6} 순열 구현
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = {1,4,2,6};
		int[] pick = {0,1,1,1};
		Arrays.sort(numbers);
		
		do { // 조합
			for(int i=0;i<pick.length;i++) {
				if(pick[i] == 1) {
					System.out.print(numbers[i]+" ");
				}
			}System.out.println();
		}while(np(pick));
		
		do { // 순열
			System.out.println(Arrays.toString(numbers));
		}while(np(numbers));
	}
	
	static boolean np(int[] p) {
		int len = p.length;
		
		int i = len-1;
		while(i>0 && p[i-1]>=p[i] ) i--;
		if(i==0) return false;
		
		int j = len-1;
		while(p[i-1]>=p[j])j--;
		
		int temp = p[i-1];
		p[i-1] = p[j];
		p[j] = temp;
		
		j = len - 1;
		while(i<j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;j--;
		}
		
		return true;
		
	}

}
