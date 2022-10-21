package ua.university.task.tape;

import ua.university.manager.Task;
import ua.university.task.MatrixHolder;

public class TapeTask extends Task {
    public TapeTask(int part_index, int pivot, MatrixHolder matrixHolder) {
        super(part_index, pivot, matrixHolder);
    }

    @Override
    public void run() {
        for (int row = part_index * pivot; row < (part_index + 1) * pivot && row < matrixHolder.getA().length; row++) {
            int counter = 0;
            int index = row;
            while (counter < matrixHolder.getA().length) {
                int cell = 0;
                for (int i = 0; i < matrixHolder.getA().length; i++) {
                    cell += matrixHolder.getA()[row][i] * matrixHolder.getB()[i][index];
                }

                matrixHolder.getRes()[row][index] = cell;
                counter++;
                index = (index + 1) % matrixHolder.getA().length;
            }
        }
    }
}
