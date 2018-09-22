package horario;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

public class CompareDias implements Comparator<Dias> {
    //-
    @Override
    public int compare(Dias o1, Dias o2) {
        LocalDate d1 = o1.getDate();
        return d1.compareTo(o2.getDate());
    }
}