package com.omstu.cursorAnalyzer.view;

import com.omstu.cursorAnalyzer.controller.ActionAreaClickController;
import com.omstu.cursorAnalyzer.controller.RegButtonClickController;
import com.omstu.cursorAnalyzer.controller.StartStopButtonClickController;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppView extends Frame {

    public AppView(String title) {
        super(title);
        setLayout(null);
        setBounds(0, 0, 1200, 700);
        addWindowListener(new CustomWindowAdapter());
        setVisible(true);

        TextField textField = new TextField(1);
        textField.setBounds(100, 50, 150, 20);
        add(textField);

        Button regButton = new Button("Sign up");
        regButton.setBounds(100, 80, 70, 30);
        regButton.addMouseListener(new RegButtonClickController());
        add(regButton);

        Label regLabel = new Label("Your name");
        regLabel.setBounds(20, 50, 70, 20);
        add(regLabel);

        Button startStopButton = new Button("Start");
        startStopButton.setBounds(100, 180, 70, 30);
        startStopButton.setEnabled(false);
        startStopButton.addMouseListener(new StartStopButtonClickController());
        add(startStopButton);

        Panel testArea = new Panel();
        testArea.setBounds(300, 0, 900, 700);
        testArea.addMouseListener(new ActionAreaClickController());
        testArea.setBackground(new Color(100, 100, 100));
        add(testArea);

        Label user = new Label("");
        user.setBounds(20, 120, 200, 20);
        add(user);

        Label counterLabel = new Label("Counter: ");
        counterLabel.setBounds(20, 150, 50, 20);
        counterLabel.setVisible(false);
        add(counterLabel);

        Label counter = new Label("");
        counter.setBounds(75, 150, 30, 20);
        add(counter);
    }

    class CustomWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
}
