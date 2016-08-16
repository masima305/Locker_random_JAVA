import java.util.ArrayList;
import java.util.Random;

// 랜덤을 하기 위한 메소드 관리.
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

	// ---------------- 기본적 정보 받아오는 메서드
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

	// ----------------------------------사물함의 범위를 분할해주자.
	// 필요한 범위는 총 5부분.
	// 사물함 전반부, 후반부.
	// 사물함 상부 중부 하부.

	// 전반부 후반부는 y=(weight/2) x<=(weight/2)(전반부) && x>((weight/2))
	// 상중하는 y=(height/3) 일때, 상부 = x <= y , 중부 = y < x < y*3
	public void dividelocker() {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {

				if (j < weight / 2 && i < height / 3) {
					// 상부 전반
					devided_FU.add(locker[i][j]);
				} else if (j < weight / 2 && i >= height / 3 && i < (height / 3) * 2) {
					// 중부 전반
					devided_FM.add(locker[i][j]);
				} else if (j < weight / 2 && i >= (height / 3) * 2) {
					// 하부 전반
					devided_FD.add(locker[i][j]);
				} else if (j >= weight / 2 && i < height / 3) {
					// 상부 후반
					devided_BU.add(locker[i][j]);
				} else if (j >= weight / 2 && i >= height / 3 && i < (height / 3) * 2) {
					// 중부 후반
					devided_BM.add(locker[i][j]);
				} else if (j >= weight / 2 && i >= (height / 3) * 2) {
					// 하부 후반
					devided_BD.add(locker[i][j]);
				}
			}

		}
	}

	public void divideShower() {
		System.out.print("전반부 상부 :");
		for (int i = 0; i < devided_FU.size(); i++) {
			System.out.print(devided_FU.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("전반부 중부 :");
		for (int i = 0; i < devided_FM.size(); i++) {
			System.out.print(devided_FM.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("전반부 하부 :");
		for (int i = 0; i < devided_FD.size(); i++) {
			System.out.print(devided_FD.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("후반부 상부 :");
		for (int i = 0; i < devided_BU.size(); i++) {
			System.out.print(devided_BU.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("후반부 중부 :");
		for (int i = 0; i < devided_BM.size(); i++) {
			System.out.print(devided_BM.get(i) + "\t");
		}
		System.out.println("");

		System.out.print("후반부 하부 :");
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
	// 학번검사
	public void idCheck() {
		
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // 아직 자리 배정을 못받은 사람 중에서
				if (students.get(i).id <= (dice.optPre() - 2)) { // 회장보다 2학번 높은
																	// 사람들 중에서
					boolean is = devided_BM.isEmpty();
					r = new Random();
					if (is == true) { // 남은 자리의 수가 0이라면
						students.get(i).locker_id = 0;
					} else {
						if (dice.rollHigher() == true) { // 주사위로 확정이 난 경우
							r = new Random();
							students.get(i).locker_id = devided_BM.get(r.nextInt(devided_BM.size()));
							// 락커 번호는 후반부 중간중의 아무거나 하나.
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// 학생의 팀넘버가 그전 학생과 같거나
									students.get(j).locker_id = students.get(i).locker_id;
									// 라커 아이디가 같음
								}
							}
						} else {
							students.get(i).locker_id = 0;
							// 아니면 그대로 0;
						}
					}
					for (int j = 0; j < devided_BM.size(); j++) {
						if (devided_BM.get(j) == students.get(i).locker_id) {
							devided_BM.remove(j);
						}
					}
				} else {
					students.get(i).locker_id = 0;
					// 다이스를 돌려서 true면 랜덤하게 전반부 중간을 받는다.
				}
				
			}
		}
	}

	// 성별검사
	public void genderManCheck() {
		for (int i = 1; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // 아직 자리 배정을 못받은 사람 중에서
				if (students.get(i).gender.equals("남") && students.get(i).gender.equals(students.get(i - 1).gender)) { // 지금

					if (dice.rollMan() == true) { // 주사위로 확정이 난 경우
						r = new Random();
						ArrayList<Integer> up = new ArrayList<Integer>();
						up.addAll(devided_FU);
						up.addAll(devided_BU);

						boolean is = up.isEmpty();
						r = new Random();
						if (is == true) { // 남은 자리의 수가 0이라면
							students.get(i).locker_id = 0;
						} else {
							students.get(i).locker_id = up.get(r.nextInt(up.size()));
							// 락커 번호는 전반부 상부중의 아무거나 하나.

							for (int j = 0; j < devided_FU.size(); j++) {
								if (devided_FU.get(j) == students.get(i).locker_id) {
									devided_FU.remove(j);
								}
							}
							for (int j = 0; j < devided_BU.size(); j++) {
								if (devided_BU.get(j) == students.get(i).locker_id) {
									devided_BU.remove(j);
								}
							} // 지워주자
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// 학생의 팀넘버가 그전 학생과 같거나
									students.get(j).locker_id = students.get(i).locker_id;
									// 라커 아이디가 같음
								}
							}
						}

					} else {
						students.get(i).locker_id = 0;
						// 아니면 그대로 0;
					}
				} else {
					students.get(i).locker_id = 0;
					// 다이스를 돌려서 true면 랜덤하게 전반부 중간을 받는다.
				}
			}
		}
	}

	public void genderWoCheck() {
		for (int i = 1; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // 아직 자리 배정을 못받은 사람 중에서
				if (students.get(i).gender.equals("여") && students.get(i).gender.equals(students.get(i - 1).gender)) { // 지금

					if (dice.rollWo() == true) { // 주사위로 확정이 난 경우
						r = new Random();
						ArrayList<Integer> MidDown = new ArrayList<Integer>();
						MidDown.addAll(devided_FM);
						MidDown.addAll(devided_FD);
						MidDown.addAll(devided_BM);
						MidDown.addAll(devided_BD);

						boolean is = MidDown.isEmpty();
						r = new Random();
						if (is == true) { // 남은 자리의 수가 0이라면
							students.get(i).locker_id = 0;
						} else {
							students.get(i).locker_id = MidDown.get(r.nextInt(MidDown.size()));
							// 락커 번호는 전반부 상부중의 아무거나 하나.

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
							} // 지워주자
							for (int j = 0; j < students.size(); j++) {
								if (students.get(i).team_id == students.get(j).team_id) {
									// 학생의 팀넘버가 그전 학생과 같거나
									students.get(j).locker_id = students.get(i).locker_id;
									// 라커 아이디가 같음
								}
							}
						}

					} else {
						students.get(i).locker_id = 0;
						// 아니면 그대로 0;
					}
				} else {
					students.get(i).locker_id = 0;
					// 다이스를 돌려서 true면 랜덤하게 전반부 중간을 받는다.
				}
			}
		}
	}

	// 나머지 통 랜덤.
	public void restCheck() {
		// 우선 배열 안에 있는 나머지 칸들을 모두 하나의 어레이에 담는다.
		ArrayList<Integer> rest = new ArrayList<>();

		rest.addAll(devided_FU);
		rest.addAll(devided_BU);
		rest.addAll(devided_FM);
		rest.addAll(devided_BM);
		rest.addAll(devided_FD);
		rest.addAll(devided_BD);

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).locker_id == 0) { // 아직 자리 배정을 못받은 사람 중에서
				boolean is = rest.isEmpty();
				r = new Random();
				if (is == true) { // 남은 자리의 수가 0이라면
					students.get(i).locker_id = 0;
				} else {
					students.get(i).locker_id = rest.get(r.nextInt(rest.size()));
					// 락커 번호는 남은 번호중의 아무거나 하나.
					for (int j = 0; j < rest.size(); j++) {
						if (rest.get(j) == students.get(i).locker_id) {
							rest.remove(j);
						}
					} // 지워주자
				}
				for (int j = 0; j < students.size(); j++) {
					if (students.get(i).team_id == students.get(j).team_id) {
						// 학생의 팀넘버가 그전 학생과 같거나
						students.get(j).locker_id = students.get(i).locker_id;
						// 라커 아이디가 같음
					}
				}
			}
		}
	}
}
