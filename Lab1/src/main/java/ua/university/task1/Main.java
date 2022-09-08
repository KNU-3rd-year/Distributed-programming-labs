package ua.university.task1;

import javax.swing.*;

public class Main {
    static JFrame frame;
    static JButton button;
    static JSpinner spinnerLeft, spinnerRight;
    static JSlider slider;
    static JPanel panelVertical, panelHorizontal;

    static MyThread threadLeft, threadRight;

    static boolean isThreadsStarted = false;

    public static void main(String[] args) {
        initComponents();

        initSpinners();
        initThreads();

        setOnButtonClick();
        setOnSinnerLeftValueChange(spinnerLeft, threadLeft);
        setOnSinnerLeftValueChange(spinnerRight, threadRight);

        initPanelHorizontal();
        initPanelVertical();

        setupFrame();
    }

    private static void initComponents() {
        frame = new JFrame("panel");
        slider = new JSlider(0, 100, 50);
        spinnerLeft = new JSpinner();
        spinnerRight = new JSpinner();
        button = new JButton("Start!");
    }

    private static void setupFrame() {
        frame.add(panelVertical);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void initSpinners() {
        spinnerLeft.setValue(1);
        spinnerRight.setValue(1);
    }

    private static void initThreads() {
        threadLeft = new MyThread(slider, 10);
        threadRight = new MyThread(slider, 90);
    }

    private static void initPanelVertical() {
        panelVertical = new JPanel();
        panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));

        panelVertical.add(slider);
        panelVertical.add(panelHorizontal);
        panelVertical.add(button);
    }

    private static void initPanelHorizontal() {
        panelHorizontal = new JPanel();
        panelHorizontal.setLayout(new BoxLayout(panelHorizontal, BoxLayout.X_AXIS));

        panelHorizontal.add(spinnerLeft);
        panelHorizontal.add(spinnerRight);
    }

    private static void setOnButtonClick() {
        if (!isThreadsStarted) {
            button.addActionListener(e -> {
                threadLeft.start();
                threadRight.start();
            });

            isThreadsStarted = true;
        }
    }

    private static void setOnSinnerLeftValueChange(JSpinner spinner, MyThread thread) {
        spinner.addChangeListener(e -> {
            int priority = (Integer) spinner.getValue();
            thread.setPriority(priority);
        });
    }
}