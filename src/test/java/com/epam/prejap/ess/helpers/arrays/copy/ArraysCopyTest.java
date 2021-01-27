package com.epam.prejap.ess.helpers.arrays.copy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

@Test(groups = "ArrayCopy")
public class ArraysCopyTest {

    private final static int[] EXPECTED_ARRAY = IntStream.rangeClosed(1, 10000).toArray();

    @Test
    public void shouldReturnTheSameArray_whenCopiedWithArrayCopyOf() {

        //when
        int[] actual = ArraysCopy.copyWithArrayCopyOf(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, EXPECTED_ARRAY);
    }

    @Test
    public void shouldReturnHalfOfArray_whenCopiedWithArrayCopyOfRange() {
        //given
        int[] expected = IntStream.rangeClosed(1, 5000).toArray();

        //when
        int[] actual = ArraysCopy.copyWithArrayCopyOfRange(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnTheSameArray_whenCopiedWithIntStream() {

        //when
        int[] actual = ArraysCopy.copyWithArraysStream(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, EXPECTED_ARRAY);
    }

    @Test
    public void shouldReturnTheSameArray_whenCopiedWithSerializationUtils() {

        //when
        int[] actual = ArraysCopy.copyWithSerializationUtils(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, EXPECTED_ARRAY);
    }

    @Test
    public void shouldReturnTheSameArray_whenCopiedWithSystemArrayCopy() {

        //when
        int[] actual = ArraysCopy.copyWithSystemArrayCopy(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, EXPECTED_ARRAY);
    }

    @Test
    public void shouldReturnTheSameArray_whenCopiedForLoop() {

        //when
        int[] actual = ArraysCopy.copyWithForLoop(EXPECTED_ARRAY);

        //then
        Assert.assertEquals(actual, EXPECTED_ARRAY);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrownAnException_whenSourceArrayIsNull() {
        ArraysCopy.copyWithSystemArrayCopy(null);
    }
}
