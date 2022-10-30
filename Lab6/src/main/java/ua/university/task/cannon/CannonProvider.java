package ua.university.task.cannon;

import ua.university.manager.Task;
import ua.university.manager.TaskProvider;
import ua.university.task.MatrixHolder;

public class CannonProvider implements TaskProvider {
    @Override
    public Task provide(int part_index, int pivot, MatrixHolder matrixHolder) {
        return new CannonTask(part_index, pivot, matrixHolder);
    }
}