package com.epam.prejap.ess.helpers.objectscompare;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.*;

public class TaskObjectsCompareTest {



    @Test(dataProvider = "listOfPeopleSortedById")
    public void shallSortPeopleByIdAscendingUsingComparable(Person[] people) {
        //given
        List<Person> list = new ArrayList<>(Arrays.asList(people));
        Collections.shuffle(list);

        //when
        Collections.sort(list);

        //then
        assertEquals(list.toArray(), people);
    }

    @Test(dataProvider = "listOfPeopleSortedByNameAsc")
    public void shallSortPeopleByNameAscendingUsingComparator(Person[] people) {
        //given
        List<Person> list = new ArrayList<>(Arrays.asList(people));
        Collections.shuffle(list);
        PersonByNameComparator comparator = new PersonByNameComparator();

        //when
        Collections.sort(list, comparator);

        //then
        assertEquals(list.toArray(), people);
    }


    @DataProvider
    public static Object[] listOfPeopleSortedById(){
        return IntStream.range(1, 11)
                .mapToObj(e -> new Person(e, "Person"))
                .toArray(Person[]::new);
    }

    @DataProvider
    public static Object[] listOfPeopleSortedByNameAsc(){
        return IntStream.range(1, 11)
                .mapToObj(e -> new Person(1, "Person" + e))
                .toArray(Person[]::new);
    }
}
