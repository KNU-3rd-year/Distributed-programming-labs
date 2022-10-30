package ua.university.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    private Schedule schedule;

    @BeforeEach
    void setUp() {
        List<Day> days = new ArrayList<>();
        {
            List<Lesson> lessons = new ArrayList<>();
            lessons.add(new Lesson(1, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("10:15:00")));
            lessons.add(new Lesson(2, "OOP", "Peter Black", LocalTime.parse("10:35:00"), LocalTime.parse("12:10:00")));
            lessons.add(new Lesson(3, "Philosophy", "Megan Fox", LocalTime.parse("12:20:00"), LocalTime.parse("13:55:00")));
            days.add(new Day(1, 0, lessons));
        }
        {
            List<Lesson> lessons = new ArrayList<>();
            lessons.add(new Lesson(4, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("10:15:00")));
            lessons.add(new Lesson(5, "Math", "John Smith", LocalTime.parse("10:35:00"), LocalTime.parse("12:10:00")));
            lessons.add(new Lesson(6, "Math", "John Smith", LocalTime.parse("12:20:00"), LocalTime.parse("13:55:00")));
            days.add(new Day(2, 1, lessons));
        }
        {
            List<Lesson> lessons = new ArrayList<>();
            lessons.add(new Lesson(7, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("10:15:00")));
            lessons.add(new Lesson(8, "OOP", "Peter Black", LocalTime.parse("10:35:00"), LocalTime.parse("12:10:00")));
            lessons.add(new Lesson(9, "Philosophy", "Megan Fox", LocalTime.parse("12:20:00"), LocalTime.parse("13:55:00")));
            lessons.add(new Lesson(10, "Philosophy", "Megan Fox", LocalTime.parse("14:05:00"), LocalTime.parse("15:40:00")));
            days.add(new Day(3, 2, lessons));
        }
        {
            List<Lesson> lessons = new ArrayList<>();
            lessons.add(new Lesson(11, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("10:15:00")));
            lessons.add(new Lesson(12, "Philosophy", "Megan Fox", LocalTime.parse("12:20:00"), LocalTime.parse("13:55:00")));
            days.add(new Day(4, 4, lessons));
        }
        schedule = new Schedule(days);
    }

    @AfterEach
    void tearDown() {
        schedule = null;
    }

    @Test
    void addDayCorrect() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
    }

    @Test
    void addDayNotCorrect() {
        assertThrows(Exception.class, () -> schedule.addDay(13, 10).orElseThrow(e -> e));
    }

    @Test
    void addDayDuplicate() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertThrows(Exception.class, () -> schedule.addDay(13, 3).orElseThrow(e -> e));
    }

    @Test
    void deleteDayCorrect() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertDoesNotThrow(() -> schedule.deleteDay(13).orElseThrow(e -> e));
    }

    @Test
    void deleteDayNoDay() {
        assertThrows(Exception.class, () -> schedule.deleteDay(13).orElseThrow(e -> e));
    }

    @Test
    void deleteDayDuplicate() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertDoesNotThrow(() -> schedule.deleteDay(13).orElseThrow(e -> e));
        assertThrows(Exception.class, () -> schedule.deleteDay(13).orElseThrow(e -> e));
    }

    @Test
    void addLessonCorrect() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertDoesNotThrow(() -> schedule.addLesson(3, "PE", "Teacher", LocalTime.now(), LocalTime.now().plusHours(1)).orElseThrow(e -> e));
    }

    @Test
    void addLessonError() {
        assertThrows(Exception.class, () -> schedule.addLesson(3, "PE", "Teacher", LocalTime.now(), LocalTime.now().plusHours(1)).orElseThrow(e -> e));
    }

    @Test
    void addLessonDuplicate() {
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertDoesNotThrow(() -> schedule.addLesson(3, "PE", "Teacher", LocalTime.now(), LocalTime.now().plusHours(1)).orElseThrow(e -> e));
        assertThrows(Exception.class, () -> schedule.addLesson(3, "PE", "Teacher", LocalTime.now(), LocalTime.now().plusHours(1)).orElseThrow(e -> e));
    }

    @Test
    void deleteLessonSuccess() {
        assertDoesNotThrow(() -> schedule.deleteLesson(2).orElseThrow(e -> e));
    }

    @Test
    void deleteLessonError() {
        assertThrows(Exception.class, () -> schedule.deleteLesson(32).orElseThrow(e -> e));
    }

    @Test
    void deleteLessonDuplicate() {
        assertDoesNotThrow(() -> schedule.deleteLesson(2).orElseThrow(e -> e));
        assertThrows(Exception.class, () -> schedule.deleteLesson(2).orElseThrow(e -> e));
    }

    @Test
    void modifyLessonSuccess() {
        Lesson lesson = new Lesson(1, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("19:15:00"));
        assertDoesNotThrow(() -> schedule.modifyLesson(lesson).orElseThrow(e -> e));
    }

    @Test
    void modifyLessonError() {
        Lesson lesson = new Lesson(32, "Math", "John Smith", LocalTime.parse("08:40:00"), LocalTime.parse("19:15:00"));
        assertThrows(Exception.class, () -> schedule.modifyLesson(lesson).orElseThrow(e -> e));
    }

    @Test
    void getLessonsCountMonday() {
        assertEquals(schedule.getLessonsCount(0), 3);
    }

    @Test
    void getLessonsCountTuesday() {
        assertEquals(schedule.getLessonsCount(1), 3);
    }

    @Test
    void getLessonsCountWednesday() {
        assertEquals(schedule.getLessonsCount(2), 4);
    }

    @Test
    void getLessonsCountFriday() {
        assertEquals(schedule.getLessonsCount(4), 2);
    }

    @Test
    void getLessons() {
        assertEquals(schedule.getLessonsCount(4), schedule.getLessons(4).size());
    }

    @Test
    void getDays() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(2);
        assertEquals(schedule.getDays(), list);
    }

    @Test
    void getDaysAdded() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(0);
        list.add(2);
        assertDoesNotThrow(() -> schedule.addDay(13, 3).orElseThrow(e -> e));
        assertEquals(schedule.getDays(), list);
    }
}