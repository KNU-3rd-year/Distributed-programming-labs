package ua.university.domain.model;

import no.gorandalum.fluentresult.VoidResult;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Schedule {
    private final List<Day> days;

    public Schedule(List<Day> days) {
        this.days = days;
    }

    public VoidResult<Exception> addDay(int id, int dayOfWeek) {
        if (days.size() >= 7) {
            return VoidResult.error(new IllegalArgumentException());
        }
        for (Day day : days) {
            if (day.getDayOfWeek() == dayOfWeek) {
                return VoidResult.error(new IllegalArgumentException());
            }
        }
        days.add(new Day(id, dayOfWeek));
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
