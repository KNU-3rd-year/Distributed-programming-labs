package ua.university.task.simple;

import ua.university.manager.Task;
import ua.university.task.MatrixHolder;

public class SimpleTask extends Task {
    public SimpleTask(int part_index, int pivot, MatrixHolder matrixHolder) {
        super(part_index, pivot, matrixHolder);
    }

    @Override
    public void run() {
        for(int i = 0; i < matrixHolder.getA().length; i++){
            for(int j = 0; j < matrixHolder.getA().length; j++){
                for(int k = 0; k < matrixHolder.getA().length; k++){
                    matrixHolder.getRes()[i][j] += matrixHolder.getA()[i][k] * matrixHolder.getB()[k][j];
                }
            }
        }
    }
}
