package org.example;

import java.util.Random;
import java.util.Scanner;

public class AverageValue {

    public static void run() {
        System.out.println("№1");
        int n = getInput();

        if (n < 0) {
            System.out.println("Неверный диапазон n");
            return;
        }

        int[] array = generateNumbers(n);
        printArray(array);

        double average = calculateAverage(array);
        System.out.println("Среднее значение элементов в массиве: " + average + '\n');
    }

    public static int getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        return scanner.nextInt();
    }

    public static int[] generateNumbers(int n) {
        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    public static void printArray(int[] array) {
        System.out.print("Массив: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static double calculateAverage(int[] array) {
        if (array.length == 0){
            return 0;
        }
        int sum = 0;
        for (int num: array){
            sum += num;
        }
        return (double) sum / array.length;
    }
}
