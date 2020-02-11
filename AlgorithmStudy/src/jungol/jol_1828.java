import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class jol_1828 {

	private static int N;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] ref = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ref[i] = new Point(a, b);
		}
		int count = 1;
		Arrays.sort(ref);
		
		Point p = ref[0]; // 초기값 저장
		
		for (int i = 1; i < N; i++) {
			Point temp = ref[i];
            if (temp.x <= p.y) {
                if (temp.y < p.y) {
                    p.y= temp.y;
                }
                continue;
            } else {
                p.y = temp.y;
                count++;
            }
        }
		System.out.println(count);
	}

	static class Point implements Comparable<Point> {
		int x, y;

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y+ "]";
		}

	}
}
