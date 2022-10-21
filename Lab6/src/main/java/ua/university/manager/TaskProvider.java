package ua.university.manager;

import ua.university.task.MatrixHolder;

public interface TaskProvider {
    Task provide(int part_index, int pivot, MatrixHolder matrixHolder);
}
