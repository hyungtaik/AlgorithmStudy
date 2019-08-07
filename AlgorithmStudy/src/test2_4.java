import java.io.*;
import java.util.Scanner;
class oddCount {
    int odd;
    int count;
    oddCount(int odd, int count) {
        this.odd = odd;
        this.count = count;
    }
}
public class test2_4{

	//상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int[][] arr;
    static oddCount ans;
    static oddCount[][] oc;
    public static oddCount maxNum(int x, int y, int n) {
        
    	// 경로찾은것
    	
    	oc[x][y].count = 1;
    	if(arr[x][y]%2==1) {
    		oc[x][y].odd +=1;
		}
    	else {
    		oc[x][y].odd +=0;
    	}
    	if(x==n-1 && y==n-1) {
    		if(ans.odd<oc[x][y].odd) {
    			ans = oc[x][y];
    			
    		}
    			
        }
        for (int i = 0; i < dx.length; i++) {
            int mvx = dx[i] + x;
            int mvy = dy[i] + y;
            if(mvx < 0 || mvx >= n || mvy < 0 || mvy >= n) {
            	continue;
            }else {
            	if(oc[mvx][mvy].count == -1) {
            		oc[mvx][mvy].odd += oc[x][y].odd;
            		maxNum(mvx,mvy,n);
            	}
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int s = 1; s <= tc; s++) {
            int n = sc.nextInt();
            arr = new int[n][n];
            oc = new oddCount[n][n];
            ans = new oddCount(n*n, 1);
            for (int i = 0; i < n; i++) {
            	for(int j = 0; j < n; j++) {
            		arr[i][j] = sc.nextInt();
                    oc[i][j]= new oddCount(0,-1);
                }
            }
            
            oddCount result = new oddCount(0,0);
            result = maxNum(0,0,n);
            

            System.out.println("#" + s + " " + result.odd);
        }
    }


    
}