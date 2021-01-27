package com.epam.prejap.ess.helpers.arrays.fillvssetall;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "CompletingArrays")
public class CompletingArrayTest {
    private static int[] array;

    @DataProvider
    public Object[][] sizeAndValues() {
        return new Object[][]{{4, 1}, {4, 2}, {10, 4}};
    }

    @Test
    public void shouldReturnExpectedArray() {
        //given
        array = new int[]{1, 2, 3, 4, 5};
        //when
        String actualArray = CompletingArray.getArrayAsString(array);
        //then
        assertEquals(actualArray, "[1, 2, 3, 4, 5]");
    }

    @Test
    public void shouldReturnUnexpectedArray() {
        //given
        array = new int[]{1, 2, 3, 4, 5};
        //when
        String actualArray = CompletingArray.getArrayAsString(array);
        //then
        assertNotEquals(actualArray, "[1, 2, 3, 4, 5, 6]");
    }

    @Test
    public void shouldReturnExpectedArrayUsingFillEntireArrayByFillMethod() {
        //given
        array = new int[4];
        //when
        CompletingArray.fillEntireArrayByFill(array, 1);
        //then
        assertEquals(array, new int[]{1, 1, 1, 1});
    }

    @Test
    public void shouldReturnExpectedArrayUsingFillSelectedPartOfArrayByFillMethod() {
        //given
        array = new int[]{0, 0, 0, 0};
        //when
        CompletingArray.fillSelectedPartOfArrayByFill(array, 1, 2, 5);
        //then
        assertEquals(array, new int[]{0, 5, 0, 0});
    }

    @Test
    public void shouldReturnExpectedArrayUsingFill() {
        //given
        array = new int[4];
        //when
        CompletingArray.fillEntireArrayBySetAll(array, i -> i + 1);
        //then
        assertEquals(array, new int[]{1, 2, 3, 4});
    }
}
