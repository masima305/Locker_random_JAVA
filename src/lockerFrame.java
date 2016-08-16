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
		Container cp = getContentPane(); // ����Ʈ�� �˾Ƴ���
		cp.setLayout(new BorderLayout());// BorderLayout ��ġ������ �ޱ�

		JPanel panCenter = new JPanel();
		textArea = new JTextArea("", 30, 60);
		JScrollPane scroll = new JScrollPane(textArea);
		panCenter.add(scroll);

		TitledBorder tb1 = BorderFactory.createTitledBorder("����â");
		panCenter.setBorder(tb1);
		Border thinBorder = LineBorder.createGrayLineBorder();
		tb1.setBorder(thinBorder);// ��ȭ�ʿ�

		JPanel panEast = new JPanel();
		panEast.setLayout(new FlowLayout());
		panEast.setPreferredSize(new Dimension(160, 220));

		TitledBorder tb2 = BorderFactory.createTitledBorder("�޴�");
		panEast.setBorder(tb2);
		tb2.setBorder(thinBorder);

		Border roundborder = new LineBorder(Color.gray, 1, true);
		JButton b1 = new JButton("���� �б�");
		b1.setBorder(roundborder);
		b1.setPreferredSize(new Dimension(100, 50));
		b1.addActionListener(listener);

		JButton b2 = new JButton("�繰�� ����");
		b2.setBorder(roundborder);
		b2.setPreferredSize(new Dimension(100, 50));
		b2.addActionListener(listener);

		JButton b3 = new JButton("���� �繰�� ����");
		b3.setBorder(roundborder);
		b3.setPreferredSize(new Dimension(100, 50));
		b3.addActionListener(listener);

		JButton b4 = new JButton("���� ����!");
		b4.setBorder(roundborder);
		b4.setPreferredSize(new Dimension(100, 50));
		b4.addActionListener(listener);

		JButton b5 = new JButton("��� ����");
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

		JButton ver = new JButton("���� 1.0");
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
			JButton b = (JButton) e.getSource(); // ����ڰ� Ŭ���� ��ư �˾Ƴ���
			textArea.setText("");

			/* case 1 */
			if (b.getText().equals("���� �б�")) { // ����ϱ�

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
			} else if (b.getText().equals("�繰�� ����")) { // �����ϱ�
				textArea.setText("");

				String st_weight = JOptionPane.showInputDialog("�繰���� ����ũ�⸦ �Է����ּ���");
				int weight = Integer.parseInt(st_weight);

				String st_height = JOptionPane.showInputDialog("�繰���� ����ũ�⸦ �Է����ּ���");
				int height = Integer.parseInt(st_height);

				locker.height = height;
				locker.weight = weight;

				locker.lockerSizer(height, weight);

				textArea.append("��Ŀ�� ���¸� �����ݴϴ�." + "\n");
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
				textArea.append("�� �繰�� ��\t:" + locker.locker_count + "�ڸ�");
				textArea.append("\n");
				textArea.append("���� ���� �ڸ�\t:" + locker.able_count + "�ڸ�");
				textArea.append("\n");
				textArea.append("��ҵ� �ڸ�\t:" + locker.disable_count + "�ڸ�");
				textArea.append("\n");

				/* case 3 */
			} else if (b.getText().equals("���� �繰�� ����")) { // ��Ϻ���
				int select = 0;
				while (select != 9999) {
					textArea.setText("");
					textArea.append("��Ŀ�� ���¸� �����ݴϴ�." + "\n");
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
					textArea.append("�� �繰�� ��\t:" + locker.locker_count + "�ڸ�");
					textArea.append("\n");
					textArea.append("���� ���� �ڸ�\t:" + locker.able_count + "�ڸ�");
					textArea.append("\n");
					textArea.append("��ҵ� �ڸ�\t:" + locker.disable_count + "�ڸ�");
					textArea.append("\n");

					String s = JOptionPane.showInputDialog("������ �繰�� ��ȣ�� 1�� �Է����ּ���. �Է��� ������ 9999�� �Է�.");
					select = Integer.parseInt(s);

					locker.disablePicker(select);

				}

				/* case 4 */
			} else if (b.getText().equals("���� ����!")) { // ��Ϻ���
				textArea.append("������ �����մϴ�");

				randomer.getPara(locker.height, locker.weight);
				
				randomer.getArray(locker.lockerGiver());
				
				randomer.getStudents(data.giver());
				
				randomer.countCheck(locker.able_count);
					
				randomer.dividelocker();

				randomer.genderManCheck();

				randomer.genderWoCheck();

				randomer.restCheck();

				textArea.append("�Ϸ�.");

				/* case 5 */
			} else if (b.getText().equals("��� ����")) { // ����
				System.out.println("���");

				for (int i = 0; i < data.lister().size(); i++) {
					String list = data.lister().get(i);
					textArea.append(list + "\n");
				}

				/* case 6 */
			} else if (b.getText().equals("���� 1.0")) {

				String pass = JOptionPane.showInputDialog("������ ������ �ñ��ϽŰ���?");

				if (pass.equals("ȸ�常��!")) {

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

					labels.add(new JLabel("����       ¦�̸�           ��   �� Ȯ��  :   %"));
					controls.add(man_pair);
					man_pair.setText(String.valueOf(dice.optMan()));

					labels.add(new JLabel("����       ¦�̸�          ���Ϻ� Ȯ��  :   %"));
					controls.add(wo_pair);
					wo_pair.setText(String.valueOf(dice.optWo()));

					labels.add(new JLabel("ȸ�庸�� �����       �տ� ��Ȯ��  :   %"));
					controls.add(higher_pre);
					higher_pre.setText(String.valueOf(dice.optHigher()));

					labels.add(new JLabel("ȸ����    �й���                                 :    "));
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
							"������ :������ 10 ������\n"
							+ " �� �ʿ��� ����� ������ masima305@gmail.com���� �������� �̸��� �ֽÿ�.\n"
							+ "���糯¥ : 2016-08-02 \n"
							+ "1.1����    : 2016-08-07 (�ڸ����� �����Ƚ� �� ���� ����)");
				}
			}
		}// actionperformed
	} // myListener
}