package condeso;

import java.util.Comparator;

public class CompareCondesos2 implements Comparator<Condeso> {
    @Override
    public int compare(Condeso o1, Condeso o2) {
        return Integer.compare(o1.getPriorityValue(), o2.getPriorityValue());
    }
}
