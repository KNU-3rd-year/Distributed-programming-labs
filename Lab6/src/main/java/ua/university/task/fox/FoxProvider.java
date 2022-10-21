package ua.university.task.fox;

import ua.university.manager.Task;
import ua.university.manager.TaskProvider;
import ua.university.task.MatrixHolder;

public class FoxProvider implements TaskProvider {
    @Override
    public Task provide(int part_index, int pivot, MatrixHolder matrixHolder) {
        return new FoxTask(part_index, pivot, matrixHolder);
    }
}
