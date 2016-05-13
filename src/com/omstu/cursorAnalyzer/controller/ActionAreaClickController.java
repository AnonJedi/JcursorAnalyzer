package com.omstu.cursorAnalyzer.controller;

import com.omstu.cursorAnalyzer.service.ParamsCalculatorService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionAreaClickController extends MouseAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        ParamsCalculatorService.saveMouseTrack(e.getPoint());
    }
}
