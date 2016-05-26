package com.omstu.cursorAnalyzer.external;

public class Complex {
    private static final float EPS = (float)0.00001;

    public float re, im;
    public boolean infinite;

    Complex() {
        this.re = this.im = 0;
        infinite = false;
    }

    Complex(boolean infinite) {
        this.re = this.im = 0;
        this.infinite = infinite;
    }

    Complex(float re) {
        this.re = re; this.im = 0;
        infinite = false;
    }

    public Complex(double re)
    {
        this((float) re);
    }

    public Complex(float re, float im) {
        this.re = re; this.im = im;
        infinite = false;
    }

    public Complex(double re, double im)
    {
        this((float) re, (float) im);
    }

    public Complex(Complex src) {
        this(src.re, src.im);
    }

    public Complex plus(Complex z) {
        if (infinite || z.infinite) return new Complex(true);
        return new Complex(re + z.re, im + z.im);
    }

    public Complex plus(float a) {
        if (infinite) return new Complex(true);
        return new Complex(re + a, im);
    }

    public Complex minus() {
        if (infinite) return new Complex(true);
        return new Complex(-re,-im);
    }

    public Complex minus(Complex z) {
        if (infinite || z.infinite) return new Complex(true);
        return new Complex(re - z.re, im - z.im);
    }

    public Complex times(Complex z) {
        if (infinite || z.infinite) return new Complex(true);
        return new Complex(re *z.re - im *z.im, re *z.im + im *z.re);
    }

    public Complex times(float k) {
        if (infinite) return new Complex(true);
        return new Complex(re *k, im *k);
    }

    public boolean equals(Complex z) {
        if (infinite && z.infinite) return true;
        if (infinite || z.infinite) return false;

        if ( (re -z.re)*(re -z.re) + (im -z.im)*(im -z.im) > EPS) return false;
        return true;
    }

    public float norm()
    {
        return re * re + im * im;
    }

    public float abs()
    {
        return (float) Math.sqrt(re * re + im * im);
    }

    public static float dist(Complex z1, Complex z2)
    {
        return z1.minus(z2).abs();
    }

    public Complex phase() {
        float a = abs();
        if (a < EPS) return new Complex(true);
        return new Complex(re /a, im /a);
    }

    public Complex bar()
    {
        return new Complex(re, -im);
    }

    public Complex inverse() {
        float n = norm();

        if (infinite) return new Complex(0,0);

        if (n < EPS) return new Complex(true);

        return new Complex(re /n, -im /n);
    }

    public Complex conjugate() {
        Complex conj = new Complex();

        conj.re = re;
        conj.im = -im;

        return (conj);
    }

    public float Arg() {
        float r = abs();
        float theta;

        if (infinite) return 0;
        if (r < EPS) return 0;

        if (re * re > im * im) {
            theta = (float) Math.atan(im / re);
            if (re < 0)
            {
                if (im >= 0) theta += (float) Math.PI;
                else        theta -= (float) Math.PI;
            }
        } else {
            theta = (float) (0.5 * Math.PI - Math.atan(re / im));
            if (im < 0) theta -= (float) Math.PI;
        }

        return theta;
    }

    public float Arg(float a) {
        float theta = Arg();
        float pi2 = 2*(float) Math.PI;

        if (infinite) return 0;

        while (theta <= a) theta += pi2;
        while (theta > a + pi2) theta -= pi2;

        return theta;
    }

    public Complex Log()
    {
        return new Complex((float) Math.log(abs()), Arg());
    }

    public Complex Log(float a)
    {
        return new Complex((float) Math.log(abs()), Arg(a));
    }

    public Complex exp() {
        return new Complex((float)(Math.exp(re)*Math.cos(im)),
                (float)(Math.exp(re)*Math.sin(im)));
    }

    public Complex over(Complex z)
    {
        return times(z.inverse());
    }

    public String toString()
    {
        return toString(false);
    }

    public String toString(boolean signed) {
        String string = new String();

        if (signed) {
            if (re > EPS) { string += "+ "; string += String.valueOf(re); }
            if (re < -EPS) { string += "- "; string += String.valueOf(-re); }
        } else if (Math.abs(re) > EPS)
            string += String.valueOf(re);
        else if (Math.abs(im) < EPS) {
            string += "0";
            return string;
        }

        if (im > EPS) {
            if (Math.abs(re) > EPS) string += " + ";
            else if (signed) string += "+ ";
            string += String.valueOf(im);
            string += "i";
            return string;
        }
        if (im < -EPS) {
            if (Math.abs(re) > EPS) {
                string += " - ";
                string += String.valueOf(-im);
            } else if (signed) {
                string += "- ";
                string += String.valueOf(-im);
            } else string += String.valueOf(im);

            string += "i";
            return string;
        }
        return string;
    }
}
