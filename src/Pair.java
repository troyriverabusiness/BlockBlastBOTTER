public class Pair<A,B> {

    public A rows;
    public B columns;

    public Pair(A first, B second) {
        this.rows = first;
        this.columns = second;
    }

    public A getRows() {
        return rows;
    }

    public B getColumns() {
        return columns;
    }
}
