package company;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args)
    {
        // 1
        Path path1 = Path.of(".", "Files", "File1.txt");
        Path path2 = Path.of(".", "files", "File2.txt");

        List<String> listFile1 = new ArrayList<>();
        List<String> listFile2 = new ArrayList<>();

        try(BufferedReader reader1 = Files.newBufferedReader(path1); BufferedReader reader2 = Files.newBufferedReader(path2))
        {
            listFile1.addAll(Files.readAllLines(path1));
            listFile2.addAll(Files.readAllLines(path2));


        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        List<String> difference1 = listFile1.stream()
                .filter(e -> !listFile2.contains(e))
                .toList();

        List<String> difference2 = listFile2.stream()
                .filter(e -> !listFile1.contains(e))
                .toList();

        System.out.println("Mismatched lines from file1:");
        difference1.stream().forEach(System.out::println);
        System.out.println();
        System.out.println("Mismatched lines from file2:");
        difference2.stream().forEach(System.out::println);

        // 2
        System.out.println();

        Path path3 = Path.of(".", "Files", "File3.txt");

        List<String> listFile3 = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(path3))
        {
            listFile3.addAll(Files.readAllLines(path3));

            Optional<String> longestString = listFile3.stream()
                    .max(Comparator.comparingInt(String::length));

            longestString.ifPresent(s -> System.out.println("Longest line: " + s));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        // 3
        System.out.println();

        Path path4 = Path.of(".", "Files", "File4.txt");

        List<String> listFile4 = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(path4))
        {
            listFile4.addAll(Files.readAllLines(path4));

        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        List<int[]> arrays = new ArrayList<>();

        for (String line : listFile4) {
            String[] stringNumbers = line.split(" ");
            int[] numbers = new int[stringNumbers.length];

            for (int i = 0; i < stringNumbers.length; i++)
            {
                numbers[i] = Integer.parseInt(stringNumbers[i]);
            }

            arrays.add(numbers);
        }

        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;
        int totalSum = 0;

        for (int i = 0; i < arrays.size(); i++)
        {
            int[] array = arrays.get(i);
            int max = array[0];
            int min = array[0];
            int sum = 0;

            for (int num : array)
            {
                if (num > max)
                {
                    max = num;
                }
                if (num < min)
                {
                    min = num;
                }

                sum += num;
            }

            if (max > globalMax)
            {
                globalMax = max;
            }
            if (min < globalMin)
            {
                globalMin = min;
            }

            totalSum += sum;

            System.out.println("Array " + (i + 1) + ": " + arrayToString(array));
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
            System.out.println("Sum: " + sum);
            System.out.println();
        }

        System.out.println("Total max: " + globalMax);
        System.out.println("Total min: " + globalMin);
        System.out.println("Total sum: " + totalSum);

        // 4
        System.out.println();

        Path path5 = Path.of(".", "Files", "File5.txt");

        int[] array5 = {114, 54, 123, 542, 4, 332, 12, 54, 21, 22};

        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();

        for (int num : array5)
        {
            if (num % 2 == 0)
            {
                evenNumbers.add(num);
            }
            else
            {
                oddNumbers.add(num);
            }
        }

        int[] reversedArray = new int[array5.length];

        for (int i = 0; i < array5.length; i++)
        {
            reversedArray[i] = array5[array5.length - 1 - i];
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path5))
        {
            writer.write(arrayToString(array5));
            writer.newLine();

            writer.write(arrayToString(evenNumbers));
            writer.newLine();

            writer.write(arrayToString(oddNumbers));
            writer.newLine();

            writer.write(arrayToString(reversedArray));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        // 5
        System.out.println();

        Path path6 = Path.of(".", "Files", "File6.txt");

        Corporation corporation = new Corporation();
        Corporation corporation2 = new Corporation();

        for(int i = 0; i < 10; i++)
        {
            corporation.addEmployee(new Employee("employee name #" + (i + 1), "employee last name #" + (i + 1), 20 + i));
        }

        corporation.showAllEmployees();
        corporation.saveToFile(path6);

        corporation2.loadFromFile(path6);
        corporation2.showAllEmployees();


    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array)
        {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    private static String arrayToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int num : list)
        {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }
}