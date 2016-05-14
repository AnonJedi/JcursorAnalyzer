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
        int x = random.nextInt(areaWidth - size);
        int y = random.nextInt(areaHeight - size);
        newButton.setBounds(x >= 0 ? x : 0, y >= 0 ? y : 0, size, size);
        newButton.addMouseListener(new TestButtonClickController());
        return newButton;
    }
}
