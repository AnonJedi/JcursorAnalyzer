package com.omstu.cursorAnalyzer.controller;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.service.AnalyzerService;
import com.omstu.cursorAnalyzer.service.ButtonGeneratorService;
import com.omstu.cursorAnalyzer.service.ParamsCalculatorService;
import com.omstu.cursorAnalyzer.view.MessageBox;

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
            TestButtonClickController.oldButton = generatorService.generateNewButton(900, 700);
            testArea.add(TestButtonClickController.oldButton);
            testArea.addMouseMotionListener(new ActionAreaClickController());
            ParamsCalculatorService.reloadFields();
        } else {
            testArea.removeAll();
            button.setLabel("Start");
            button.setEnabled(false);
            testArea.removeMouseMotionListener(new ActionAreaClickController());
            try {
                AnalyzerService.stopTest(true);
            } catch (ServiceException ex) {
                new MessageBox("Error", ex.getMessage(), "OK");
            }
        }
    }
}
