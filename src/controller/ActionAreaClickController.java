package controller;

import service.AnalyzerService;
import service.ParamsCalculatorService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionAreaClickController extends MouseAdapter {
    private static ParamsCalculatorService calculatorService;
    private static AnalyzerService analyzerService;

    public static AnalyzerService getAnalyzerService() {
        return analyzerService;
    }

    public ActionAreaClickController() {
        restartTestServices();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        calculatorService.saveMouseTrack(e.getPoint());
    }

    public static void restartTestServices(){
        calculatorService = new ParamsCalculatorService();
        analyzerService = new AnalyzerService(calculatorService);
    }
}
