package ua.university.infrastructure;

import ua.university.data.DB.DBScheduleRepository;
import ua.university.data.XML.XMLScheduleRepository;
import ua.university.domain.repository.ScheduleRepository;

public class DI {
    private static ScheduleRepository scheduleRepository;

    public DI() {}

    public ScheduleRepository getScheduleRepository(boolean useDB) {
        if (scheduleRepository == null) {
            if (useDB) {
                scheduleRepository = new DBScheduleRepository();
            } else {
                scheduleRepository = new XMLScheduleRepository();
            }
        }
        return scheduleRepository;
    }
}
