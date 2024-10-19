package com.jonathantippy.FuzzyFraction;

import static java.lang.Math.*;
import static java.lang.Math.abs;
import static java.lang.Long.*;

/*
for now uses longs but eventually want to make hexaLong or octaLong 
(hexaLong would represent integers up to about 10^300)
*/

public class Rational 
{

    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1);

    private final long numerator;
    private final long denomenator;
    private final int sign;

    // Constructors with both numerator and denomenator
    public Rational(long numerator, long denomenator) 
    throws ArithmeticException {
        this.numerator = numerator;
        if (!(denomenator==0)) {
            this.denomenator = denomenator;
        } else {
            throw new ArithmeticException("/ by zero");
        }
        this.sign = this.calcSign();
    }

    // Constructors with integers
    public Rational(long numerator) {
        this.numerator = numerator; 
        this.denomenator = 1;
        this.sign = this.calcSign();
    }


    // Adaptive constructors
    public Rational(String fraction) 
    throws ArithmeticException, IllegalArgumentException {
        if (fraction.matches("^(-?)\\d+(/(-?)\\d+)?")) {
            if (fraction.contains("/")) {
                String[] terms = fraction.split("/");
                numerator = parseLong(terms[0]);
                if (!(0==(parseLong(terms[1])))) {
                    denomenator = parseLong(terms[1]);
                } else {
                    throw new ArithmeticException("/ by zero");
                }
            } else {
                numerator = parseLong(fraction);
                denomenator = 1;
            }
        } else {
            throw new IllegalArgumentException("Not a fraction");
        }
        this.sign = this.calcSign();
    }

    // Accessors
    public long getNumerator() {
        return this.numerator;
    }
    public long getDenomenator() {
        return this.denomenator;
    }
    public int getSign() {
        return this.sign;
    }

    // Display
    @Override
    public String toString() {
        StringBuilder numberConstruct = new StringBuilder();

        if (sign >= 0) {;} else {
            numberConstruct.append('-');
        }
        numberConstruct.append(Math.abs(numerator));
        numberConstruct.append('/');
        numberConstruct.append(Math.abs(denomenator));
        return numberConstruct.toString();
    }

    // Division
    public Rational divide(Rational divisor) {
        return new Rational(
            this.numerator * divisor.denomenator
            , this.denomenator * divisor.numerator
            );
    }

    // Multiplication
    public Rational multiply(Rational factor) {
        return new Rational(
            this.numerator * factor.numerator
            , this.denomenator * factor.denomenator
        );
    }

    // Addition
    public Rational add(Rational addend) {
        return new Rational(
            (this.numerator * addend.denomenator)
            + (addend.numerator * this.denomenator)
            , this.denomenator * addend.denomenator
        );
    }

    // Absoulte Value
    public Rational abs() {
        return new Rational(
            Math.abs(this.numerator)
            , Math.abs(this.denomenator)
            );
    }


    // UTILS

    private int calcSign() {
        int numeratorSign = Long.signum(this.numerator);
        int denomenatorSign = Long.signum(this.denomenator);
        int sign = numeratorSign*denomenatorSign;
        return sign;
    }
}