// 학생의 정보를 
public class stuInfo {

	int id; // 학번
	String name; // 이름
	String gender; // 성별

	int locker_id = 0; // 락커 배치를 위한 id
	int team_id = 0; // 같이 쓰는 사람들 묶어주는 id

	// ------------------ 생성자
	public stuInfo() {
	}

	public stuInfo(String name, String id) {
		this.id = Integer.parseInt(id);
		this.name = name;
		gender = null;
	}

	public stuInfo(String name, String id, String gender) {
		this.id = Integer.parseInt(id);
		this.name = name;
		this.gender = gender;
	}

	// --------------------- 시스템 입력파라.
	public void lockerId(int locker_id) { // 사물함 고유 아이디 배정을 위한 공간
		this.locker_id = locker_id;
	}

	public void teamId(int team_id) { // 팀 배정을 위한 공간.
		this.team_id = team_id;
	}

	// ----------------------출력용 메서드
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String toString() {
		String str;
		if (locker_id == 99999) {
			str = (team_id + "번째" + "\t" + id + "\t" + name + "\t" + gender + "\t라커부족");
		} else {
			str = (team_id + "번째" + "\t" + id + "\t" + name + "\t" + gender + "\t" + locker_id);
		}
		return str;
	}
}
