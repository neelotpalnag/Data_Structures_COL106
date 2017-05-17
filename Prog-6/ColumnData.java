import java.util.Comparator;

/**
 * Created by Neelotpal Nag on 4/20/2017.
 */
public class ColumnData implements Comparable<ColumnData> {
    public int colIndex;
    public int colCap;

    public ColumnData(int k, int v) {
        colCap = v;
        colIndex = k;
    }

    public int compareTo(ColumnData compareCapacity) {

        int compareCap = ((ColumnData) compareCapacity).colCap;
        return compareCap - this.colCap;

    }
}