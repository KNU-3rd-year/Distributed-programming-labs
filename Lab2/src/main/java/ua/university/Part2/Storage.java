package ua.university.Part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Storage {
    private final List<StorageItem> items = new ArrayList<>();

    public Storage(int numberOfGoods) {
        Random random = new Random();

        // fill the storage with items
        for (int i = 0; i < numberOfGoods; i++) {
            items.add(new StorageItem(random.nextInt(100), random.nextInt(10)));
        }
    }

    StorageItem takeItem() {
        StorageItem item = items.get(0);
        items.remove(0);
        return item;
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

}
