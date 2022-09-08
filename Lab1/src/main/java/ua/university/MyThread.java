package ua.university;

import javax.swing.*;

public class MyThread extends Thread{

    JSlider slider;
    int targetValue;

    MyThread(JSlider slider, int targetValue) {
        this.slider = slider;
        this.targetValue = targetValue;
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            slider.setValue(targetValue);

            try {
                sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
