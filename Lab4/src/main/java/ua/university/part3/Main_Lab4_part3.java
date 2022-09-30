package ua.university.part3;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main_Lab4_part3 {
    private static final String KYIV = "Kyiv";
    private static final String LVIV = "Lviv";
    private static final String KHARKIV = "Kharkiv";
    private static final String HERSON = "Herson";
    private static final String CHERKASY = "Cherkasy";
    private static final String UZHHHOROD = "Uzhhorod";
    private static final String ODESSA = "Odessa";
    private static final String RIVNE = "Rivne";

    public static void main(String... args) throws InterruptedException {
        BusScheduler schedule = new BusScheduler();
        Creator threadCreator = new Creator(new ReentrantReadWriteLock(false), schedule);

        threadCreator.addBusStop(KYIV);
        threadCreator.addBusStop(LVIV);
        threadCreator.addBusStop(KHARKIV);
        threadCreator.addBusStop(HERSON);
        threadCreator.addBusStop(CHERKASY);
        threadCreator.addBusStop(UZHHHOROD);
        threadCreator.addBusStop(ODESSA);
        threadCreator.addBusStop(RIVNE);

        threadCreator.addRoad(KYIV, LVIV, 230);
        threadCreator.addRoad(ODESSA, LVIV, 130);
        threadCreator.addRoad(CHERKASY, KHARKIV, 100);
        threadCreator.addRoad(KHARKIV, ODESSA, 150);
        threadCreator.addRoad(KYIV, LVIV, 150);
        threadCreator.addRoad(CHERKASY, LVIV, 170);
        threadCreator.addRoad(CHERKASY, KHARKIV, 120);
        threadCreator.addRoad(KYIV, CHERKASY, 50);
        threadCreator.addRoad(UZHHHOROD, RIVNE, 100);
        threadCreator.addRoad(CHERKASY, HERSON, 170);
        threadCreator.addRoad(HERSON, KHARKIV, 190);
        threadCreator.addRoad(KYIV, ODESSA, 70);

        threadCreator.changeTicketPrice(KYIV, CHERKASY, 80);
        threadCreator.changeTicketPrice(LVIV, CHERKASY, 180);
        threadCreator.changeTicketPrice(KYIV, ODESSA, 280);

        System.out.println("Price for ticket " + CHERKASY + " - " + KHARKIV + " = " + threadCreator.getTicketPrice(CHERKASY, KHARKIV));
        System.out.println("Price for ticket " + UZHHHOROD + " - " + RIVNE + " = " + threadCreator.getTicketPrice(UZHHHOROD, RIVNE));
        System.out.println("Price for ticket " + CHERKASY + " - " + HERSON + " = " + threadCreator.getTicketPrice(CHERKASY, HERSON));

        threadCreator.deleteRoad(CHERKASY, KYIV);
        threadCreator.deleteBusStop(KHARKIV);

        System.out.println("Price for ticket " + CHERKASY + " - " + KYIV + " = " + threadCreator.getTicketPrice(CHERKASY, KYIV));
        System.out.println("Price for ticket " + CHERKASY + " - " + KHARKIV + " = " + threadCreator.getTicketPrice(CHERKASY, KHARKIV));
    }
}
