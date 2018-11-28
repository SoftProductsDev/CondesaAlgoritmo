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
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javafx.scene.paint.Color;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
  private String path;

  public ExcelWriter(List<Tiendas> tiendas,List<Condeso> condesos,LocalDate calendar, String path){
    workbookMaster = new XSSFWorkbook();
    workbookCondesos = new XSSFWorkbook();
    borders = createBordersStyle(workbookMaster);
    borders2 = createBordersStyle(workbookCondesos);
    this.tiendas = tiendas;
    this.condesos = condesos;
    this.calendar = calendar;
    this.path = path;
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

  public void createHorarioMasterExcel()
      throws IOException, InvalidFormatException {

    CreationHelper createHelper = workbookMaster.getCreationHelper();

    createCondesoSheets(condesos, 2, 1, calendar);

    int i = 0;
    int tiendaColumn = 2;
    for (Tiendas t:tiendas
    ) {
      // Create a Sheet
      Sheet sheet = workbookMaster.createSheet(t.getNombre());
      workbookMaster.setSheetOrder(t.getNombre(),i);

      createHourList(sheet, 5, 5);

      createCondesoLists(sheet,1,1);

      SetHorarioMaster(t.getMaster(), sheet, calendar, tiendaColumn);
      i++;
      tiendaColumn += 58;
    }

    // Write the output to a file
    //Tambien puede especificar el path ("C:\\Report\\TestCase.xlsx"));
    FileOutputStream fileOut = new FileOutputStream(path + "Plan " + calendar.getMonth().getDisplayName
        (TextStyle.FULL, Locale.GERMAN) + ".xlsx");
    workbookMaster.write(fileOut);
    fileOut.close();

    // Closing the workbook
    workbookMaster.close();
  }

  private void createCondesoLists(Sheet sheet, int i, int i1) {
    int rowStart = 3;
    int columnStart = 1;
    for (Condeso c:condesos
    ) {
       Row row = sheet.getRow(rowStart);
       if (row == null){
         row = sheet.createRow(rowStart);
       }
       Cell abrevCell = row.createCell(columnStart);
       abrevCell.setCellValue(c.getAbreviacion());
       abrevCell.setCellStyle(colorStyle(c.getColor()));
       Cell nombreCell = row.createCell(columnStart + 1);
       sheet.setColumnWidth(columnStart, 1700);
       nombreCell.setCellValue(c.getNombre());
      rowStart++;
    }
  }

  private void createCondesoSheets(List<Condeso> condesos, int column, int row, LocalDate calendar){
    int ogColumn = column;
    for (Condeso c:condesos){
      Sheet sheet = workbookMaster.createSheet(c.getAbreviacion());
      Cell nombreCell = sheet.createRow(0).createCell(1);
      CellRangeAddress range = new CellRangeAddress(0,0,1,9);
      sheet.addMergedRegion(range);
      nombreCell.setCellStyle(colorStyle("#ffc100"));
      nombreCell.setCellValue("Plan für: " + c.getNombre());
      Cell horasCell = sheet.getRow(0).createCell(17);
      horasCell.setCellValue("Horas Total: " + c.getHorasMes().get(calendar).getHoras());
      CellRangeAddress rangeHoras = new CellRangeAddress(0,0,17,24);
      sheet.addMergedRegion(rangeHoras);
      horasCell.setCellStyle(colorStyle("#ffc100"));
      for (Tiendas t: tiendas){
        createMonth(column, sheet);
        column += 58;
      }
      column = ogColumn;
    }
  }

  private void createMonth(int ogColumn, Sheet sheet){
    int lettersRow = 4;
    int dayOfMonthRow = 3;
    int dayOfWeekRow = 2;
    int turnosRegRow = 5;
    int column = ogColumn;
    for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
    {
      if(i == 1){
        column += 8 * (calendar.withDayOfMonth(i).getDayOfWeek().getValue() - 1);
        createHourList(sheet,column - 1,turnosRegRow);
      }
      setLettersUP(sheet, column, lettersRow, borders);
      setDayOfWeek(calendar.withDayOfMonth(i).getDayOfWeek(), sheet, column,dayOfWeekRow, workbookMaster);
      setDayOfMonth(calendar.withDayOfMonth(i), sheet, column, dayOfMonthRow, workbookMaster);
      setTurnosRegion(sheet, column, turnosRegRow);
      if(calendar.withDayOfMonth(i).getDayOfWeek() == DayOfWeek.SUNDAY){
        createHourList(sheet,column + 7, turnosRegRow);
        lettersRow += 20;
        dayOfMonthRow += 20;
        dayOfWeekRow += 20;
        turnosRegRow += 20;
        column = ogColumn - 8;
      }
      else if(calendar.withDayOfMonth(i).getDayOfWeek() == DayOfWeek.MONDAY){
        createHourList(sheet,column - 1, turnosRegRow);
        sheet.setColumnWidth(column + 7, 1200);
      }
      else {
        sheet.setColumnWidth(column + 7, 1200);
      }
      column += 8;
    }
  }

  private void SetHorarioMaster(HorarioMaster master, Sheet sheet, LocalDate calendar,
      int condesoTiendaColumn) {
    int column = 6;//columna
    int row = 5;
    int lettersRow = 4;
    int dayOfMonthRow = 3;
    int dayOfWeekRow = 2;
    int turnosRegRow = 5;
    for (int i = 1; i <= calendar.getMonth().length(calendar.isLeapYear()); i++)
    {
      setLettersUP(sheet, column, lettersRow, borders);
      DayOfWeek weekDay = calendar.withDayOfMonth(i).getDayOfWeek();
      setDayOfWeek(weekDay, sheet, column,dayOfWeekRow, workbookMaster);
      setDayOfMonth(calendar.withDayOfMonth(i), sheet, column, dayOfMonthRow, workbookMaster);
      if(weekDay == DayOfWeek.SUNDAY){
        createHourList(sheet,column + 7, 5);
      }
      else {
        sheet.setColumnWidth(column + 7, 1200);
      }
      setTurnosRegion(sheet, column, turnosRegRow);
      Map<LocalDate, Dias> mes = master.getMes();
      Dias dia = mes.get(calendar.withDayOfMonth(i));
      if(dia!=null){
        setDias(dia, column, row,sheet, condesoTiendaColumn);
      }
      column+=8;
    }
  }

  private void setDias(Dias dia, int column, int row,Sheet sheet, int condesoColumn) {
    int ogColumn = condesoColumn;
    for (Turnos turno:dia.getTurnos()){
      setTurno(turno, column, row,sheet);
      if(turno.getCondeso() != null) {
        Sheet condesoSheet = workbookMaster.getSheet(turno.getCondeso().getAbreviacion());
        condesoColumn += 8 * (dia.getDate().getDayOfWeek().getValue() - 1);
        TemporalField weekField = WeekFields.of(Locale.GERMAN).weekOfMonth();
        int condesoRow = row;
        int weekNumb = dia.getDate().get(weekField);
        if(dia.getDate().getDayOfWeek() == DayOfWeek.SUNDAY){
          weekNumb -= 1;
        }
        condesoRow += ( weekNumb - 1) * 20;
        setTurno(turno, condesoColumn, condesoRow, condesoSheet);
        condesoColumn = ogColumn;
      }
    }
  }

  private void setTurno(Turnos turno, int column, int row ,Sheet sheet) {
    int hourIndex = (row - 8) + turno.getInicio();
    for (int i = 1; i <= turno.getDuracion(); i++){
      Row r = sheet.getRow(hourIndex);
      if(r == null){
        r = sheet.createRow(hourIndex);
      }
      Cell cell = r.getCell(
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
    cell.setCellValue(day + " " + date.getMonth());
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

  private void createHourList(Sheet sheet, int column, int rowInt) {
    String[] rows = {"08-09","09-10", "10-11","11-12", "12-13", "13-14", "14-15", "15-16", "16-17",
        "17-18", "18-19","19-20", "20-21", "21-22", "22-23", "23-24"};
    CellRangeAddress turnosRegion = new CellRangeAddress(
        rowInt,rowInt + 15,column,column
    );
    for (String row:rows
    ) {
       Row r = sheet.getRow(rowInt);
       if(r == null){
         r = sheet.createRow(rowInt);
       }
       Cell cell = r.createCell(column);
       cell.setCellValue(row);
       cell.setCellStyle(centerStyle(workbookMaster));
       rowInt++;
    }
    sheet.setColumnWidth(column, 1700);
    setRegionBorderWithMedium(turnosRegion, sheet);
  }

  private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
    RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
    RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
  }

  private void setTurnosRegion(Sheet sheet, int column, int row){
    CellRangeAddress turnosRegion = new CellRangeAddress(
        row,row + 15,column,column + 6
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
        LocalDate.of(2018,11,1), "");
    excelWriter.createHorarioMasterExcel();
    HibernateUtil.shutdown();
  }

}
