//package com.omstu.cursorAnalyzer.external;
//
///**
// * 16 первых амлитуд гармоник нормированных по энергии
// * Created by wh1 on 19.08.15.
// * @author wh1
// */
//public class Amplitude {
//    protected int _sequence_size = 0;
//    protected int _amp_count = 16;
//
//    private double[][] _result = null;
//
//    public static String getTitle() {
//        return "Амлитуды гармоник";
//    }
//
//    public void process(Experiment experiment) throws IllegalArgumentException {
//        double[][] input = experiment.getInput();
//        if (input.length < 2) {
//            throw new IllegalArgumentException("Matrix should contain at less two dimension");
//        }
//        _sequence_size = experiment.getSeries().getSize() < 128 ? 128 : experiment.getSeries().getSize();
//        Complex[][] step2 = new Complex[experiment.getSeries().getSpace()][];
//
//        for(int dim = 0; dim < experiment.getSeries().getSpace(); ++dim) {
//            step2[dim] = experiment.getFourierDim(dim);
//        }
//
//        //По длине подгоняем под тот размер, что задали (32,64...)
//        int step = (int) Math.floor(experiment.getFourierLength() / _sequence_size);
//
//        Complex[][] tmp2 = new Complex[experiment.getSeries().isUse_p() ? 2 : 1][_sequence_size];
//        Complex[] velocity = experiment.getFourierV();
//        Complex[] p = experiment.getFourierP();
//
//        for(int i = 0; i < _sequence_size; ++i) {
//            tmp2[0][i] = velocity[i * step];
//            if (experiment.getSeries().isUse_p()) {
//                tmp2[1][i] = p[i * step];
//            }
//        }
//
//        Complex[][] step3 = new Complex[tmp2.length][];
//        for(int j = 0; j < tmp2.length; ++j) {
//            step3[j] = FFT.fft(tmp2[j]);
//        }
//
//        //устраняем  зеркальный эффект
//        for (Complex[] aStep3 : step3) {
//            for (int i = 1; i < aStep3.length; ++i) {
//                aStep3[_sequence_size - i].re -= aStep3[i].re;
//                aStep3[_sequence_size - i].im += aStep3[i].im;
//            }
//        }
//
//        _result = new double[step3.length][_amp_count];
//
//        //считаем энергию сигнала и амплитуды
//        for(int j = 0; j < _result.length; ++j) {
//            double energy = 0;
//            for (int i = 0; i < _amp_count; ++i) {
//                _result[j][i] = Math.sqrt(step3[j][i].re * step3[j][i].re + step3[j][i].im * step3[j][i].im) * 2 / experiment.getFourierLength(); // Вот та самая формула!
//                energy += _result[j][i];
//            }
//            //нормируем амплитуды по энергии
//            for (int i = 0; i < _amp_count; ++i) {
//                _result[j][i] /= energy;
//            }
//        }
//    }
//}
