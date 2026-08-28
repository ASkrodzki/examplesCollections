package all.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        int[] numbers = {5, 10, 15, 20, 20, 12, 5, 10, 25};
        int[] numbers2 = {5, 10, 15, 20, 20, 12, 5, 10, 25};
        int[] numbers3 = {2, 4, 5, 6, 6, 8, 10, 0, 11, 0};
        //Znajdź największy element tablicy.
        System.out.println(biggestElInArray(numbers));
        System.out.println(biggestElInArray2(numbers));
        System.out.println(biggestElInArray3(numbers));
        //Znajdź drugi największy element tablicy.
        System.out.println(secondBiggestEl(numbers));
        System.out.println(secondBiggestEl2(numbers));
        //Oblicz sumę elementów tablicy.
        //Sprawdź, czy tablica jest posortowana rosnąco.
        System.out.println(isSortedArray(numbers));
        //Odwróć tablicę.
        //Odwróć tablicę bez użycia dodatkowej tablicy.
        System.out.println(Arrays.toString(reverseArray(numbers)));
        System.out.println(Arrays.toString(reverseArray2(numbers2)));
        System.out.println(Arrays.toString(reverseArray3(numbers2)));
        //Odwróć tylko elementy parzyste w tablicy.
        System.out.println("--");
        System.out.println(Arrays.toString(reverseEven(numbers3)));

        //Znajdź element występujący najczęściej.
        System.out.println(mostFrequency(numbers));
        //Usuń duplikaty z tablicy.
        System.out.println(Arrays.toString(removeDuplicates(numbers2)));
        System.out.println(Arrays.toString(removeDuplicates2(numbers2)));

        //Znajdź brakującą liczbę, np. [1,2,3,5] → 4.
        //Sprawdź, czy tablica tworzy ciąg arytmetyczny.
        //Sprawdź, czy tablica tworzy ciąg geometryczny.
        //Sprawdź, czy tablica tworzy ciąg Fibonacciego.
        //Przesuń elementy tablicy o k pozycji w prawo z zawijaniem.
        System.out.println(Arrays.toString(rotateRight(numbers2, 2)));
        //Przesuń elementy tablicy o k pozycji w lewo z zawijaniem.
        System.out.println(Arrays.toString(rotateLeft(numbers2, 3)));
        //Znajdź parę liczb, której suma daje k.
        System.out.println(Arrays.toString(sumOf2DigitsIsK(numbers2, 20)));
        //Przesuń wszystkie zera na koniec tablicy.
        System.out.println(Arrays.toString(moveToLast(numbers3)));

        //Zwróć tablicę unikalnych elementów, które występują więcej niż raz.
        System.out.println(Arrays.toString(uniqueElMoreThanOnce(numbers3)));
        //Połącz wiele tablic int[] w jedną posortowaną tablicę.
        System.out.println(Arrays.toString(mergeArray(numbers3, numbers2)));
        //Zwróć nową tablicę z kwadratami liczb nieparzystych.
        //Zwróć z String[] tylko słowa zawierające przynajmniej jedną cyfrę.
        //Oblicz iloczyn skalarny dwóch tablic double[].

        System.out.println("\n-----");

        //   Zadanie 1.
        //        Napisz program, który tworzy tablicę dwuwymiarową 5x5, uzupełnia ją liczbami
        //        naturalnymi od liczby podanej przez użytkownika (jako zmienna) w dół i liczy sumę
        //        wszystkich wprowadzonych liczb.

        System.out.println("ex marr");
        int[][] multipleArray = createMultipleArray();
        System.out.println(Arrays.deepToString(multipleArray));

    }

    public static int[][] createMultipleArray() {
        //Scanner scanner = new Scanner(System.in);

        int[][] array = new int[5][5];
      //  System.out.println("Input value:");
        int input = 25;
        int sum = 0;
        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = input;
                input--;
                sum += input;
            }
        }

        return array;

    }

    public static String[] wordWithDigit(String[] words) {
        return Arrays.stream(words)
                .filter(w -> w.chars().anyMatch(Character::isDigit))
                .toArray(String[]::new);
    }

    public static int[] squareOddNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .toArray();
    }

    private static int[] mergeArray(int[] a, int[] b) {
        return Stream.of(a, b)
                .flatMapToInt(Arrays::stream)
                .sorted()
                .toArray();
    }

    private static int[] mergeArray2(int[] a, int[] b) {
        return IntStream.concat(Arrays.stream(a), Arrays.stream(b))
                .sorted()
                .toArray();
    }

    private static int[] mergeArray3(int[]... arrays) {
        return Arrays.stream(arrays)
                .flatMapToInt(Arrays::stream)
                .sorted()
                .toArray();
    }

    private static int[] uniqueElMoreThanOnce(int[] numbers3) {
        return Arrays.stream(numbers3)
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue).toArray();

    }

    private static int[] moveToLast(int[] arr) {
        int[] result = new int[arr.length];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                result[index] = arr[i];
                index++;
            }
        }
        return result;
    }

    private static int[] sumOf2DigitsIsK(int[] numbers, int x) {
        int[] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == x) {
                    result[0] = numbers[i];
                    result[1] = numbers[j];
                }
            }
        }
        return result;
    }

    public static int[] rotateRight(int[] array, int x) {
        int[] result = new int[array.length];

        x = x % array.length;

        for (int i = 0; i < array.length; i++) {
            result[(i + x) % array.length] = array[i];
        }
        return result;
    }

    public static int[] rotateLeft(int[] array, int x) {
        int[] result = new int[array.length];

        x = x % array.length;

        for (int i = 0; i < array.length; i++) {
            result[i] = array[(i + x) % array.length];
        }
        return result;

    }

    public static boolean isFibo(int[] numbers) {
        if (numbers == null || numbers.length < 3) {
            return false;
        }

        for (int i = 2; i < numbers.length; i++) {
            if (numbers[i] != numbers[i - 1] + numbers[i - 2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFibo2(int[] numbers) {
        if (numbers == null || numbers.length < 3) {
            return false;
        }
        return IntStream.range(2, numbers.length)
                .allMatch(i -> numbers[i] == numbers[i - 1] + numbers[i - 2]);
    }


    public static boolean isGeometric(int[] numbers) {
        if (numbers == null || numbers.length < 2 || numbers[0] == 0) {
            return false;
        }

        for (int i = 2; i < numbers.length; i++) {
            if (numbers[i] * numbers[0] != numbers[i - 1] * numbers[1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGeometric2(int[] numbers) {
        if (numbers == null || numbers.length < 2 || numbers[0] == 0) {
            return false;
        }


        return IntStream.range(2, numbers.length)
                .allMatch(i -> numbers[i] * numbers[0] != numbers[i - 1] * numbers[1]);
    }

    public static boolean isArithmetic(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }

        for (int i = 2; i < numbers.length; i++) {
            if (numbers[i] - numbers[i - 1] != numbers[1] - numbers[0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isArithmetic2(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }

        return IntStream.range(2, numbers.length)
                .allMatch(i -> numbers[i] - numbers[i - 1] == numbers[1] - numbers[0]);
    }

    public static int[] removeDuplicates2(int[] numbers) {
        return Arrays.stream(numbers)
                .distinct().toArray();
    }

    public static int[] removeDuplicates(int[] numbers) {
        Set<Integer> set = new LinkedHashSet<>();

        for (int number : numbers) {
            set.add(number);
        }
        int[] resultArray = new int[set.size()];

        int index = 0;
        for (Integer i : set) {
            resultArray[index++] = i;
        }
        return resultArray;
    }

    private static int mostFrequency(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);

    }

    private static int[] reverseEven(int[] numbers) {
        int[] result = Arrays.copyOf(numbers, numbers.length);
        int left = 0;
        int right = result.length - 1;

        while (left < right) {
            while (left < right && result[left] % 2 != 0) {
                left++;
            }
            while (left < right && result[right] % 2 != 0) {
                right--;
            }
            if (left < right) {
                int temp = result[left];
                result[left] = result[right];
                result[right] = temp;

                left++;
                right--;
            }
        }
        return result;
    }

    private static int[] reverseArray3(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[numbers.length - 1 - i];
        }
        return result;
    }

    private static int[] reverseArray2(int[] numbers) {
        return IntStream.rangeClosed(1, numbers.length)
                .map(i -> numbers[numbers.length - i])
                .toArray();

    }

    private static int[] reverseArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
        }
        return numbers;
    }


    private static boolean isSortedArray(int[] numbers) {
        return IntStream.range(0, numbers.length - 1)
                .allMatch(i -> numbers[i] < numbers[i + 1]);
    }

    public static int secondBiggestEl(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
    }

    public static int secondBiggestEl2(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        Collections.sort(list);
        return list.get(list.size() - 2);

    }

    public static int biggestElInArray(int[] arr) {
        return Arrays.stream(arr)
                .max()
                .orElse(0);
    }

    public static int biggestElInArray2(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return Collections.max(list);
    }


    public static int biggestElInArray3(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
