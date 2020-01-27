import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Desination extends JFrame {
	int x = 30; //
	int y = 65; 
	int name = 0; //
	int count = 0; 
	
	JCheckBox[] jcbarr = new JCheckBox[45]; 
	JButton btn1 = new JButton("�ʱ�ȭ");
	JButton btn2 = new JButton("Ȯ��");
	
	public Desination(String title) {
		super(title);
		getContentPane().setLayout(null);
		
		JLabel la_title = new JLabel("������ ��ȣ�� �����ϼ���.");
		la_title.setFont(new Font("HY�߰��", Font.PLAIN, 17));
		la_title.setBounds(30, 10, 210, 43);
		getContentPane().add(la_title);
	
		for(int i = 0;i<jcbarr.length;i++) {
			jcbarr[i] = new JCheckBox((name+1)+""); 
			jcbarr[i].setBounds(x,y,50,50);
			jcbarr[i].setFont(new Font("HY�߰��",Font.PLAIN,18));
			jcbarr[i].setBorderPainted(true);
			x += 50;
			name++;
			if(name%5==0) {
				x = 30; 
				y += 50;
			}
			getContentPane().add(jcbarr[i]);
			jcbarr[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int result = e.getStateChange()==1 ? count++:count--;
				 	
					for(int i=0;i<45;i++) {
						if(jcbarr[i].isSelected()==true ) {
							jcbarr[i].setBackground(Color.GREEN);
						}	 
						else {
							jcbarr[i].setBackground(null);
						}
					}
					
					if(count>6) {
						for(int i=0;i<45;i++) {
						if(e.getItem()==jcbarr[i])
							jcbarr[i].setSelected(false);
						if(count == 7)
							JOptionPane.showMessageDialog(null,"6�� ���Ϸθ� �����Ͻÿ�.");
						}
					}
				}
			});
			
			btn1.setBounds(30, 530, 250, 30);
			btn1.setFont(new Font("HY�߰��",Font.PLAIN,20));
			getContentPane().add(btn1);
			btn1.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<45;i++)
						jcbarr[i].setSelected(false);
				}
			}); 
			
			btn2.setBounds(30, 570, 250, 30);
			btn2.setFont(new Font("HY�߰��",Font.PLAIN,20));
			getContentPane().add(btn2);
			btn2.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<45;i++)
						setVisible(false);
				}
			}); 
		}
			setLocation(70,0);
			setSize(330,670); 
			setVisible(false);
	}
}


