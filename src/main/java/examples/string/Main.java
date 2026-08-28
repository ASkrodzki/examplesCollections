package examples.string;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String textA = "naan";
        String text1 = "Anna";
        String name = "Arkadiusz";
        String text2 = "Anna ma kotka";
        String text3 = "Arkadiusz  Programista 50k $";

        String[] arrStr = new String[]{"Arkad", "iusz"};
        System.out.println("PALINDROM");
        System.out.println(isPalindrom(text1));
        System.out.println(isPalindrom(text2));
        System.out.println(isPalindrom(text3));

        System.out.println("VOWELS");
        System.out.println(getVowelsCount(text1));
        System.out.println(getVowelsCount(text2));
        System.out.println(getVowelsCount(text3));
        System.out.println("---");
        System.out.println(getVowelsCount2(text1));
        System.out.println(getVowelsCount2(text2));
        System.out.println(getVowelsCount2(text3));

        System.out.println("REVERSE TEXT");
        System.out.println(reverseText(text1));
        System.out.println(reverseText(text2));
        System.out.println(reverseText(text3));

        System.out.println("REVERSE LETTER");
        System.out.println(reverseLetter2(text1));
        System.out.println(reverseLetter2(text2));
        System.out.println(reverseLetter2(text3));

        System.out.println("REPLACE WHITESPACE");
        System.out.println(cleanWhiteSpaces(text1));
        System.out.println(cleanWhiteSpaces(text2));
        System.out.println(cleanWhiteSpaces(text3));

        System.out.println("Longest words");
        System.out.println(longestWord(text1));
        System.out.println(longestWord(text2));
        System.out.println(longestWord(text3));
        System.out.println("---");
        System.out.println(longestWord2(text1));
        System.out.println(longestWord2(text2));
        System.out.println(longestWord2(text3));

        System.out.println("is Anagram");
        System.out.println(isAnagram(text1, textA));
        System.out.println(isAnagramInMap(text1, textA));

        System.out.println("Distinxt char");
        System.out.println(findUniqueChar(text1));
        System.out.println(findUniqueChar(text2));
        System.out.println(findUniqueChar(text3));
        System.out.println("---");
        System.out.println(findUniqueChar2(text1));
        System.out.println(findUniqueChar2(text2));
        System.out.println(findUniqueChar2(text3));

        System.out.println("compressString");
        System.out.println(compressString("a2aabbc5c"));
        System.out.println(compressString("Arkadiusz"));
        System.out.println(compressString2("a2aabbc5c"));
        System.out.println(compressString2("Arkadiusz"));

        System.out.println("count char");
        System.out.println(countChar(text1));
        System.out.println(countChar(text2));
        System.out.println(countChar(text3));
        System.out.println("---");
        System.out.println(countChar2(text1));
        System.out.println(countChar2(text2));
        System.out.println(countChar2(text3));
        System.out.println("---");
        System.out.println(countChar3(text1));
        System.out.println(countChar3(text2));
        System.out.println(countChar3(text3));

        /*
        String
   ↓ toCharArray()
char[]

String
   ↓ chars()
IntStream

IntStream
   ↓ mapToObj(c -> (char)c)
Stream<Character>

Stream<Character>
   ↓ toArray(Character[]::new)
Character[]

String
   ↓ split("")
String[]
         */
        System.out.println("REMOVE RE");

        System.out.println(removeRepeatedCharacter(name));
        System.out.println(removeRepeatedCharacter(text2));
        System.out.println(removeRepeatedCharacter(text3));


        System.out.println("???");
    //    System.out.println(Arrays.toString(convertToString("Anna")));
        System.out.println(convertToString("Anna"));
    }

    public static List<String> convertToString(String text) {
        return Arrays.stream(text.split(""))
                .toList();
    }

    //Napisz metodę, która usuwa z napisu wszystkie powtarzające się znaki.

    public static String removeRepeatedCharacter(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    //Napisz metodę, która zlicza wystąpienia każdego znaku.

    public static Map<Character, Long> countChar2(String text) {
        return text.toLowerCase().chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
    }

    public static String countChar3(String text) {
        return text.toLowerCase().chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining());
    }

    public static String countChar(String text) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(", ");
        }
        return sb.toString();
    }

    //Napisz metodę, która kompresuje napis: "aaabbcccc" → "a3b2c4"

    public static String compressString2(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(e -> String.valueOf(e.getKey()) + e.getValue())
                .collect(Collectors.joining(""));
    }


    public static String compressString(String text) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder newText = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            newText.append(entry.getKey()).append(entry.getValue());
        }
        return newText.toString();
    }

    //Napisz metodę, która zwraca pierwszy niepowtarzający się znak.

    public static Character findUniqueChar2(String text) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            if (characterIntegerEntry.getValue() == 1) {
                return characterIntegerEntry.getKey();
            }
        }
        return null;
    }

    public static Character[] toCharrArrayMethod(String text) {
        return Arrays.stream(text.toLowerCase().split(""))
                .toArray(Character[]::new);
    }

    public static String findUniqueChar(String text) {
        return text.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .map(String::valueOf)
                .orElse("nope");
    }

    // Napisz metodę, która sprawdza, czy dwa napisy są anagramami.

    public static boolean isAnagramInMap(String text1, String text2) {
        Map<Character, Long> map1 = text1.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Map<Character, Long> map2 = text2.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return map1.equals(map2);

    }

    public static boolean isAnagram(String text1, String text2) {
        char[] charArray = text1.toLowerCase().toCharArray();
        char[] charArray2 = text2.toLowerCase().toCharArray();

        Arrays.sort(charArray);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray, charArray2);
    }

    //Napisz metodę, która znajduje najdłuższe słowo w zdaniu.

    public static String longestWord(String text) {
        return Arrays.stream(text.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().orElse(null);
    }

    public static String longestWord2(String text) {
        List<String> resultList = new ArrayList<>(List.of(text.split(" ")));

        String longest = resultList.getFirst();

        for (String s : resultList) {
            if (longest.length() < s.length()) {
                longest = s;
            }
        }
        return longest;

    }


    // Napisz metodę, która usuwa wszystkie spacje z napisu.

    public static String cleanWhiteSpaces(String text) {
        return text.replaceAll(" ", "");
    }

    //Napisz metodę, która odwraca kolejność słów w zdaniu.

    public static String reverseLetter2(String text) {
        return new StringBuilder(text).reverse().toString();

    }

    public static String reverseText(String text) {
        List<String> resultList = new ArrayList<>(List.of(text.split(" ")));
        Collections.reverse(resultList);
        return String.join(" ", resultList);
    }

    //napisz metode ktora sprwadza czy napis jest palindromem

    public static boolean isPalindrom(String text) {
        return new StringBuilder(text).reverse().toString().equalsIgnoreCase(text);
        //  return text.toLowerCase().equals(new StringBuilder(text.toLowerCase()).reverse().toString());
    }

    //napisz metoda ktora zwraca liczbe samogłosek w napisie

    public static int getVowelsCount(String text) {
        List<Character> vowels = List.of('a', 'ą', 'e', 'ę', 'i', 'o', 'ó', 'u', 'y');

        return (int) Arrays.stream(text.toLowerCase().split(""))
                .filter(s -> vowels.contains(s.charAt(0)))
                .count();
    }

    //napisz metoda ktora zwraca liczbe samogłosek w napisie
    public static int getVowelsCount2(String text) {
        List<Character> vowels = List.of('a', 'ą', 'e', 'ę', 'i', 'o', 'ó', 'u', 'y');

        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (vowels.contains(Character.toLowerCase(text.charAt(i)))) {
                counter++;
            }
        }
        return counter;
    }

}
