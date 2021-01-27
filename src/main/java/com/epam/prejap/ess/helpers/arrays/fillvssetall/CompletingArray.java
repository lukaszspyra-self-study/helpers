package com.epam.prejap.ess.helpers.arrays.fillvssetall;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * The task is to present the differences between the Arrays.fill and Arrays.setAll methods.
 * <p>
 * The <b>fill</b> method takes the array to be filled and the value to be stored in elements of the array.
 * It can also be the starting and ending index of the array as a parameter.
 * <p>
 * The <b>setAll</b> method set all elements of the specified array, using the provided generator function to compute each element.
 * Generator is an interface that can be used as the assignment target for a lambda expression or method reference.
 *
 * @author Wiktoria Majchrzak
 * @see CompletingArrayWithBenchmark
 */

class CompletingArray {

    public static void main(String... args) {

        int arraySize = 10;
        int[] array = new int[arraySize];
        int valueToBeFilled;
        int fromIndex;
        int toIndex;

        /*
            Fill the entire array with the value of 4 using Arrays.fill method
        */
        valueToBeFilled = 4;
        fillEntireArrayByFill(array, valueToBeFilled);
        printArray(array);

        /*
            Overwriting the value of the array with indexes from 2 to 6 with the value of 5 using Arrays.fill method
        */
        fromIndex = 2;
        toIndex = 6;
        valueToBeFilled = 5;
        fillSelectedPartOfArrayByFill(array, fromIndex, toIndex, valueToBeFilled);
        printArray(array);

        /*
            Fill the array with numbers greater than their index by one using Arrays.fill method
        */
        IntStream.range(0, array.length).forEach(i -> fillSelectedPartOfArrayByFill(array, i, i + 1, i + 1));
        printArray(array);

        /*
            Fill the array with numbers greater than their index by one using Arrays.setAll method
        */
        IntUnaryOperator function = (index) -> 1 + index;
        fillEntireArrayBySetAll(array, function);
        printArray(array);


        /*
            Additional task: Fill an array with the value 1 using the Arrays.fill and Arrays.setAll methods
            Explanation of the difference in action in the PR comment.
            To see the runtime differences see class CompletingArrayWithBenchmark
        */
        valueToBeFilled = 1;
        fillEntireArrayByFill(array, valueToBeFilled);
        printArray(array);

        Arrays.setAll(array, (index) -> 1);
        printArray(array);
    }

    /**
     * Set all elements of the specified array, using the provided generator function to compute each element.
     *
     * @param array    array of ints
     * @param function generator function to compute each element
     */
    static void fillEntireArrayBySetAll(int[] array, IntUnaryOperator function) {
        Arrays.setAll(array, function);
    }

    /**
     * Sets elements of an array between the indicated indices to the given value
     *
     * @param array     array of ints
     * @param fromIndex the index of the first element (inclusive) to be filled with the specified value
     * @param toIndex   the index of the last element (exclusive) to be filled with the specified value
     * @param value     the value to be stored in the array
     */
    static void fillSelectedPartOfArrayByFill(int[] array, int fromIndex, int toIndex, int value) {
        Arrays.fill(array, fromIndex, toIndex, value);
    }

    /**
     * Sets all elements of an array to a given value
     *
     * @param array array of ints
     * @param value the value to be stored in the array
     */
    static void fillEntireArrayByFill(int[] array, int value) {
        Arrays.fill(array, value);
    }

    /**
     * Print array values
     *
     * @param arr array of ints
     */
    private static void printArray(int[] arr) {
        System.out.println(getArrayAsString(arr));
    }

    /**
     * Returns a string representation of the contents of the ints array.
     *
     * @param arr array of ints
     * @return a string representation of an array
     */
    static String getArrayAsString(int[] arr) {
        return Arrays.toString(arr);
    }
}
