package ua.university.task.simple;

import ua.university.manager.Task;
import ua.university.manager.TaskProvider;
import ua.university.task.MatrixHolder;

public class SimpleProvider implements TaskProvider {
    @Override
    public Task provide(int part_index, int pivot, MatrixHolder matrixHolder) {
        return new SimpleTask(part_index, pivot, matrixHolder);
    }
}
