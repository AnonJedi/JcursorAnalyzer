package service;

import repository.MetricsRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AnalyzerService {

    private ParamsCalculatorService calculatorService;
    private int clickCounter; //counter of clicked buttons
    private Date beginTestTime; //test time
    private Date currentClickTime; //current click

    public AnalyzerService(ParamsCalculatorService calculationService) {
        this.calculatorService = calculationService;
        clickCounter = 0;
    }

    /**
     * Method for parse mouse metrics and time betveen clicks
     * and save params to class-container
     */
    public int parseClickParams(int buttonSize, Point buttonPos) {
        Date timeRangeBetweenClicks;
        if (clickCounter == 0) {
            beginTestTime = new Date();
            timeRangeBetweenClicks = new Date(0);
            currentClickTime = new Date();
            calculatorService.getClickTimeContainer().add(beginTestTime);
        }
        else
        {
            Date previousClickTime = currentClickTime;
            currentClickTime = new Date();
            calculatorService.getClickTimeContainer().add(currentClickTime);
            timeRangeBetweenClicks = new Date(currentClickTime.getTime() - previousClickTime.getTime());
            calculatorService.getMouseTracksContainer().add(calculatorService.getMouseTrack());
            calculatorService.setMouseTrack(new ArrayList<>());
        }
        calculatorService.getClickTimeContainer().add(timeRangeBetweenClicks);

        calculatorService.saveAllParams(buttonSize, buttonPos, clickCounter,
                calculatorService.getClickTimeContainer().get(calculatorService.getClickTimeContainer().size() - 1));
        return clickCounter++;
    }

    /**
     * Method for saving result data in DB
     * @param isStore
     */
    public void stopTest(boolean isStore) {
        if (clickCounter > 1) {
            Date testTime = new Date(currentClickTime.getTime() - beginTestTime.getTime());
            calculatorService.saveMidV(testTime);
            calculatorService.saveMathExpectation();
            calculatorService.saveVariance();
        }
        if (isStore)
        {
            MetricsRepository repository = new MetricsRepository();
            repository.saveMouseParamsAndMetrics(calculatorService.getMidDiffTracks(),
                    calculatorService.getMaxDiffTracks(), calculatorService.getT(),
                    calculatorService.getAmpContainer(), calculatorService.getMouseSpeed(),
                    calculatorService.getEnergyContainer(), calculatorService.getLensContainer());
        }
    }
}
