package sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connection.GetConnection;
import pojo.Pojo;

@SuppressWarnings("serial")
public class CreateSheet extends JFrame{

	Pojo pojo;
	String query;

	public CreateSheet(Pojo pojo, String query) {
		this.pojo = pojo;
		this.query = query;
	}

	public boolean createSheet() {
		try {
			String name = pojo.getSheet_name();

			File file = new File(name + ".xlsx");

			if (file.exists()) {
				file.delete();
				System.out.println("Old file deleted, creating new file...");
			}

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Sheet1");

			Statement statement = GetConnection.getConnection(pojo.getDb_name(), pojo.getUser(), pojo.getPassword())
					.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			XSSFRow header = sheet.createRow(0);
			for (int i = 1; i <= columnCount; i++) {
				header.createCell(i - 1).setCellValue(metaData.getColumnName(i));
			}

			int rowIndex = 1;
			while (resultSet.next()) {
				XSSFRow row = sheet.createRow(rowIndex++);

				for (int i = 1; i <= columnCount; i++) {
					Object value = resultSet.getObject(i);
					row.createCell(i - 1).setCellValue(value == null ? "" : value.toString());
				}
			}

			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);

			out.close();
			workbook.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
