import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

public class lockerFrame extends JFrame {

	Scanner s = new Scanner(System.in);
	dataManager data = new dataManager();
	lockerManager locker = new lockerManager();
	randomer randomer = new randomer();
	dice dice = new dice();

	JTextArea textArea;

	void start() {
		MyListener listener = new MyListener();

		setTitle("Locker Manager 1.1vr");
		Container cp = getContentPane(); // 컨텐트팬 알아내기
		cp.setLayout(new BorderLayout());// BorderLayout 배치관리자 달기

		JPanel panCenter = new JPanel();
		textArea = new JTextArea("", 30, 60);
		JScrollPane scroll = new JScrollPane(textArea);
		panCenter.add(scroll);

		TitledBorder tb1 = BorderFactory.createTitledBorder("상태창");
		panCenter.setBorder(tb1);
		Border thinBorder = LineBorder.createGrayLineBorder();
		tb1.setBorder(thinBorder);// 번화필요

		JPanel panEast = new JPanel();
		panEast.setLayout(new FlowLayout());
		panEast.setPreferredSize(new Dimension(160, 220));

		TitledBorder tb2 = BorderFactory.createTitledBorder("메뉴");
		panEast.setBorder(tb2);
		tb2.setBorder(thinBorder);

		Border roundborder = new LineBorder(Color.gray, 1, true);
		JButton b1 = new JButton("파일 읽기");
		b1.setBorder(roundborder);
		b1.setPreferredSize(new Dimension(100, 50));
		b1.addActionListener(listener);

		JButton b2 = new JButton("사물함 설정");
		b2.setBorder(roundborder);
		b2.setPreferredSize(new Dimension(100, 50));
		b2.addActionListener(listener);

		JButton b3 = new JButton("제외 사물함 설정");
		b3.setBorder(roundborder);
		b3.setPreferredSize(new Dimension(100, 50));
		b3.addActionListener(listener);

		JButton b4 = new JButton("랜덤 시작!");
		b4.setBorder(roundborder);
		b4.setPreferredSize(new Dimension(100, 50));
		b4.addActionListener(listener);

		JButton b5 = new JButton("결과 보기");
		b5.setBorder(roundborder);
		b5.setPreferredSize(new Dimension(100, 50));
		b5.addActionListener(listener);

		panEast.add(b2);
		panEast.add(b3);
		panEast.add(b1);
		panEast.add(b4);
		panEast.add(b5);

		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 23, 4));
		panSouth.setSize(500, 50);

		JButton ver = new JButton("버전 1.0");
		ver.setBorder(null);
		ver.setBorderPainted(false);
		ver.setContentAreaFilled(false);
		ver.setOpaque(false);
		ver.setPreferredSize(new Dimension(50, 20));
		// b1.addActionListener(listener);

		panSouth.add(ver);
		ver.addActionListener(listener);

		this.add(panCenter, BorderLayout.CENTER);
		this.add(panEast, BorderLayout.EAST);
		this.add(panSouth, BorderLayout.SOUTH);

		setBounds(250, 100, 900, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} // myFrame

	private class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource(); // 사용자가 클릭한 버튼 알아내기
			textArea.setText("");

			/* case 1 */
			if (b.getText().equals("파일 읽기")) { // 등록하기

				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
				chooser.setMultiSelectionEnabled(false);
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String readSrc = chooser.getSelectedFile().toString();
					data.reader(readSrc);
					for (int i = 0; i < data.lister().size(); i++) {
						
						String list = data.lister().get(i);
						
						textArea.append(list + "\n");
					}
				}
				
				
				/* case 2 */
			} else if (b.getText().equals("사물함 설정")) { // 삭제하기
				textArea.setText("");

				String st_weight = JOptionPane.showInputDialog("사물함의 가로크기를 입력해주세요");
				int weight = Integer.parseInt(st_weight);

				String st_height = JOptionPane.showInputDialog("사물함의 세로크기를 입력해주세요");
				int height = Integer.parseInt(st_height);

				locker.height = height;
				locker.weight = weight;

				locker.lockerSizer(height, weight);

				textArea.append("라커의 상태를 보여줍니다." + "\n");
				for (int i = 0; i < height; i++) {
					textArea.append("\n");
					textArea.append("\n");
					for (int j = 0; j < weight; j++) {
						if (locker.locker[i][j] == 0) {
							textArea.append(" |XX| ");
						} else if (locker.locker[i][j] < 10 && locker.locker[i][j] != 0) {
							textArea.append(" |0" + locker.locker[i][j] + "| ");
						} else {
							textArea.append(" |" + locker.locker[i][j] + "| ");
						}
					}
				}
				textArea.append("\n");
				textArea.append("\n");
				textArea.append("총 사물함 수\t:" + locker.locker_count + "자리");
				textArea.append("\n");
				textArea.append("배정 가능 자리\t:" + locker.able_count + "자리");
				textArea.append("\n");
				textArea.append("취소된 자리\t:" + locker.disable_count + "자리");
				textArea.append("\n");

				/* case 3 */
			} else if (b.getText().equals("제외 사물함 설정")) { // 목록보기
				int select = 0;
				while (select != 9999) {
					textArea.setText("");
					textArea.append("라커의 상태를 보여줍니다." + "\n");
					for (int i = 0; i < locker.height; i++) {
						textArea.append("\n");
						textArea.append("\n");
						for (int j = 0; j < locker.weight; j++) {
							if (locker.locker[i][j] == 0) {
								textArea.append(" |XX| ");
							} else if (locker.locker[i][j] < 10 && locker.locker[i][j] != 0) {
								textArea.append(" |0" + locker.locker[i][j] + "| ");
							} else {
								textArea.append(" |" + locker.locker[i][j] + "| ");
							}
						}
					}
					textArea.append("\n");
					textArea.append("\n");
					textArea.append("총 사물함 수\t:" + locker.locker_count + "자리");
					textArea.append("\n");
					textArea.append("배정 가능 자리\t:" + locker.able_count + "자리");
					textArea.append("\n");
					textArea.append("취소된 자리\t:" + locker.disable_count + "자리");
					textArea.append("\n");

					String s = JOptionPane.showInputDialog("제외할 사물함 번호를 1개 입력해주세요. 입력이 끝나면 9999를 입력.");
					select = Integer.parseInt(s);

					locker.disablePicker(select);

				}

				/* case 4 */
			} else if (b.getText().equals("랜덤 시작!")) { // 목록보기
				textArea.append("배정을 시작합니다");

				randomer.getPara(locker.height, locker.weight);
				
				randomer.getArray(locker.lockerGiver());
				
				randomer.getStudents(data.giver());
				
				randomer.countCheck(locker.able_count);
					
				randomer.dividelocker();

				randomer.genderManCheck();

				randomer.genderWoCheck();

				randomer.restCheck();

				textArea.append("완료.");

				/* case 5 */
			} else if (b.getText().equals("결과 보기")) { // 종료
				System.out.println("결과");

				for (int i = 0; i < data.lister().size(); i++) {
					String list = data.lister().get(i);
					textArea.append(list + "\n");
				}

				/* case 6 */
			} else if (b.getText().equals("버전 1.0")) {

				String pass = JOptionPane.showInputDialog("만든이 정보가 궁금하신가요?");

				if (pass.equals("회장만세!")) {

					JTextField man_pair;
					JTextField wo_pair;
					JTextField pre_id;
					JTextField higher_pre;

					JButton submit;

					JDialog main = new JDialog();
					main.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

					man_pair = new JTextField(5);
					wo_pair = new JTextField(5);
					pre_id = new JTextField(5);
					higher_pre = new JTextField(5);

					JPanel gui = new JPanel(new BorderLayout(3, 3));
					gui.setBorder(new EmptyBorder(3, 3, 3, 3));
					main.setContentPane(gui);

					JPanel labels = new JPanel(new GridLayout(0, 1));
					JPanel controls = new JPanel(new GridLayout(0, 1));
					gui.add(labels, BorderLayout.WEST);
					gui.add(controls, BorderLayout.CENTER);

					labels.add(new JLabel("남남       짝이면           상   부 확률  :   %"));
					controls.add(man_pair);
					man_pair.setText(String.valueOf(dice.optMan()));

					labels.add(new JLabel("여여       짝이면          중하부 확률  :   %"));
					controls.add(wo_pair);
					wo_pair.setText(String.valueOf(dice.optWo()));

					labels.add(new JLabel("회장보다 선배면       앞에 올확률  :   %"));
					controls.add(higher_pre);
					higher_pre.setText(String.valueOf(dice.optHigher()));

					labels.add(new JLabel("회장의    학번은                                 :    "));
					controls.add(pre_id);
					pre_id.setText(String.valueOf(dice.optPre()));

					submit = new JButton("Submit");

					gui.add(submit, BorderLayout.SOUTH);
					main.pack();
					main.setVisible(true);
					;

					submit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String text_pre = pre_id.getText();
							dice.optPre(Integer.parseInt(text_pre));
							String text_hi = higher_pre.getText();
							dice.optHigher(Integer.parseInt(text_hi));
							String text_wo = wo_pair.getText();
							dice.optWo(Integer.parseInt(text_wo));
							String text_man = man_pair.getText();
							dice.optMan(Integer.parseInt(text_man));

							main.setVisible(false);
						}
					});
				} else {
					JOptionPane.showMessageDialog(getFocusOwner(),
							"만든이 :영문과 10 이정훈\n"
							+ " 더 필요한 기능이 있으면 masima305@gmail.com으로 주저없이 이메일 주시오.\n"
							+ "만든날짜 : 2016-08-02 \n"
							+ "1.1버전    : 2016-08-07 (자리배정 버그픽스 및 순서 수정)");
				}
			}
		}// actionperformed
	} // myListener
}