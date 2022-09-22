package ua.university.part3;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static ua.university.part3.Component.*;

public class Diller extends Thread {

    private final BlockingQueue<Pair<Component, Component>> components = new ArrayBlockingQueue<>(1);
    private final Random random = new Random();

    public Diller() {
        this.start();
    }

    public BlockingQueue<Pair<Component, Component>> getComponents() {
        return components;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Diller " + Thread.currentThread().getName() + " wants to give new components");
                components.put(generateComponents());
                System.out.println("Diller " + Thread.currentThread().getName() + " gave components and go to sleep");
                sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Pair<Component, Component> generateComponents() {
        Set<Component> comp = new HashSet<>();
        while (comp.size() < 2) {
            comp.add(generateComponent());
        }
        List<Component> compArray = new ArrayList<>(comp);
        return new Pair<>(compArray.get(0), compArray.get(1));
    }

    private Component generateComponent() {
        int comId = random.nextInt(3);
        switch (comId) {
            case 0: return TOBACCO;
            case 1: return PAPER;
            case 2: return MATCHES;
        }

        throw new IllegalStateException();
    }
}
