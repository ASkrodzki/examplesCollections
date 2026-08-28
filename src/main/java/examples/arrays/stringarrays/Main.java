package examples.arrays.stringarrays;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println(countA("Arkadiuszaaa"));
    }

    public static int countA1(String text) {

        return (int) Arrays.stream(text.split(""))
                .filter(s -> s.toLowerCase().equals("a"))
                .count();
    }

    public static long countA(String text) {
        return text.chars()
                .filter(c -> Character.toLowerCase(c) == 'a')
                .count();
    }

    public static long countA3(String text) {

        if (text == null) {
            throw new IllegalArgumentException("not null");
        }
        int counter = 0;
        String lowerCase = text.toLowerCase();
        for (int i = 0; i < lowerCase.length(); i++) {
            if (lowerCase.charAt(i) == 'a') {
                counter++;
            }
        }

        return counter;
    }


}
