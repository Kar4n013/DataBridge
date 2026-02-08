package connection;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateSheet {

    @SuppressWarnings("unused")
	public static void createSheet(String name) {
        try {
            File file = new File(name + ".xlsx");

            if (file.exists()) {
                System.out.println("File already exists. Using existing file: " + file.getName());
                return;
            }

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sheet1");

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);

            out.close();
            workbook.close();

            System.out.println("Empty Excel sheet created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
