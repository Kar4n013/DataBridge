package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connection.CreateSheet;
import connection.GetConnection;

@SuppressWarnings("serial")
public class Index extends JFrame {
	JLabel welcome, label1, label2, label3, label4;
	JTextField field1, field2, field4;
	JPasswordField field3;
	JButton button1, button2;

	public Index() {

		welcome = new JLabel("Welcome");
		label1 = new JLabel("Enter database name: ");
		label2 = new JLabel("Enter username: ");
		label3 = new JLabel("Enter password: ");
		label4 = new JLabel("Enter sheet name: ");

		field1 = new JTextField(45);
		field2 = new JTextField(45);
		field3 = new JPasswordField(45);
		field4 = new JTextField(45);

		button1 = new JButton("Submit");
		button2 = new JButton("Reset");

		welcome.setFont(new Font("Arial", Font.BOLD, 20));
		label1.setFont(new Font("Arial", Font.BOLD, 15));
		label2.setFont(new Font("Arial", Font.BOLD, 15));
		label3.setFont(new Font("Arial", Font.BOLD, 15));
		label4.setFont(new Font("Arial", Font.BOLD, 15));

		welcome.setBounds(180, 30, 200, 40);

		label1.setBounds(60, 120, 170, 30);
		field1.setBounds(230, 120, 150, 30);

		label2.setBounds(60, 180, 150, 30);
		field2.setBounds(230, 180, 150, 30);

		label3.setBounds(60, 240, 150, 30);
		field3.setBounds(230, 240, 150, 30);

		label4.setBounds(60, 300, 150, 30);
		field4.setBounds(230, 300, 150, 30);

		button1.setBounds(120, 360, 100, 35);
		button2.setBounds(240, 360, 100, 35);

		add(welcome);
		add(label1);
		add(field1);
		add(label2);
		add(field2);
		add(label3);
		add(field3);
		add(label4);
		add(field4);
		add(button1);
		add(button2);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GetConnection.getConnection(field1.getText(), field2.getText(), field3.getPassword());
				CreateSheet.createSheet(field4.getText());
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				field1.setText("");
				field2.setText("");
				field3.setText("");
				field4.setText("");
			}
		});

		setSize(500, 500);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Index();
	}
}
