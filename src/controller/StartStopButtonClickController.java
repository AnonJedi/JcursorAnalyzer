package controller;

import common.Common;
import service.ButtonGeneratorService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartStopButtonClickController extends MouseAdapter {

    private ButtonGeneratorService generatorService;

    public StartStopButtonClickController() {
        generatorService = new ButtonGeneratorService();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Button button = (Button) e.getComponent();
        Panel testArea = (Panel) e.getComponent()
                .getParent()
                .getComponent(Common.TEST_AREA_NUMBER);
        if (button.getLabel().equals("Start")) {
            button.setLabel("Stop");
            testArea.add(generatorService.generateNewButton(null, 900, 700));
        } else {
            testArea.removeAll();
            button.setLabel("Start");
            button.setEnabled(false);

            ActionAreaClickController.restartTestServices();
        }
    }
}
