import java.io.*;
import java.util.Scanner;

public class test2_2{

	//상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int[][] arr;
    static int[][] trr; //count
    static int[][] odd;
    public static int maxNum(int x, int y, int n) {
        
    	// 경로찾은것
    	if(trr[x][y] != -1) {
        	return trr[x][y];
        }
        
        trr[x][y] = 1;
        for (int i = 0; i < dx.length; i++) {
            int mvx = dx[i] + x;
            int mvy = dy[i] + y;
            if(mvx < 0 || mvx >= n || mvy < 0 || mvy >= n) {
            	continue;
            }else {
            	
            	if(arr[mvx][mvy] > arr[x][y]) {
            		trr[x][y] = Math.max(trr[x][y], maxNum(mvx, mvy,n) + 1);
                }
            }
        }
        return trr[x][y];
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int s = 1; s <= tc; s++) {
            int n = sc.nextInt();
            int result = 0;

            arr = new int[n][n];
            trr = new int[n][n];

            for (int i = 0; i < n; i++) {
            	for(int j = 0; j < n; j++) {
            		arr[i][j] = sc.nextInt();
                    trr[i][j] = -1;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result = Math.max(maxNum(i, j, n), result);
                }
            }

            System.out.println("#" + s + " " + result);
        }
    }


    
}