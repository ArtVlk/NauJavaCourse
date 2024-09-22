package org.example;

import java.util.Random;
import java.util.Scanner;

public class AverageValue {
    public static void run(){
        System.out.println("№1");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        int n = scanner.nextInt();

        if (n < 0){
            System.out.println("Неверный диапазон n");
            return;
        }

        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++){
            array[i] = random.nextInt(100);
        }

        System.out.print("Массив: ");
        for (int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();

        double average = calculateAverage(array);
        System.out.println("Среднее значение элементов в массиве: " + average);
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
