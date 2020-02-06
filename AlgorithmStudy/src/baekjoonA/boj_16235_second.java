package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16235_second {

	static int N, M, K; // ��ũ��, ���� ����, �������
	static PriorityQueue<Tree> tree; // ���� ���� - ����

	static int[][] robot; // �κ��� �Ѹ��� ��� ����
	static int[][] feed; // ��� ����

	static int count;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		tree = new PriorityQueue<Tree>();
		robot = new int[N][N];
		feed = new int[N][N];
		count = 0;
		for (int i = 0; i < N; i++) { // �ܿ￡ �߰��Ǵ� ���
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				robot[i][j] = Integer.parseInt(st.nextToken());
				feed[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			tree.add(new Tree(x, y, z, 1));
		}
		for (int i = 0; i < K; i++) {
			solve(i);
		}
		System.out.println(tree.size());
	}

	static void solve(int index) {
		ArrayList<Tree> deadT = new ArrayList<Tree>();
		ArrayList<Tree> plusT = new ArrayList<Tree>();

		PriorityQueue<Tree> temp = new PriorityQueue<Tree>();

		// ��
		int tsize = tree.size();
		for (int i = 0; i < tsize; i++) {
			Tree t = tree.poll();
			if (feed[t.x][t.y] >= t.age) {
				feed[t.x][t.y] -= t.age; // ��� ����
				t.age++; // ���� ����
				temp.add(t);
				if (t.age % 5 == 0) {
					plusT.add(t);
				}
			} else {
				deadT.add(t);
			}
		}
		tree = new PriorityQueue<>(temp);

		// ����
		for (Tree t : deadT) {
			feed[t.x][t.y] += t.age / 2;
		}
		deadT.clear();

		// ����
		for (Tree t : plusT) {
			for (int i = 0; i < 8; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
					continue;
				tree.add(new Tree(nx, ny, 1, 1));
			}
		}

		if (index == K - 1)

		{
			return;
		}

		
		// �ܿ�
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (robot[i][j] == 0)
					continue;
				feed[i][j] += robot[i][j];
			}
		}
	}

	static class Tree implements Comparable<Tree> { // ��ĭ�� ������ �����׷� �� �� �ֵ�.
		int age;
		int x;
		int y;
		int dead;

		public Tree(int x, int y, int age, int dead) {
			this.x = x;
			this.y = y;
			this.age = age;
			this.dead = dead;
		}

		@Override
		// ���� ������ ���̱� ����
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
}