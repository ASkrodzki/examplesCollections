package test1;

import java.util.*;
import java.util.stream.IntStream;

public class SecondMain {
    public static void main(String[] args) {

        //Sprawdź, czy suma cyfr setek i dziesiątek liczby trzycyfrowej jest równa cyfrze jedności (np. 314 → 3+1=4).
        System.out.println(hundresAndTensEqualsOnes(101));
        System.out.println(hundresAndTensEqualsOnes(151));
        //Policz ilość dzielników podanej liczby.
        System.out.println(countDivisorsOfNumber(6));
        System.out.println(countDivisorsOfNumber(5));
        System.out.println(countDivisorsOfNumber(7));
        //Sprawdź, czy podana liczba jest liczbą pierwszą.
        System.out.println(isPrime(7));
        System.out.println(isPrime(70));
        System.out.println(isPrime2(7));
        System.out.println(isPrime2(70));
        //Zwróć tablicę wszystkich liczb pierwszych do podanego zakresu.
        System.out.println(Arrays.toString(getPrimeArray(20)));
        System.out.println(Arrays.toString(getPrimeArray2(20)));
        System.out.println(Arrays.toString(getPrimeArray3(20)));
        //Znajdź najdłuższy napis w liście słów.
        //Znajdź najkrótszy napis w liście słów.
        //Policz, ile słów w liście to palindromy (ignorując wielkość liter).

        List<String> words = new ArrayList<>(List.of("Anna", "Arek"));
        System.out.println(countPalindrome(words));
        System.out.println(countPalindrome2(words));
        System.out.println(countPalindrome3(words));
        //Oblicz medianę tablicy liczb.
        int[] numbersArray = new int[]{5, 10, 5, 15, 25};
        System.out.println(median(numbersArray));
        //Znajdź liczbę z tablicy, która ma najwięcej dzielników.
        System.out.println(mostDividors(numbersArray));
        System.out.println(mostDividors2(numbersArray));

        System.out.println(isGeometric(numbersArray));
    }

    public static boolean isGeometric(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }

        for (int i = 2; i <= numbers.length; i++) {
            if (numbers[i] - numbers[i - 1] != numbers[1] - numbers[0]) {
                return false;
            }
        }
        return true;
    }

    private static int mostDividors(int[] numbersArray) {
        return Arrays.stream(numbersArray)
                .boxed()
                .max(Comparator.comparing(SecondMain::countDividors))
                .orElse(0);

    }

    private static int mostDividors2(int[] numbersArray) {
        int best = numbersArray[0];

        for (int i : numbersArray) {
            if (countDividors(i) > countDividors(best)) {
                best = i;
            }
        }
        return best;

    }

    public static int countDividors(int number) {
        int count = 0;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }


    private static double median(int[] numbersArray) {

        Arrays.sort(numbersArray);
        int length = numbersArray.length;

        if (length % 2 == 0) {
            return (numbersArray[length / 2 - 1] + numbersArray[length / 2]) / 2.0;
        }
        return numbersArray[length / 2];

    }

    private static int countPalindrome(List<String> words) {
        return (int) words.stream()
                .filter(text -> text.equalsIgnoreCase(new StringBuilder(text).reverse().toString()))
                .count();
    }

    private static int countPalindrome2(List<String> words) {
        int counter = 0;

        for (String word : words) {
            if (word.equalsIgnoreCase(new StringBuilder(word).reverse().toString())) {
                counter++;
            }
        }
        return counter;
    }

    private static int countPalindrome3(List<String> words) {
        int counter = 0;
        for (String word : words) {
            if (isPalindrome(word)) {
                counter++;
            }
        }
        return counter;
    }

    public static boolean isPalindrome(String text) {

        String lowerCase = text.toLowerCase();
        for (int i = 0; i < lowerCase.length() / 2; i++) {
            if (lowerCase.charAt(i) != lowerCase.charAt(lowerCase.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static int[] getPrimeArray(int limit) {
        return IntStream.rangeClosed(2, limit)
                .filter(n -> IntStream.rangeClosed(2, (int) Math.sqrt(n))
                        .allMatch(i -> n % i != 0))
                .toArray();
    }

    public static int[] getPrimeArray3(int limit) {
        List<Integer> result = new ArrayList<>();

        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] getPrimeArray2(int limit) {

        int count = 0;

        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        int[] primes = new int[count];
        int index = 0;

        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                primes[index] = i;
                index++;
            }
        }
        return primes;

    }

    public static boolean isPrime2(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int number) {
        return !IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .anyMatch(i -> number % i == 0);
    }

    public static int countDivisorsOfNumber(int number) {
        int counter = 0;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                counter++;
            }
        }
        return counter;
    }

    public static boolean hundresAndTensEqualsOnes(int number) {

        int hundres = number / 100;
        int tens = (number / 10) % 10;
        int ones = number % 10;

        if (hundres + tens == ones) {
            return true;
        }
        return false;
    }
}
