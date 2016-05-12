package service;

import controller.ActionAreaClickController;
import controller.TestButtonClickController;

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
        newButton.setBounds(x, y, size, size);
        newButton.addMouseListener(new TestButtonClickController(
                ActionAreaClickController.getAnalyzerService()));
        return newButton;
    }
}
