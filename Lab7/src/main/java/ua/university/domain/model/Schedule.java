package ua.university.domain.model;

import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule {
    private List<Day> days;

    public Schedule(@NotNull List<Day> days) {
        this.days = days.stream().sorted().collect(Collectors.toList());
    }

    public Schedule() {
        days = new ArrayList<>();
    }

    public VoidResult<Exception> addDay(int id, int dayOfWeek) {
        if (dayOfWeek >= 7) {
            return VoidResult.error(new IllegalArgumentException());
        }
        if (days.size() >= 7) {
            return VoidResult.error(new IllegalArgumentException());
        }
        for (Day day : days) {
            if (day.getDayOfWeek() == dayOfWeek) {
                return VoidResult.error(new IllegalArgumentException());
            }
        }
        days.add(new Day(id, dayOfWeek));
        days = days.stream().sorted().collect(Collectors.toList());
        return VoidResult.success();
    }

    public VoidResult<Exception> deleteDay(int dayId) {
        for (Day day : days) {
            if (day.getId() == dayId) {
                days.remove(day);
                return VoidResult.success();
            }
        }
        return VoidResult.error(new IllegalArgumentException());
    }

    public VoidResult<Exception> addLesson(int dayOfWeek, String subjectName, String teacherName, LocalTime startTime, LocalTime endTime) {
        int newLessonId = days.stream().map(Day::getId).max(Comparator.naturalOrder()).get() + 1;
        for (Day day : days) {
            if (day.getDayOfWeek() == dayOfWeek) {
                return day.addLesson(newLessonId, subjectName, teacherName, startTime, endTime);
            }
        }
        days = days.stream().sorted().collect(Collectors.toList());
        return VoidResult.error(new NoSuchElementException());
    }

    public VoidResult<Exception> deleteLesson(int lessonId) {
        for (Day day : days) {
            if (day.hasLesson(lessonId)) {
                return day.removeLesson(lessonId);
            }
        }
        return VoidResult.error(new NoSuchElementException());
    }

    public VoidResult<Exception> modifyLesson(Lesson newLesson) {
        for (Day day : days) {
            if (day.hasLesson(newLesson.getId())) {
                return day.modifyLesson(newLesson);
            }
        }
        days = days.stream().sorted().collect(Collectors.toList());
        return VoidResult.error(new NoSuchElementException());
    }

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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Day day : days) {
            s.append(day).append("\n");
        }
        return s.toString();
    }
}
