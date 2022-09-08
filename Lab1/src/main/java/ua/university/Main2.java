package ua.university;

import javax.swing.*;

public class Main2 {
    static JFrame frame;
    static JButton buttonLeftStart, buttonRightStart, buttonLeftStop, buttonRightStop;
    static JSlider slider;
    static JPanel panelVertical, panelHorizontalStart, panelHorizontalStop;

    static MyThread threadLeft, threadRight;

    /**
     * 0 if both threads are stopped;
     * 1 if a thread is running.
     */
    static int semaphore = 0;

    public static void main(String[] args) {
        initComponents();

        setOnButtonClick();

        initPanelHorizontalStart();
        initPanelHorizontalStop();
        initPanelVertical();

        setupFrame();
    }

    private static void initComponents() {
        frame = new JFrame("panel");
        slider = new JSlider(0, 100, 50);
        buttonLeftStart = new JButton("Start to 10!");
        buttonRightStart = new JButton("Start to 90!");
        buttonLeftStop = new JButton("Stop to 10!");
        buttonRightStop = new JButton("Stop to 90!");

        buttonRightStop.setVisible(false);
        buttonLeftStop.setVisible(false);
    }

    private static void setupFrame() {
        frame.add(panelVertical);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void initPanelVertical() {
        panelVertical = new JPanel();
        panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));

        panelVertical.add(slider);
        panelVertical.add(panelHorizontalStart);
        panelVertical.add(panelHorizontalStop);
    }

    private static void initPanelHorizontalStart() {
        panelHorizontalStart = new JPanel();
        panelHorizontalStart.setLayout(new BoxLayout(panelHorizontalStart, BoxLayout.X_AXIS));

        panelHorizontalStart.add(buttonLeftStart);
        panelHorizontalStart.add(buttonRightStart);
    }

    private static void initPanelHorizontalStop() {
        panelHorizontalStop = new JPanel();
        panelHorizontalStop.setLayout(new BoxLayout(panelHorizontalStop, BoxLayout.X_AXIS));

        panelHorizontalStop.add(buttonLeftStop);
        panelHorizontalStop.add(buttonRightStop);
    }

    private static void setOnButtonClick() {
        buttonLeftStart.addActionListener(e -> {
            if (semaphore == 0) {
                threadLeft = new MyThread(slider, 10);
                threadLeft.start();
                buttonLeftStart.setVisible(false);
                buttonLeftStop.setVisible(true);

                semaphore = 1;
            }
        });

        buttonRightStart.addActionListener(e -> {
            if (semaphore == 0) {
                threadRight = new MyThread(slider, 90);
                threadRight.start();
                buttonRightStart.setVisible(false);
                buttonRightStop.setVisible(true);

                semaphore = 1;
            }
        });

        buttonRightStop.addActionListener(e -> {
            if (semaphore == 1) {
                threadRight.interrupt();
                buttonRightStop.setVisible(false);
                buttonRightStart.setVisible(true);
                buttonLeftStart.setVisible(true);

                semaphore = 0;
            }
        });

        buttonLeftStop.addActionListener(e -> {
            if (semaphore == 1) {
                threadLeft.interrupt();
                buttonLeftStop.setVisible(false);
                buttonLeftStart.setVisible(true);
                buttonRightStart.setVisible(true);

                semaphore = 0;
            }
        });
    }

}
