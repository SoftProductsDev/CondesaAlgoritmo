package condeso;

import java.util.Comparator;

public class CompareCondesos implements Comparator<Condeso> {
    @Override
    public int compare(Condeso o1, Condeso o2) {
        double d1 = ((double) o1.getHoras())/((double) o1.getHorasAsignadas());
        double d2 = ((double) o2.getHoras())/((double) o2.getHorasAsignadas());
        if(d1 != d2){
        return Double.compare(d1, d2);}
        else return Integer.compare(o2.getLevel(), o1.getLevel());

    }
}
