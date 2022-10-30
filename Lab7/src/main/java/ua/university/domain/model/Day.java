package ua.university.domain.model;

import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Day {
    private final int id;
    private int dayOfWeek;
    private final @NotNull List<Lesson> lessons;

    public Day(int id, int dayOfWeek, @NotNull List<Lesson> lessons) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessons = lessons;
    }

    public Day(int id, int dayOfWeek) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public VoidResult<Exception> setDayOfWeek(int dayOfWeek) {
        if (dayOfWeek < 0 || dayOfWeek > 6) {
            return VoidResult.error(new IllegalArgumentException());
        }
        this.dayOfWeek = dayOfWeek;
        return VoidResult.success();
    }

    public @NotNull List<Lesson> getLessons() {
        return lessons;
    }

    public VoidResult<Exception> addLesson(int id, String subjectName, String teacherName, LocalTime startTime, LocalTime endTime) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == id) {
                return VoidResult.error(new IllegalArgumentException());
            }
        }
        lessons.add(new Lesson(id, subjectName, teacherName, startTime, endTime));
        return VoidResult.success();
    }

    public VoidResult<Exception> removeLesson(int lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                lessons.remove(lesson);
                return VoidResult.success();
            }
        }
        return VoidResult.error(new IllegalArgumentException());
    }

    public VoidResult<Exception> modifyLesson(Lesson newLesson) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == newLesson.getId()) {
                return lesson.setSubjectName(newLesson.getSubjectName()).toOptionalResult()
                        .consume(x -> lesson.setTeacherName(newLesson.getTeacherName()))
                        .consume(x -> lesson.setStartTime(newLesson.getStartTime()))
                        .consume(x -> lesson.setEndTime(newLesson.getEndTime()))
                        .toVoidResult();
            }
        }
        return VoidResult.error(new NoSuchElementException());
    }

    public boolean hasLesson(int lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(getDayName(dayOfWeek) + ":\n");
        for (Lesson lesson : lessons) {
            s.append("\t").append(lesson).append("\n");
        }
        return s.toString();
    }

    @Contract(pure = true)
    private @NotNull String getDayName(int i) {
        switch (i) {
            case 0: return "Mon";
            case 1: return "Tue";
            case 2: return "Wed";
            case 3: return "Thu";
            case 4: return "Fri";
            case 5: return "Sat";
            case 6: return "Sun";
            default: throw new IllegalStateException("Wrong day index");
        }
    }
}
