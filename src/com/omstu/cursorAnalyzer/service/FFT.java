package com.omstu.cursorAnalyzer.service;

public class FFT {
    /**
     * Method for transformation simple arrays to complex
     * @param sign Sign of array members
     * @param n Size of array
     * @param ar
     * @param ai
     */
    public static void ComplexToComplex(int sign, int n, float[] ar, float[] ai) {
        float scale = (float) Math.sqrt(1.0f/n);

        int i, j;

        for (i = j = 0; i < n; ++i) {
            if (j >= i) {
                float tempr = ar[j] * scale;
                float tempi = ai[j] * scale;

                ar[j] = ar[i] * scale;
                ai[j] = ai[i] * scale;

                ar[i] = tempr;
                ai[i] = tempi;
            }

            double m = n/2;

            while (m >= 1 && j >= m) {
                j -= m;
                m /= 2;
            }

            j += m;
        }

        int mmax, istep;

        for (mmax = 1, istep = 2; mmax < n; mmax = istep, istep = 2 * mmax) {
            float delta = (float)(sign * Math.PI) / mmax;

            for (int k = 0; k < mmax; ++k) {
                float w = k * delta;
                float wr = (float) Math.cos(w);
                float wi = (float) Math.sin(w);

                for (i = k;  i < n-1; i += istep) {
                    j = i + mmax;

                    float tr = wr * ar[j] - wi * ai[j];
                    float ti = wr * ai[j] + wi * ar[j];

                    ar[j] = ar[i] - tr;
                    ai[j] = ai[i] - ti;

                    ar[i] += tr;
                    ai[i] += ti;
                }
            }
        }
    }
}
