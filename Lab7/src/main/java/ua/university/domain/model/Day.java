package ua.university.domain.model;

import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Day {
    private final int id;
    private int dayOfWeek;
    private final @NotNull List<Lesson> lessons;

    public Day(int id, int dayOfWeek, @NotNull List<Lesson> lessons) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessons = lessons;
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

    public VoidResult<Exception> addLesson(Lesson newLesson) {
        if (lessons.size() >= 7) {
            return VoidResult.error(new IllegalArgumentException());
        }
        for (Lesson lesson : lessons) {
            if (lesson.getId() == newLesson.getId()) {
                return VoidResult.error(new IllegalArgumentException());
            }
        }
        lessons.add(newLesson);
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
}
