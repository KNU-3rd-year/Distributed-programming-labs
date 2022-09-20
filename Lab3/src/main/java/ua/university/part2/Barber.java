package ua.university.part2;

import org.jetbrains.annotations.NotNull;

public class Barber extends Thread {

    private final Barbershop barbershop;

    public Barber(@NotNull Barbershop barbershop) {
        this.barbershop = barbershop;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Barber " + Thread.currentThread().getName() + " started working");

        while (true) {
            synchronized (barbershop.barber) {
                while (barbershop.isChairFree()) {
                    try {
                        barbershop.barber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("Barber " + Thread.currentThread().getName() + " is awake");
                shave(barbershop.getCurrentClient());
                barbershop.makeChairFree();
                try {
                    System.out.println("Barber " + Thread.currentThread().getName() + " goes to sleep");
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void shave(@NotNull Client client) {
        System.out.println("Barber " + Thread.currentThread().getName() + " started shaving");
        try {
            Thread.sleep(2000);
            client.shave();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barber " + Thread.currentThread().getName() + " stoped shaving");
    }
}
