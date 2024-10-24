package com.jonathantippy.FuzzyFraction;

import static com.jonathantippy.FuzzyFraction.Utility.addBits;
import static com.jonathantippy.FuzzyFraction.Utility.branchlessDoz;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;


public class RationalTest {

    static Random random = new Random();

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //BASIC CONSTRUCTOR
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void basicConstructorTest() {
        Rational r = new Rational(1, 1);
        String s = r.toString();
        assertEquals("1/1", s);
    }


    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //STRINGS
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void stringPositiveTest() {
        Rational r = new Rational("50/7");
        String s = r.toString();
        assertEquals("50/7", s);
    }

    @Test
    public void stringNegativeTest() {
        Rational r = new Rational("-50/7");
        String s = r.toString();
        assertEquals("-50/7", s);
    }

    @Test
    public void stringNegativeSimplifyTest() {
        Rational r = new Rational("-50/-7");
        String s = r.toString();
        assertEquals("50/7", s);
    }

    @Test
    public void stringIntegerTest() {
        Rational r = new Rational("5");
        String s = r.toString();
        assertEquals("5/1", s);
    }

    @Test
    public void hardStringIntegerTest() {
        Rational r = new Rational("60");
        String s = r.toString();
        assertEquals("60/1", s);
    }


    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //INTEGERS
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void intPositiveTest() {
        Rational r = new Rational(50L);
        String s = r.toString();
        assertEquals("50/1", s);
    }

    @Test
    public void intNegativeTest() {
        Rational r = new Rational(-50L);
        String s = r.toString();
        assertEquals("-50/1", s);
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //DIVISION
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void divisionTest() {
        Rational dividend = new Rational(1);
        Rational divisor = new Rational(3);
        Rational answer = dividend.divide(divisor);
        String s = answer.toString();
        assertEquals("1/3", s);
    }

    @Test
    public void hardDivisionTest() {
        Rational dividend = new Rational("5/6");
        Rational divisor = new Rational("2/3");
        Rational answer = dividend.divide(divisor);
        String s = answer.toString();
        assertEquals("15/12", s);
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //MULTIPLICATION
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void multiplicationTest() {
        Rational factorOne = new Rational(1);
        Rational factorTwo = new Rational(3);
        Rational answer = factorOne.multiply(factorTwo);
        String s = answer.toString();
        assertEquals("3/1", s);
    }

    @Test
    public void hardMultiplicationTest() {
        Rational factorOne = new Rational("5/7");
        Rational factorTwo = new Rational("2/3");
        Rational answer = factorOne.multiply(factorTwo);
        String s = answer.toString();
        assertEquals("10/21", s);
    }

    // multiply round down

    @Test
    public void mrdTest() {
        Rational factorOne = new Rational(1);
        Rational factorTwo = new Rational(3);
        Rational answer = factorOne.multiplyRoundDown(factorTwo);
        String s = answer.toString();
        assertEquals("3/1", s);
    }

    @Test
    public void hardmrdTest() {
        Rational factorOne = new Rational("5/7");
        Rational factorTwo = new Rational("2/3");
        Rational answer = factorOne.multiplyRoundDown(factorTwo);
        String s = answer.toString();
        assertEquals("10/21", s);
    }

    @Test
    public void hardermrdTest() {
        Rational factorOne = new Rational(Long.MAX_VALUE);
        Rational factorTwo = new Rational(1);
        Rational answer = factorOne.multiplyRoundDown(factorTwo);
        String s = answer.toString();
        assertEquals(Long.toString(Long.MAX_VALUE) + "/1", s);
    }

    @Test
    public void hardermrdTest2() {
        Rational factorOne = new Rational(Long.MAX_VALUE);
        Rational factorTwo = new Rational(1, Long.MAX_VALUE);
        Rational answer = factorOne.multiplyRoundDown(factorTwo);
        String s = answer.toString();
        assertEquals(Long.toString(Long.MAX_VALUE) + "/" + Long.MAX_VALUE, s);
    }

    @Test
    public void hardermrdTest3() {
        Rational factorOne = new Rational(Long.MAX_VALUE, Long.MAX_VALUE);
        Rational factorTwo = new Rational(Long.MAX_VALUE, Long.MAX_VALUE);
        Rational answer = factorOne.multiplyRoundDown(factorTwo);
        String s = answer.toString();
        assert(((double) answer.getNumerator()/(double)answer.getDenomenator() <= 1.0)
        ): (double) answer.getNumerator()/(double)answer.getDenomenator() + " is greater than one";
    }

    // MULTIPLY ROUND UP

    @Test
    public void mruTest() {
        Rational factorOne = new Rational(1);
        Rational factorTwo = new Rational(3);
        Rational answer = factorOne.multiplyRoundUp(factorTwo);
        String s = answer.toString();
        assertEquals("3/1", s);
    }

    @Test
    public void hardmruTest() {
        Rational factorOne = new Rational("5/7");
        Rational factorTwo = new Rational("2/3");
        Rational answer = factorOne.multiplyRoundUp(factorTwo);
        String s = answer.toString();
        assertEquals("10/21", s);
    }

    @Test
    public void hardermruTest() {
        Rational factorOne = new Rational(Long.MAX_VALUE);
        Rational factorTwo = new Rational(1);
        Rational answer = factorOne.multiplyRoundUp(factorTwo);
        String s = answer.toString();
        assertEquals(Long.toString(Long.MAX_VALUE) + "/1", s);
    }

    @Test
    public void hardermruTest2() {
        Rational factorOne = new Rational(Long.MAX_VALUE);
        Rational factorTwo = new Rational(1, Long.MAX_VALUE);
        Rational answer = factorOne.multiplyRoundUp(factorTwo);
        String s = answer.toString();
        assertEquals(Long.toString(Long.MAX_VALUE) + "/" + Long.MAX_VALUE, s);
    }

    @Test
    public void hardermruTest3() {
        Rational factorOne = new Rational(Long.MAX_VALUE, Long.MAX_VALUE);
        Rational factorTwo = new Rational(Long.MAX_VALUE, Long.MAX_VALUE);
        Rational answer = factorOne.multiplyRoundUp(factorTwo);
        String s = answer.toString();
        assert(((double) answer.getNumerator()/(double)answer.getDenomenator() >= 1.0)
        ): (double) answer.getNumerator()/(double)answer.getDenomenator() + " is less than one";
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //ADDITION
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void additionTest() {
        Rational a = new Rational(5);
        Rational b = new Rational(6);
        Rational answer = a.add(b);
        String s = answer.toString();
        assertEquals("11/1", s);
    }

    @Test
    public void subtractionTest() {
        Rational a = new Rational(5);
        Rational b = new Rational(6);
        Rational answer = a.subtract(b);
        String s = answer.toString();
        assertEquals("-1/1", s);
    }

     //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //SIMPLIFICATION
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void simplifyTwosTest() {
        Rational a = new Rational("50/20");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("25/10", s);
    }
    @Test
    public void simplifyTwosTest2() {
        Rational a = new Rational("1/1");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("1/1", s);
    }
    @Test
    public void simplifyTwosTest3() {
        Rational a = new Rational("0/1");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("0/1", s);
    }
    @Test
    public void negativeSimplifyTwosTest() {
        Rational a = new Rational("-50/20");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("-25/10", s);
    }
    @Test
    public void negativeSimplifyTwosTest2() {
        Rational a = new Rational("-50/-20");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("25/10", s);
    }
    @Test
    public void negativeSimplifyTwosTest3() {
        Rational a = new Rational("50/-20");
        Rational answer = a.twoSimplify();
        String s = answer.toString();
        assertEquals("-25/10", s);
    }

      //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
                                //UTILS
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @Test
    public void addBitsTest() {
        ArrayList<Long> inputs = new ArrayList<Long>();
        inputs.addAll(Arrays.asList(Long.MAX_VALUE, Long.MIN_VALUE, 0L, 1L, -1L));
        for (int i=0; i<100; i++) {
            inputs.add(random.nextLong());
        }
        for (long inputA: inputs) {
            for (long inputB: inputs) {
                assert(addBits(inputA, inputB) <= 126);
                assert(addBits(inputA, inputB) >= 0);
            }
        }
    }


    @Test
    public void branchlessDozTest() {
        ArrayList<Long> inputs = new ArrayList<Long>();
        inputs.addAll(Arrays.asList(Long.MAX_VALUE, Long.MIN_VALUE, 0L, 1L, -1L));
        for (int i=0; i<100; i++) {
            inputs.add(random.nextLong());
        }
        for (long inputA: inputs) {
            for (long inputB: inputs) {
                assertEquals(doz(inputA, inputB), 
                branchlessDoz(inputA, inputB), 
                ("ERROR: Input was " + inputA + " and " + inputB
                + " and output was " + branchlessDoz(inputA, inputB)));
            }
        }
    }

    private static long doz(long inputA, long inputB) {
        long returned = inputA-inputB;
        if (returned<0) {
            returned=0;
        }
        return returned;
    }

}