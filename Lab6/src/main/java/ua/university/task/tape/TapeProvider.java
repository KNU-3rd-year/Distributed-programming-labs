package ua.university.task.tape;

import ua.university.manager.Task;
import ua.university.manager.TaskProvider;
import ua.university.task.MatrixHolder;

public class TapeProvider implements TaskProvider {
    @Override
    public Task provide(int part_index, int pivot, MatrixHolder matrixHolder) {
        return new TapeTask(part_index, pivot, matrixHolder);
    }
}
