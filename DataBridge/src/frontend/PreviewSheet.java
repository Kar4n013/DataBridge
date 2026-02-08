package frontend;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.GetConnection;
import pojo.Pojo;

@SuppressWarnings("serial")
public class PreviewSheet extends JFrame {
	JTable jtable;
	DefaultTableModel defaultTableModel;
	JScrollPane scrollPane;
	JButton button1, button2;
	Pojo pojo;

	public PreviewSheet(Pojo pojo) throws HeadlessException {
		this.pojo = pojo;
	}

	public void previewSheet(String table_name) {

		setLayout(null);
		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		try {
			Statement statement = GetConnection.getConnection(pojo.getDb_name(), pojo.getUser(), pojo.getPassword())
					.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM " + table_name);
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

			scrollPane = new JScrollPane(jtable);
			scrollPane.setBounds(30, 30, 720, 380);

			jtable.setRowHeight(30);
			jtable.setFont(new Font("Arial", Font.PLAIN, 16));
			jtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

			add(scrollPane);

			resultset.close();
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		button1 = new JButton("Proceed");
		button2 = new JButton("Cancel");

		add(button1);
		add(button2);

		setVisible(true);
	}
}
