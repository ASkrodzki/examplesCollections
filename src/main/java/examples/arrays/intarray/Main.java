package examples.arrays.intarray;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        String[] stringArray = new String[]{"Arkadiusz", "Tomasz", "Mikołaj"};
        int[] intArray = new int[]{5, 10, 15, 5, 20, 22, 4, 6};
        int[] intArray2 = new int[]{1, 2, 3, 5};
        int[] intArray3 = new int[]{3, 6, 12};
        Integer[] integerArray = new Integer[]{5, 10, 15, 20, 22, 4, 6};

        System.out.println("ex1");
        System.out.println(findBiggestElementsOfArray(stringArray));
        System.out.println(findBiggestInt(intArray));
        System.out.println(findBiggestElementsOfArray(integerArray));
        System.out.println("ex2");
        System.out.println(findSecondBiggestEle(integerArray));
        System.out.println("ex3");
        System.out.println(sumOfElements(intArray));

        System.out.println("ex4");
        System.out.println(isSorted(intArray));

        System.out.println(Arrays.toString(reverseArray(intArray)));

        System.out.println("ex5");
        System.out.println(mostOftenOccursIn(intArray));

        System.out.println(Arrays.toString(removeDuplicates(intArray)));

        System.out.println("Ex6");
        System.out.println(findMissingNumber(intArray2));
        System.out.println(findMissingNumber(intArray3));

        System.out.println(Arrays.toString(rotateElement(intArray2, 1)));
        System.out.println(Arrays.toString(rotateElementLeft(intArray2, 1)));
        System.out.println(Arrays.toString(rotateElementLeft(intArray2, 1)));
    }


    //Znajdź największy element tablicy.

    public static <T extends Comparable<T>> T findBiggestElementsOfArray(T[] array) {
        return Arrays.stream(array)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("array is empty"));
    }

    public static int findBiggestInt(int[] array) {
        return Arrays.stream(array)
                .max()
                .orElse(0);
    }

    //Znajdź drugi największy element tablicy.

    public static Integer findSecondBiggestEle(Integer[] arr) {
        return Arrays.stream(arr).sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
    }

    //Oblicz sumę wszystkich elementów.

    public static int sumOfElements(int[] array) {
        int sum = 0;

        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    //Sprawdź, czy tablica jest posortowana rosnąco.

    public static boolean isSorted(int[] array) {
        return IntStream.range(0, array.length - 1)
                .allMatch(i -> array[i] <= array[i + 1]);
    }

    public static boolean isSorted2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //Odwróć tablicę bez użycia dodatkowej tablicy.

    public static int[] reverseArray(int[] array) {
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - 1 - i];
        }
        return result;
    }

    public static int[] reverseArray5(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    public static int[] reverseArray4(int[] array) {
        Integer[] arrayResult = Arrays.stream(array)
                .boxed()
                .toArray(Integer[]::new);
        Collections.reverse(Arrays.asList(arrayResult));

        return Arrays.stream(arrayResult)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static int[] reverseArray3(int[] array) {
        Integer[] arrayResult = Arrays.stream(array)
                .mapToObj(Integer::valueOf)
                .toArray(Integer[]::new);
        Collections.reverse(Arrays.asList(arrayResult));

        return Arrays.stream(arrayResult)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static int[] reverseArray2(int[] array) {
        return IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .toArray();
    }

    //Znajdź element występujący najczęściej.

    public static int mostOftenOccursIn(int[] array) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        return map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();


    }

    public static int mostOftenOccursIn3(int[] array) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int maxCount = 0;
        int result = array[0];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;


    }

    public static int mostOftenOccursIn2(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

    }

    //Usuń duplikaty z tablicy.


    public static int[] removeDuplicates(int[] array) {
        int[] temp = new int[array.length];
        int size = 0;

        for (int i = 0; i < array.length; i++) {
            boolean exists = false;

            for (int j = 0; j < size; j++) {
                if (temp[j] == array[i]) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                temp[size++] = array[i];
            }
        }
        return temp;
    }

    public static int[] removeDuplicates4(int[] array) {
        Set<Integer> seen = new LinkedHashSet<>();
        for (int i : array) {
            seen.add(i);
        }

        int[] result = new int[seen.size()];
        int index = 0;
        for (Integer i : seen) {
            result[index++] = i;
        }
        return result;
    }


    public static int[] removeDuplicates3(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static int[] removeDuplicates2(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .distinct()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    //Znajdź brakującą liczbę:

    //[1,2,3,5] → 4
    public static int findMissingNumber(int[] array) {
        int step = array[1] - array[0];

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] != step) {
                return array[i] + step;
            }
        }
        return -1;

    }

    //is aritmetic
    public static boolean isAritmetic(int[] array) {
        int step = array[1] - array[0];
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] != step) {
                return false;
            }
        }
        return true;
    }

    //is geomegric

    public static boolean isGeometric(int[] array) {
        double ratio = (double) array[1] / array[0];
        for (int i = 1; i < array.length - 1; i++) {
            if ((double) array[i + 1] / array[i] != ratio) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFibonacci(int[] array) {
        for (int i = 2; i < array.length; i++) {
            if (array[i] != array[i - 1] + array[i - 2]) {
                return false;
            }
        }
        return true;

    }

    // Napisz metodę, która dla tablicy liczb całkowitych i liczby k zwraca nową tablicę,
    // będącą wynikiem przesunięcia elementów o k pozycji w prawo (z zawijaniem).
    // [1, 2, 3, 4, 5] k = 1
    // -> [5, 1, 2, 3, 4]

    public static int[] rotateElement(int[] array, int k) {
        return Optional.ofNullable(array)
                .stream()
                .flatMapToInt(arr -> IntStream.range(0, arr.length)
                        // 0 % 5 = 0
                        // 1 % 5 = 1
                        // 2 % 5 = 2
                        // 3 % 5 = 3
                        // 4 % 5 = 4
                        .map(i -> arr[(i - k + arr.length) % arr.length]))
                .toArray();
    }

    private static int[] rotateElementLeft(int[] arr, int k) {
        k %= arr.length;

        int finalK = k;
        return IntStream.range(0, arr.length)
                .map(i -> arr[(i + finalK) % arr.length])
                .toArray();
    }

    //Znajdź parę liczb, której suma daje podaną wartość k.

    public static int[] findPair(int[] array, int k) {
        Set<Integer> seen = new HashSet<>();

        for (int i : array) {
            int complement = k - i;
            if (seen.contains(complement)) {
                return new int[]{complement, i};
            }

            seen.add(i);
        }
        return new int[0];
    }

    //Przesuń wszystkie zera na koniec:


    //[1,0,3,0,5]
    //→ [1,3,5,0,0]


    public static int[] moveZerosToEnd(int[] array) {
        int[] result = new int[array.length];
        int index = 0;

        for (int num : array) {
            if (num != 0) {
                result[index++] = num;
            }
        }
        return result;
    }

}