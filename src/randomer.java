import java.util.ArrayList;
import java.util.Random;

// ������ �ϱ� ���� �޼ҵ� ����.
public class randomer {

	int height;
	int weight;
	int locker[][];
	ArrayList<stuInfo> students = new ArrayList<stuInfo>();
	
	ArrayList<Integer> devided_FU = new ArrayList<Integer>();
	ArrayList<Integer> devided_FM = new ArrayList<Integer>();
	ArrayList<Integer> devided_FD = new ArrayList<Integer>();
	ArrayList<Integer> devided_BU = new ArrayList<Integer>();
	ArrayList<Integer> devided_BM = new ArrayList<Integer>();
	ArrayList<Integer> devided_BD = new ArrayList<Integer>();

	dice dice = new dice();

	Random r;

	// ---------------- �⺻�� ���� �޾ƿ��� �޼���
	public void getPara(int height, int weight) {
		this.height = height;
		this.weight = weight;
	}

	public void getArray(int[][] is) {
		this.locker = is;
	}

	public void getStudents(ArrayList<stuInfo> students) {
		this.students = students;
	}

	// ----------------------------------�繰���� ������ ����������.
	// �ʿ��� ������ �� 5�κ�.
	// �繰�� ���ݺ�, �Ĺݺ�.
	// �繰�� ��� �ߺ� �Ϻ�.

	// ���ݺ� �Ĺݺδ� y=(weight/2) x<=(weight/2)(���ݺ�) && x>((weight/2))
	// �����ϴ� y=(height/3) �϶�, ��� = x <= y , �ߺ� = y < x < y*3
	public void dividelocker() {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {

				if (j < weight / 2 && i < height / 3) {
					// ��� ����
					devided_FU.add(locker[i][j]);
				} else if (j < weight / 2 && i >= height / 3 && i < (height / 3) * 2) {
					// �ߺ� ����
					devided_FM.add(locker[i][j]);
				} else if (j < weight / 2 && i >= (height / 3) * 2) {
					// �Ϻ� ����
					devided_FD.add(locker[i][j]);
				} else if (j >= weight / 2 && i < height / 3) {
					// ��� �Ĺ�
					devided_BU.add(locker[i][j]);
				} else if (j >= weight / 2 && i >= height / 3 && i < (height / 3) * 2) {
					// �ߺ� �Ĺ�
					devided_BM.add(locker[i][j]);
				} else if (j >= weight / 2 && i >= (height / 3) * 2) {
					// �Ϻ� �Ĺ�
					devided_BD.add(locker[i][j]);
				}
			}

		}
	}

	public void divideShower() {
		System.out.print("���ݺ� ��� :");
		for (int i = 0; i < devided_FU.size(); i++) {
			System.out.print(devided_FU.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("���ݺ� �ߺ� :");
		for (int i = 0; i < devided_FM.size(); i++) {
			System.out.print(devided_FM.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("���ݺ� �Ϻ� :");
		for (int i = 0; i < devided_FD.size(); i++) {
			System.out.print(devided_FD.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("�Ĺݺ� ��� :");
		for (int i = 0; i < devided_BU.size(); i++) {
			System.out.print(devided_BU.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("�Ĺݺ� �ߺ� :");
		for (int i = 0; i < devided_BM.size(); i++) {
			System.out.print(devided_BM.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("�Ĺݺ� �Ϻ� :");
		for (int i = 0; i < devided_BD.size(); i++) {
			System.out.print(devided_BD.get(i) + "\t");
		}
		System.out.println("");

	}
	void countCheck(int able){
		
		int able_team = able;
		
		for (int i = 1 ; i < students.size(); i++){
			if (students.get(i).team_id > able_team){
				students.get(i).locker_id = 99999;	
			}
		}
	}
	// �й��˻�
	public void idCheck() {
		
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // ���� �ڸ� ������ ������ ��� �߿���
				if (students.get(i).id <= (dice.optPre() - 2)) { // ȸ�庸�� 2�й� ����
																	// ����� �߿���
					boolean is = devided_BM.isEmpty();
					r = new Random();
					if (is == true) { // ���� �ڸ��� ���� 0�̶��
						students.get(i).locker_id = 0;
					} else {
						if (dice.rollHigher() == true) { // �ֻ����� Ȯ���� �� ���
							r = new Random();
							students.get(i).locker_id = devided_BM.get(r.nextInt(devided_BM.size()));
							// ��Ŀ ��ȣ�� �Ĺݺ� �߰����� �ƹ��ų� �ϳ�.
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// �л��� ���ѹ��� ���� �л��� ���ų�
									students.get(j).locker_id = students.get(i).locker_id;
									// ��Ŀ ���̵� ����
								}
							}
						} else {
							students.get(i).locker_id = 0;
							// �ƴϸ� �״�� 0;
						}
					}
					for (int j = 0; j < devided_BM.size(); j++) {
						if (devided_BM.get(j) == students.get(i).locker_id) {
							devided_BM.remove(j);
						}
					}
				} else {
					students.get(i).locker_id = 0;
					// ���̽��� ������ true�� �����ϰ� ���ݺ� �߰��� �޴´�.
				}
				
			}
		}
	}

	// �����˻�
	public void genderManCheck() {
		for (int i = 1; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // ���� �ڸ� ������ ������ ��� �߿���
				if (students.get(i).gender.equals("��") && students.get(i).gender.equals(students.get(i - 1).gender)) { // ����

					if (dice.rollMan() == true) { // �ֻ����� Ȯ���� �� ���
						r = new Random();
						ArrayList<Integer> up = new ArrayList<Integer>();
						up.addAll(devided_FU);
						up.addAll(devided_BU);

						boolean is = up.isEmpty();
						r = new Random();
						if (is == true) { // ���� �ڸ��� ���� 0�̶��
							students.get(i).locker_id = 0;
						} else {
							students.get(i).locker_id = up.get(r.nextInt(up.size()));
							// ��Ŀ ��ȣ�� ���ݺ� ������� �ƹ��ų� �ϳ�.

							for (int j = 0; j < devided_FU.size(); j++) {
								if (devided_FU.get(j) == students.get(i).locker_id) {
									devided_FU.remove(j);
								}
							}
							for (int j = 0; j < devided_BU.size(); j++) {
								if (devided_BU.get(j) == students.get(i).locker_id) {
									devided_BU.remove(j);
								}
							} // ��������
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// �л��� ���ѹ��� ���� �л��� ���ų�
									students.get(j).locker_id = students.get(i).locker_id;
									// ��Ŀ ���̵� ����
								}
							}
						}

					} else {
						students.get(i).locker_id = 0;
						// �ƴϸ� �״�� 0;
					}
				} else {
					students.get(i).locker_id = 0;
					// ���̽��� ������ true�� �����ϰ� ���ݺ� �߰��� �޴´�.
				}
			}
		}
	}

	public void genderWoCheck() {
		for (int i = 1; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // ���� �ڸ� ������ ������ ��� �߿���
				if (students.get(i).gender.equals("��") && students.get(i).gender.equals(students.get(i - 1).gender)) { // ����

					if (dice.rollWo() == true) { // �ֻ����� Ȯ���� �� ���
						r = new Random();
						ArrayList<Integer> MidDown = new ArrayList<Integer>();
						MidDown.addAll(devided_FM);
						MidDown.addAll(devided_FD);
						MidDown.addAll(devided_BM);
						MidDown.addAll(devided_BD);

						boolean is = MidDown.isEmpty();
						r = new Random();
						if (is == true) { // ���� �ڸ��� ���� 0�̶��
							students.get(i).locker_id = 0;
						} else {
							students.get(i).locker_id = MidDown.get(r.nextInt(MidDown.size()));
							// ��Ŀ ��ȣ�� ���ݺ� ������� �ƹ��ų� �ϳ�.

							for (int j = 0; j < devided_FM.size(); j++) {
								if (devided_FM.get(j) == students.get(i).locker_id) {
									devided_FM.remove(j);
								}
							}
							for (int j = 0; j < devided_BM.size(); j++) {
								if (devided_BM.get(j) == students.get(i).locker_id) {
									devided_BM.remove(j);
								}
							}
							for (int j = 0; j < devided_FD.size(); j++) {
								if (devided_FD.get(j) == students.get(i).locker_id) {
									devided_FD.remove(j);
								}
							}
							for (int j = 0; j < devided_BD.size(); j++) {
								if (devided_BD.get(j) == students.get(i).locker_id) {
									devided_BD.remove(j);
								}
							} // ��������
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// �л��� ���ѹ��� ���� �л��� ���ų�
									students.get(j).locker_id = students.get(i).locker_id;
									// ��Ŀ ���̵� ����
								}
							}
						}

					} else {
						students.get(i).locker_id = 0;
						// �ƴϸ� �״�� 0;
					}
				} else {
					students.get(i).locker_id = 0;
					// ���̽��� ������ true�� �����ϰ� ���ݺ� �߰��� �޴´�.
				}
			}
		}
	}

	// ������ �� ����.
	public void restCheck() {
		// �켱 �迭 �ȿ� �ִ� ������ ĭ���� ��� �ϳ��� ��̿� ��´�.
		ArrayList<Integer> rest = new ArrayList<>();

		rest.addAll(devided_FU);
		rest.addAll(devided_BU);
		rest.addAll(devided_FM);
		rest.addAll(devided_BM);
		rest.addAll(devided_FD);
		rest.addAll(devided_BD);

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // ���� �ڸ� ������ ������ ��� �߿���
				boolean is = rest.isEmpty();
				r = new Random();
				if (is == true) { // ���� �ڸ��� ���� 0�̶��
					students.get(i).locker_id = 0;
				} else {
					students.get(i).locker_id = rest.get(r.nextInt(rest.size()));
					// ��Ŀ ��ȣ�� ���� ��ȣ���� �ƹ��ų� �ϳ�.
					for (int j = 0; j < rest.size(); j++) {
						if (rest.get(j) == students.get(i).locker_id) {
							rest.remove(j);
						}
					} // ��������
				}
				for (int j = 0; j < students.size(); j++) {
					if (students.get(i).team_id == students.get(j).team_id) {
						// �л��� ���ѹ��� ���� �л��� ���ų�
						students.get(j).locker_id = students.get(i).locker_id;
						// ��Ŀ ���̵� ����
					}
				}
			}
		}
	}
}
