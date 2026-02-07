package connection;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateSheet {
	@SuppressWarnings("unused")
	public static void createSheet(String name) {
		try {

			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet("Sheet1");

			FileOutputStream out = new FileOutputStream(name+".xlsx");
			workbook.write(out);

			out.close();
			workbook.close();

			System.out.println("Empty Excel sheet created successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
