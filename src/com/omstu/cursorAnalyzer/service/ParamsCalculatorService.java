package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.external.Complex;
import com.omstu.cursorAnalyzer.external.FFT;
import com.omstu.cursorAnalyzer.repository.UserRepository;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
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

    //mouse current point
    private static Point mousePoint;

    //mouse track between buttons
    private static ArrayList<Point> mouseTrack;

    //tracks container
    private static ArrayList<ArrayList<Point>> mouseTracksContainer;

    //buttons size container
    private static ArrayList<Integer> shapeSize;

    private static ArrayList<Double> distanceLen;

    public ArrayList<Double> getDistanceLen() {
        return distanceLen;
    }

    public void setDistanceLen(ArrayList<Double> distanceLen) {
        ParamsCalculatorService.distanceLen = distanceLen;
    }

    //container of distances between buttons
    private static ArrayList<Double> lensContainer;

    //хранилище времён между кликами
    private static ArrayList<Long> clickTimeContainer;

    //средняя скорость движения мыши
    private static Double midMouseSpeed;

    //хранилище амплитуд ряда распределения отклонений мыши
    private static ArrayList<Float[]> ampContainer;

    //
    private static float[] allAmp;

    //контейнер вычисленной энергии движения мыши
    private static ArrayList<Double> energyContainer;

    //контейнер времён
    private static ArrayList<Date> timeContainer;

    //текущая скорость движения мыши
    private static ArrayList<Double> mouseSpeed;

    private static ArrayList<Float[]> normalizedAnmContainer;

    private static ArrayList<Float[]> logAmpContainer;

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

    public static ArrayList<Float[]> getAmpContainer() {
        return ampContainer;
    }

    public static void setAmpContainer(ArrayList<Float[]> ampContainer) {
        ParamsCalculatorService.ampContainer = ampContainer;
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
        ampContainer = new ArrayList<>();
        normalizedAnmContainer = new ArrayList<>();
        energyContainer = new ArrayList<>();
        timeContainer = new ArrayList<>();
        mouseSpeed = new ArrayList<>();
        mouseTracksContainer = new ArrayList<>();
        mousePoint = new Point(0, 0);
    }

    /**
     * Method for calculation math expiration for
     * all params and save it in container
     */
    public static void saveMathExpectation() {
        allAmp = new float[2];
        allAmp[0] = 0;
        allAmp[1] = 0;

        int count = 0;
        for (Float[] floats : ampContainer) {
            for (Float f : floats) {
                count++;
                allAmp[0] += f;
            }
        }
        allAmp[0] = allAmp[0]/count;
    }

    /**
     * Method for calculation dispersion for all parameters
     * and save it in container
     */
    public static void saveVariance() {

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
    //TODO remastered all calculations
    public static void saveAllParams(Point prevPos, Point currentPos) {
        int size = 64;

        double distance = Math.sqrt(Math.pow(prevPos.getX() - currentPos.getX(), 2) +
                Math.pow(prevPos.getY() - currentPos.getY(), 2));
        distanceLen
        double[] V = new double[mouseTrack.size()];
        for (int i = 0; i < mouseTrack.size() - 1; i++) {
            V[i] = Math.sqrt(
                    Math.pow(mouseTrack.get(i).getX() - mouseTrack.get(i + 1).getX(), 2) +
                    Math.pow(mouseTrack.get(i).getY() - mouseTrack.get(i + 1).getY(), 2));
        }

        Complex[] fft = FFT.ifft(FFT.fft(V), size);

        double energy = 0;
        for (Complex amp : fft) energy += amp.im * amp.im;

        Float[] amps = new Float[15];
        Float[] normalizedAmp = new Float[15];
        Float[] logAmp = new Float[15];
        for (int i = 0; i < 15; i++) {
            Float amp = fft[i].im;
            amps[i] = amp;
            normalizedAmp[i] = amp / ((float) energy);
        }

        ampContainer.add(amps);
        normalizedAnmContainer.add(normalizedAmp);
        energyContainer.add(energy);

        amps = new Float[15];
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
