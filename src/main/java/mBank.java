import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class mBank {
    public static void main(String[] args) {

    }

    public static boolean isContainsDigit(String text) {
        return Optional.ofNullable(text)
                .stream()
                .flatMapToInt(String::chars)
                //.allMatch(Character::isDigit);
                .allMatch(c -> c >= 0 && c <= 9);
    }

    public static String revereseWords(String text) {
        List<String> words = Arrays.asList(text.split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    public static String reverWords(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static List<String> findDuplicates(List<String> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(n -> n.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static Optional<Integer> findMax(List<Integer> list) {
        return list.stream()
                .max(Integer::compareTo);
    }

    public static int findMax2(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    public static boolean isEvenOrOdd(List<Integer> list) {
        return list.stream()
                .allMatch(i -> i % 2 == 0);
    }

    public static boolean isEven(int x) {
        return x % 2 == 0 ? true : false;
    }

    public static String reverseString(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public static boolean isPalindrom(String text) {
        String newStr = new StringBuilder(text)
                .reverse().toString();

        return text.equals(newStr);
    }

    public static int countletter2(String text) {

        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'o') {
                count++;
            }
        }
        return count;
    }


    public static long countletter(String text) {
        return text.chars()
                .filter(l -> l == 'o')
                .count();
    }

    public static int countLet(String text) {
        int count = 0;

        for (char c : text.toCharArray()) {
            if (c == 'o') {
                count++;
            }
        }
        return count;
    }


}
