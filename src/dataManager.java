
//데이터를 읽고 쓰고, 공간을 처리하는 그 모든일을 하는 메니저.
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
	// 읽을 파일의 주소
	ArrayList<stuInfo> students = new ArrayList<stuInfo>();
	// 학생 가변배열

	
	
	
	void reader(String src) {

		String a = src;
		file = new File(a);
		System.out.println(a + "을 읽어옵니다.");

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String c;// 스트링 c를 소환 후
			int team_count = 1;

			while ((c = br.readLine()) != null) { // 내용이 null이 나오기 전까지 한줄씩 읽는다

				if (c.equals("---")) {
					team_count++;

				} else {
					String[] cToc = c.split("\t"); // 한줄 읽은 내용에서 탭을 단위로 쪼갠 뒤 배열로
													// 만들어주고
					stuInfo stu = new stuInfo(cToc[0], cToc[1], cToc[2]);
					stu.team_id = team_count;
					students.add(stu); // 만든 배열에서 순차적으로 생성자에 넣어서 리스트에 저장.
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
		String a = ("등록순서" + "\t" + "학번" + "\t" + "이름" + "\t" + "성별" + "\t" + "사물함 번호");
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

	// 리스트 파일 쓰는놈
	ArrayList<stuInfo> giver() {
			return students;
	}
	// 리스트 파일 주는놈
}
