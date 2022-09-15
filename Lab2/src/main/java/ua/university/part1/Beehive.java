package ua.university.part1;

public class Beehive {

    private final Territory territory;
    private int lastScannedPart = 0;

    private boolean isBearFound = false;

    public Beehive(int numberOfSwarms, int numberOfParts) {
        territory = new Territory(numberOfParts);

        for (int i = 0; i < numberOfSwarms; i++) {
            new Swarm();
        }
    }

    class Swarm {

        private final Thread thread = new Thread(() -> {
            while (true) {
                if (!isBearFound && lastScannedPart < territory.getNumberOfParts()) {
                    int partNumber = lastScannedPart;
                    lastScannedPart++;
                    scan(partNumber);
                } else {
                    break;
                }
            }
        });

        public Swarm() {
            thread.start();
        }

        void scan(int partNumber) {
            try {
                System.out.println("Part " + partNumber + " is being checked...");
                Thread.sleep(10L);
                if (territory.isBearHere(partNumber)) {
                    isBearFound = true;
                    System.out.println("Bear is gound in part " + partNumber);
                } else {
                    System.out.println("There is no any bear in part " + partNumber);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
