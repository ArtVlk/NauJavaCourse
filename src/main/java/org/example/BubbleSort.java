package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class BubbleSort {
    public static void run() {
        System.out.println("№2");
        int n = getInput();

        if (n < 0) {
            System.out.println("Неверный диапазон n");
            return;
        }

        ArrayList<Double> list = generateNumbers(n);
        printList(list, "Исходный массив: ");

        bubbleSort(list);
        printList(list, "Отсортированный список: ");
    }

    public static int getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        return scanner.nextInt();
    }

    public static ArrayList<Double> generateNumbers(int n) {
        ArrayList<Double> list = new ArrayList<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble() * 100);
        }

        return list;
    }

    public static void printList(ArrayList<Double> list, String message) {
        System.out.print(message);
        for (Double num : list) {
            System.out.print(num + " ");
        }
        System.out.println('\n');
    }

    public static void bubbleSort(ArrayList<Double> list){
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++){
            swapped = false;
            for (int j = 0; j < n - i - 1; j++){
                if (list.get(j) > list.get(j + 1)) {
                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped){
                break;
            }
        }
    }
}
