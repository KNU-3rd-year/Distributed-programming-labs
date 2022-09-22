package ua.university.part3;

public class Main_Lab3_part3 {
    public static void main(String[] args) {
        Diller diller = new Diller();

        new Smoker(Component.TOBACCO, diller);
        new Smoker(Component.PAPER, diller);
        new Smoker(Component.MATCHES, diller);
    }
}
