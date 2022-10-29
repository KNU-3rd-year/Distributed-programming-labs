package ua.university.data.DB;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import no.gorandalum.fluentresult.Result;
import no.gorandalum.fluentresult.VoidResult;
import ua.university.domain.model.Schedule;
import ua.university.domain.repository.ScheduleRepository;

public class DBScheduleRepository implements ScheduleRepository {
    @Override
    @Nullable
    public Result<Schedule, Exception> getSchedule() {
        return null;
    }

    @Override
    public VoidResult<Exception> saveSchedule(@NotNull Schedule schedule) {
        return null;
    }
}
