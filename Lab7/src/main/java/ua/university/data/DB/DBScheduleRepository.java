package ua.university.data.DB;

import javafx.util.Pair;
import no.gorandalum.fluentresult.Result;
import no.gorandalum.fluentresult.VoidResult;
import org.jetbrains.annotations.NotNull;
import ua.university.domain.model.Day;
import ua.university.domain.model.Lesson;
import ua.university.domain.model.Schedule;
import ua.university.domain.repository.ScheduleRepository;
import ua.university.infrastructure.Constant;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DBScheduleRepository implements ScheduleRepository {
    private final Connection connection;
    private final Statement s;
    private final String DB_NAME = "schedule";

    private final String URL = "jdbc:mysql://" + Constant.DB_IP + ":" + Constant.PORT + "/" + DB_NAME;

    public DBScheduleRepository() {
        try {
            connection = DriverManager.getConnection(URL, "root", Constant.ROOT_PASSWORD);
            s = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Result<Schedule, Exception> getSchedule() {
        return getDays().map(Schedule::new);
    }

    @Override
    public VoidResult<Exception> saveSchedule(@NotNull Schedule schedule) {
        return clearTables()
                .toOptionalResult()
                .consume(s -> saveDays(schedule.getSchedules()))
                .toVoidResult();
    }

    private Result<List<Day>, Exception> getDays() {
        try {
            List<Day> days = new ArrayList<>();
            List<Pair<Integer, Integer>> daysData = new ArrayList<>();
            String sql = "SELECT * FROM DAYS";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                int dayOfWeek = rs.getInt("DAY_OF_WEEK");
                daysData.add(new Pair<>(id, dayOfWeek));
            }
            for (Pair<Integer, Integer> pair : daysData){
                Result<List<Lesson>, Exception> lessons = getLessons(pair.getKey());
                lessons.fold(
                        lessonList -> days.add(new Day(pair.getKey(), pair.getValue(), lessonList)),
                        e -> {throw new RuntimeException(e);}
                );
            }
            return Result.success(days);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    private Result<List<Lesson>, Exception> getLessons(int dayId) {
        try {
            List<Lesson> lessons = new ArrayList<>();
            String sql = "SELECT * FROM LESSONS WHERE ID_DAY = " + dayId;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String subjectName = rs.getString("SUBJECT_NAME");
                String teacherName = rs.getString("TEACHER_NAME");
                String startTime = rs.getString("START_TIME");
                String endTime = rs.getString("END_TIME");
                lessons.add(new Lesson(
                        id,
                        subjectName,
                        teacherName,
                        LocalTime.parse(startTime),
                        LocalTime.parse(endTime)
                ));
            }
            return Result.success(lessons);
        } catch (SQLException e) {
            return Result.error(e);
        }
    }

    private VoidResult<Exception> clearTables() {
        try {
            String sqlLessons = "DELETE FROM LESSONS";
            String sqlDays = "DELETE FROM DAYS";
            s.executeUpdate(sqlLessons);
            s.executeUpdate(sqlDays);
            return VoidResult.success();
        } catch (SQLException e) {
            return VoidResult.error(e);
        }
    }

    private VoidResult<Exception> saveDays(@NotNull List<Day> days) {
        try {
            for (Day day : days) {
                String sql = "INSERT INTO DAYS VALUES (" +
                        day.getId() + ", " +
                        day.getDayOfWeek() +
                        ");";
                s.executeUpdate(sql);
                saveLessons(day.getLessons(), day.getId())
                        .consumeError(e -> {throw new RuntimeException(e);});
            }
            return VoidResult.success();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private VoidResult<Exception> saveLessons(@NotNull List<Lesson> lessons, int dayId) {
        try {
            for (Lesson lesson : lessons) {
                String sql = "INSERT INTO LESSONS VALUES (" +
                        lesson.getId() + ", " +
                        dayId + ", " +
                        lesson.getSubjectName() + ", " +
                        lesson.getTeacherName() + ", " +
                        lesson.getStartTime() + ", " +
                        lesson.getEndTime() + ", " +
                        ");";
                s.executeUpdate(sql);
            }
            return VoidResult.success();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
