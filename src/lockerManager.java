import java.util.*;

public class lockerManager {

	int height;
	int weight;
	int locker[][] = null; // 2���� �迭 �����Ҵ�
	int locker_count = 0;
	int disable_count = 0;
	int able_count = 0; 
	
	ArrayList<Integer> disable = new ArrayList<Integer>();

	void lockerSizer(int h, int w) {
		
		locker_count = h*w;
		
		height = h;
		locker = new int[height][];
		// =====����ĭ �����Ҵ�

		weight = w;
		for (int i = 0; i < locker.length; i++) {
			locker[i] = new int[weight];
			// =====����ĭ �����Ҵ�
		}

		int x = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				locker[i][j] = x;
				x++;
			}
		}
	}

	public void lockerShower() {

		System.out.println("��Ŀ�� ���¸� �����ݴϴ�.");
		for (int i = 0; i < height; i++) {
			System.out.println("");
			for (int j = 0; j < weight; j++) {
				if (locker[i][j] == 0) {
					System.out.print("|XX|");
				} else if (locker[i][j] < 10 && locker[i][j] != 0) {
					System.out.print("|0" + locker[i][j] + "|");
				} else {
					System.out.print("|" + locker[i][j] + "|");
				}
			}
		}
		System.out.println("");
	}

	void disablePicker(int s) {

		int select = s;

		if (select != 9999) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < weight; j++) {
					if (locker[i][j] == select) {
						disable.add(select);
						locker[i][j] = 0;
						disable_count++;
						able_count = locker_count - disable_count;
					}
				}
			}
		}

	}

	public int[][] lockerGiver() {
		return locker;
	}

	public boolean comparer(int team_count) {
		return false;
	}

}
