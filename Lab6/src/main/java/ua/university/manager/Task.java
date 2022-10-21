package ua.university.manager;

import ua.university.task.MatrixHolder;

public abstract class Task extends Thread {
    protected final int part_index;
    protected final int pivot;
    protected final MatrixHolder matrixHolder;

    public Task(int part_index, int pivot, MatrixHolder matrixHolder) {
        this.part_index = part_index;
        this.pivot = pivot;
        this.matrixHolder = matrixHolder;
    }
}
