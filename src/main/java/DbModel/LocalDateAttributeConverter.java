package DbModel;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

  @Override
  public Date convertToDatabaseColumn(LocalDate locDate) {
    var Date1 = Date.valueOf(locDate);
    Date1.toString();
    return (locDate == null ? null : Date1);
  }

  @Override
  public LocalDate convertToEntityAttribute(Date sqlDate) {
    var date = (sqlDate == null ? null : new Date(sqlDate.getTime()).toLocalDate());
    return date.plusDays(1);
  }
}
