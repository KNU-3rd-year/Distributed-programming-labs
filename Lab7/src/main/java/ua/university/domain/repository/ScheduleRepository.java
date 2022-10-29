package ua.university.domain.repository;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import no.gorandalum.fluentresult.Result;
import no.gorandalum.fluentresult.VoidResult;
import ua.university.domain.model.Schedule;

public interface ScheduleRepository {
    @Nullable
    Result<Schedule, Exception> getSchedule();
    VoidResult<Exception> saveSchedule(@NotNull Schedule schedule);
}
