package com.omstu.cursorAnalyzer.external;

/*************************************************************************
 *  Compilation:  javac FFT.java
 *  Execution:    java FFT N
 *  Dependencies: Complex.java
 *
 *  Compute the FFT and inverse FFT of a length N complex sequence.
 *  Bare bones implementation that runs in O(N log N) time. Our goal
 *  is to optimize the clarity of the code, rather than performance.
 *
 *  Limitations
 *  -----------
 *   -  assumes N is a power of 2
 *
 *   -  not the most memory efficient algorithm (because it uses
 *      an object type for representing complex numbers and because
 *      it re-allocates memory for the subarray, instead of doing
 *      in-place or reusing a single temporary array)
 *
 *************************************************************************/

public class FFT {

    private static final Complex ZERO = new Complex(0, 0);

    public static double[] resample(double[] data, int size) {
        if ((data.length < size) || (size <= 0)) {
            return null;
        }
        double[] result = new double[size];

        result[0] = data[0];
        result[size - 1] = data[data.length - 1];

        for (int i = 1; i < size - 1; ++i) {
            double ipos = i * data.length / (double)size;
            if (Math.ceil(ipos) != Math.floor(ipos)) {
                result[i] = data[(int) Math.floor(ipos)] * (Math.ceil(ipos) - ipos) +
                        data[(int) Math.ceil(ipos)] * (ipos - Math.floor(ipos));
            } else {
                result[i] = data[(int) Math.floor(ipos)];
            }
        }

        return result;
    }

    public static Complex[] resample(Complex[] data, int size) {
        if ((data.length < size) || (size <= 0)) {
            return null;
        }
        Complex[] result = new Complex[size];

        result[0] = new Complex(data[0]);
        result[size - 1] = new Complex(data[data.length - 1]);

        for (int i = 1; i < size - 1; ++i) {
            double ipos = i * data.length / (double)size;
            if (Math.ceil(ipos) != Math.floor(ipos)) {
                result[i] = new Complex(0);
                result[i].re = (float) (data[(int) Math.floor(ipos)].re * (Math.ceil(ipos) - ipos) +
                        data[(int) Math.ceil(ipos)].re * (ipos - Math.floor(ipos)));
                result[i].im = (float) (data[(int) Math.floor(ipos)].im * (Math.ceil(ipos) - ipos) +
                        data[(int) Math.ceil(ipos)].im * (ipos - Math.floor(ipos)));
            } else {
                result[i] = new Complex(data[(int) Math.floor(ipos)]);
            }
        }
        return result;
    }

    public static Complex[] fft(double[] x) {
        return fft(x, 0);
    }

    public static Complex[] fft(double[] x, int size) {
        if ((size > 0) && (size != x.length)) {
            x = resample(x, size);
        }

        Complex[] xx = new Complex[x.length];
        for (int i = 0; i < x.length; ++i) {
            xx[i] = new Complex(x[i]);
        }
        return fft(xx);
    }

    public static Complex[] fft(Complex[] x, int size) {
        if ((size > 0) && (size != x.length)) {
            x = resample(x, size);
        }

        return fft(x);
    }


    // compute the FFT of re[], assuming its length is a power of 2
    public static Complex[] fft(Complex[] x) {
        int N = x.length;

        // base case
        if (N == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (N % 2 != 0) { throw new IllegalArgumentException("N is not a power of 2"); }

        // fft of even terms
        Complex[] even = new Complex[N/2];

        for (int k = 0; k < N/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd  = even;  // reuse the array
        for (int k = 0; k < N/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        int n2 = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
        Complex[] y = new Complex[n2];
        for (int k = 0; k < N/2; k++) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }

    public static Complex[] ifft(Complex[] x) {
        return ifft(x, 0);
    }


    // compute the inverse FFT of re[], assuming its length is a power of 2
    public static Complex[] ifft(Complex[] x, int size) {
        int N = x.length;
        Complex[] y = new Complex[N];

        // take conjugate
        for (int i = 0; i < N; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y, size);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
        for (int i = 0; i < N; i++) {
            y[i] = y[i].times(new Complex((float)(1.0 / N)));
        }

        return y;

    }

    // compute the circular convolution of re and im
    public static Complex[] cconvolve(Complex[] x, Complex[] y) {

        // should probably pad re and im with 0s so that they have same length
        // and are powers of 2
        if (x.length != y.length) { throw new IllegalArgumentException("Dimensions don't agree"); }

        int N = x.length;

        // compute FFT of each sequence
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // point-wise multiply
        Complex[] c = new Complex[N];
        for (int i = 0; i < N; i++) {
            c[i] = a[i].times(b[i]);
        }

        // compute inverse FFT
        return ifft(c);
    }


    // compute the linear convolution of re and im
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        Complex[] a = new Complex[2*x.length];
        for (int i = 0;        i <   x.length; i++) a[i] = x[i];
        for (int i = x.length; i < 2*x.length; i++) a[i] = ZERO;

        Complex[] b = new Complex[2*y.length];
        for (int i = 0;        i <   y.length; i++) b[i] = y[i];
        for (int i = y.length; i < 2*y.length; i++) b[i] = ZERO;

        return cconvolve(a, b);
    }

    // display an array of Complex numbers to standard output
    public static void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }


    /*********************************************************************
     *  Test client and sample execution
     *
     *  % java FFT 4
     *  re
     *  -------------------
     *  -0.03480425839330703
     *  0.07910192950176387
     *  0.7233322451735928
     *  0.1659819820667019
     *
     *  im = fft(re)
     *  -------------------
     *  0.9336118983487516
     *  -0.7581365035668999 + 0.08688005256493803i
     *  0.44344407521182005
     *  -0.7581365035668999 - 0.08688005256493803i
     *
     *  z = ifft(im)
     *  -------------------
     *  -0.03480425839330703
     *  0.07910192950176387 + 2.6599344570851287E-18i
     *  0.7233322451735928
     *  0.1659819820667019 - 2.6599344570851287E-18i
     *
     *  c = cconvolve(re, re)
     *  -------------------
     *  0.5506798633981853
     *  0.23461407150576394 - 4.033186818023279E-18i
     *  -0.016542951108772352
     *  0.10288019294318276 + 4.033186818023279E-18i
     *
     *  d = convolve(re, re)
     *  -------------------
     *  0.001211336402308083 - 3.122502256758253E-17i
     *  -0.005506167987577068 - 5.058885073636224E-17i
     *  -0.044092969479563274 + 2.1934338938072244E-18i
     *  0.10288019294318276 - 3.6147323062478115E-17i
     *  0.5494685269958772 + 3.122502256758253E-17i
     *  0.240120239493341 + 4.655566391833896E-17i
     *  0.02755001837079092 - 2.1934338938072244E-18i
     *  4.01805098805014E-17i
     *
     *********************************************************************/
}

