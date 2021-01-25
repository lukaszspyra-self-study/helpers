package com.epam.prejap.ess.helpers;

import java.io.PrintStream;
import java.util.*;

/**
 * Presentation of comparing instances of {@link Person}, using helper class method
 * {@link Objects#compare(Object, Object, Comparator)}.
 */
final class TaskObjectsCompare {

    private static PrintStream out = System.out;

    public static void main(String[] args) {
        Person lukasz = new Person(1, "Lukasz");
        Person anthony = new Person(2, "Anthony");
        Person david = new Person(3, "David");
        List<Person> people = Arrays.asList(lukasz, david, anthony);

        //natural order using Comparable
        Collections.sort(people);
        out.println("Comparable: " + people);

        //sorting using custom comparator by name ascending
        Collections.sort(people, new PersonByNameComparator());
        System.out.println("Comparator by name ascending: " + people);

        //sorting using custom comparator by name descending - as lambda
        Collections.sort(people, (p1, p2) -> p2.name().compareTo(p1.name()));
        System.out.println("Comparator by name descending:" + people);

        //comparing two objects using helper Objects class and its compare() with comparator
        System.out.println("Lukasz - Anthony/by name asc/returns positive number: " + Objects.compare(lukasz, anthony, new PersonByNameComparator()));
        System.out.println("Lukasz - Anthony/by id asc/returns negative number: " + Objects.compare(lukasz, anthony, Comparator.comparingInt(Person::id)));
        System.out.println("Two nulls are equal - no comparator used/returns zero: " + Objects.compare(null, null, null));
        System.out.println("Null - Lukasz/Null safe comparator (null first)/returns negative number: " + Objects.compare(null, lukasz, Comparator.nullsFirst(new PersonByNameComparator())));
        System.out.println("Null - Lukasz/Null safe comparator (null last)/returns positive number: " + Objects.compare(null, lukasz, Comparator.nullsLast(new PersonByNameComparator())));
    }
}


/**
 * Implements {@link Comparable<Person>} for comparing by id/ascending as natural order.
 */
record Person(int id, String name) implements Comparable<Person> {
    @Override
    public int compareTo(final Person o) {
        return this.id - o.id;
    }
}


/**
 * Custom comparator, comparing instances of {@link Person} by name/ascending.
 */
class PersonByNameComparator implements Comparator<Person> {
    @Override
    public int compare(final Person o1, final Person o2) {

        return o1.name().compareTo(o2.name());
    }
}
