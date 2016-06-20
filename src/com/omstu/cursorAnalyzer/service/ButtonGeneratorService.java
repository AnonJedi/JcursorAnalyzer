package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.controller.TestButtonClickController;

import java.awt.*;
import java.util.Random;

public class ButtonGeneratorService {

    private Random random;

    public ButtonGeneratorService() {
        random = new Random();
    }

    public Button generateNewButton(Button oldButton, int areaWidth, int areaHeight) {
        Button newButton = new Button();
        int size = random.nextInt(180) + 40;
        int oldSize = oldButton.getWidth();
        int oldX = oldButton.getX();
        int oldY = oldButton.getY();
        int newX = 0, newY = 0;
        double buttonsLen = 1;
        boolean cond = false;
        //check distance between two buttons on not lower than 64 pixels
        while(buttonsLen * 2 < 64 && !cond) {
            newX = random.nextInt(areaWidth - size);
            newY = random.nextInt(areaHeight - size);
            buttonsLen = Math.sqrt(Math.pow(newX - oldX, 2) + Math.pow(newY - oldY, 2));
            buttonsLen -= (Math.sqrt(oldSize * oldSize) + Math.sqrt(size * size));
            cond = size < buttonsLen;
        }
        newButton.setBounds(newX, newY, size, size);
        newButton.setBackground(new Color(135, 0, 201));
        newButton.addMouseListener(new TestButtonClickController());
        return newButton;
    }

    public Button generateNewButton(int areaWidth, int areaHeight) {
        Button newButton = new Button();
        int size = random.nextInt(180) + 40;
        int x = random.nextInt(areaWidth - size);
        int y = random.nextInt(areaHeight - size);
        newButton.setBounds(x, y, size, size);
        newButton.setBackground(new Color(135, 0, 201));
        newButton.addMouseListener(new TestButtonClickController());
        return newButton;
    }
}
