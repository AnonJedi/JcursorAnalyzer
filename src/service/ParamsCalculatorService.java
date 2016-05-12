package service;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Service for calculation params of mouse metrics
 */
public class ParamsCalculatorService {

    //текущие координаты мыши
    private Point mousePoint;

    //траектория мышки
    private ArrayList<Point> mouseTrack;

    //хранилище траекторий мыши
    private ArrayList<ArrayList<Point>> mouseTracksContainer;

    //хранилище текущего параметра C
    private ArrayList<ArrayList<Double>> tracksDiffContainer;

    //хранилище максимальных отклонений траекторий
    private ArrayList<Double> maxDiffTracks;

    //хранилище средних отклонений траекторий
    private ArrayList<Double> midDiffTracks;

    //размер стороны куба
    private ArrayList<Integer> shapeSize;

    //хранилище расстояний между центрами фигур
    private ArrayList<Double> lensContainer;

    //длинна траектории мыши
    private ArrayList<Double> distanceLen;

    //хранилище времён между кликами
    private ArrayList<Date> clickTimeContainer;

    //средняя скорость движения мыши
    private double midMouseSpeed;

    //хранилище Т
    private ArrayList<Double> t;

    //математическое ожидание значений Т
    private double expirationT;

    //математическое ожидание максимальных отклонений траектории мыши от идеальной траектории
    private double maxDiffTracksExpiration;

    //математическое ожидание средних отклонений траектории мыши от идеальной траектории
    private double midDiffTracksExpiration;

    //среднеквадратичное отклонение параметра T
    private double tDispertion;

    //среднеквадратичное отклонение среднего отклонения траекторий
    private double midDiffTracksDispertion;

    //среднеквадратичное отклонение максимального отклонения траекторий
    private double maxDiffTracksDispersion;

    //хранилище отклонений координаты мыши от идеальной координаты
    private ArrayList<Double> diffContainer;

    //хранилище амплитуд ряда распределения отклонений мыши
    private ArrayList<Float[]> ampContainer;

    //математическое ожидание амплитуд отклонения
    private float[] ampExpiration;

    //дисперсия амплитуд отклонения
    private float[] ampDispertion;

    //
    private static float[] allAmp;

    //контейнер вычисленной энергии движения мыши
    private ArrayList<Double> energyContainer;

    //контейнер времён
    private ArrayList<Date> timeContainer;

    //текущая скорость движения мыши
    private ArrayList<Double> mouseSpeed;

    public Point getMousePoint() {
        return mousePoint;
    }

    public void setMousePoint(Point mousePoint) {
        this.mousePoint = mousePoint;
    }

    public ArrayList<Point> getMouseTrack() {
        return mouseTrack;
    }

    public void setMouseTrack(ArrayList<Point> mouseTrack) {
        this.mouseTrack = mouseTrack;
    }

    public ArrayList<ArrayList<Point>> getMouseTracksContainer() {
        return mouseTracksContainer;
    }

    public void setMouseTracksContainer(ArrayList<ArrayList<Point>> mouseTracksContainer) {
        this.mouseTracksContainer = mouseTracksContainer;
    }

    public ArrayList<ArrayList<Double>> getTracksDiffContainer() {
        return tracksDiffContainer;
    }

    public void setTracksDiffContainer(ArrayList<ArrayList<Double>> tracksDiffContainer) {
        this.tracksDiffContainer = tracksDiffContainer;
    }

    public ArrayList<Double> getMaxDiffTracks() {
        return maxDiffTracks;
    }

    public void setMaxDiffTracks(ArrayList<Double> maxDiffTracks) {
        this.maxDiffTracks = maxDiffTracks;
    }

    public ArrayList<Double> getMidDiffTracks() {
        return midDiffTracks;
    }

    public void setMidDiffTracks(ArrayList<Double> midDiffTracks) {
        this.midDiffTracks = midDiffTracks;
    }

    public ArrayList<Integer> getShapeSize() {
        return shapeSize;
    }

    public void setShapeSize(ArrayList<Integer> shapeSize) {
        this.shapeSize = shapeSize;
    }

    public ArrayList<Double> getLensContainer() {
        return lensContainer;
    }

    public void setLensContainer(ArrayList<Double> lensContainer) {
        this.lensContainer = lensContainer;
    }

    public ArrayList<Double> getDistanceLen() {
        return distanceLen;
    }

    public void setDistanceLen(ArrayList<Double> distanceLen) {
        this.distanceLen = distanceLen;
    }

    public ArrayList<Date> getClickTimeContainer() {
        return clickTimeContainer;
    }

    public void setClickTimeContainer(ArrayList<Date> clickTimeContainer) {
        this.clickTimeContainer = clickTimeContainer;
    }

    public double getMidMouseSpeed() {
        return midMouseSpeed;
    }

    public void setMidMouseSpeed(double midMouseSpeed) {
        this.midMouseSpeed = midMouseSpeed;
    }

    public ArrayList<Double> getT() {
        return t;
    }

    public void setT(ArrayList<Double> t) {
        this.t = t;
    }

    public double getExpirationT() {
        return expirationT;
    }

    public void setExpirationT(double expirationT) {
        this.expirationT = expirationT;
    }

    public double getMaxDiffTracksExpiration() {
        return maxDiffTracksExpiration;
    }

    public void setMaxDiffTracksExpiration(double maxDiffTracksExpiration) {
        this.maxDiffTracksExpiration = maxDiffTracksExpiration;
    }

    public double getMidDiffTracksExpiration() {
        return midDiffTracksExpiration;
    }

    public void setMidDiffTracksExpiration(double midDiffTracksExpiration) {
        this.midDiffTracksExpiration = midDiffTracksExpiration;
    }

    public double gettDispertion() {
        return tDispertion;
    }

    public void settDispertion(double tDispertion) {
        this.tDispertion = tDispertion;
    }

    public double getMidDiffTracksDispertion() {
        return midDiffTracksDispertion;
    }

    public void setMidDiffTracksDispertion(double midDiffTracksDispertion) {
        this.midDiffTracksDispertion = midDiffTracksDispertion;
    }

    public double getMaxDiffTracksDispersion() {
        return maxDiffTracksDispersion;
    }

    public void setMaxDiffTracksDispersion(double maxDiffTracksDispersion) {
        this.maxDiffTracksDispersion = maxDiffTracksDispersion;
    }

    public ArrayList<Double> getDiffContainer() {
        return diffContainer;
    }

    public void setDiffContainer(ArrayList<Double> diffContainer) {
        this.diffContainer = diffContainer;
    }

    public ArrayList<Float[]> getAmpContainer() {
        return ampContainer;
    }

    public void setAmpContainer(ArrayList<Float[]> ampContainer) {
        this.ampContainer = ampContainer;
    }

    public float[] getAmpExpiration() {
        return ampExpiration;
    }

    public void setAmpExpiration(float[] ampExpiration) {
        this.ampExpiration = ampExpiration;
    }

    public float[] getAmpDispertion() {
        return ampDispertion;
    }

    public void setAmpDispertion(float[] ampDispertion) {
        this.ampDispertion = ampDispertion;
    }

    public static float[] getAllAmp() {
        return allAmp;
    }

    public static void setAllAmp(float[] allAmp) {
        ParamsCalculatorService.allAmp = allAmp;
    }

    public ArrayList<Double> getEnergyContainer() {
        return energyContainer;
    }

    public void setEnergyContainer(ArrayList<Double> energyContainer) {
        this.energyContainer = energyContainer;
    }

    public ArrayList<Date> getTimeContainer() {
        return timeContainer;
    }

    public void setTimeContainer(ArrayList<Date> timeContainer) {
        this.timeContainer = timeContainer;
    }

    public ArrayList<Double> getMouseSpeed() {
        return mouseSpeed;
    }

    public void setMouseSpeed(ArrayList<Double> mouseSpeed) {
        this.mouseSpeed = mouseSpeed;
    }

    public ParamsCalculatorService() {
        mouseTrack = new ArrayList<Point>();
        shapeSize = new ArrayList<Integer>();
        lensContainer = new ArrayList<Double>();
        clickTimeContainer = new ArrayList<Date>();
        t = new ArrayList<Double>();
        ampContainer = new ArrayList<Float[]>();
        maxDiffTracks = new ArrayList<Double>();
        midDiffTracks = new ArrayList<Double>();
        energyContainer = new ArrayList<Double>();
        timeContainer = new ArrayList<Date>();
        mouseSpeed = new ArrayList<Double>();
        mouseTracksContainer = new ArrayList<ArrayList<Point>>();
        diffContainer = new ArrayList<Double>();
        distanceLen = new ArrayList<Double>();
        tracksDiffContainer = new ArrayList<ArrayList<Double>>();
        mousePoint = new Point(0, 0);
    }

    /**
     * Method for calculation math expiration for
     * all params and save it in container
     */
    public void saveMathExpectation() {
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
    public void saveVariance() {
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
    public void saveMidV(Date time) {
        double temp = 0;
        for (int i = 1; i < mouseTrack.size(); i++) {
            temp += Math.sqrt(Math.pow(mouseTrack.get(i).getX() - mouseTrack.get(i - 1).getX(), 2))
                    + Math.sqrt(Math.pow(mouseTrack.get(i).getY() - mouseTrack.get(i-1).getY(), 2));
        }
        midMouseSpeed = temp * 10000000 / time.getTime();
    }

    /**
     * Method for calculation and final save params in containers
     */
    public void saveAllParams(int buttonSize, Point buttonPos, int counter, Date time) {
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

        for (int i = 0; i < mouseTracksContainer.get(counter).size(); i++)
        {
            diffContainer.add(Math.abs(a * mouseTracksContainer.get(counter).get(i).getX() +
                    mouseTracksContainer.get(counter).get(i).getY() + c) / Math.sqrt(a * a + 1));
            midDiffTracks.set(midDiffTracks.size() - 1,
                    midDiffTracks.get(midDiffTracks.size() - 1) + diffContainer.get(diffContainer.size() - 1));
        }
        midDiffTracks.set(midDiffTracks.size() - 1, midDiffTracks.get(midDiffTracks.size() - 1) / diffContainer.size()/
                lensContainer.get(lensContainer.size() - 1));

        for (int i = 0; i < mouseTracksContainer.get(counter).size() - 1; i++)
        {
            distanceLen.add(Math.sqrt(Math.pow(mouseTracksContainer.get(counter).get(i).getX() -
                    mouseTracksContainer.get(counter).get(i + 1).getX(), 2)) +
                    Math.sqrt(Math.pow(mouseTracksContainer.get(counter).get(i).getY() -
                            mouseTracksContainer.get(counter).get(i + 1).getY(), 2)));
        }

        double max = diffContainer.get(0);
        for (double d : diffContainer) if (d > max) max = d;

        maxDiffTracks.add(max / lensContainer.get(lensContainer.size() - 1));

        int n = 128;
        double temp = 0;

        float[] ar = new float[n];
        float[] ai = new float[n];

        for (int i = 0; i < n; i++)
        {
            if (i < distanceLen.size()) ar[i] = Float.parseFloat(distanceLen.get(i).toString());
            else ar[i] = 0;
            ai[i] = 0;
        }

        FFT.ComplexToComplex(-1, n, ar, ai);

        float[] am = new float[n];
        double energy = 0;
        for (int i = 0; i < n; i++)
        {
            ar[ar.length - i - 1] = ar[ar.length - i - 1] - ar[i];
            ai[ai.length - i - 1] = ai[ai.length - i - 1] + ai[i];
            am[i] = ((ar[i] * ar[i] + ai[i] * ai[i]) / am.length);
        }

        for (int i = 0; i < distanceLen.size(); i++)
        {
            temp += i;
            energy += Math.pow(i, 2);
        }

        energyContainer.add(energy);
        Float[] amp = new Float[n];
        for (int i = 0; i < n; i++) amp[i] = am[i] / (float)energy;

        ampContainer.add(amp);

        mouseSpeed.add(temp * 10000000 / time.getTime() / lensContainer.get(lensContainer.size() - 1));
        tracksDiffContainer.add(diffContainer);
        diffContainer = new ArrayList<>();

        mousePoint = new Point(buttonPos);
    }

    public void saveMouseTrack(Point mousePos) {
        mouseTrack.add(mousePos);
    }
}
