package ua.university.part2;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Barbershop {

    final Barber barber;
    private Client currentClient = null;

    public Barbershop() {
        barber = new Barber(this);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void makeChairFree() {
        synchronized (this) {
            currentClient = null;
            System.out.println("Barber " + Thread.currentThread().getName() + " remove a client from the chair");
            this.notify();
        }
    }

    public boolean isChairFree() {
        return currentClient == null;
    }

    public void sitInChair(@NotNull Client client) {
        if (isChairFree()) {
            synchronized (barber) {
                currentClient = client;
                System.out.println("Client " + Thread.currentThread().getName() + " has sat in the chair");

                barber.notify();
            }
        } else {
            throw new IllegalStateException("A client tries to sit in the chair, but there is already a person.");
        }
    }

    public void startWorking(int numberOfClients) {
        Random random = new Random();
        for(int i = 0; i < numberOfClients; i++) {
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Client(this);
        }
    }
}
