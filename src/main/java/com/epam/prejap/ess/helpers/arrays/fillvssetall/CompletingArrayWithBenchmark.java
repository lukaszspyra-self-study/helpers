package com.epam.prejap.ess.helpers.arrays.fillvssetall;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.IntUnaryOperator;

/**
 * Fill an array with the value 1 using the Arrays.fill and Arrays.setAll methods.
 *
 * @author Wiktoria Majchrzak
 * @see CompletingArray
 */
@Fork(value = 1)
@Threads(value = 1)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 100, timeUnit = TimeUnit.MILLISECONDS, time = 10)
@Warmup(iterations = 10, timeUnit = TimeUnit.MILLISECONDS, time = 10)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.MICROSECONDS)
public class CompletingArrayWithBenchmark {
    public static void main(String... args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    /**
     * Set all elements of the specified array, using the provided generator function to compute each element.
     *
     * @param array    array of ints
     * @param function generator function to compute each element
     */
    private static void fillEntireArrayBySetAll(int[] array, IntUnaryOperator function) {
        Arrays.setAll(array, function);
    }

    /**
     * Sets all elements of an array to a given value
     *
     * @param array array of ints
     * @param value the value to be stored in the array
     */
    private static void fillEntireArrayByFill(int[] array, int value) {
        Arrays.fill(array, value);
    }

    @Benchmark
    public static void fillEntireArrayByFillBenchmark(BenchmarkInput benchmarkInput) {
        fillEntireArrayByFill(benchmarkInput.array, benchmarkInput.value);
    }

    @Benchmark
    public static void fillEntireArrayBySetAllBenchmark(BenchmarkInput benchmarkInput) {
        fillEntireArrayBySetAll(benchmarkInput.array, benchmarkInput.function);
    }

    @State(Scope.Benchmark)
    public static class BenchmarkInput {
        final int[] array = new int[1_000_000];
        final int value = 1;
        final IntUnaryOperator function = (index) -> value;
    }
}
