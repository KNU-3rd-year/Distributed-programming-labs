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
        System.out.println(Thread.currentThread().getName() + ": day is started");

        while (true) {
            synchronized (barbershop.barber) {
                while (barbershop.isChairFree()) {
                    try {
                        barbershop.barber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                shave(barbershop.getCurrentClient());
                barbershop.makeChairFree();
                barbershop.notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void shave(@NotNull Client client) {
        System.out.println(Thread.currentThread().getName() + ": shaving started");
        try {
            Thread.sleep(2000);
            client.shave();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": shaving ended");
    }
}
