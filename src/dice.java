import java.util.*;

class dice {

	// ---------------------------������ ����
	private int pre_id = 12; // ȸ���� �й�
	private int man_pair = 60; // ���� ¦�� ���
	private int wo_pair = 50; // ��� ¦�ϰ��
	private int couple = 30; // ���� ¦�ϰ��
	private int higher_pre = 65; // ȸ�庸�� ���� Ȯ��

	// ---------------------------������ ����� �޼���

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

	// ----------------------------------------���� �ż���
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
