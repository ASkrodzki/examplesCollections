package test1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //Zadanie 01:
        //wypisz wszystkie liczby 3 cyfrowe ktorych suma cyfr dziesiatek i setek jest rowna cyfrze jednosci. np: 314 bo 3+1=4
        //

        System.out.println("EXAMPLE1");

        for (int i = 100; i <= 999; i++) {
            int hundres = i / 100;
            int tens = (i / 10) % 10;
            int ones = i % 10;

            if (hundres + tens == ones) {
                System.out.print(i + ", ");
            }
        }

        //Zadanie 02:
        //dla każdej liczby dwucyfrowej wypisz jej ilosc dzielników.
        //

        for (int i = 10; i <= 99; i++) {
            int divisorCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divisorCount++;
                }
            }
            System.out.println("number: " + i + "has " + divisorCount + " divisor");
        }

        for (int i = 2; i <= 100; i++) {
            int divisors = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divisors++;
                }
            }
            if (divisors == 2) {
                System.out.println(i + " ");
            }

            IntStream.rangeClosed(2, 100)
                    .filter(n -> IntStream.rangeClosed(2, (int) Math.sqrt(n)).allMatch(x -> n % x != 0))
                    .boxed()
                    .toList();
        }


        //Zadanie 03:
        //Poproś użytkownika o podanie 10ciu !roznych! napisów.
        //Wpisz je wszystkie do tablicy a następnie:
        //- wypisz najdłuższy napis i ile ma znaków.
        //- wypisz najkrótszy napis i ile ma znaków.
        //- wypisz ile jest palindromów wśród napisów.
        // (palindrom to taki wyraz czytany od tyłu jest taki sam jak czytany od przodu np: kajak)
        // [podczas porownania ignoruj wielkosc liter]
        //

        System.out.println("Enter 5 words");

//        Scanner sc = new Scanner(System.in);
//        List<String> wordsList = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            System.out.println("input " + i + " words");
//            wordsList.add(sc.nextLine());
//        }
//        System.out.println(longestWord(wordsList));
//        System.out.println(shortestString(wordsList));
//        System.out.println(countPalindromes(wordsList));
//        System.out.println("--LIST--");
//        wordsList.forEach(System.out::println);

        //Zadanie 04:
        //Zapytaj użytkownika o liczby rozdzielone spacją, następnie:
        //- wypisz medianę wprowadzonych liczb.
        //- wypisz liczbę z największą ilością dzielników.
        //- wypisz wszystkie liczby pierwsze.
        //

        Scanner sc = new Scanner(System.in);

        System.out.println("Input numbers separated by space:");
        String input = sc.nextLine();

        sc.close();

        String[] parts = input.trim().split(" ");
        int[] numbers = Arrays.stream(parts)
                .mapToInt(Integer::parseInt)
                .toArray();


        System.out.println(Arrays.toString(numbers));

        System.out.println(mediana(numbers));
        System.out.println(counterDivideMost(numbers));
        System.out.println(Arrays.toString(primeNumbers(numbers)));

    }

    private static int[] primeNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
                        .noneMatch(i -> n % i == 0))
                .toArray();
    }

    private static long counterDivideMost(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .max(Comparator.comparing(Main::countDivisor))
                .orElseThrow();
    }

    private static int countDivisor(int number) {
        int count = 0;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count;
    }

    private static double mediana(int[] numbers) {
        Arrays.sort(numbers);
        double median;
        int length = numbers.length;
        if (length % 2 == 0) {
            median = (numbers[length / 2 - 1] + numbers[length / 2]) / 2.0;
        } else {
            median = numbers[length / 2];
        }
        return median;
    }


    public static String longestWord(List<String> list) {
        //  return list.stream().sorted().findFirst().orElse(null);
        String max = list.getFirst();
        for (String s : list) {
            if (s.length() > max.length()) {
                max = s;
            }
        }
        return max;
    }

    public static String shortestString(List<String> list) {
        return list.stream().min(Comparator.comparing(String::length)).orElse(null);
    }

    public static long countPalindromes(List<String> list) {
        return list.stream()
                .filter(s -> new StringBuilder(s).reverse().toString().equalsIgnoreCase(s))
                .count();
    }

    public static long countPalindromes2(List<String> list) {
        int counter = 0;
        for (String s : list) {
            if (isPalindrom(s)) {
                counter++;
            }
        }
        return counter;
    }

    public static long countPalindromes3(List<String> list) {

        long count = 0;

        for (String s : list) {

            boolean isPalindrome = true;

            for (int i = 0; i < s.length() / 2; i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                count++;
            }
        }
        return count;
    }


    public static boolean isPalindrom(String text) {

        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }
        return true;


    }
}

