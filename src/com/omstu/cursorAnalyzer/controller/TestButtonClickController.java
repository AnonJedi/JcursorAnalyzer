package com.omstu.cursorAnalyzer.controller;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.service.AnalyzerService;
import com.omstu.cursorAnalyzer.service.ButtonGeneratorService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestButtonClickController extends MouseAdapter {

    private ButtonGeneratorService generatorService;
    public static Button oldButton;

    public TestButtonClickController() {
        this.generatorService = new ButtonGeneratorService();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int size = e.getComponent().getWidth();
        Label counter = (Label) e.getComponent()
                .getParent()
                .getParent()
                .getComponent(Common.USER_TEST_COUNTER_NUMBER);
        counter.setText(String.valueOf(AnalyzerService.parseClickParams(size, e.getPoint())));

        Panel testArea = (Panel) e.getComponent().getParent();
        Button newButton = generatorService.generateNewButton(oldButton, testArea.getWidth(), testArea.getHeight());
        testArea.removeAll();
        testArea.add(newButton);
        oldButton = newButton;
    }
}
