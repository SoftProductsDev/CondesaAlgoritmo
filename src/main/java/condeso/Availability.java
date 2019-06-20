package condeso;

import java.util.List;

public class Availability {
    public long Id ;
    public int Min ;
    public int Max ;
    public List<AvailableDay> availableDays ;
    public int Month ;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int min) {
        Min = min;
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int max) {
        Max = max;
    }

    public List<AvailableDay> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<AvailableDay> availableDays) {
        this.availableDays = availableDays;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }
}
