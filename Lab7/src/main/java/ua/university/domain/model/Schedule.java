package ua.university.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {
    private final List<Day> days;

    public Schedule(List<Day> days) {
        this.days = days;
    }

    public void addDay() {}

    public void deleteDay() {}

    public void addLesson() {}

    public void deleteLesson() {}

    public void modifyLesson() {}

    public int getLessonsCount(int weekDay) {
        for (Day day : days) {
            if (day.getDayOfWeek() == weekDay) {
                return day.getLessons().size();
            }
        }
        return 0;
    }

    public List<Lesson> getLessons(int weekDay) {
        for (Day day : days) {
            if (day.getDayOfWeek() == weekDay) {
                return day.getLessons();
            }
        }
        return Collections.emptyList();
    }

    public List<Day> getSchedules() {
        return days;
    }

    public List<Integer> getDays() {
        return days.stream().map(d -> d.getLessons().size()).collect(Collectors.toList());
    }
}
