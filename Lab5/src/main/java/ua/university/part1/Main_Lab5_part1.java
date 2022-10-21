package ua.university.part1;

public class Main_Lab5_part1 {
    public static void main(String[] args){
        Recruits recruits = new Recruits(160);
        recruits.printRecruits();
        ThreadManager manager = new ThreadManager(recruits, 50);
    }
}