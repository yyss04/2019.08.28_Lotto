import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;

public class LottoCheck extends JFrame implements ActionListener {
	private Desination ds = new Desination("������ ����");
	private Remove rm = new Remove("���ܼ� ����");
	
	private JTextField myNo1;
	private JTextField myNo2;
	private JTextField myNo3;
	private JTextField myNo4;
	private JTextField myNo5;
	private JTextField myNo6;
	// ���� ��ȣ �Է� �ؽ�Ʈ �ʵ�
	
	JLabel drwtNo1 = new JLabel();
	JLabel drwtNo2 = new JLabel();
	JLabel drwtNo3 = new JLabel();
	JLabel drwtNo4 = new JLabel();
	JLabel drwtNo5 = new JLabel();
	JLabel drwtNo6 = new JLabel();
	JLabel lb_plus = new JLabel();
	JLabel bnusNo = new JLabel();
	// ��÷ ��ȣ ��� ��
	
	JLabel accordNo1 = new JLabel();
	JLabel correctNo1 = new JLabel();
	JLabel accordcount1 = new JLabel();
	JLabel accordNo2 = new JLabel();
	JLabel correctNo2 = new JLabel();
	JLabel accordcount2 = new JLabel();
	// accord/correct :: (����ġ/��ġ)��ȣ ��� ��  ||  countNo :: ��ġ ��ȣ ���� ��� ��
	
	JLabel lb_turntitle = new JLabel("nnnȸ�� ��÷ ��ȣ");
	JLabel lb_tx1 = new JLabel("�� ��ȣ ��÷ Ȯ��");
	JLabel lb_tx2 = new JLabel("�� ȸ���� ��÷ Ȯ��");
	JLabel lb_tx3 = new JLabel("�� ��ȣ");
	JLabel lb_tx4 = new JLabel("��÷ ��ȣ");
	JLabel lb_tx5 = new JLabel("��ġ");
	JLabel lb_tx6 = new JLabel("���� ��ȣ");
	
	JLabel randomNo1 = new JLabel();
	JLabel randomNo2 = new JLabel();
	JLabel randomNo3 = new JLabel();
	JLabel randomNo4 = new JLabel();
	JLabel randomNo5 = new JLabel();
	JLabel randomNo6 = new JLabel();
	// ���� ��ȣ ��� ��
	JLabel lb_fixNo = new JLabel();		// ������ ������ ��� ��
	JLabel lb_exNo = new JLabel();		// ������ ���ܼ� ��� ��
	
	JLabel lb_img1 = new JLabel();
	JLabel lb_img2 = new JLabel();
	JLabel lb_img3 = new JLabel();
	JLabel lb_img4 = new JLabel();
	JLabel lb_img5 = new JLabel();
	JLabel lb_img6 = new JLabel();
	JLabel lb_imgbo = new JLabel();		// �̹��� ��Ŭ ��

	ImageIcon unit0 = new ImageIcon("src/lotto_circle/yellow.png");
	ImageIcon unit1 = new ImageIcon("src/lotto_circle/blue.png");
	ImageIcon unit2 = new ImageIcon("src/lotto_circle/red.png");
	ImageIcon unit3 = new ImageIcon("src/lotto_circle/gray.png");
	ImageIcon unit4 = new ImageIcon("src/lotto_circle/green.png");
	ImageIcon plus = new ImageIcon("src/lotto_circle/plus.png");

	JButton btn_check = new JButton("��ȸ");
	JButton btn_fixNo = new JButton("������ ����");
	JButton btn_exNo = new JButton("���ܼ� ����");

	JComboBox<String> comboBox = new JComboBox<String>();
	private List<String> year = new ArrayList<String>();	// �޺� �ڼ��� �߰��� �⵵
	private List<Integer> thisNo = new ArrayList<Integer>();	// ���� ��÷ ��ȣ
	static List<Integer> outputNo = new ArrayList<Integer>();	// ��� ��ȣ
	static List<Integer> getNo = new ArrayList<Integer>();		// �Է¹��� ��ȣ

	public int count = 0;	// �ҽ� ������ ��Ʋ�� ����� ī��Ʈ
	URL result;
	
	// �޺� �ڽ� ����
	public void genComboBox() {
		for (int i = 0; i < 873; i++) {		// 873  ::  2019-09-03 ���� �ֽ� ȸ��
			int y = i + 1;
			year.add(String.valueOf(y));
		}
		comboBox.addItem("nnn");
		for (String temp : year) {
			comboBox.addItem(temp);
		}
	}

	// �Է� ���� ��
	public void compareNo(List<Integer> winlist, List<Integer> mylist) {
		count = 0;		// ��ġ�ϴ� ��ȣ ����
		int i = 0;
		for (Integer temp1 : mylist) {		// temp1 :: �� ��ȣ
			for (Integer temp2 : winlist) {	// temp2 ::	��÷ ��ȣ
				if (temp1 == temp2) {
					count++;		// ��ȣ�� ��ġ�� ���� ī��Ʈ ����
					if (temp1 >= 10)
						accordNo1.setText(accordNo1.getText() + "����");
					else
						accordNo1.setText(accordNo1.getText() + " ��");
					correctNo1.setText(correctNo1.getText() + String.valueOf(temp1) + "��");
					break;
				}
				if (i == 6) {
					accordNo1.setText(accordNo1.getText() + String.valueOf(temp1) + "��");
					if (temp1 >= 10)
						correctNo1.setText(correctNo1.getText() + "����");
					else
						correctNo1.setText(correctNo1.getText() + " ��");
				}	// accord�󺧰� correct���� ��ġ ��ũ ����
				i++;
			}
			i = 0;
		}
	}

	// ȭ�� �������
	public void init() {
		drwtNo1.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo1.setForeground(Color.WHITE);
		drwtNo1.setBounds(35, 81, 38, 25);
		drwtNo1.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo1);

		drwtNo2.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo2.setForeground(Color.WHITE);
		drwtNo2.setBounds(92, 81, 38, 25);
		drwtNo2.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo2);

		drwtNo3.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo3.setForeground(Color.WHITE);
		drwtNo3.setBounds(151, 81, 38, 25);
		drwtNo3.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo3);

		drwtNo4.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo4.setForeground(Color.WHITE);
		drwtNo4.setBounds(209, 81, 38, 25);
		drwtNo4.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo4);

		drwtNo5.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo5.setForeground(Color.WHITE);
		drwtNo5.setBounds(266, 81, 38, 25);
		drwtNo5.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo5);

		drwtNo6.setFont(new Font("���� ���", Font.BOLD, 25));
		drwtNo6.setForeground(Color.WHITE);
		drwtNo6.setBounds(325, 81, 38, 25);
		drwtNo6.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(drwtNo6);

		lb_plus.setBounds(420, 67, 50, 50);
		getContentPane().add(lb_plus);
		lb_plus.setIcon(plus);

		bnusNo.setFont(new Font("���� ���", Font.BOLD, 25));
		bnusNo.setForeground(Color.WHITE);
		bnusNo.setBounds(499, 81, 38, 25);
		bnusNo.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(bnusNo);
		
		accordNo1.setFont(new Font("���� ���", Font.BOLD, 12));
		accordNo1.setBounds(378, 227, 163, 38);
		getContentPane().add(accordNo1);

		correctNo1.setForeground(Color.RED);
		correctNo1.setFont(new Font("���� ���", Font.BOLD, 12));
		correctNo1.setBounds(378, 227, 163, 38);
		getContentPane().add(correctNo1);

		accordcount1.setBounds(571, 227, 57, 38);
		getContentPane().add(accordcount1);

		accordNo2.setFont(new Font("���� ���", Font.BOLD, 12));
		accordNo2.setBounds(378, 297, 163, 38);
		getContentPane().add(accordNo2);

		correctNo2.setForeground(Color.RED);
		correctNo2.setFont(new Font("���� ���", Font.BOLD, 12));
		correctNo2.setBounds(378, 297, 163, 38);
		getContentPane().add(correctNo2);

		accordcount2.setBounds(571, 297, 57, 38);
		getContentPane().add(accordcount2);

		lb_turntitle.setFont(new Font("���� ���", Font.BOLD | Font.ITALIC, 25));
		lb_turntitle.setBounds(35, 8, 242, 49);
		getContentPane().add(lb_turntitle);
		
		lb_tx1.setFont(new Font("���� ���", Font.BOLD, 17));
		lb_tx1.setBounds(35, 136, 157, 25);
		getContentPane().add(lb_tx1);
		
		lb_tx2.setFont(new Font("���� ���", Font.PLAIN, 14));
		lb_tx2.setBounds(45, 167, 138, 25);
		getContentPane().add(lb_tx2);

		lb_tx3.setBounds(55, 202, 57, 15);
		getContentPane().add(lb_tx3);

		lb_tx4.setBounds(378, 202, 57, 15);
		getContentPane().add(lb_tx4);

		lb_tx5.setBounds(571, 202, 57, 15);
		getContentPane().add(lb_tx5);

		randomNo1.setBounds(55, 297, 37, 38);
		getContentPane().add(randomNo1);

		lb_tx6.setBounds(55, 275, 57, 15);
		getContentPane().add(lb_tx6);

		randomNo2.setBounds(104, 297, 37, 38);
		getContentPane().add(randomNo2);

		randomNo3.setBounds(154, 297, 37, 38);
		getContentPane().add(randomNo3);

		randomNo4.setBounds(204, 297, 37, 38);
		getContentPane().add(randomNo4);

		randomNo5.setBounds(254, 297, 37, 38);
		getContentPane().add(randomNo5);

		randomNo6.setBounds(304, 297, 37, 38);
		getContentPane().add(randomNo6);
		
		btn_fixNo.setBounds(55, 353, 117, 23);
		getContentPane().add(btn_fixNo);
		
		btn_exNo.setBounds(55, 386, 117, 23);
		getContentPane().add(btn_exNo);
		
		lb_fixNo.setBounds(184, 353, 357, 23);
		getContentPane().add(lb_fixNo);
		
		lb_exNo.setBounds(184, 386, 357, 23);
		getContentPane().add(lb_exNo);

		myNo1 = new JTextField();
		myNo1.setColumns(10);
		myNo1.setBounds(54, 227, 38, 38);
		myNo1.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo1);

		myNo2 = new JTextField();
		myNo2.setColumns(10);
		myNo2.setBounds(104, 227, 38, 38);
		myNo2.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo2);

		myNo3 = new JTextField();
		myNo3.setColumns(10);
		myNo3.setBounds(154, 227, 38, 38);
		myNo3.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo3);

		myNo4 = new JTextField();
		myNo4.setColumns(10);
		myNo4.setBounds(204, 227, 38, 38);
		myNo4.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo4);

		myNo5 = new JTextField();
		myNo5.setColumns(10);
		myNo5.setBounds(254, 227, 38, 38);
		myNo5.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo5);

		myNo6 = new JTextField();
		myNo6.setColumns(10);
		myNo6.setBounds(304, 227, 38, 38);
		myNo6.setHorizontalAlignment(JTextField.CENTER);
		getContentPane().add(myNo6);

		comboBox.setBounds(215, 170, 57, 21);
		getContentPane().add(comboBox);

		btn_check.setBounds(571, 386, 97, 23);
		getContentPane().add(btn_check);
	}

	// ��÷��ȣ �÷�����
	public void setColor(JLabel lb_img, int num, int i) {
		if (num <= 10) {
			lb_img.setBounds(29 * (i * 2 + 1), 67, 50, 50);
			getContentPane().add(lb_img);
			lb_img.setIcon(unit0);
		} else if (num <= 20) {
			lb_img.setBounds(29 * (i * 2 + 1), 67, 50, 50);
			getContentPane().add(lb_img);
			lb_img.setIcon(unit1);
		} else if (num <= 30) {
			lb_img.setBounds(29 * (i * 2 + 1), 67, 50, 50);
			getContentPane().add(lb_img);
			lb_img.setIcon(unit2);
		} else if (num <= 40) {
			lb_img.setBounds(29 * (i * 2 + 1), 67, 50, 50);
			getContentPane().add(lb_img);
			lb_img.setIcon(unit3);
		} else {
			lb_img.setBounds(29 * (i * 2 + 1), 67, 50, 50);
			getContentPane().add(lb_img);
			lb_img.setIcon(unit4);
		}
	}

	// �ڵ� ��ȣ ��÷
	public void randommyNo() {
		Set<Integer> set = new HashSet<Integer>();		// ���� ��ȣ ����
		Set<Integer> selds = new HashSet<Integer>();	// ������ ����
		Set<Integer> selrm = new HashSet<Integer>();	// ���ܼ� ����
		
		try {
			for (int i = 0; i < 45; i++) {
				if (ds.jcbarr[i].isSelected() == true) {
					set.add(Integer.parseInt(ds.jcbarr[i].getText()));
					selds.add(Integer.parseInt(ds.jcbarr[i].getText()));
				} else if (ds.jcbarr[i].isSelected() == false) {
					set.remove(Integer.parseInt(ds.jcbarr[i].getText()));
				}
			}
		} catch (NullPointerException e1) {}

		while (set.size() < 6) {
			int number = (int) (Math.random() * 45 + 1);
			set.add(number);

			try {
				for (int i = 0; i < 45; i++) {
					if (rm.jcbarr[i].isSelected() == true) {
						set.remove(Integer.parseInt(rm.jcbarr[i].getText()));
						selrm.add(Integer.parseInt(rm.jcbarr[i].getText()));
					}
				}
			} catch (NullPointerException e1) {}
		}
		
		List<Integer> ranmyNo = new ArrayList<Integer>(set);
		List<Integer> listds = new ArrayList<Integer>(selds);
		List<Integer> listrm = new ArrayList<Integer>(selrm);
		Collections.sort(ranmyNo);
		Collections.sort(listds);
		Collections.sort(listrm);
		
		for(Integer temp : listds) {
			lb_fixNo.setText(lb_fixNo.getText() + String.valueOf(temp) + "��");	// ��Ʈ�� ������ ���
		}
		
		for(Integer temp : listrm) {
			lb_exNo.setText(lb_exNo.getText() + String.valueOf(temp) + "��");	// ��Ʈ�� ���ܼ� ���
		}
		
		while (set.size() < 6) {
			Double d = Math.random() * 45 + 1;
			set.add(d.intValue());
		}
		
		int i = 0;
		for (Integer temp : ranmyNo) {
			if (i == 0) {
				randomNo1.setText(String.valueOf(temp));
				i++;
			} else if (i == 1) {
				randomNo2.setText(String.valueOf(temp));
				i++;
			} else if (i == 2) {
				randomNo3.setText(String.valueOf(temp));
				i++;
			} else if (i == 3) {
				randomNo4.setText(String.valueOf(temp));
				i++;
			} else if (i == 4) {
				randomNo5.setText(String.valueOf(temp));
				i++;
			} else {
				randomNo6.setText(String.valueOf(temp));
				i = 0;
			}
		}
		
		count = 0;
		for (Integer temp1 : ranmyNo) {
			for (Integer temp2 : outputNo) {
				if (temp1 == temp2) {
					count++;
					if (temp1 >= 10)
						accordNo2.setText(accordNo2.getText() + "����");
					else
						accordNo2.setText(accordNo2.getText() + " ��");
					correctNo2.setText(correctNo2.getText() + String.valueOf(temp1) + "��");
					break;
				}
				if (i == 6) {
					accordNo2.setText(accordNo2.getText() + String.valueOf(temp1) + "��");
					if (temp1 >= 10)
						correctNo2.setText(correctNo2.getText() + "����");
					else
						correctNo2.setText(correctNo2.getText() + " ��");
				}
				i++;
			}
			i = 0;
		}	// CompareNo �Լ��� ������ �����ϴ� ���
	}

	// ��÷ ��ȣ ����
	public void settingNo(List<Integer> list) {
		int i = 0;
		for (Integer temp : list) {
			if (i == 0) {
				drwtNo1.setText(String.valueOf(temp));
				setColor(lb_img1, temp, i);
				i++;
			} else if (i == 1) {
				drwtNo2.setText(String.valueOf(temp));
				setColor(lb_img2, temp, i);
				i++;
			} else if (i == 2) {
				drwtNo3.setText(String.valueOf(temp));
				setColor(lb_img3, temp, i);
				i++;
			} else if (i == 3) {
				drwtNo4.setText(String.valueOf(temp));
				setColor(lb_img4, temp, i);
				i++;
			} else if (i == 4) {
				drwtNo5.setText(String.valueOf(temp));
				setColor(lb_img5, temp, i);
				i++;
			} else if (i == 5) {
				drwtNo6.setText(String.valueOf(temp));
				setColor(lb_img6, temp, i);
				i++;
			} else {
				bnusNo.setText(String.valueOf(temp));
				setColor(lb_imgbo, temp, 8);
				i = 0;
			}
		}
	}

	// �̹�ȸ�� ��÷��ȣ
	public void thistimeNo() {
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < 6) {
			Double d = Math.random() * 45 + 1;
			set.add(d.intValue());
		}
		outputNo = new ArrayList<Integer>(set);
		Collections.sort(outputNo);

		while (true) { // ���ʽ� ��ȣ ��÷
			Double d = Math.random() * 45 + 1;
			if (outputNo.contains(d.intValue()))
				continue;
			else {
				outputNo.add(d.intValue());
				break;
			}
		}

		thisNo = new ArrayList<Integer>(outputNo);
	}

	public void event() {
		btn_check.addActionListener(this);
		btn_fixNo.addActionListener(this);
		btn_exNo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("������ ����"))
			ds.setVisible(true);
		else if(e.getActionCommand().contentEquals("���ܼ� ����"))
			rm.setVisible(true);
		else if(e.getActionCommand().contentEquals("��ȸ")) {
			try {
				String sel_year = comboBox.getSelectedItem().toString();
				String array[] = new String[7];
				lb_turntitle.setText(sel_year + "ȸ�� ��÷ ��ȣ");

				if (comboBox.getSelectedItem().toString().equals("nnn")) {
					outputNo.clear();
					outputNo.addAll(thisNo);
				} else {
					outputNo.clear();
					result = new URL("https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo="
							+ comboBox.getSelectedItem().toString());
					InputStreamReader isr = new InputStreamReader(result.openConnection().getInputStream(), "UTF-8");
					JSONObject object = (JSONObject) JSONValue.parse(isr);
					if ("success".equals(object.get("returnValue"))) {
						for (int j = 1; j <= 6; j++) {
							array[j - 1] = String.valueOf(object.get("drwtNo" + j));
							array[6] = String.valueOf(object.get("bnusNo"));
						}
						for (String temp : array) {
							outputNo.add(Integer.parseInt(temp));
						}
					} else {
						System.out.println("���� �б� ����");
					}
				}
				settingNo(outputNo);

				getNo.clear();
				lb_fixNo.setText("");
				lb_exNo.setText("");
				accordNo1.setText("");
				correctNo1.setText("");
				accordNo2.setText("");
				correctNo2.setText("");

				getNo.add(Integer.parseInt(myNo1.getText()));
				getNo.add(Integer.parseInt(myNo2.getText()));
				getNo.add(Integer.parseInt(myNo3.getText()));
				getNo.add(Integer.parseInt(myNo4.getText()));
				getNo.add(Integer.parseInt(myNo5.getText()));
				getNo.add(Integer.parseInt(myNo6.getText()));

				if (Integer.parseInt(myNo1.getText()) < 1 || Integer.parseInt(myNo1.getText()) > 45
						|| Integer.parseInt(myNo2.getText()) < 1 || Integer.parseInt(myNo2.getText()) > 45
						|| Integer.parseInt(myNo3.getText()) < 1 || Integer.parseInt(myNo3.getText()) > 45
						|| Integer.parseInt(myNo4.getText()) < 1 || Integer.parseInt(myNo4.getText()) > 45
						|| Integer.parseInt(myNo5.getText()) < 1 || Integer.parseInt(myNo5.getText()) > 45
						|| Integer.parseInt(myNo6.getText()) < 1 || Integer.parseInt(myNo6.getText()) > 45) {
					JOptionPane.showMessageDialog(null, "1~45 ������ ���ڸ� �Է��Ͻÿ�.");
					accordNo1.setText("");
					correctNo1.setText("");
					accordcount1.setText("");
					return;
				}
				
				HashSet<Integer> hash = new HashSet<Integer>(getNo);
				ArrayList<Integer> dup_check = new ArrayList<Integer>(hash); // �ߺ� Ȯ��
				if (dup_check.size() < 6) {		// �ߺ����� ������ hash���� ���ܵǹǷ� dup_check�� ũ�Ⱑ 6�� �� �� ����.
					JOptionPane.showMessageDialog(null, "�ߺ� ���� �Է��Ͻÿ�.");
					accordNo1.setText("");
					correctNo1.setText("");
					accordcount1.setText("");
					return;
				}

				compareNo(outputNo, getNo);
				accordcount1.setText(String.valueOf(count));
				randommyNo();
				accordcount2.setText(String.valueOf(count));
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(null, "���ڸ� �Է��Ͻÿ�.");
				accordNo1.setText("");
				correctNo1.setText("");
				accordcount1.setText("");
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// ������
	public LottoCheck(String title) {
		super(title);
		getContentPane().setLayout(null);

		genComboBox();
		init();
		thistimeNo();
		settingNo(thisNo);
		event();

		setSize(750, 500);
		getContentPane().setBackground(new Color(252,252,215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// �����Լ�
	public static void main(String[] args) {
		new LottoCheck("Lotto");
	}
}
