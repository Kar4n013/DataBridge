package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connection.GetConnection;
import pojo.Pojo;

@SuppressWarnings("serial")
public class TableSelectionPage extends JFrame {

	JLabel tables;
	JTable jtable;
	DefaultTableModel defaultTableModel;
	JScrollPane scrollPane;
	JLabel label;
	JTextField field;
	JButton button1, button2;
	Pojo pojo;
	public TableSelectionPage(Pojo pojo) {
		this.pojo = pojo;
	}
	
	public void showTable() {

		setTitle("Table Selection");
		setLayout(null);

		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		tables = new JLabel("Tables");
		tables.setFont(new Font("Arial", Font.BOLD, 20));
		tables.setBounds(150, 20, 200, 40);
		add(tables);

		String[] columns = { "table_name" };
		defaultTableModel = new DefaultTableModel(columns, 0);

		jtable = new JTable(defaultTableModel);

		scrollPane = new JScrollPane(jtable);
		scrollPane.setBounds(30, 70, 350, 350);
		jtable.setRowHeight(30);

		jtable.setFont(new Font("Arial", Font.PLAIN, 16));

		jtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

		jtable.getColumnModel().getColumn(0).setPreferredWidth(200);

		add(scrollPane);

		try (Statement statement = GetConnection.getConnection(pojo.getDb_name(), pojo.getUser(), pojo.getPassword()).createStatement();
				ResultSet resultSet = statement.executeQuery("SHOW TABLES")) {

			while (resultSet.next()) {
				String name = resultSet.getString(1);
				defaultTableModel.addRow(new Object[] { name });
			}
			
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

		label = new JLabel("Enter table name to export:");
		label.setBounds(430, 120, 300, 30);

		field = new JTextField();
		field.setBounds(430, 160, 250, 30);

		button1 = new JButton("Submit");
		button1.setBounds(430, 210, 100, 35);

		button2 = new JButton("Reset");
		button2.setBounds(580, 210, 100, 35);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PreviewSheet previewSheet = new PreviewSheet(pojo);
				previewSheet.previewSheet(field.getText());
			}
		});

		add(label);
		add(field);
		add(button1);
		add(button2);

		setVisible(true);
	}
}
