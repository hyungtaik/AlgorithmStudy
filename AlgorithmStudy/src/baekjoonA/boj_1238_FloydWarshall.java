package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class boj_1238_FloydWarshall {
    public static int size;
    public static int[][] distance;
    public static int student;
    
    public static int INF = 1000000;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        size = Integer.parseInt(st.nextToken());
        int lineCount = Integer.parseInt(st.nextToken());
        student = Integer.parseInt(st.nextToken());
        
        distance = new int[size+1][size+1];
        
        for(int i=1; i <= size; i++) {
            for(int j=1; j <= size; j++) {
                if(i==j) continue;
                
                distance[i][j] = INF;
            }
        }
        while(lineCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            distance[start][end] = cost;
        }
        
        
        
        floydWarshall();
        
        int result = 0;
        
        for(int i=1; i <= size; i++) {
            result = Math.max(result, distance[i][student] + distance[student][i]);
        }
        
        System.out.println(result);
    }
    
    public static void floydWarshall() {
        for(int k=1; k <= size; k++) {
            for(int i=1; i <= size; i++) {
                for(int j=1; j <= size; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }
}
