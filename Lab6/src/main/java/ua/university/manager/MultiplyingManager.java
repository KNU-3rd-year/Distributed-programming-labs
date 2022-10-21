package ua.university.manager;

import javafx.util.Pair;
import ua.university.task.MatrixHolder;

public class MultiplyingManager implements MatrixHolder {
    private int[][] a;
    private int[][] b;
    private int[][] res;

    public Pair<int[][], Long> multiply(int[][] a, int[][] b, int processAmount, TaskProvider taskProvider) {
        long start;
        long end;

        this.a = a;
        this.b = b;

        res = new int[a.length][a.length];

        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                res[i][j] = 0;
            }
        }

        Thread[] tasks = new Thread[processAmount];
        for(int i = 0; i < tasks.length; i++){
            tasks[i] = taskProvider.provide(i, (int) Math.ceil(a.length / (double) processAmount), this);
        }

        start = System.currentTimeMillis();
        for (Thread task : tasks) {
            task.start();
        }

        for (Thread task : tasks) {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();

        return new Pair<>(res, end - start);
    }

    @Override
    public int[][] getA() {
        return a;
    }

    @Override
    public int[][] getB() {
        return b;
    }

    @Override
    public int[][] getRes() {
        return res;
    }
}
