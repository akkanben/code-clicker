package com.crudalchemy.codeclicker;

import static org.junit.Assert.assertEquals;

import com.crudalchemy.codeclicker.utility.LargeNumbers;

import org.junit.Test;

public class LargeNumbersLinesPerSecondTest {

    @Test
    public void under_million() {
        String sut = LargeNumbers.convertWithDecimals(0.0);
        assertEquals(".00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(1.0);
        assertEquals("1.00 line",sut);

        sut = LargeNumbers.convertWithDecimals(2.0);
        assertEquals("2.00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(10.0);
        assertEquals("10.00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(1000.0);
        assertEquals("1,000.00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(10000.0);
        assertEquals("10,000.00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(100000.0);
        assertEquals("100,000.00 lines",sut);
    }

    @Test
    public void million() {
        String sut = LargeNumbers.convertWithDecimals(1000000.0);
        assertEquals("1.000 million lines",sut);

        sut = LargeNumbers.convertWithDecimals(1234567.0);
        assertEquals("1.234 million lines",sut);

        sut = LargeNumbers.convertWithDecimals(12345678.0);
        assertEquals("12.345 million lines",sut);

        sut = LargeNumbers.convertWithDecimals(123456789.0);
        assertEquals("123.456 million lines",sut);
    }

    @Test
    public void billion() {
        String sut = LargeNumbers.convertWithDecimals(1000000000.0);
        assertEquals("1.000 billion lines",sut);

        sut = LargeNumbers.convertWithDecimals(1234567000.0);
        assertEquals("1.234 billion lines",sut);

        sut = LargeNumbers.convertWithDecimals(12345678900.0);
        assertEquals("12.345 billion lines",sut);

        sut = LargeNumbers.convertWithDecimals(123456789000.0);
        assertEquals("123.456 billion lines",sut);
    }

    @Test
    public void trillion() {
        String sut = LargeNumbers.convertWithDecimals(1000000000000.0);
        assertEquals("1.000 trillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(1234567000000.0);
        assertEquals("1.234 trillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(12345678900000.0);
        assertEquals("12.345 trillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(123456789000000.0);
        assertEquals("123.456 trillion lines",sut);
    }

    @Test
    public void quadrillino() {
        String sut = LargeNumbers.convertWithDecimals(1000000000000000.0);
        assertEquals("1.000 quadrillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(1234567000000000.0);
        assertEquals("1.234 quadrillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(12345678900000000.0);
        assertEquals("12.345 quadrillion lines",sut);

        sut = LargeNumbers.convertWithDecimals(123456789000000000.0);
        assertEquals("123.456 quadrillion lines",sut);
    }

    @Test
    public void negative() {
        String sut = LargeNumbers.convertWithDecimals(-100.0);
        assertEquals("-100.00 lines",sut);

        sut = LargeNumbers.convertWithDecimals(-123456789.0);
        assertEquals("-123456789.00 lines",sut);
    }

    @Test
    public void rounding() {
        String sut = LargeNumbers.convertWithDecimals(100.5555);
        assertEquals("100.55 lines",sut);

        sut = LargeNumbers.convertWithDecimals(123456789.0099999);
        assertEquals("123.456 million lines",sut);
    }

}
