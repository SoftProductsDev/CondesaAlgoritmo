package condeso;

public class AvailableDay {
    public long Id ;
    public int CalendarDay ;
    public int Start ;
    public int End ;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getCalendarDay() {
        return CalendarDay;
    }

    public void setCalendarDay(int calendarDay) {
        CalendarDay = calendarDay;
    }

    public int getStart() {
        return Start;
    }

    public void setStart(int start) {
        Start = start;
    }

    public int getEnd() {
        return End;
    }

    public void setEnd(int end) {
        End = end;
    }
}
