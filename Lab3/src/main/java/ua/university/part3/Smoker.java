package ua.university.part3;

import javafx.util.Pair;

public class Smoker extends Thread {

    private final Component components;
    private final Diller diller;

    public Smoker(Component components, Diller diller) {
        this.components = components;
        this.diller = diller;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Smoker " + Thread.currentThread().getName() + " ready");
        while (true) {
            Pair<Component, Component> p = diller.getComponents().peek();
            if (p != null && p.getKey() != components && p.getValue() != components) {
                try {
                    diller.getComponents().take();
                    System.out.println("Smoker " + Thread.currentThread().getName() + " took the components and start smoking");
                    sleep(3000);
                    System.out.println("Smoker " + Thread.currentThread().getName() + " finished smoking and ready for a new one");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
