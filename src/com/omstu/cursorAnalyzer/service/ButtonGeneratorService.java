package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.controller.ActionAreaClickController;
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
        int size = random.nextInt(180) + 20;
        int oldSize = oldButton.getWidth();
        int oldX = oldButton.getX();
        int oldY = oldButton.getY();
        int newX = 0, newY = 0;
        double buttonsLen = -1;
        //check distance between two buttons on not lower than 64 pixels
        while(buttonsLen < (64 + Math.sqrt(2 * size * size) + Math.sqrt(2 * oldSize * oldSize))) {
            newX = random.nextInt(areaWidth - size);
            newY = random.nextInt(areaHeight - size);
            buttonsLen = Math.sqrt(Math.pow(newX - oldX, 2) + Math.pow(newY - oldY, 2));
        }

        newButton.setBounds(newX >= 0 ? newX : 0, newY >= 0 ? newY : 0, size, size);
        newButton.addMouseListener(new TestButtonClickController());
        return newButton;
    }

    public Button generateNewButton(int areaWidth, int areaHeight) {
        Button newButton = new Button();
        int size = random.nextInt(180) + 20;
        int x = random.nextInt(areaWidth - size);
        int y = random.nextInt(areaHeight - size);
        newButton.setBounds(x >= 0 ? x : 0, y >= 0 ? y : 0, size, size);
        newButton.addMouseListener(new TestButtonClickController());
        return newButton;
    }
}
