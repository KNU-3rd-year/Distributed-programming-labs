package ua.university.task1;

import javax.swing.*;

public class MyThread extends Thread{

    JSlider slider;
    int targetValue;

    MyThread(JSlider slider, int targetValue) {
        this.slider = slider;
        this.targetValue = targetValue;

        setPriority(1);
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            int currentValue = slider.getValue();
            if (currentValue > targetValue) {
                slider.setValue(currentValue - 1);
            } else {
                slider.setValue(currentValue + 1);
            }
            System.out.println("Target: " + targetValue + ", priority: " + Thread.currentThread().getPriority());

            try {
                sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
