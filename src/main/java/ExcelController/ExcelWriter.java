package ExcelController;

import DbController.HibernateCrud;
import DbModel.HibernateUtil;
import condeso.Condeso;
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
import javafx.scene.paint.Color;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.CustomIndexedColorMap;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tiendas.Tiendas;

public class  ExcelWriter {

  private final String[] columns = {"GM", "GM","G", "F", "D", "B", "R"};
  private CellStyle borders;
  private Workbook workbookMaster;
  private Workbook workbookCondesos;
  private CellStyle borders2;
  private List<Tiendas> tiendas;
  private List<Condeso> condesos;
  private LocalDate calendar;
  private Row[] hourRows;

  public ExcelWriter(List<Tiendas> tiendas,List<Condeso> condesos,LocalDate calendar){
    workbookMaster = new XSSFWorkbook();
    workbookCondesos = new XSSFWorkbook();
    borders = createBordersStyle(workbookMaster);
    borders2 = createBordersStyle(workbookCondesos);
    this.tiendas = tiendas;
    this.condesos = condesos;
    this.calendar = calendar;
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

  public void CreateHorarioMasterExcel()
      throws IOException, InvalidFormatException {

    CreationHelper createHelper = workbookMaster.getCreationHelper();

    createCondesoSheets(condesos, 1, 1, calendar);

    int i = 0;
    for (Tiendas t:tiendas
    ) {
      // Create a Sheet
      Sheet sheet = workbookMaster.createSheet(t.getNombre());
      workbookMaster.setSheetOrder(t.getNombre(),i);
      
      createCondesoLists(sheet,1,1);

      hourRows = createHourRows(5, sheet);
      createHourList(sheet, 0);

      SetHorarioMaster(t.getMaster(), sheet, calendar);
      i++;
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

  private void createCondesoLists(Sheet sheet, int i, int i1) {
    for (Condeso c:condesos
    ) {

    }
  }

  private void createCondesoSheets(List<Condeso> condesos, int column, int row, LocalDate calendar){
    int ogColumn = column;
    for (Condeso c:condesos){
      Sheet sheet = workbookMaster.createSheet(c.getAbreviacion());
      int lettersRow = 4;
      int dayOfMonthRow = 3;
      int dayOfWeekRow = 2;
      int turnosRegRow = 5;
      for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
      {
        setLettersUP(sheet, column, lettersRow, borders);
        setDayOfWeek(calendar.withDayOfMonth(i).getDayOfWeek(), sheet, column,dayOfWeekRow, workbookMaster);
        setDayOfMonth(calendar.withDayOfMonth(i), sheet, column, dayOfMonthRow, workbookMaster);
        setTurnosRegion(sheet, column, turnosRegRow);
        if(calendar.withDayOfMonth(i).getDayOfWeek() == DayOfWeek.SUNDAY){
          lettersRow += 20;
          dayOfMonthRow += 20;
          dayOfWeekRow += 20;
          turnosRegRow += 20;
          column = ogColumn;
        }
        column += 8;
      }
    }
  }

  private void SetHorarioMaster(HorarioMaster master, Sheet sheet, LocalDate calendar) {
    int column = 1;//columna
    int row = 0;
    int lettersRow = 4;
    int dayOfMonthRow = 3;
    int dayOfWeekRow = 2;
    int turnosRegRow = 5;
    for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
    {
      setLettersUP(sheet, column, lettersRow, borders);
      setDayOfWeek(calendar.withDayOfMonth(i).getDayOfWeek(), sheet, column,dayOfWeekRow, workbookMaster);
      setDayOfMonth(calendar.withDayOfMonth(i), sheet, column, dayOfMonthRow, workbookMaster);
      setTurnosRegion(sheet, column, turnosRegRow);
      Map<LocalDate, Dias> mes = master.getMes();
      Dias dia = mes.get(calendar.withDayOfMonth(i));
      if(dia!=null){
        setDias(dia, column, row,sheet);
      }
      column+=8;
    }
  }

  private void setDias(Dias dia, int column, int row,Sheet sheet) {
    for (Turnos turno:dia.getTurnos()){
      setTurno(turno, column, row,sheet);
    }
  }

  private void setTurno(Turnos turno, int column, int row ,Sheet sheet) {
    int hourIndex = -4 + turno.getInicio();
    for (int i = 0; i <= turno.getDuracion(); i++){
      Cell cell = sheet.getRow(hourIndex).getCell(
          column + turno.getTipoTurno().ordinal() + 1);
      if (cell == null){
        cell = sheet.getRow(hourIndex).createCell(
            column + turno.getTipoTurno().ordinal() + 1);
      }
      if(turno.getCondeso() != null){
        cell.setCellValue(turno.getCondeso().getAbreviacion());
        cell.setCellStyle(colorStyle(turno.getCondeso().getColor()));
      }
      else {
        cell.setCellValue("-");
      }
      hourIndex += 1;
    }
  }

  private CellStyle colorStyle(String hexColor) {
    XSSFCellStyle style = (XSSFCellStyle) workbookMaster.createCellStyle();
    Color fx = Color.web(hexColor);
    java.awt.Color awtColor = new java.awt.Color((float) fx.getRed(),
        (float) fx.getGreen(),
        (float) fx.getBlue(),
        (float) fx.getOpacity());
    byte[] rgb = {(byte)fx.getRed(), (byte) fx.getGreen(),(byte) fx.getBlue(),(byte) fx.getOpacity()};
    XSSFColor color = new XSSFColor(awtColor, new DefaultIndexedColorMap());
    style.setFillForegroundColor(color);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.getFont().setFontHeightInPoints((short) 9);
    return style;
  }

  private void setDayOfMonth(LocalDate date, Sheet sheet, int column, int row, Workbook book) {
    Row r = sheet.getRow(row);
    if(r == null){
      r = sheet.createRow(row);
    }
    Cell cell = r.createCell(column);
    String day =  Integer.toString(date.getDayOfMonth());
    cell.setCellValue(day);
    cell.setCellStyle(centerStyle(book));
    CellRangeAddress regionDia =  new CellRangeAddress(
        row, //first row (0-based)
        row, //last row  (0-based)
        column, //first column (0-based)
        column + 6 ); //last column  (0-based)
    //Unifica las celdas
    sheet.addMergedRegion(regionDia);
    //Les pone margen
    setRegionBorderWithMedium(regionDia, sheet);
  }

  private void setLettersUP(Sheet sheet, int column, int row, CellStyle borders){
    Row r = sheet.getRow(row);
    if(r == null){
      r = sheet.createRow(row);
    }
    for(int i = 0; i < columns.length; i++) {
      Cell cell = r.createCell(i + column);
      cell.setCellValue(columns[i]);
      cell.setCellStyle(borders);
      sheet.setColumnWidth(i + column, 800);
    }
  }

 private void setDayOfWeek(DayOfWeek dayOfWeek,Sheet sheet, int column, int row, Workbook book){
    Row r = sheet.getRow(row);
    if(r == null){
      r = sheet.createRow(row);
    }
    Cell cell = r.createCell(column);
    String day =  dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMAN);
    cell.setCellValue(day);
    cell.setCellStyle(centerStyle(book));
    CellRangeAddress regionDia =  new CellRangeAddress(
       row, //first row (0-based)
       row, //last row  (0-based)
       column, //first column (0-based)
       column + 6 ); //last column  (0-based)
    //Unifica las celdas
    sheet.addMergedRegion(regionDia);
    //Les pone margen
    setRegionBorderWithMedium(regionDia, sheet);
 }

  private void createHourList(Sheet sheet, int column) {
    String[] rows = {"08-09","09-10", "10-11","11-12", "12-13", "13-14", "14-15", "15-16", "16-17",
        "17-18", "18-19","19-20", "20-21", "21-22", "22-23", "23-24"};
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

  private void setTurnosRegion(Sheet sheet, int column, int row){
    CellRangeAddress turnosRegion = new CellRangeAddress(
        row,row + 14,column,column + 6
    );
    setRegionBorderWithMedium(turnosRegion, sheet);
  }

  private CellStyle centerStyle(Workbook book){
    CellStyle c = book.createCellStyle();
    c.setAlignment(HorizontalAlignment.CENTER);
    return c;
  }

  public static void main(String[] args) throws IOException, InvalidFormatException{
    ExcelWriter excelWriter = new ExcelWriter(HibernateCrud.GetAllTiendas(),HibernateCrud.GetAllCondesos(),
        LocalDate.of(2018,11,1));
    excelWriter.CreateHorarioMasterExcel();
    HibernateUtil.shutdown();
  }

}
