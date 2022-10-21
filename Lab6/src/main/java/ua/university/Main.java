package ua.university;

import ua.university.manager.MultiplyingManager;
import ua.university.manager.TaskProvider;
import ua.university.task.cannon.CannonProvider;
import ua.university.task.fox.FoxProvider;
import ua.university.task.simple.SimpleProvider;
import ua.university.task.tape.TapeProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        List<Integer> sizes =  Arrays.asList(100, 500, 1000, 1500, 2000, 2500, 3000);

        printHeader();
        printTable(sizes);
    }

    private static void printHeader() {
        System.out.printf("%-20s", "SIZE");
        System.out.printf("%-15s", "SIMPLE(1)");
        System.out.printf("%-15s", "TAPE(2)");
        System.out.printf("%-15s", "TAPE(4)");
        System.out.printf("%-15s", "TAPE(8)");
        System.out.printf("%-15s", "FOX(2)");
        System.out.printf("%-15s", "FOX(4)");
        System.out.printf("%-15s", "FOX(8)");
        System.out.printf("%-15s", "CANNON(2)");
        System.out.printf("%-15s", "CANNON(4)");
        System.out.printf("%-15s", "CANNON(8)");
        System.out.println();
    }

    private static void printTable(List<Integer> sizes) {
        for (Integer size: sizes) {
            printRow(size);
        }
    }

    private static void printRow(Integer size) {
        List<Long> results = getResults(size);
        System.out.printf("%-20s", size);
        for (long r : results) {
            System.out.printf("%-15s", r + "  ms");
        }
        System.out.println();
    }

    private static int[][] generateMatrix(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        return matrix;
    }

    private static List<Long> getResults(Integer size) {
        List<Long> results = new ArrayList<>();

        int[][] a = generateMatrix(size);
        int[][] b = generateMatrix(size);

        MultiplyingManager multiplyingManager = new MultiplyingManager();

        TaskProvider simpleProvider = new SimpleProvider();
        TaskProvider cannonProvider = new CannonProvider();
        TaskProvider foxProvider = new FoxProvider();
        TaskProvider tapeProvider = new TapeProvider();

        results.add(multiplyingManager.multiply(a, b, 1, simpleProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 2, tapeProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 4, tapeProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 8, tapeProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 2, foxProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 4, foxProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 8, foxProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 2, cannonProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 4, cannonProvider).getValue());
        results.add(multiplyingManager.multiply(a, b, 8, cannonProvider).getValue());

        return results;
    }
}