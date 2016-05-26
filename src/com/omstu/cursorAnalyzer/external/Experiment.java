//package com.omstu.cursorAnalyzer.external;
//
//import thesis.helpers.Complex;
//import thesis.helpers.FFT;
//import thesis.signs.Sign;
//
///**
// * Класс для выдачи входных данных
// * Created by user on 17.05.2016.
// * @author wh1
// */
//public class Experiment {
//    private Series series = null;
//
//    private double[][] input = null;
//    private double[] velocity = null;
//
//
//    private Complex[] complexVelocity = null;
//    private Complex[][] complexInput = null;
//
//    public Experiment(Series series, double[][] input) throws IllegalArgumentException {
//        this.series = series;
//        this.input = input;
//
//        if (3 != input.length) {
//            throw new IllegalArgumentException("Incorrect input! Too less dimensions!");
//        }
//        if ((input[Sign._x_].length != input[Sign._y_].length) || (input[Sign._y_].length != input[Sign._p_].length)) {
//            throw new IllegalArgumentException("Incorrect input! Length of dimensions not equal!");
//        }
//
//        if (input[Sign._x_].length < 8) {
//            throw new IllegalArgumentException("Incorrect input! Too short dimension length!");
//        }
//    }
//
//    public int getInputLength() {
//        return input[Sign._x_].length; // = =input[Sign._y_].length and ==input[Sign._p_].length
//    }
//
//    public Series getSeries() {
//        return series;
//    }
//
//    public double[][] getInput() {
//        return input;
//    }
//
//    public double[] getX() {
//        return getDim(Sign._x_);
//    }
//
//    public double[] getY() {
//        return getDim(Sign._y_);
//    }
//
//    public double[] getP() {
//        return getDim(Sign._p_);
//    }
//
//    public double[] getDim(int dim) {
//        return input[dim];
//    }
//
//    public double[] getV() {
//        if (null == velocity) {
//            int length = input[Sign._x_].length > input[Sign._y_].length ? input[Sign._y_].length : input[Sign._x_].length;
//            velocity = new double[length];
//            velocity[0] = 0;
//            for(int i = 1; i < velocity.length; ++i) {
//                double dx = input[Sign._x_][i] - input[Sign._x_][i - 1];
//                double dy = input[Sign._y_][i] - input[Sign._y_][i - 1];
//                velocity[i] = Math.sqrt(dx * dx + dy * dy);
//            }
//        }
//        return velocity;
//    }
//
//    public int getFourierLength() {
//        //Сколько элементов нужно для дополнения до степени 2
//        return (int) Math.pow(2, Math.ceil(Math.log(getInputLength()) / Math.log(2)) - 1);
//    }
//
//    public Complex[] getFourierDim(int dim) {
//        if (null == complexInput) {
//            complexInput = new Complex[3][];
//        }
//
//        if (null == complexInput[dim]) {
//            complexInput[dim] = FFT.ifft(FFT.fft(input[dim], getFourierLength()));
//        }
//        return complexInput[dim];
//    }
//
//    public Complex[] getFourierX() {
//        return getFourierDim(Sign._x_);
//    }
//
//    public Complex[] getFourierY() {
//        return getFourierDim(Sign._y_);
//    }
//
//    public Complex[] getFourierP() {
//        return getFourierDim(Sign._p_);
//    }
//
//    public Complex[] getFourierV() {
//        if (null == complexVelocity) {
//            complexVelocity =  FFT.ifft(FFT.fft(getV(), getFourierLength()));
//        }
//        return complexVelocity;
//    }
//}
