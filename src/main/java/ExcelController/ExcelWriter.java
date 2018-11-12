package ExcelController;

import DbController.HibernateCrud;
import horario.Dias;
import horario.HorarioMaster;
import horario.Turnos;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tiendas.Tiendas;

public class  ExcelWriter {

  private final String[] columns = {"GM", "GM","G", "F", "D", "B", "R"};
  private CellStyle borders;
  private Workbook workbookMaster;
  private Workbook workbookCondesos;
  private CellStyle borders2;
  private Row[] hourRows;

  public ExcelWriter(){
    workbookMaster = new XSSFWorkbook();
    workbookCondesos = new XSSFWorkbook();
    borders = createBordersStyle(workbookMaster);
    borders2 = createBordersStyle(workbookCondesos);
  }

  private Row[] createHourRows(int startingRow, Sheet sheet) {
    Row[] result = new Row[17];
    for (int i = 0; i < 17; i++)
     {
      Row r = sheet.createRow(i + startingRow);
      result[i] = r;
     }
     return result;
  }

  private CellStyle createBordersStyle(Workbook book) {
    // Style the cell with borders all around.
    CellStyle borders = book.createCellStyle();
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

  public void CreateHorarioMasterExcel(List<Tiendas> tiendas,LocalDate calendar)
      throws IOException, InvalidFormatException {

    CreationHelper createHelper = workbookMaster.getCreationHelper();

    for (Tiendas t:tiendas
    ) {
      // Create a Sheet
      Sheet sheet = workbookMaster.createSheet(t.getNombre());

      hourRows = createHourRows(5, sheet);
      createHourList(sheet, 0);

      // Create a Row

      /*for(int i = 1; i < 100; i+=8){
        setLettersUP(sheet, i, lettersRow, borders);
        setDayOfWeek(DayOfWeek.SATURDAY, sheet, i,dayOfWeekRow, workbookMaster);
        setDayOfMonth(LocalDate.now(), sheet, i, dayOfMonthRow, workbookMaster);
        setTurnosRegion(sheet, i);
      }*/

      SetHorarioMaster(t.getMaster(), sheet, calendar);
    }
    // Write the output to a file
    //Tambien puede especificar el path ("C:\\Report\\TestCase.xlsx"));
    FileOutputStream fileOut = new FileOutputStream("Plan " + calendar.getMonth().getDisplayName
        (TextStyle.FULL, Locale.GERMAN) + ".xlsx");
    workbookMaster.write(fileOut);
    fileOut.close();

    // Closing the workbook
    workbookMaster.close();
  }

  private void SetHorarioMaster(HorarioMaster master, Sheet sheet, LocalDate calendar) {
    int column = 1;//columna
    //Forma chacalosa, poner metodo que solo tome la primera row con int
    Row lettersRow = sheet.createRow(4);
    Row dayOfMonthRow = sheet.createRow(3);
    Row dayOfWeekRow = sheet.createRow(2);
    for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
    {
      setLettersUP(sheet, column, lettersRow, borders);
      setDayOfWeek(calendar.withDayOfMonth(i).getDayOfWeek(), sheet, column,dayOfWeekRow, workbookMaster);
      setDayOfMonth(calendar.withDayOfMonth(i), sheet, column, dayOfMonthRow, workbookMaster);
      setTurnosRegion(sheet, column);
      Map<LocalDate, Dias> mes = master.getMes();
      Dias dia = mes.get(calendar.withDayOfMonth(i));
      if(dia!=null){
        setDias(dia, column, sheet);
      }
      column+=8;
    }
  }

  private void setDias(Dias dia, int column, Sheet sheet) {
    for (Turnos turno:dia.getTurnos()){
      setTurno(turno, column, sheet);
    }
  }

  private void setTurno(Turnos turno, int column, Sheet sheet) {
    int hourIndex = -3 + turno.getInicio();
    for (int i = 0; i <= turno.getDuracion(); i++){
      Cell cell = sheet.getRow(hourIndex).getCell(
          column + turno.getTipoTurno().ordinal() + 1);
      cell.setCellValue(turno.getCondeso().getAbreviacion());
      //cell.setCellStyle();
      hourIndex += 1;
    }
  }

  private void setDayOfMonth(LocalDate date, Sheet sheet, int column, Row row, Workbook book) {
    Cell cell = row.createCell(column);
    String day =  Integer.toString(date.getDayOfMonth());
    cell.setCellValue(day);
    cell.setCellStyle(centerStyle(book));
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

  private void setLettersUP(Sheet sheet, int column, Row row, CellStyle borders){

    // Create cells
    for(int i = 0; i < columns.length; i++) {
      Cell cell = row.createCell(i + column);
      cell.setCellValue(columns[i]);
      cell.setCellStyle(borders);
      sheet.setColumnWidth(i + column, 800);
    }
  }

 private void setDayOfWeek(DayOfWeek dayOfWeek,Sheet sheet, int column, Row row, Workbook book){
   Cell cell = row.createCell(column);
   String day =  dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN);
   cell.setCellValue(day);
   cell.setCellStyle(centerStyle(book));
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

  private void createHourList(Sheet sheet, int column) {
    String[] rows = {"08-09","09-10", "10-11","11-12", "12-13", "13-14", "14-15", "15-16", "16-17",
        "17-18", "19-20", "20-21", "21-22", "22-23", "23-24"};
    int i = 0;
    for (String row:rows
    ) {
       Row r = hourRows[i];
       Cell cell = r.createCell(column);
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

  private CellStyle centerStyle(Workbook book){
    CellStyle c = book.createCellStyle();
    c.setAlignment(HorizontalAlignment.CENTER);
    return c;
  }

  public static void main(String[] args) throws IOException, InvalidFormatException{
    ExcelWriter excelWriter = new ExcelWriter();
    excelWriter.CreateHorarioMasterExcel(HibernateCrud.GetAllTiendas(),
        LocalDate.of(2018,11,1));
  }

}
