package examples.arrays.methods;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] arrayInt = new int[]{4, 5, 6};
        int[] arrayInt2 = new int[]{4, 5, 6, 7, 8, 10};
        char[] letters = {'J', 'a', 'v', 'a'};
        List<String> stringList = new ArrayList<>(Arrays.asList("a", "d", "b", "c"));

        System.out.println(Arrays.toString(print2TimesBiggerArray(arrayInt)));
        System.out.println(sumOfArrayByIndex(arrayInt2, 2));
        System.out.println(Arrays.toString(reverseArray(arrayInt2)));
        System.out.println(Arrays.toString(reverseArrayEven(arrayInt2)));
        System.out.println(Arrays.toString(reverseCharArray(letters)));
        System.out.println(Arrays.toString(bubbleSort(arrayInt2)));

        //multi

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.deepToString(matrix1));

        int[][] matrix = multiArrayTask();
        System.out.println(Arrays.deepToString(multiArrayTask()));
        System.out.println(minValueFromMatrix(matrix));
        System.out.println(maxValueFromMatrix(matrix));
        System.out.println(avgValueFromMatrix(matrix));
        System.out.println(checkNumbersAboveAndBelow50(matrix));
    }

    private static int minValueFromMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .min().orElse(0);
    }

    private static int maxValueFromMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .max().orElse(0);
    }

    private static double avgValueFromMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .average().orElse(0);

    }

    private static boolean checkNumbersAboveAndBelow50(int[][] matrix) {
        return false;

    }

    private static int[][] multiArrayTask() {
        int[][] matrix = new int[5][8];
        Random random = new Random();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(81) + 10;
            }
        }
        return matrix;
    }

    private static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }


    public static char[] reverseCharArray(char[] arr) {
        char[] result = new char[arr.length];
        IntStream.range(0, arr.length)
                .forEach(i -> result[i] = arr[arr.length - 1 - i]);
        return result;
    }

    public static String[] reverseIntArray3(String[] arr) {
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        Collections.reverse(list);
        return list.toArray(new String[0]);
    }

    public static int[] reverseIntArray3(int[] arr) {
        return IntStream.range(0, arr.length)
                .map(i -> arr[arr.length - 1 - i])
                .toArray();
    }

    public static int[] reverseArrayEven(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (left < right && arr[left] % 2 != 0) left++;
            while (left < right && arr[right] % 2 != 0) right--;

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return arr;
    }

    public static int[] reverseArray(int[] arr) {
        return IntStream.range(0, arr.length)
                .map(i -> arr[arr.length - 1 - i])
                .toArray();
    }

    public static int sumOfArrayByIndex(int[] array, int index) {
//        return IntStream.range(0, index)
//                .map(i -> array[i])
//                .sum();
        return Arrays.stream(array, 0, index).sum();
    }


    public static int[] print2TimesBiggerArray(int[] array) {

        int basicArrayLength = array.length;

        int[] resultArray = new int[basicArrayLength * 2];

        resultArray[resultArray.length - 1] = resultArray.length;

        return resultArray;
    }
}
