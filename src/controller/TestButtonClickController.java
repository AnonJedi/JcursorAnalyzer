package controller;

import common.Common;
import service.AnalyzerService;
import service.ButtonGeneratorService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestButtonClickController extends MouseAdapter {

    private ButtonGeneratorService generatorService;
    private AnalyzerService analyzerService;
    private Button oldButton;

    public TestButtonClickController(AnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
        this.generatorService = new ButtonGeneratorService();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int size = e.getComponent().getWidth();
        Label counter = (Label) e.getComponent()
                .getParent()
                .getParent()
                .getComponent(Common.COUNTER_LABEL_NUMBER);
        counter.setText(String.valueOf(analyzerService.parseClickParams(size, e.getPoint())));

        Button newButton = generatorService.generateNewButton(oldButton, 900, 700);
        Panel testArea = (Panel) e.getComponent().getParent();
        testArea.removeAll();
        testArea.add(newButton);
        oldButton = newButton;
    }
}
