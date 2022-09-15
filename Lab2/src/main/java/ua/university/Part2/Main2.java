package ua.university.Part2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Main2 {
    public static void main(String[] args) {
        Storage storage = new Storage(500);
        BlockingQueue<StorageItem> toMove = new ArrayBlockingQueue<>(1);
        BlockingQueue<StorageItem> toCalculate = new ArrayBlockingQueue<>(1);

        Thread a = new Thread(() -> {
            while (!storage.isEmpty()) {
                StorageItem item = storage.takeItem();
                try {
                    toMove.put(item);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread b = new Thread(() -> {
            while (toMove.size() > 0 || a.isAlive()) {
                try {
                    StorageItem item = toMove.take();
                    sleep(item.getWeight());
                    toCalculate.put(item);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread c = new Thread(() -> {
            int sum = 0;
            while (toCalculate.size() > 0 || b.isAlive()) {
                try {
                    StorageItem item = toCalculate.take();
                    sum += item.getPrice();
                    System.out.println("New sum = " + sum);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Final sum = " + sum);
        });

        a.start();
        b.start();
        c.start();
    }
}