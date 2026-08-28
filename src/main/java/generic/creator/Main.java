package generic.creator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Integer> src1 = Arrays.asList(1, 6, 8, 6, 10);
        System.out.println(src1);
        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
        System.out.println(src2);
    }


    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = integer -> integer < 10;

        Mapper<Integer, Integer> map = input -> input + 10;
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

}
