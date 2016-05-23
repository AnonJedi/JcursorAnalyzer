package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.sun.media.sound.*;
import com.sun.media.sound.FFT;

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
        Date previousClickTime = currentClickTime;
        currentClickTime = new Date();
        if (clickCounter == 0) {
            beginTestTime = new Date();

            clickCounter++;
        } else if (ParamsCalculatorService.getMouseTrack().size() >= 64) {
            Long timeRangeBetweenClicks = currentClickTime.getTime() - previousClickTime.getTime();
            ParamsCalculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);
            ParamsCalculatorService.saveAllParams(
                    buttonSize, buttonPos, clickCounter,
                    timeRangeBetweenClicks);
            clickCounter++;
        }
        ParamsCalculatorService.setMouseTrack(new ArrayList<>());
        return clickCounter;
    }

    /**
     * Method for saving result data in DB
     * @param isStore
     */
    public static void stopTest(boolean isStore) throws ServiceException {
        if (clickCounter > 1) {
            Long testTime = currentClickTime.getTime() - beginTestTime.getTime();
            ParamsCalculatorService.saveMidV(testTime);
            ParamsCalculatorService.saveMathExpectation();
            ParamsCalculatorService.saveVariance();
        }
        if (isStore) {
            ParamsCalculatorService.saveMouseParamsAndMetrics(ParamsCalculatorService.getMidDiffTracks(),
                    ParamsCalculatorService.getMaxDiffTracks(), ParamsCalculatorService.getT(),
                    ParamsCalculatorService.getAmpContainer(), ParamsCalculatorService.getMouseSpeed(),
                    ParamsCalculatorService.getEnergyContainer(), ParamsCalculatorService.getLensContainer());
        }

        ParamsCalculatorService.reloadFields();
        clickCounter = 0;
    }
}
