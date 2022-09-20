package ua.university.part2;

import org.jetbrains.annotations.NotNull;

public class Client extends Thread {
    private boolean isShaved = false;
    private final Barbershop barbershop;

    Client(@NotNull Barbershop barbershop) {
        this.barbershop = barbershop;
        this.start();
    }

    void shave() {
        isShaved = true;
    }

    @Override
    public void run() {
        System.out.println("Client " + Thread.currentThread().getName() + " comes to the barbershop");

        while (!isShaved) {
            synchronized (barbershop) {
                while (!barbershop.isChairFree()) {
                    try {
                        barbershop.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                barbershop.sitInChair(this);
                try {
                    System.out.println("Client " + Thread.currentThread().getName() + " goes to sleep during the shaving");
                    barbershop.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("Client " + Thread.currentThread().getName() + " goes away");
        interrupt();
    }
}
