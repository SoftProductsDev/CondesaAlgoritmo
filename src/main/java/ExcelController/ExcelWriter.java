package ExcelController;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
  public static void CreateExcel() throws IOException, InvalidFormatException {
    // Create a Workbook generates new Excel file
    Workbook workbook = new XSSFWorkbook();

    CreationHelper createHelper = workbook.getCreationHelper();

    // Create a Sheet
    Sheet sheet = workbook.createSheet("HorarioMaster");

    // Write the output to a file
    //Tambien puede especificar el path ("C:\\Report\\TestCase.xls"));
    FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Closing the workbook
    workbook.close();
  }

  public static void main(String[] args) throws IOException, InvalidFormatException{
    CreateExcel();
  }

}
