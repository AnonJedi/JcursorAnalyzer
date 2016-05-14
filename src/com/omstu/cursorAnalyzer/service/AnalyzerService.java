package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.repository.MetricsRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AnalyzerService {

    private static int clickCounter = 0; //counter of clicked buttons
    private static Date beginTestTime; //test time
    private static Date currentClickTime; //current click

    /**
     * Method for parse mouse metrics and time betveen clicks
     * and save params to class-container
     */
    public static int parseClickParams(int buttonSize, Point buttonPos) {
        Long timeRangeBetweenClicks;
        if (clickCounter == 0) {
            beginTestTime = new Date();
            timeRangeBetweenClicks = 0L;
            currentClickTime = new Date();
            ParamsCalculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);
        }
        else
        {
            Date previousClickTime = currentClickTime;
            currentClickTime = new Date();
            timeRangeBetweenClicks = currentClickTime.getTime() - previousClickTime.getTime();
            ParamsCalculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);
            ParamsCalculatorService.getMouseTracksContainer().add(ParamsCalculatorService.getMouseTrack());
            ParamsCalculatorService.setMouseTrack(new ArrayList<>());
        }
        ParamsCalculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);

        if (ParamsCalculatorService.getMouseTrack().size() != 0) {
            ParamsCalculatorService.saveAllParams(buttonSize, buttonPos, clickCounter,
                    ParamsCalculatorService.getClickTimeContainer().get(
                            ParamsCalculatorService.getClickTimeContainer().size() - 1));
            clickCounter++;
        }
        return clickCounter;
    }

    /**
     * Method for saving result data in DB
     * @param isStore
     */
    public static void stopTest(boolean isStore) {
        if (clickCounter > 1) {
            Long testTime = currentClickTime.getTime() - beginTestTime.getTime();
            ParamsCalculatorService.saveMidV(testTime);
            ParamsCalculatorService.saveMathExpectation();
            ParamsCalculatorService.saveVariance();
        }
        if (isStore)
        {
            MetricsRepository repository = new MetricsRepository();
            repository.saveMouseParamsAndMetrics(ParamsCalculatorService.getMidDiffTracks(),
                    ParamsCalculatorService.getMaxDiffTracks(), ParamsCalculatorService.getT(),
                    ParamsCalculatorService.getAmpContainer(), ParamsCalculatorService.getMouseSpeed(),
                    ParamsCalculatorService.getEnergyContainer(), ParamsCalculatorService.getLensContainer());
        }

        ParamsCalculatorService.reloadFields();
        clickCounter = 0;
    }
}
