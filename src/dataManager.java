
//�����͸� �а� ����, ������ ó���ϴ� �� ������� �ϴ� �޴���.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class dataManager {

	FileReader fr = null;
	BufferedReader br = null;
	FileWriter fw = null;
	BufferedWriter bw = null;

	File file;
	// ���� ������ �ּ�
	ArrayList<stuInfo> students = new ArrayList<stuInfo>();
	// �л� �����迭

	
	
	
	void reader(String src) {

		String a = src;
		file = new File(a);
		System.out.println(a + "�� �о�ɴϴ�.");

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String c;// ��Ʈ�� c�� ��ȯ ��
			int team_count = 1;

			while ((c = br.readLine()) != null) { // ������ null�� ������ ������ ���پ� �д´�

				if (c.equals("---")) {
					team_count++;

				} else {
					String[] cToc = c.split("\t"); // ���� ���� ���뿡�� ���� ������ �ɰ� �� �迭��
													// ������ְ�
					stuInfo stu = new stuInfo(cToc[0], cToc[1], cToc[2]);
					stu.team_id = team_count;
					students.add(stu); // ���� �迭���� ���������� �����ڿ� �־ ����Ʈ�� ����.
				}
			}
		} catch (IOException e) {

		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
				}
		}
		
	}
	ArrayList<String> lister() {
		ArrayList<String> line = new ArrayList<String>();
		String a = ("��ϼ���" + "\t" + "�й�" + "\t" + "�̸�" + "\t" + "����" + "\t" + "�繰�� ��ȣ");
		line.add(a);
		String b = (students.get(0).toString());
		line.add(b);

		for (int i = 1; i < students.size(); i++) {
			if ((students.get(i).team_id) != (students.get(i - 1).team_id)) {
				line.add("---------------------------------------------------"
						+ "--------------------------------------------------"
						+ "--------------------------------------");
			}
			line.add(students.get(i).toString());
		}
		return line;
	}

	// ����Ʈ ���� ���³�
	ArrayList<stuInfo> giver() {
			return students;
	}
	// ����Ʈ ���� �ִ³�
}
