package generic.creator;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {

    private List<T> list;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator when(Selector<T> selector) {
        List<T> resultList = new ArrayList<>();

        for (T el : list) {
            if (selector.select(el)) {
                resultList.add(el);
            }
        }
        return new ListCreator<>(resultList);
    }

    public <V> List<V> mapEvery(Mapper<T, V> mapper) {
        List<V> resultList = new ArrayList<>();

        for (T t : list) {
            resultList.add(mapper.map(t));
        }
        return resultList;
    }
}
