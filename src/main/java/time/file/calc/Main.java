package time.file.calc;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        // 1 ETAP stworz sobie jakis plik z liczbami, kazda linia w pliku ma zawierac
//        // jakas
//        // liczbe ktora ma np: 100 znakow niech plik ma 100 linii(randomowe liczby, rozne od siebie), ale
//        // przed linia ma byc zapisana godzina zapisu (z dokladnoscia co do milisekund)
//        // 2 ETAP Wczytaj te liczby z pliku i zapisz iloczyn tych liczb do pliku
//        // wynik.txt a na
//        // koncu masz zapisac ile trwały wszystkie obliczenia :)

        File file = new File("data1.txt");
        Instant now = Instant.now();

        createFile(file);
        createInputData(file, 50);
        List<BigInteger> integerList = readDataFromFile(file);
        System.out.println(integerList);
        System.out.println(readData2(file));
        BigInteger bigInteger = calcIntegerList(integerList);
        System.out.println("\n" + bigInteger);

        File file2 = new File("data2.txt");
        createFile(file2);
        saveResultData(file2, bigInteger);

        Instant end = Instant.now();
        long between = Duration.between(now, end).toMillis();
        System.out.println(between);
    }

    private static void saveResultData(File file2, BigInteger bigInteger) {
        try (FileWriter fileWriter = new FileWriter(file2)) {
            fileWriter.write(String.valueOf(bigInteger));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static BigInteger calcIntegerList(List<BigInteger> integerList) {
        return integerList.stream()
                .reduce(BigInteger.ONE, (a, b) -> a.multiply(b));
    }

    private static List<BigInteger> readDataFromFile(File file) throws IOException {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines
                    .map(line -> line.substring(0, line.indexOf(" | ")))
                    .map(BigInteger::new)
                    .toList();
        }
    }

    private static List<BigInteger> readData2(File file) {
        List<BigInteger> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String substring = line.substring(0, line.indexOf(" | "));
                list.add(new BigInteger(substring));
            }

        } catch (FileNotFoundException e) {


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static void createInputData(File file, int counter) {

        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (int i = 1; i < counter; i++) {
                String number = String.valueOf(random.nextInt(100) + 1) + " | " + LocalDateTime.now();

                writer.write(number + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createFile(File file) {
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("create file: " + file.getName());
            } else {
                System.out.println("already exist");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
