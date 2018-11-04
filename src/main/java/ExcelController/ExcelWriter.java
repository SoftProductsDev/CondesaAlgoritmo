package ExcelController;

import DbController.HibernateCrud;
import horario.HorarioMaster;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tiendas.Tiendas;

public class  ExcelWriter {

  private static String[] columns = {"GM", "GM","G", "F", "D", "B", "R"};
  private static CellStyle borders;
  private static Workbook workbook;
  private Row[] hourRows;

  public ExcelWriter(){
    workbook = new XSSFWorkbook();
    borders = createBordersStyle();
    hourRows = new Row[15];
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

  public void CreateHorarioMasterExcel(List<Tiendas> tiendas, Month month)
      throws IOException, InvalidFormatException {

    CreationHelper createHelper = workbook.getCreationHelper();

    for (Tiendas t:tiendas
    ) {
      // Create a Sheet
      Sheet sheet = workbook.createSheet(t.getNombre());

      createHourList(sheet);

      // Create a Row
      Row lettersRow = sheet.createRow(4);
      Row dayOfMonthRow = sheet.createRow(3);
      Row dayOfWeekRow = sheet.createRow(2);

      for(int i = 1; i < 100; i+=8){
        setLettersUP(sheet, i, lettersRow);
        setDayOfWeek(DayOfWeek.SATURDAY, sheet, i,dayOfWeekRow);
        setDayOfMonth(LocalDate.now(), sheet, i, dayOfMonthRow);
        setTurnosRegion(sheet, i);
      }
    }
    // Write the output to a file
    //Tambien puede especificar el path ("C:\\Report\\TestCase.xlsx"));
    FileOutputStream fileOut = new FileOutputStream("Plan " + month.getDisplayName
        (TextStyle.FULL, Locale.GERMAN) + ".xlsx");
    workbook.write(fileOut);
    fileOut.close();

    // Closing the workbook
    workbook.close();
  }

  private void setDayOfMonth(LocalDate date, Sheet sheet, int column, Row row) {
    Cell cell = row.createCell(column);
    String day =  Integer.toString(date.getDayOfMonth());
    cell.setCellValue(day);
    cell.setCellStyle(centerStyle());
    CellRangeAddress regionDia =  new CellRangeAddress(
        3, //first row (0-based)
        3, //last row  (0-based)
        column, //first column (0-based)
        column + 6 ); //last column  (0-based)
    //Unifica las celdas
    sheet.addMergedRegion(regionDia);
    //Les pone margen
    setRegionBorderWithMedium(regionDia, sheet);
  }

  private void setLettersUP(Sheet sheet, int column, Row row){

    // Create cells
    for(int i = 0; i < columns.length; i++) {
      Cell cell = row.createCell(i + column);
      cell.setCellValue(columns[i]);
      cell.setCellStyle(borders);
      sheet.setColumnWidth(i + column, 800);
    }
  }

 private void setDayOfWeek(DayOfWeek dayOfWeek,Sheet sheet, int column, Row row){
   Cell cell = row.createCell(column);
   String day =  dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN);
   cell.setCellValue(day);
   cell.setCellStyle(centerStyle());
   CellRangeAddress regionDia =  new CellRangeAddress(
       2, //first row (0-based)
       2, //last row  (0-based)
       column, //first column (0-based)
       column + 6 ); //last column  (0-based)
   //Unifica las celdas
   sheet.addMergedRegion(regionDia);
   //Les pone margen
   setRegionBorderWithMedium(regionDia, sheet);
 }

  private void createHourList(Sheet sheet) {
    String[] rows = {"08-09","09-10", "10-11","11-12", "12-13", "13-14", "14-15", "15-16", "16-17",
        "17-18", "19-20", "20-21", "21-22", "22-23", "23-24"};
    int i = 5;
    for (String row:rows
    ) {
       Row r = sheet.createRow(i);
       hourRows[i - 5] = r;
       Cell cell = r.createCell(0);
       cell.setCellValue(row);
       i++;
    }
  }

  private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
  }

  private void setTurnosRegion(Sheet sheet, int column){
    CellRangeAddress turnosRegion = new CellRangeAddress(
        5,19,column,column + 6
    );
    setRegionBorderWithMedium(turnosRegion, sheet);
  }

  private CellStyle centerStyle(){
    CellStyle c = workbook.createCellStyle();
    c.setAlignment(HorizontalAlignment.CENTER);
    return c;
  }

  public static void main(String[] args) throws IOException, InvalidFormatException{
    ExcelWriter excelWriter = new ExcelWriter();
    excelWriter.CreateHorarioMasterExcel(HibernateCrud.GetAllTiendas(), Month.OCTOBER);
  }

}
