package generic.creator;

public interface Mapper<V, T> {

    T map(V v);

}
