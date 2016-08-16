// �л��� ������ 
public class stuInfo {

	int id; // �й�
	String name; // �̸�
	String gender; // ����

	int locker_id = 0; // ��Ŀ ��ġ�� ���� id
	int team_id = 0; // ���� ���� ����� �����ִ� id

	// ------------------ ������
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

	// --------------------- �ý��� �Է��Ķ�.
	public void lockerId(int locker_id) { // �繰�� ���� ���̵� ������ ���� ����
		this.locker_id = locker_id;
	}

	public void teamId(int team_id) { // �� ������ ���� ����.
		this.team_id = team_id;
	}

	// ----------------------��¿� �޼���
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
			str = (team_id + "��°" + "\t" + id + "\t" + name + "\t" + gender + "\t��Ŀ����");
		} else {
			str = (team_id + "��°" + "\t" + id + "\t" + name + "\t" + gender + "\t" + locker_id);
		}
		return str;
	}
}
