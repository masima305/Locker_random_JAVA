import java.util.*;

class dice {

	// ---------------------------설정용 변수
	private int pre_id = 12; // 회장의 학번
	private int man_pair = 60; // 남자 짝일 경우
	private int wo_pair = 50; // 녀녀 짝일경우
	private int couple = 30; // 남녀 짝일경우
	private int higher_pre = 65; // 회장보다 높을 확룰

	// ---------------------------설정값 변경용 메서드

	int optMan() {
		return man_pair;
	}

	void optMan(int re_opt) {
		man_pair = re_opt;
	}

	int optWo() {
		return wo_pair;
	}

	void optWo(int re_opt) {
		wo_pair = re_opt;
	}

	int optCouple() {
		return couple;
	}

	void optCouple(int re_opt) {
		couple = re_opt;
	}

	int optHigher() {
		return higher_pre;
	}

	void optHigher(int re_opt) {
		higher_pre = re_opt;
	}

	int optPre() {
		return pre_id;
	}

	void optPre(int re_opt) {
		pre_id = re_opt;
	}

	// ----------------------------------------실행 매서드
	boolean rollMan() {
		Random r = new Random();

		if (r.nextInt(100) > man_pair) {
			return true;
		} else {
			return false;
		}
	}

	boolean rollWo() {
		Random r = new Random();

		if (r.nextInt(100) > wo_pair) {
			return true;
		} else {
			return false;
		}
	}

	boolean rollCouple() {
		Random r = new Random();

		if (r.nextInt(100) > couple) {
			return true;
		} else {
			return false;
		}
	}

	boolean rollHigher() {
		Random r = new Random();

		if (r.nextInt(100) > higher_pre) {
			return true;
		} else {
			return false;
		}
	}

	boolean rollHalfBool() {
		Random r = new Random();

		if (r.nextInt(100) > 50) {
			return true;
		} else {
			return false;
		}
	}
}
