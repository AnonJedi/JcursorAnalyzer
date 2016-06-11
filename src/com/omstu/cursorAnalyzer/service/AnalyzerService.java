package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.repository.ParamsRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AnalyzerService {

    private static int clickCounter = 0; //counter of clicked buttons
    private static Date currentClickTime; //current click
    private static Point previousButtonPos;

    /**
     * Method for parse mouse metrics and time between clicks
     * and save params to class-container
     */
    public static int parseClickParams(int buttonSize, Point buttonPos) {
        Date previousClickTime = currentClickTime;
        currentClickTime = new Date();
        if (clickCounter == 0) {
            clickCounter++;
        } else if (ParamsCalculatorService.getMouseTrack().size() > 64) {
            try {
                ParamsRepository.savePoints(ParamsCalculatorService.getMouseTrack());
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
            Long timeRangeBetweenClicks = currentClickTime.getTime() - previousClickTime.getTime();
            ParamsCalculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);
            ParamsCalculatorService.saveAllParams(
                    previousButtonPos, buttonPos, timeRangeBetweenClicks, buttonSize);
            clickCounter++;
        }
        previousButtonPos = buttonPos;
        ParamsCalculatorService.setMouseTrack(new ArrayList<Point>());
        return clickCounter;
    }

    /**
     * Method for saving result data in DB
     * @param isStore
     */
    public static void stopTest(boolean isStore) throws ServiceException {
        if (isStore) {
            ParamsCalculatorService.saveMouseParamsAndMetrics(clickCounter);
        }

        ParamsCalculatorService.reloadFields();
        clickCounter = 0;
    }
}
