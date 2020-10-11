package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DFS(백트래킹)
 * 
 * @see 백준 19235번: 모노미노도미노 <br>
 *      메모리: 26380 KB <br>
 *      시간: 228 ms
 * @since 2020-10-11
 * 
 */

public class boj_19235 {

	private static int N;
	private static int[][] blue;
	private static int[][] green;
	private static int score;
	private static int count;
	private static HashMap<Integer, Integer> mapBlue;
	private static HashMap<Integer, Integer> mapGreen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		blue = new int[4][6];
		green = new int[6][4];
		score = 0;
		count = 0;
		mapBlue = new HashMap<Integer, Integer>();
		mapGreen = new HashMap<Integer, Integer>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			mapBlue.put(i, t);
			mapGreen.put(i, t);

			// x,y
			// x,y - x,y+1 (가로)
			// x,y - x+1,y (세로)

			moveBlue(t, x, y, i);
			moveGreen(t, x, y, i);

			getScore(1); // 점수 체크 및 조정
			getScore(2);

		}
		printBlue();
		printGreen();
		System.out.println(score);
		System.out.println(count);
	}

	static int changeNum;
	// 점수 얻을 수 있는지 체크
	static boolean checkScore(int num) {
		changeNum = 0;
		boolean flag = false;
		if (num == 1) { // Blue
			for (int i = 2; i < 6; i++) {
				int count = 0;
				for (int j = 0; j < 4; j++) {
					if (blue[j][i] == 0)
						break;
					count++;
				}
				if (count == 4) {
					changeNum = Math.max(changeNum, i);
					flag = true; // 점수 올름
					for (int j = 0; j < 4; j++) {
						int type = mapBlue.get(blue[j][i]);
						if (type == 2) {
							mapBlue.put(blue[j][i], 1);
						}
						blue[j][i] = 0;
					}
					score++;
				}
			}
			return flag;
		} else { // Green
			for (int i = 2; i < 6; i++) {
				int count = 0;
				for (int j = 0; j < 4; j++) {
					if (green[i][j] == 0)
						break;
					count++;
				}
				if (count == 4) {
					changeNum = Math.max(changeNum, i);
					flag = true; // 점수 올름
					for (int j = 0; j < 4; j++) {
						if (green[i][j] > 0) {
							int type = mapGreen.get(green[i][j]);
							if (type == 3) {
								mapGreen.put(green[i][j], 1);
							}
							green[i][j] = 0;
						}
					}
					score++;
				}
			}

			return flag;
		}

	}

	static void getScore(int num) {
		if (num == 1) { // Blue
			while (true) {
				if (checkScore(1)) { // 조정
					for (int j = changeNum-1; j >= 0; j--) {
						for (int i = 0; i < 4; i++) {
							if (blue[i][j] > 0) {
								int type = mapBlue.get(blue[i][j]);
								if (type == 3) {
									for (int k = j + 1; k < 6; k++) {
										if (blue[i][k] > 0 || blue[i + 1][k] > 0) {
											blue[i][k - 1] = blue[i][j];
											blue[i + 1][k - 1] = blue[i + 1][j];
											if(k-1 == j) break;
											blue[i][j] = 0;
											blue[i + 1][j] = 0;
											break;
										}
										if(k==5) {
											blue[i][k] = blue[i][j];
											blue[i+1][k] = blue[i+1][j];
											blue[i][j] = 0;
											blue[i+1][j] = 0;
										}
									}
									i++;
								} else {
									for (int k = j + 1; k < 6; k++) {
										if (blue[i][k] > 0) {
											blue[i][k - 1] = blue[i][j];
											if(k-1 == j) break;
											blue[i][j] = 0;
											break;
										}
										if (k == 5) {
											blue[i][k] = blue[i][j];
											blue[i][j] = 0;
											break;
										}
									}
								}
							}
						}
					}
				} else {
					checkMap(1);
					break;
				}
			}
		} else { // Green
			while (true) {
				if (checkScore(2)) { // 조정
					for (int j = changeNum-1; j >= 0; j--) {
						for (int i = 0; i < 4; i++) {
							if (green[j][i] > 0) {
								int type = mapGreen.get(green[j][i]);
								if (type == 2) {
									for (int k = j + 1; k < 6; k++) {
										if (green[k][i] > 0 || green[k][i + 1] > 0) {
											green[k - 1][i] = green[j][i];
											green[k - 1][i + 1] = green[j][i + 1];
											if(k-1 == j) break;
											green[j][i] = 0;
											green[j][i + 1] = 0;
											break;
										}
										if(k==5) {
											green[k][i] = green[j][i];
											green[k][i+1] = green[j][i+1];
											green[j][i] = 0;
											green[j][i+1] = 0;
										}
									}
									i++;
								} else {
									for (int k = j + 1; k < 6; k++) {
										if (green[k][i] > 0) {
											green[k - 1][i] = green[j][i];
											if((k-1) == j) break;
											green[j][i] = 0;
											break;
										}
										if (k == 5) {
											green[k][i] = green[j][i];
											green[j][i] = 0;
											break;
										}
									}
								}
							}
						}
					}
				} else {
					checkMap(2);
					break;
				}
			}
		}
	}

	static void checkMap(int num) {
		if (num == 1) { // Blue
			int count = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					if (blue[j][i] > 0) {
						count++;
						break;
					}
				}
			}
			if (count > 0) {
				for (int i = 5; i > 5 - count; i--) {
					for (int j = 0; j < 4; j++) {
						mapBlue.put(blue[j][i], 1);
						blue[j][i] = 0;
					}
				}
				for (int j = 0; j < 4; j++) {
					for (int i = 5 - count; i >= 0; i--) {
						if (blue[j][i] > 0) {
							blue[j][i + count] = blue[j][i];
							blue[j][i] = 0;
						}
					}
				}
			}
		} else { // Green
			int count = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					if (green[i][j] > 0) {
						count++;
						break;
					}
				}
			}
			if (count > 0) {
				for (int i = 5; i > 5 - count; i--) {
					for (int j = 0; j < 4; j++) {
						mapGreen.put(green[i][j], 1);
						green[i][j] = 0;
					}
				}
				for (int j = 0; j < 4; j++) {
					for (int i = 5 - count; i >= 0; i--) {
						if (green[i][j] > 0) {
							green[i + count][j] = green[i][j];
							green[i][j] = 0;
						}
					}
				}
			}
		}
	}

	static void printBlue() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (blue[i][j] > 0)
					count++;
			}
		}
	}

	static void printGreen() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j] > 0)
					count++;
			}
		}
	}

	static void moveBlue(int t, int x, int y, int index) {
		switch (t) {
		case 1:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && blue[x][i] == 0) {
					blue[x][i] = index;
					break;
				}
				if (blue[x][i] > 0) {
					blue[x][i - 1] = index;
					break;
				}
			}
			break;
		case 2:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && blue[x][i] == 0) {
					blue[x][i] = index;
					blue[x][i - 1] = index;
					break;
				}
				if (blue[x][i] > 0) {
					blue[x][i - 1] = index;
					blue[x][i - 2] = index;
					break;
				}
			}
			break;
		case 3:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && blue[x][i] == 0 && blue[x + 1][i] == 0) {
					blue[x][i] = index;
					blue[x+1][i] = index;
					break;
				}
				if (blue[x][i] > 0 || blue[x + 1][i] > 0) {
					blue[x][i - 1] = index;
					blue[x + 1][i - 1] = index;
					break;
				}
			}
			break;
		}
	}

	static void moveGreen(int t, int x, int y, int index) {
		switch (t) {
		case 1:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && green[i][y] == 0) {
					green[i][y] = index;
					break;
				}
				if (green[i][y] > 0) {
					green[i - 1][y] = index;
					break;
				}
			}
			break;
		case 2:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && green[i][y] == 0 && green[i][y + 1] == 0) {
					green[i][y] = index;
					green[i][y + 1] = index;
					break;
				}
				if (green[i][y] > 0 || green[i][y + 1] > 0) {
					green[i - 1][y] = index;
					green[i - 1][y + 1] = index;
					break;
				}
			}

			break;
		case 3:
			for (int i = 2; i < 6; i++) {
				if (i == 5 && green[i][y] == 0) {
					green[i][y] = index;
					green[i - 1][y] = index;
					break;
				}
				if (green[i][y] > 0) {
					green[i - 1][y] = index;
					green[i - 2][y] = index;
					break;
				}
			}
			break;
		}
	}

}
