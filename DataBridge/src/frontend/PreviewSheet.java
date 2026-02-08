package frontend;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connection.GetConnection;
import pojo.Pojo;
import sheet.CreateSheet;

@SuppressWarnings("serial")
public class PreviewSheet extends JFrame {
	JLabel label, label2, label3;
	JTextField field, field2;
	JTable jtable;
	DefaultTableModel defaultTableModel;
	JScrollPane scrollPane;
	JButton button1;
	Pojo pojo;
	String query, selectedcolumns;

	public PreviewSheet(Pojo pojo) throws HeadlessException {
		this.pojo = pojo;
	}

	public void previewSheet(String table_name) {

		setLayout(null);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		try {

			Statement statement = GetConnection.getConnection(pojo.getDb_name(), pojo.getUser(), pojo.getPassword())
					.createStatement();

			query = "SELECT * FROM " + table_name + " LIMIT 5";

			ResultSet resultset = statement.executeQuery(query);
			ResultSetMetaData meta = resultset.getMetaData();

			int columnCount = meta.getColumnCount();

			String[] columns = new String[columnCount];

			for (int i = 1; i <= columnCount; i++) {
				columns[i - 1] = meta.getColumnName(i);
			}

			defaultTableModel = new DefaultTableModel(columns, 0);
			jtable = new JTable(defaultTableModel);

			while (resultset.next()) {

				Object[] row = new Object[columnCount];

				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = resultset.getObject(i);
				}

				defaultTableModel.addRow(row);
			}

			jtable.setRowHeight(25);
			jtable.setFont(new Font("Arial", Font.PLAIN, 14));
			jtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

			scrollPane = new JScrollPane(jtable);
			scrollPane.setBounds(20, 20, 550, 400);
			add(scrollPane);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Wrong input or table not found.", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
		}

		label = new JLabel("Enter column names:");
		label.setBounds(600, 40, 250, 25);

		label2 = new JLabel("<html>Type 'all' for all columns.<br>" + "Or enter names separated by commas.<br>"
				+ "Example: name,age,address</html>");
		label2.setBounds(600, 70, 260, 60);

		field = new JTextField();
		field.setBounds(600, 140, 220, 30);

		label3 = new JLabel("Enter sheet name:");
		label3.setBounds(600, 190, 250, 25);

		field2 = new JTextField();
		field2.setBounds(600, 220, 220, 30);

		button1 = new JButton("Proceed");
		button1.setBounds(650, 280, 120, 35);

		Font font = new Font("Arial", Font.PLAIN, 15);

		label.setFont(font);
		label2.setFont(new Font("Arial", Font.PLAIN, 13));
		label3.setFont(font);
		field.setFont(font);
		field2.setFont(font);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				pojo.setSheet_name(field2.getText());

				selectedcolumns = field.getText();

				if (selectedcolumns.equalsIgnoreCase("all")) {
					selectedcolumns = "*";
				}

				query = "select " + selectedcolumns + " from " + table_name;

				CreateSheet sheet = new CreateSheet(pojo, query);
				Boolean success = sheet.createSheet();

				if (success) {
					JOptionPane.showMessageDialog(PreviewSheet.this, "Excel sheet created with database data",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}
				dispose();
			}
		});

		add(label);
		add(label2);
		add(field);
		add(label3);
		add(field2);
		add(button1);

		setVisible(true);
	}

}
