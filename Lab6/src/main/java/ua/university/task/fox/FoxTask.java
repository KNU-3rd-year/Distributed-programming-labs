package ua.university.task.fox;

import ua.university.manager.Task;
import ua.university.task.MatrixHolder;

public class FoxTask extends Task {
    public FoxTask(int part_index, int pivot, MatrixHolder matrixHolder) {
        super(part_index, pivot, matrixHolder);
    }

    @Override
    public void run() {
        for (int row = part_index * pivot; row < (part_index + 1) * pivot && row < matrixHolder.getA().length; row++) {
            int counter = 0;
            int a_j = row;
            int b_i = row;
            while (counter < matrixHolder.getA().length) {
                for (int i = 0; i < matrixHolder.getA().length; i++) {
                    matrixHolder.getRes()[row][i] += matrixHolder.getA()[row][a_j] * matrixHolder.getB()[b_i][i];
                }

                b_i = (b_i + 1) % matrixHolder.getA().length;
                a_j = (a_j + 1) % matrixHolder.getA().length;
                counter++;
            }
        }
    }
}
