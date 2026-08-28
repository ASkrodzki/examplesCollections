package examples.birthday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Poproś program aby zapytal Cię o datę Twojego urodzenia
        //- jeśli podałeś datę późniejszą niż dziś to niech program rzuci
        // błędem: InvalidBirthDateException
        //- program wypisuje ile dni żyjesz już
        //- program wypisuje ile już miesiecy żyjesz
        //- program wypisuje już ile lat żyjesz
        //- program wypisuje w który dzień tygodnia się urodziłeś

        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Input ur birthday data:");
            String input = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("dd.MM.yyyy"));
            LocalDate localDate = LocalDate.parse(input, formatter);

            validateDate(localDate);
            System.out.println(checkHowManyDays(localDate));
            System.out.println(checkHowManyMonths(localDate));
            System.out.println(checkHowManyYears(localDate));

        }


        //    // Napisz metodę, która dla podanego stringa zwraca strukturę, w której dla każdego znaku przechowywana jest
        //    // liczba jego wystąpień.

        System.out.println(countCharacter("Aaabbcc"));
        System.out.println(countCharacter2("Aaabbcc"));
        System.out.println(countCharacter3("Aaabbcc"));
    }

    private static long checkHowManyYears(LocalDate localDate) {
        return ChronoUnit.YEARS.between(localDate, LocalDate.now());
    }

    private static long checkHowManyMonths(LocalDate localDate) {
        return ChronoUnit.MONTHS.between(localDate, LocalDate.now());
    }

    private static long checkHowManyDays(LocalDate localDate) {
        return ChronoUnit.DAYS.between(localDate, LocalDate.now());
    }

    private static void validateDate(LocalDate localDate) {
        if (localDate.isAfter(LocalDate.now())) {
            throw new InvalidBirthDateException("wrong");
        }
    }

    public static Map<Character, Long> countCharacter2(String text) {
        Map<Character, Long> result = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            result.put(c, result.getOrDefault(c, 0L) + 1);
        }
        return result;
    }

    public static Map<String, Long> countCharacter(String text) {

        return Arrays.stream(text.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    public static Map<Character, Long> countCharacter3(String text) {

        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }


}
