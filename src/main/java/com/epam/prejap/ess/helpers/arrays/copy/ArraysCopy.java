package com.epam.prejap.ess.helpers.arrays.copy;

import org.apache.commons.lang3.SerializationUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Comparison of performance of methods that make an
 * array copy using different utility classes and libraries.
 * The input is an int array with numbers up to 10 000
 * Run the main method to see the results
 *
 * @author Dominik Janiga
 * @since 0.3
 * @version 0.3
 */
@Fork(value = 1)
@Threads(value = 1)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 100, timeUnit = TimeUnit.MILLISECONDS,  time = 10)
@Warmup(iterations = 10, timeUnit = TimeUnit.MILLISECONDS, time = 10)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.MICROSECONDS)
public class ArraysCopy {

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);

    }

    /**
     * Use Stream.arraycopy under the hood to copy an array. Note that use also Math.min(...)
     * for selecting the minimum of the source array length and the value of
     * the new length parameter to determine the size of the resulting array.
     * Do a shallow copy of objects if applied on an array of non-primitive object types.
     *
     */
    static int[] copyWithArrayCopyOf(int[] elements) {
        return Arrays.copyOf(elements, elements.length);
    }

    /**
     * Works in similar way as Array.copyOf, takes 2 parameters, ‘from' and ‘to' in
     * addition to the source array parameter.
     * Copied array includes 'from' index but 'to' index is excluded.
     *
     */
    static int[] copyWithArrayCopyOfRange(int[] elements) {
        return Arrays.copyOfRange(elements, 0, elements.length / 2);
    }

    /**
     * stream() - use IntStream under the hood the returns a sequential ordered
     * stream whose elements are the specified values.
     * toArray() - returns an array containing the elements of this stream
     * For the non-primitive types, it will also do a shallow copy of objects.
     *
     */
    static int[] copyWithArraysStream(int[] elements) {
        return Arrays.stream(elements).toArray();
    }

    /**
     * Deep clone an Object using serialization. This is many times slower than writing clone methods.
     * It is very useful if we need to do a deep copy of an array of non-primitive types.
     * All the objects must be Serializable.
     *
     */
    static int[] copyWithSerializationUtils(int[] elements) {
        return SerializationUtils.clone(elements);
    }

    /**
     *
     * Copies an array from the specified source array, beginning at the specified position,
     * to the specified position of the destination array
     *
     * In the following example:
     *  - elements is the source array.
     *  - 0 is starting position in the source array.
     *  - newElements is the destination array.
     *  - 0 is starting position in the destination array.
     *  - end is the number of array elements to be copied.
     *
     * @throws NullPointerException if source or destination array is null
     * @throws ArrayStoreException if an element in the src array could not be stored into the dest array because of a type mismatch.
     * @throws ArrayIndexOutOfBoundsException if any of the integer arguments is negative or out of range
     */
    static int[] copyWithSystemArrayCopy(int[] elements) {
        int end = elements.length;
        int[] newElements = new int[end];
        System.arraycopy(elements, 0, newElements, 0, end);

        return newElements;
    }

    static int[] copyWithForLoop(int[] elements) {
        int[] newElements = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        return newElements;
    }


    @Benchmark
    public void copyWithArrayCopyOfBenchmark(BenchmarkInput input) {
        copyWithArrayCopyOf(input.SAMPLE_ARRAY);
    }

    @Benchmark
    public void copyWithArraysStreamBenchmark(BenchmarkInput input) {
        copyWithArraysStream(input.SAMPLE_ARRAY);
    }

    @Benchmark
    public void copyWithSerializationUtilsBenchmark(BenchmarkInput input) {
        copyWithSerializationUtils(input.SAMPLE_ARRAY);
    }

    @Benchmark
    public void copyWithSystemArrayCopyBenchmark(BenchmarkInput input) {
        copyWithSystemArrayCopy(input.SAMPLE_ARRAY);
    }

    @Benchmark
    public void copyWithForLoopBenchMark(BenchmarkInput input) {
        copyWithForLoop(input.SAMPLE_ARRAY);
    }


    @State(Scope.Benchmark)
    public static class BenchmarkInput {
        final int[] SAMPLE_ARRAY = IntStream.rangeClosed(1, 10_000).toArray();
    }
}
