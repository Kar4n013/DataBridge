package frontend;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Index extends JFrame {
	JLabel welcome, label1, label2;
	JTextField field1, field2;
	JButton button1, button2;

	public Index() {

		welcome = new JLabel("Welcome");
		label1 = new JLabel("Enter database name: ");
		label2 = new JLabel("Enter sheet name: ");

		field1 = new JTextField(45);
		field2 = new JTextField(45);

		button1 = new JButton("Submit");
		button2 = new JButton("Reset");

		welcome.setFont(new Font("Arial", Font.BOLD, 20));

		welcome.setBounds(180, 30, 200, 40);

		label1.setBounds(80, 120, 150, 30);
		field1.setBounds(230, 120, 150, 30);

		label2.setBounds(80, 180, 150, 30);
		field2.setBounds(230, 180, 150, 30);

		button1.setBounds(120, 260, 100, 35);
		button2.setBounds(240, 260, 100, 35);

		add(welcome);
		add(label1);
		add(field1);
		add(label2);
		add(field2);
		add(button1);
		add(button2);

		setSize(500, 500);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new Index();
	}
}
