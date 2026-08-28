package all.string;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String text = "Arkadiusz";
        String text2 = "Ala ma kota";
        String text3 = "kajAK";
        String text4 = "aajkk";
        //Policz liczbę liter a w napisie, ignorując wielkość liter.
        System.out.println(countLetterA(text));
        System.out.println(countLetterA2(text));
        System.out.println(countLetterA3(text));
        System.out.println(countLetterA4(text));
        System.out.println(countLetterA5(text));
        //Sprawdź, czy napis jest palindromem.
        System.out.println(isPalindrome(text3));
        //Policz liczbę samogłosek w napisie.
        System.out.println(countVowels(text2));
        System.out.println(countVowels2(text2));
        System.out.println(countVowels3(text2));
        //Odwróć cały napis.
        System.out.println(reverseString(text));
        System.out.println(reverseString2(text));
        System.out.println(reverseString3(text));
        //Odwróć kolejność słów w zdaniu.
        System.out.println(reverseWordsInString(text2));
        System.out.println(reverseWordsInString2(text2));
        //Usuń wszystkie spacje z napisu.
        System.out.println(cleanString(text2));
        System.out.println(cleanString2(text2));
        System.out.println(cleanString3(text2));
        //Znajdź najdłuższe słowo w zdaniu.
        System.out.println(longestWord(text2));
        System.out.println(longestWord2(text2));
        //Sprawdź, czy dwa napisy są anagramami.
        System.out.println(isAnagram(text3, text4));

        //Znajdź pierwszy niepowtarzający się znak.
        System.out.println(findFirstUniqueChar(text));
        System.out.println(findFirstUniqueChar2(text));
        System.out.println(findFirstUniqueChar3(text));
        //Skompresuj napis, np. "aaabbcccc" → "a3b2c4".
        System.out.println(compressMethod(text4));
        //Zlicz wystąpienia każdego znaku.
        System.out.println(countAllChar(text4));
        //Usuń z napisu wszystkie powtarzające się znaki.
        System.out.println(removeDuplicateChar(text4));
        //Zamień napis na listę pojedynczych znaków.
        System.out.println(changeToList(text4));
    }

    public static boolean startWithBigLetter(String text) {
        return Arrays.stream(text.split(""))
                .allMatch(s -> Character.isUpperCase(s.charAt(0)));
    }

    public static boolean textHaveDigits(String text) {
        return text.chars()
                .anyMatch(Character::isDigit);

    }

    public static boolean textHaveDigits2(String text) {
        return Character.isDigit(text.charAt(text.length() -1));

    }

    private static long countLetterA(String text) {
        return Arrays.stream(text.split(""))
                .map(c -> c.toLowerCase())
                .filter(c -> c.equals("a"))
                .count();
    }

    private static long countLetterA2(String text) {
        return Arrays.stream(text.split(""))
                .filter(c -> Character.toLowerCase(c.charAt(0)) == 'a')
                .count();
    }

    private static long countLetterA3(String text) {
        return text.chars()
                .filter(c -> Character.toLowerCase(c) == 'a')
                .count();
    }

    private static long countLetterA4(String text) {
        return text.chars()
                .map(Character::toLowerCase)
                .filter(c -> c == 'a')
                .count();
    }

    private static int countLetterA5(String text) {
        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.toLowerCase(text.charAt(i)) == 'a') {
                counter++;
            }
        }
        return counter;
    }

    private static boolean isPalindrome(String text) {
        return new StringBuilder(text)
                .reverse()
                .toString()
                .equalsIgnoreCase(text);
    }

    private static long countVowels(String text) {
        String[] vowels = {"a", "e", "o", "u", "i", "y"};

        return Arrays.stream(text.split(""))
                .map(String::toLowerCase)
                .filter(s -> Arrays.stream(vowels)
                        .anyMatch(v -> v.contains(s)))
                .count();
    }

    private static long countVowels2(String text) {
        String[] vowels = {"a", "e", "o", "u", "i", "y"};
        return text.chars()
                .map(Character::toLowerCase)
                .filter(s -> Arrays.stream(vowels)
                        .anyMatch(v -> v.contains(String.valueOf((char) s))))
                .count();
    }

    private static long countVowels3(String text) {
        int counter = 0;
        String[] vowels = {"a", "e", "o", "u", "i", "y"};

        for (char c : text.toLowerCase().toCharArray()) {
            if (Arrays.asList(vowels).contains(String.valueOf(c))) {
                counter++;
            }
        }
        return counter;
    }

    public static String reverseString(String text) {
        return text.chars()
                .mapToObj(s -> String.valueOf((char) s))
                .reduce("", (a, b) -> b + a);
    }

    public static String reverseString2(String text) {
        char[] chars = text.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static String reverseString3(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public static String reverseWordsInString(String text) {
        return Arrays.stream(text.split(" "))
                .reduce((a, b) -> b + " " + a)
                .orElse("");
    }

    public static String reverseWordsInString2(String text) {
        List<String> words = Arrays.asList(text.split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    public static String cleanString(String text) {
        return Arrays.stream(text.split(""))
                .filter(c -> !c.isBlank())
                .reduce("", (a, b) -> a + b);

    }

    public static String cleanString2(String text) {
        return text.replaceAll(" ", "");
    }

    public static String cleanString3(String text) {
        return text.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a, b) -> a + b);
    }

    public static String longestWord(String text) {
        List<String> list = Arrays.asList(text.split(" "));
        String max = list.getFirst();

        for (String s : list) {
            if (s.length() > max.length()) {
                max = s;
            }
        }
        return max;
    }


    public static String longestWord2(String text) {
        return Arrays.stream(text.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().orElse("");
    }

    public static boolean isAnagram(String text1, String text2) {
        String first = Arrays.stream(text1.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        String second = Arrays.stream(text2.toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());

        return first.equals(second);
    }

    public static String findFirstUniqueChar(String text) {
        return Arrays.stream(text.toLowerCase().split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static String findFirstUniqueChar2(String text) {
        Map<Character, Integer> resultMap = new HashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {
            resultMap.put(c, resultMap.getOrDefault(c, 0) + 1);

        }

        for (Map.Entry<Character, Integer> charResult : resultMap.entrySet()) {
            if (charResult.getValue() == 1) {
                return String.valueOf(charResult.getKey());
            }
        }
        return null;
    }


    public static String findFirstUniqueChar3(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .map(String::valueOf)
                .findFirst().orElse(null);
    }

    public static String compressMethod(String text) {
        return Arrays.stream(text.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet()
                .stream()
                .map(e -> String.valueOf(e.getKey() + e.getValue()))
                .collect(Collectors.joining(""));
    }

    public static Map<Character, Long> countAllChar(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    public static String removeDuplicateChar(String text) {
        return Arrays.stream(text.split(""))
                .distinct()
                .collect(Collectors.joining(""));
    }

    public static List<Character> changeToList(String text) {
        return text.chars().mapToObj(c -> (char) c)
                .toList();
    }

}