package ua.university.part2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class StringEditor {
    public synchronized void modifyString(StringWithCounting string) {
        Random random = new Random();

        for (int i = 0; i < string.getString().length(); ++i) {
            if (string.areABEqual()) {
                break;
            }
            switch (string.getCharAtPos(i)) {
                case 'A' -> {
                    if (random.nextBoolean()) {
                        string.setCharAtPos('C', i);
                    }
                }
                case 'B' -> {
                    if (random.nextBoolean()) {
                        string.setCharAtPos('D', i);
                    }
                }
                case 'C' -> {
                    if (random.nextBoolean()) {
                        string.setCharAtPos('A', i);
                    }
                }
                case 'D' -> {
                    if (random.nextBoolean()) {
                        string.setCharAtPos('B', i);
                    }
                }
            }
        }
    }

    public synchronized void tryToJoinTheBarrier(StringWithCounting string, CyclicBarrier barrier) {
        try {
            if (string.areABEqual() && barrier.getParties() != barrier.getNumberWaiting()) {
                System.out.println("\n" + Thread.currentThread().getName() + " has fulfilled the condition.\nnumberA = " +
                        string.getNumberA() + "; numberB = " +
                        string.getNumberB() + "\n" +
                        "Number of waiting threads: " + (barrier.getNumberWaiting() + 1));

                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has finished the work.");
            }

        } catch (InterruptedException |
                 BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}