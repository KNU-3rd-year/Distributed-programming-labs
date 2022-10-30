package ua.university.domain.model;

import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;

public class Lesson implements Comparable<Lesson> {
    private final int id;
    private String subjectName;
    private String teacherName;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(int id, String subjectName, String teacherName, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public VoidResult<Exception> setSubjectName(@NotNull String subjectName) {
        if (subjectName.trim().isEmpty()) {
            return VoidResult.error(new IllegalArgumentException());
        }
        this.subjectName = subjectName.trim();
        return VoidResult.success();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public VoidResult<Exception> setTeacherName(@NotNull String teacherName) {
        if (teacherName.trim().isEmpty()) {
            return VoidResult.error(new IllegalArgumentException());
        }
        this.teacherName = teacherName.trim();
        return VoidResult.success();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public VoidResult<Exception> setStartTime(@NotNull LocalTime startTime) {
        if (!startTime.isBefore(endTime)) {
            return VoidResult.error(new IllegalArgumentException());
        }
        this.startTime = startTime;
        return VoidResult.success();
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public VoidResult<Exception> setEndTime(@NotNull LocalTime endTime) {
        if (!endTime.isAfter(startTime)) {
            return VoidResult.error(new IllegalArgumentException());
        }
        this.endTime = endTime;
        return VoidResult.success();
    }

    @Override
    public String toString() {
        return "Lesson" +
                " (id = " + id + ")" +
                " in " + subjectName +
                " is taught by " + teacherName +
                " (" + startTime.getHour() + ":" + startTime.getMinute() + " - " +
                endTime.getHour() + ":" + endTime.getMinute() + ")";
    }

    @Override
    public int compareTo(@NotNull Lesson o) {
        return startTime.compareTo(o.startTime);
    }
}
