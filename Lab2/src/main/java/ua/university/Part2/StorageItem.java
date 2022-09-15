package ua.university.Part2;

class StorageItem {
    private final int price;
    private final int weight;

    public StorageItem(int price, int weight) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}
