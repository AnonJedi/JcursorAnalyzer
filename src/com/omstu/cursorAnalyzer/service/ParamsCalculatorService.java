package com.omstu.cursorAnalyzer.service;

import com.omstu.cursorAnalyzer.common.Common;
import com.omstu.cursorAnalyzer.exceptions.RepositoryException;
import com.omstu.cursorAnalyzer.exceptions.ServiceException;
import com.omstu.cursorAnalyzer.external.Complex;
import com.omstu.cursorAnalyzer.external.FFT;
import com.omstu.cursorAnalyzer.repository.UserRepository;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Service for calculation params of mouse metrics
 */
public class ParamsCalculatorService {

    //mouse track between buttons
    private static ArrayList<Point> mouseTrack;

    //tracks container
    private static ArrayList<ArrayList<Point>> mouseTracksContainer;

    //buttons size container
    private static ArrayList<Integer> buttonSizes;

    //times container for two buttons
    private static ArrayList<Long> clickTimeContainer;

    private static ArrayList<Double> buttonLens;
    static ArrayList<Point> getMouseTrack() {
        return mouseTrack;
    }

    static void setMouseTrack(ArrayList<Point> mouseTrack) {
        ParamsCalculatorService.mouseTrack = mouseTrack;
    }

    static ArrayList<Long> getClickTimeContainer() {
        return clickTimeContainer;
    }

    public static void reloadFields() {
        mouseTrack = new ArrayList<Point>();
        buttonSizes = new ArrayList<Integer>();
        clickTimeContainer = new ArrayList<Long>();
        buttonLens = new ArrayList<Double>();
        mouseTracksContainer = new ArrayList<ArrayList<Point>>();
    }

    /**
     * Method for calculation and final save params in containers
     */
    static void saveAllParams(Point prevPos, Point currentPos, long timeRange, int buttonSize) {
        //save shortest distance between buttons
        double distance = Math.sqrt(Math.pow(prevPos.getX() - currentPos.getX(), 2) +
                Math.pow(prevPos.getY() - currentPos.getY(), 2));
        buttonLens.add(distance);

        buttonSizes.add(buttonSize);
        mouseTracksContainer.add(mouseTrack);
        clickTimeContainer.add(timeRange);
    }

    public static void saveMouseTrack(Point mousePos) {
        mouseTrack.add(mousePos);
    }

    private static double[] calculateAmps(ArrayList<Point> tracks, long time) {
        double[] V = new double[tracks.size()-1];
        for (int i = 0; i < tracks.size() - 1; i++) {
            V[i] = Math.sqrt(
                    Math.pow(tracks.get(i).getX() - tracks.get(i+1).getX(), 2) +
                            Math.pow(tracks.get(i).getY() - tracks.get(i+1).getY(), 2)
            ) * 1000 / time;
        }
        Complex[] ifft = FFT.ifft(FFT.fft(V, Common.NUMBER_OF_COUNTS));
        Complex[] fft = FFT.fft(ifft);
        double[] amps = new double[fft.length];
        for (int i = 1; i < fft.length; i++) {
            fft[fft.length - i].re -= fft[i].re;
            fft[fft.length - i].im += fft[i].im;
        }
        for (int i = 0; i < amps.length; i++) {
            amps[i] = Math.sqrt(fft[i].re * fft[i].re + fft[i].im * fft[i].im) * 2 / fft.length;
        }
        return amps;
    }

    static void saveMouseParamsAndMetrics(int counter) throws ServiceException {

        UserRepository repository = new UserRepository(Common.DB_PATH);
        try {
            Document document = repository.getXMLDocument();
            Node features = document.getElementsByTagName("Features").item(0);
            Node classNode = features.getLastChild();

            for (int i = 0; i < counter - 1; i++) {
                Element realizationNode = document.createElement("Realization");
                ArrayList<Point> tracks = mouseTracksContainer.get(i);
                double[] amps = calculateAmps(tracks, clickTimeContainer.get(i));

                int energy = 0;
                for (double d : amps) energy += d * d;
                //save energy into xml
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "16", String.valueOf(energy)));

                //save all types of amps into xml
                double normalizedLog = Math.log(buttonLens.get(i)/buttonSizes.get(i)) / Math.log(2);
                for (int j = 0; j < Common.AMPS_COUNT; j++) {
                    realizationNode.appendChild(
                            buildXmlFeatureNode(document, String.valueOf(j + 1),
                                                String.valueOf(amps[j])));
                    realizationNode.appendChild(
                            buildXmlFeatureNode(document, String.valueOf(j + 17),
                                                String.valueOf(amps[j] / energy)));
                    realizationNode.appendChild(
                            buildXmlFeatureNode(document, String.valueOf(j + 37),
                                                String.valueOf(amps[j] / (energy * normalizedLog))));
                }

                //save Sfact/Smin param into xml
                double param = 0;
                for (int j = 0; j < tracks.size() - 1; j++) {
                    param += Math.sqrt(
                            Math.pow(tracks.get(j).getX() - tracks.get(j+1).getX(), 2) +
                            Math.pow(tracks.get(j).getY() - tracks.get(j+1).getY(), 2));
                }
                param /= buttonLens.get(i);
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "32", String.valueOf(param)));

                //save b param into xml
                double b = clickTimeContainer.get(i) / normalizedLog;
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "33", String.valueOf(b)));

                //save D param into xml
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "34", String.valueOf(buttonLens.get(i))));

                //save W param into xml
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "35", String.valueOf(buttonSizes.get(i))));

                //save normalized energy into xml
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "36", String.valueOf(energy/normalizedLog)));

                //save normalized Sfact/Smin by log
                realizationNode.appendChild(
                        buildXmlFeatureNode(document, "52", String.valueOf(param/normalizedLog)));

                classNode.appendChild(realizationNode);
            }

            features.appendChild(classNode);
            repository.insert(document);
        } catch (RepositoryException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    private static Element buildXmlFeatureNode(Document document, String id, String value) {
        Element featureNode = document.createElement("Feature");
        Attr idAttribute = document.createAttribute("id");
        idAttribute.setValue(id);
        Attr valueAttribute = document.createAttribute("value");
        valueAttribute.setValue(String.valueOf(value));
        featureNode.setAttributeNode(idAttribute);
        featureNode.setAttributeNode(valueAttribute);
        return featureNode;
    }
}
