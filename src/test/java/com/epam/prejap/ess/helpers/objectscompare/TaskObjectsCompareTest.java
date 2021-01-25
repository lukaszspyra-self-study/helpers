package com.epam.prejap.ess.helpers.objectscompare;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;
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

    @Test
    public void shallCompareTwoPeopleByName() {
        //given
        Person lukasz = new Person(1, "Lukasz");
        Person anthony = new Person(2, "Anthony");

        //when
        int result = Objects.compare(lukasz, anthony, new PersonByNameComparator());

        //then
        assertTrue(result > 0);
    }

    @Test
    public void shallCompareNullWithPersonWithNoException() {
        //given
        Person lukasz = new Person(1, "Lukasz");
        SoftAssert softAssert = new SoftAssert();

        //when
        int result1 = Objects.compare(null, lukasz, Comparator.nullsFirst(new PersonByNameComparator()));
        int result2 = Objects.compare(null, lukasz, Comparator.nullsLast(new PersonByNameComparator()));
        int result3 = Objects.compare(null, null, null);

        //then
        softAssert.assertTrue(result1 < 0);
        softAssert.assertTrue(result2 > 0);
        softAssert.assertTrue(result3 == 0);
        softAssert.assertAll();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shallCompareNullWithPersonWithNPEException() {
        //given
        Person lukasz = new Person(1, "Lukasz");

        //when
        Objects.compare(null, lukasz, Comparator.comparing(Person::name, String.CASE_INSENSITIVE_ORDER));
    }

}
