package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.repository.UserRepository;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Service for calculation params of mouse metrics
 */
public class ParamsCalculatorService {

    //текущие координаты мыши
    private static Point mousePoint;

    //траектория мышки
    private static ArrayList<Point> mouseTrack;

    //хранилище траекторий мыши
    private static ArrayList<ArrayList<Point>> mouseTracksContainer;

    //хранилище текущего параметра C
    private static ArrayList<ArrayList<Double>> tracksDiffContainer;

    //хранилище максимальных отклонений траекторий
    private static ArrayList<Double> maxDiffTracks;

    //хранилище средних отклонений траекторий
    private static ArrayList<Double> midDiffTracks;

    //размер стороны куба
    private static ArrayList<Integer> shapeSize;

    //хранилище расстояний между центрами фигур
    private static ArrayList<Double> lensContainer;

    //длинна траектории мыши
    private static ArrayList<Double> distanceLen;

    //хранилище времён между кликами
    private static ArrayList<Long> clickTimeContainer;

    //средняя скорость движения мыши
    private static Double midMouseSpeed;

    //хранилище Т
    private static ArrayList<Double> t;

    //математическое ожидание значений Т
    private static double expirationT;

    //математическое ожидание максимальных отклонений траектории мыши от идеальной траектории
    private static double maxDiffTracksExpiration;

    //математическое ожидание средних отклонений траектории мыши от идеальной траектории
    private static double midDiffTracksExpiration;

    //среднеквадратичное отклонение параметра T
    private static double tDispertion;

    //среднеквадратичное отклонение среднего отклонения траекторий
    private static double midDiffTracksDispertion;

    //среднеквадратичное отклонение максимального отклонения траекторий
    private static double maxDiffTracksDispersion;

    //хранилище отклонений координаты мыши от идеальной координаты
    private static ArrayList<Double> diffContainer;

    //хранилище амплитуд ряда распределения отклонений мыши
    private static ArrayList<Float[]> ampContainer;

    //математическое ожидание амплитуд отклонения
    private static float[] ampExpiration;

    //дисперсия амплитуд отклонения
    private static float[] ampDispertion;

    //
    private static float[] allAmp;

    //контейнер вычисленной энергии движения мыши
    private static ArrayList<Double> energyContainer;

    //контейнер времён
    private static ArrayList<Date> timeContainer;

    //текущая скорость движения мыши
    private static ArrayList<Double> mouseSpeed;

    public static Point getMousePoint() {
        return mousePoint;
    }

    public static void setMousePoint(Point mousePoint) {
        ParamsCalculatorService.mousePoint = mousePoint;
    }

    public static ArrayList<Point> getMouseTrack() {
        return mouseTrack;
    }

    public static void setMouseTrack(ArrayList<Point> mouseTrack) {
        ParamsCalculatorService.mouseTrack = mouseTrack;
    }

    public static ArrayList<ArrayList<Point>> getMouseTracksContainer() {
        return mouseTracksContainer;
    }

    public static void setMouseTracksContainer(ArrayList<ArrayList<Point>> mouseTracksContainer) {
        ParamsCalculatorService.mouseTracksContainer = mouseTracksContainer;
    }

    public static ArrayList<ArrayList<Double>> getTracksDiffContainer() {
        return tracksDiffContainer;
    }

    public static void setTracksDiffContainer(ArrayList<ArrayList<Double>> tracksDiffContainer) {
        ParamsCalculatorService.tracksDiffContainer = tracksDiffContainer;
    }

    public static ArrayList<Double> getMaxDiffTracks() {
        return maxDiffTracks;
    }

    public static void setMaxDiffTracks(ArrayList<Double> maxDiffTracks) {
        ParamsCalculatorService.maxDiffTracks = maxDiffTracks;
    }

    public static ArrayList<Double> getMidDiffTracks() {
        return midDiffTracks;
    }

    public static void setMidDiffTracks(ArrayList<Double> midDiffTracks) {
        ParamsCalculatorService.midDiffTracks = midDiffTracks;
    }

    public static ArrayList<Integer> getShapeSize() {
        return shapeSize;
    }

    public static void setShapeSize(ArrayList<Integer> shapeSize) {
        ParamsCalculatorService.shapeSize = shapeSize;
    }

    public static ArrayList<Double> getLensContainer() {
        return lensContainer;
    }

    public static void setLensContainer(ArrayList<Double> lensContainer) {
        ParamsCalculatorService.lensContainer = lensContainer;
    }

    public static ArrayList<Double> getDistanceLen() {
        return distanceLen;
    }

    public static void setDistanceLen(ArrayList<Double> distanceLen) {
        ParamsCalculatorService.distanceLen = distanceLen;
    }

    public static ArrayList<Long> getClickTimeContainer() {
        return clickTimeContainer;
    }

    public static void setClickTimeContainer(ArrayList<Long> clickTimeContainer) {
        ParamsCalculatorService.clickTimeContainer = clickTimeContainer;
    }

    public static Double getMidMouseSpeed() {
        return midMouseSpeed;
    }

    public static void setMidMouseSpeed(Double midMouseSpeed) {
        ParamsCalculatorService.midMouseSpeed = midMouseSpeed;
    }

    public static ArrayList<Double> getT() {
        return t;
    }

    public static void setT(ArrayList<Double> t) {
        ParamsCalculatorService.t = t;
    }

    public static double getExpirationT() {
        return expirationT;
    }

    public static void setExpirationT(double expirationT) {
        ParamsCalculatorService.expirationT = expirationT;
    }

    public static double getMaxDiffTracksExpiration() {
        return maxDiffTracksExpiration;
    }

    public static void setMaxDiffTracksExpiration(double maxDiffTracksExpiration) {
        ParamsCalculatorService.maxDiffTracksExpiration = maxDiffTracksExpiration;
    }

    public static double getMidDiffTracksExpiration() {
        return midDiffTracksExpiration;
    }

    public static void setMidDiffTracksExpiration(double midDiffTracksExpiration) {
        ParamsCalculatorService.midDiffTracksExpiration = midDiffTracksExpiration;
    }

    public static double gettDispertion() {
        return tDispertion;
    }

    public static void settDispertion(double tDispertion) {
        ParamsCalculatorService.tDispertion = tDispertion;
    }

    public static double getMidDiffTracksDispertion() {
        return midDiffTracksDispertion;
    }

    public static void setMidDiffTracksDispertion(double midDiffTracksDispertion) {
        ParamsCalculatorService.midDiffTracksDispertion = midDiffTracksDispertion;
    }

    public static double getMaxDiffTracksDispersion() {
        return maxDiffTracksDispersion;
    }

    public static void setMaxDiffTracksDispersion(double maxDiffTracksDispersion) {
        ParamsCalculatorService.maxDiffTracksDispersion = maxDiffTracksDispersion;
    }

    public static ArrayList<Double> getDiffContainer() {
        return diffContainer;
    }

    public static void setDiffContainer(ArrayList<Double> diffContainer) {
        ParamsCalculatorService.diffContainer = diffContainer;
    }

    public static ArrayList<Float[]> getAmpContainer() {
        return ampContainer;
    }

    public static void setAmpContainer(ArrayList<Float[]> ampContainer) {
        ParamsCalculatorService.ampContainer = ampContainer;
    }

    public static float[] getAmpExpiration() {
        return ampExpiration;
    }

    public static void setAmpExpiration(float[] ampExpiration) {
        ParamsCalculatorService.ampExpiration = ampExpiration;
    }

    public static float[] getAmpDispertion() {
        return ampDispertion;
    }

    public static void setAmpDispertion(float[] ampDispertion) {
        ParamsCalculatorService.ampDispertion = ampDispertion;
    }

    public static float[] getAllAmp() {
        return allAmp;
    }

    public static void setAllAmp(float[] allAmp) {
        ParamsCalculatorService.allAmp = allAmp;
    }

    public static ArrayList<Double> getEnergyContainer() {
        return energyContainer;
    }

    public static void setEnergyContainer(ArrayList<Double> energyContainer) {
        ParamsCalculatorService.energyContainer = energyContainer;
    }

    public static ArrayList<Date> getTimeContainer() {
        return timeContainer;
    }

    public static void setTimeContainer(ArrayList<Date> timeContainer) {
        ParamsCalculatorService.timeContainer = timeContainer;
    }

    public static ArrayList<Double> getMouseSpeed() {
        return mouseSpeed;
    }

    public void setMouseSpeed(ArrayList<Double> mouseSpeed) {
        ParamsCalculatorService.mouseSpeed = mouseSpeed;
    }

    public static void reloadFields() {
        mouseTrack = new ArrayList<>();
        shapeSize = new ArrayList<>();
        lensContainer = new ArrayList<>();
        clickTimeContainer = new ArrayList<>();
        t = new ArrayList<>();
        ampContainer = new ArrayList<>();
        maxDiffTracks = new ArrayList<>();
        midDiffTracks = new ArrayList<>();
        energyContainer = new ArrayList<>();
        timeContainer = new ArrayList<>();
        mouseSpeed = new ArrayList<>();
        mouseTracksContainer = new ArrayList<>();
        diffContainer = new ArrayList<>();
        distanceLen = new ArrayList<>();
        tracksDiffContainer = new ArrayList<>();
        mousePoint = new Point(0, 0);
    }

    /**
     * Method for calculation math expiration for
     * all params and save it in container
     */
    public static void saveMathExpectation() {
        expirationT = 0;
        maxDiffTracksExpiration = 0;
        midDiffTracksExpiration = 0;
        ampExpiration = new float[10];
        allAmp = new float[2];
        allAmp[0] = 0;
        allAmp[1] = 0;
        for (int i = 0; i < 10; i++) ampExpiration[i] = 0;

        for (int i = 0; i < lensContainer.size(); i++) {
            t.add(midMouseSpeed * Math.log(lensContainer.get(i) / shapeSize.get(i) + 1) / Math.log(2));
            expirationT += t.get(i);
            maxDiffTracksExpiration += maxDiffTracks.get(i);
        }

        for (double d : midDiffTracks) midDiffTracksExpiration += d;
        for (Float[] floats : ampContainer) {
            ampExpiration[0] += floats[0];
            ampExpiration[1] += floats[1];
            ampExpiration[2] += floats[2];
            ampExpiration[3] += floats[3];
            ampExpiration[4] += floats[4];
            ampExpiration[5] += floats[5];
            ampExpiration[6] += floats[6];
            ampExpiration[7] += floats[7];
            ampExpiration[8] += floats[8];
            ampExpiration[9] += floats[9];
        }

        int count = 0;
        for (Float[] floats : ampContainer) {
            for (Float f : floats) {
                count++;
                allAmp[0] += f;
            }
        }
        allAmp[0] = allAmp[0]/count;

        expirationT = expirationT/lensContainer.size();
        maxDiffTracksExpiration = maxDiffTracksExpiration/lensContainer.size();
        midDiffTracksExpiration = midDiffTracksExpiration / midDiffTracks.size();

        for (int i = 0; i < 10; i++) ampExpiration[i] = ampExpiration[i]/ampContainer.size();
    }

    /**
     * Method for calcuulation dispersion for all parameters
     * and save it in container
     */
    public static void saveVariance() {
        midDiffTracksDispertion = 0;
        for (int i = 1; i < midDiffTracks.size(); i++) {
            midDiffTracksDispertion = Math.sqrt((i - 1) * midDiffTracksDispertion * midDiffTracksDispertion
                    / i + Math.pow(midDiffTracks.get(i) - midDiffTracksExpiration, 2));
        }

        maxDiffTracksDispersion = 0;
        for (int i = 1; i < maxDiffTracks.size(); i++) {
            maxDiffTracksDispersion = Math.sqrt((i - 1) * maxDiffTracksDispersion * maxDiffTracksDispersion
                    / i + Math.pow(maxDiffTracks.get(i) - maxDiffTracksExpiration, 2));
        }

        tDispertion = 0;
        for (int i = 1; i < t.size(); i++) {
            tDispertion = Math.sqrt((i - 1) * tDispertion * tDispertion / i + Math.pow(t.get(i) - expirationT, 2));
        }

        ArrayList<Float> temp = new ArrayList<>();
        for (Float[] floats : ampContainer) {
            Collections.addAll(temp, floats);
        }

        for (int i = 1; i < temp.size(); i++) {
            allAmp[1] = (float)Math.sqrt((i - 1) * allAmp[1] * allAmp[1] / i + Math.pow(temp.get(i) - allAmp[0], 2));
        }

        ampDispertion = new float[10];
        for (int i = 0; i < 10; i++) ampDispertion[i] = 0;

        for (int i = 1; i < ampContainer.size(); i++) {
            ampDispertion[0] = (float)Math.sqrt((i - 1) * ampDispertion[0] * ampDispertion[0]
                    / i + Math.pow(ampContainer.get(i)[0] - ampExpiration[0], 2));
            ampDispertion[1] = (float)Math.sqrt((i - 1) * ampDispertion[1] * ampDispertion[1]
                    / i + Math.pow(ampContainer.get(i)[1] - ampExpiration[1], 2));
            ampDispertion[2] = (float)Math.sqrt((i - 1) * ampDispertion[2] * ampDispertion[2]
                    / i + Math.pow(ampContainer.get(i)[2] - ampExpiration[2], 2));
            ampDispertion[3] = (float)Math.sqrt((i - 1) * ampDispertion[3] * ampDispertion[3]
                    / i + Math.pow(ampContainer.get(i)[3] - ampExpiration[3], 2));
            ampDispertion[4] = (float)Math.sqrt((i - 1) * ampDispertion[4] * ampDispertion[4]
                    / i + Math.pow(ampContainer.get(i)[4] - ampExpiration[4], 2));
            ampDispertion[5] = (float)Math.sqrt((i - 1) * ampDispertion[5] * ampDispertion[5]
                    / i + Math.pow(ampContainer.get(i)[5] - ampExpiration[5], 2));
            ampDispertion[6] = (float)Math.sqrt((i - 1) * ampDispertion[6] * ampDispertion[6]
                    / i + Math.pow(ampContainer.get(i)[6] - ampExpiration[6], 2));
            ampDispertion[7] = (float)Math.sqrt((i - 1) * ampDispertion[7] * ampDispertion[7]
                    / i + Math.pow(ampContainer.get(i)[7] - ampExpiration[7], 2));
            ampDispertion[8] = (float)Math.sqrt((i - 1) * ampDispertion[8] * ampDispertion[8]
                    / i + Math.pow(ampContainer.get(i)[8] - ampExpiration[8], 2));
            ampDispertion[9] = (float)Math.sqrt((i - 1) * ampDispertion[9] * ampDispertion[9]
                    / i + Math.pow(ampContainer.get(i)[9] - ampExpiration[9], 2));
        }
    }

    /**
     * Calculation of middle speed of mouse moving
     */
    public static void saveMidV(Long testTime) {
        double temp = 0;
        for (int i = 1; i < mouseTrack.size(); i++) {
            temp += Math.sqrt(Math.pow(mouseTrack.get(i).getX() - mouseTrack.get(i - 1).getX(), 2))
                    + Math.sqrt(Math.pow(mouseTrack.get(i).getY() - mouseTrack.get(i-1).getY(), 2));
        }
        midMouseSpeed = temp * 1000 / testTime;
    }

    /**
     * Method for calculation and final save params in containers
     */
    public static void saveAllParams(int buttonSize, Point buttonPos, int counter, Long timeRange) {
        mouseTracksContainer.add(mouseTrack);
        double a;
        double c;
        try {
            a = (buttonPos.getY() + buttonSize / 2 - mousePoint.getY() - buttonSize / 2) /
                    (buttonPos.getX() - mousePoint.getX() - buttonPos.getX() / 2);
        } catch (Exception e) {
            a = 0;
        }
        c = mousePoint.getY() - a * (mousePoint.getY() + buttonSize / 2);

        lensContainer.add(Math.sqrt(Math.pow(buttonPos.getX() + buttonSize / 2 - mousePoint.getX() + buttonSize / 2, 2))
                + Math.sqrt(Math.pow(buttonPos.getY() + buttonSize / 2 - mousePoint.getY() + buttonSize / 2, 2)));
        shapeSize.add(buttonSize);
        midDiffTracks.add(0.0);

        for (int i = 0; i < mouseTracksContainer.get(counter-1).size(); i++) {
            diffContainer.add(Math.abs(a * mouseTracksContainer.get(counter-1).get(i).getX() +
                    mouseTracksContainer.get(counter-1).get(i).getY() + c) / Math.sqrt(a * a + 1));
            midDiffTracks.set(midDiffTracks.size() - 1,
                    midDiffTracks.get(midDiffTracks.size() - 1) + diffContainer.get(diffContainer.size() - 1));
        }
        midDiffTracks.set(midDiffTracks.size() - 1, midDiffTracks.get(midDiffTracks.size() - 1) / diffContainer.size()/
                lensContainer.get(lensContainer.size() - 1));

        for (int i = 0; i < mouseTracksContainer.get(counter-1).size() - 1; i++) {
            distanceLen.add(Math.sqrt(Math.pow(mouseTracksContainer.get(counter-1).get(i).getX() -
                    mouseTracksContainer.get(counter-1).get(i + 1).getX(), 2)) +
                    Math.sqrt(Math.pow(mouseTracksContainer.get(counter-1).get(i).getY() -
                            mouseTracksContainer.get(counter-1).get(i + 1).getY(), 2)));
        }

        double max = diffContainer.get(0);
        for (double d : diffContainer) if (d > max) max = d;

        maxDiffTracks.add(max / lensContainer.get(lensContainer.size() - 1));

        int n = 128;
        double len = mouseTrack.size();

        float[] ar = new float[n];
        float[] ai = new float[n];

        for (int i = 0; i < n; i++) {
            if (i < distanceLen.size()) ar[i] = Float.parseFloat(distanceLen.get(i).toString());
            else ar[i] = 0;
            ai[i] = 0;
        }

        FFT.ComplexToComplex(-1, n, ar, ai);

        float[] am = new float[n];
        double energy = 0;
        for (int i = 0; i < n; i++) {
            ar[ar.length - i - 1] = ar[ar.length - i - 1] - ar[i];
            ai[ai.length - i - 1] = ai[ai.length - i - 1] + ai[i];
            am[i] = ((ar[i] * ar[i] + ai[i] * ai[i]) / am.length);
        }

        Float[] amp = new Float[n];
        for (Float f : am) energy += f*f;
        for (int i = 0; i < n; i++) amp[i] = am[i] / (float)energy;
        energyContainer.add(energy);

        ampContainer.add(amp);

        mouseSpeed.add((double) (mouseTrack.size() * 1000 / timeRange));
        tracksDiffContainer.add(diffContainer);
        diffContainer = new ArrayList<>();

        mousePoint = new Point(buttonPos);
    }

    public static void saveMouseTrack(Point mousePos) {
        mouseTrack.add(mousePos);
    }

    public static void saveMouseParamsAndMetrics(
            ArrayList<Double> midDiffTracks, ArrayList<Double> maxDiffTracks, ArrayList<Double> T,
            ArrayList<Float[]> ampContainer, ArrayList<Double> mouseSpeed, ArrayList<Double> energyContainer,
            ArrayList<Double> distanceLen) throws ServiceException {

        UserRepository repository = new UserRepository(Common.DB_PATH);
        try {
            Document document = repository.getXMLDocument();
            Node features = document.getElementsByTagName("Features").item(0);
            Node classNode = features.getLastChild();

            for (int i = 0; i < midDiffTracks.size(); i++) {
                Element realizationNode = document.createElement("Realization");

                Element featureNode = document.createElement("Feature");
                Attr idAttribute = document.createAttribute("id");
                idAttribute.setValue("1");
                Attr valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(midDiffTracks.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                featureNode = document.createElement("Feature");
                idAttribute = document.createAttribute("id");
                idAttribute.setValue("2");
                valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(maxDiffTracks.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                featureNode = document.createElement("Feature");
                idAttribute = document.createAttribute("id");
                idAttribute.setValue("3");
                valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(T.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                for (int j = 0; j < 10; j++) {
                    featureNode = document.createElement("Feature");
                    idAttribute = document.createAttribute("id");
                    idAttribute.setValue(String.valueOf(j + 4));
                    valueAttribute = document.createAttribute("value");
                    valueAttribute.setValue(ampContainer.get(i)[j].toString());
                    featureNode.setAttributeNode(idAttribute);
                    featureNode.setAttributeNode(valueAttribute);
                    realizationNode.appendChild(featureNode);
                }

                featureNode = document.createElement("Feature");
                idAttribute = document.createAttribute("id");
                idAttribute.setValue("14");
                valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(mouseSpeed.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                featureNode = document.createElement("Feature");
                idAttribute = document.createAttribute("id");
                idAttribute.setValue("15");
                valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(energyContainer.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                featureNode = document.createElement("Feature");
                idAttribute = document.createAttribute("id");
                idAttribute.setValue("16");
                valueAttribute = document.createAttribute("value");
                valueAttribute.setValue(distanceLen.get(i).toString());
                featureNode.setAttributeNode(idAttribute);
                featureNode.setAttributeNode(valueAttribute);
                realizationNode.appendChild(featureNode);

                classNode.appendChild(realizationNode);
            }

            features.appendChild(classNode);
            repository.insert(document);
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
