package ua.university.part1;

import ua.university.part1.instructions.ReaderInstruction;
import ua.university.part1.instructions.WriterInstruction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class Main_Lab4_part1 {
    private static final String fileName = "database.txt";
    private static final String absolutePath = Paths.get(".").toAbsolutePath().normalize().toString();
    private static final String relativePath = "\\Lab4\\src\\main\\java\\ua\\university\\part1\\";
    private static final String fullPath = absolutePath + relativePath + fileName;

    public static void main(String... args) {
        ReadWriteLock lock = new ReadWriteLock();
        Reader reader = new Reader(fullPath, lock);
        Writer writer = new Writer(fullPath, lock);

        try {
            Random r = new Random();
            for (int i = 0; i < 100; i++) {
                int number = r.nextInt(99);
                String name = "my_name_is_" + number;
                String phone = "+0123456789" + number;
                boolean status = writer.changeFile(WriterInstruction.ADD, name, phone);
                System.out.println("Status of adding operation: " + status);
            }

            for (int i = 0; i < 50; i++) {
                int number = r.nextInt(99);
                String name = "my_name_is_" + number;
                String phone = "+0123456789" + number;
                boolean status = writer.changeFile(WriterInstruction.REMOVE, name, phone);
                System.out.println("Status of removing operation: " + status);
            }

            for (int i = 0; i < 30; i++) {
                int number = r.nextInt(99);
                String phone = "+0123456789" + number;
                System.out.println("Name founded: " + reader.completeSearch(ReaderInstruction.FIND_NAME_BY_NUMBER, phone));
            }

            for (int i = 0; i < 30; i++) {
                int number = r.nextInt(99);
                String name = "my_name_is_" + number;
                System.out.println("Number founded: " + reader.completeSearch(ReaderInstruction.FIND_NUMBER_BY_NAME, name));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}