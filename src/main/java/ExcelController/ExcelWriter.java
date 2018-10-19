package ExcelController;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class  ExcelWriter {

  private static String[] columns = {"GM", "GM","G", "F", "D", "B", "R"};
  private static CellStyle borders;
  private static Workbook workbook;

  public ExcelWriter(){
    workbook = createWorkbook();
    borders = createBordersStyle();
  }

  private Workbook createWorkbook() {
    // Create a Workbook generates new Excel file
    Workbook workbook = new XSSFWorkbook();
    return workbook;
  }

  private CellStyle createBordersStyle() {
    // Style the cell with borders all around.
    CellStyle borders = workbook.createCellStyle();
    borders.setBorderBottom(BorderStyle.THIN);
    borders.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    borders.setBorderLeft(BorderStyle.THIN);
    borders.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    borders.setBorderRight(BorderStyle.THIN);
    borders.setRightBorderColor(IndexedColors.BLACK.getIndex());
    borders.setBorderTop(BorderStyle.THIN);
    borders.setTopBorderColor(IndexedColors.BLACK.getIndex());
    return borders;
  }

  public void CreateHorarioMasterExcel() throws IOException, InvalidFormatException {

    CreationHelper createHelper = workbook.getCreationHelper();

    // Create a Sheet
    Sheet sheet = workbook.createSheet("HorarioMaster");



    // Create a Row
    Row lettersRow = sheet.createRow(4);

    // Create cells
    for(int i = 0; i < columns.length; i++) {
      Cell cell = lettersRow.createCell(i + 1);
      cell.setCellValue(columns[i]);
      cell.setCellStyle(borders);
    }

    Row row = sheet.createRow(2);
    Cell cell = row.createCell(1);
    cell.setCellValue("Mo");

    sheet.addMergedRegion(new CellRangeAddress(
        2, //first row (0-based)
        2, //last row  (0-based)
        1, //first column (0-based)
        7  //last column  (0-based)
    ));

    sheet.addMergedRegion(new CellRangeAddress(
        3, //first row (0-based)
        3, //last row  (0-based)
        1, //first column (0-based)
        7  //last column  (0-based)
    ));

    CellRangeAddress turnosRegion = new CellRangeAddress(
        5,17,1,7
    );

    createHourList(sheet);
    setRegionBorderWithMedium(turnosRegion, sheet);

    // Write the output to a file
    //Tambien puede especificar el path ("C:\\Report\\TestCase.xlsx"));
    FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Closing the workbook
    workbook.close();
  }

  private void createHourList(Sheet sheet) {
    String[] rows = {"09-10", "10-11","11-12", "12-13", "13-14", "14-15", "15-16", "16-17",
        "17-18", "19-20", "20-21", "21-22", "22-23"};
    int i = 5;
    for (String row:rows
    ) {
       Row r = sheet.createRow(i);
       Cell cell = r.createCell(0);
       cell.setCellValue(row);
       i++;
    }
  }

  private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
    Workbook wb = sheet.getWorkbook();
    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
  }

  public static void main(String[] args) throws IOException, InvalidFormatException{
    ExcelWriter excelWriter = new ExcelWriter();
    excelWriter.CreateHorarioMasterExcel();
  }

}
