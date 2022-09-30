package ua.university.part3;

import java.util.concurrent.locks.ReadWriteLock;

public class Creator implements Runnable {
    private ReadWriteLock lock;
    private BusScheduler scheduler;
    private Instructions instruction;
    private String firstCity;
    private String secondCity;
    private Integer price;

    public Creator(ReadWriteLock lock, BusScheduler schedule) {
        this.lock = lock;
        this.scheduler = schedule;
    }

    public void addBusStop(String city) throws InterruptedException {
        this.instruction = Instructions.ADD_BUS_STOP;
        this.firstCity = city;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }

    public void deleteBusStop(String city) throws InterruptedException {
        this.instruction = Instructions.REMOVE_BUS_STOP;
        this.firstCity = city;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }

    public void changeTicketPrice(String firstCity, String secondCity, int price) throws InterruptedException {
        this.instruction = Instructions.CHANGE_PRICE;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.price = price;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }

    public void addRoad(String firstCity, String secondCity, int price) throws InterruptedException {
        this.instruction = Instructions.ADD_ROAD;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.price = price;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }

    public void deleteRoad(String firstCity, String secondCity) throws InterruptedException {
        this.instruction = Instructions.REMOVE_ROAD;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }

    public Integer getTicketPrice(String firstCity, String secondCity) throws InterruptedException {
        this.instruction = Instructions.GET_TICKET_PRICE;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        Thread thread = new Thread(this);
        thread.start();
        thread.join();
        return price;
    }

    private void addBusStopImpl() {
        lock.writeLock().lock();
        scheduler.addBusStop(firstCity);
        lock.writeLock().unlock();
    }

    private void deleteBusStopImpl() {
        lock.writeLock().lock();
        scheduler.deleteBusStop(firstCity);
        lock.writeLock().unlock();
    }

    private void changeTicketPriceImpl() {
        lock.writeLock().lock();
        scheduler.changeTicketPrice(firstCity, secondCity, price);
        lock.writeLock().unlock();
    }

    private void addRoadImpl() {
        lock.writeLock().lock();
        scheduler.addRoad(firstCity, secondCity, price);
        lock.writeLock().unlock();
    }

    private void deleteRoadImpl() {
        lock.writeLock().lock();
        scheduler.deleteRoad(firstCity, secondCity);
        lock.writeLock().unlock();
    }

    private void getTicketPriceImpl() {
        lock.writeLock().lock();
        price = scheduler.getTicketPrice(firstCity, secondCity);
        lock.writeLock().unlock();
    }

    @Override
    public void run() {
        switch (instruction) {
            case ADD_BUS_STOP: {
                addBusStopImpl();
                break;
            }
            case REMOVE_BUS_STOP: {
                deleteBusStopImpl();
                break;
            }
            case CHANGE_PRICE: {
                changeTicketPriceImpl();
                break;
            }
            case ADD_ROAD: {
                addRoadImpl();
                break;
            }
            case REMOVE_ROAD: {
                deleteRoadImpl();
                break;
            }
            case GET_TICKET_PRICE: {
                getTicketPriceImpl();
                break;
            }
        }
    }
}