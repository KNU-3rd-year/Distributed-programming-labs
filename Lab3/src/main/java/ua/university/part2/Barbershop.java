package ua.university.part2;

import org.jetbrains.annotations.NotNull;

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
        currentClient = null;
    }

    public boolean isChairFree() {
        return currentClient == null;
    }

    public void sitInChair(@NotNull Client client) {
        if (isChairFree()) {
            currentClient = client;
        } else {
            throw new IllegalStateException("A client tries to sit in the chair, but there is already a person.");
        }
    }

    public void startWorking() {
        for(int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Client(this);
        }
    }
}
