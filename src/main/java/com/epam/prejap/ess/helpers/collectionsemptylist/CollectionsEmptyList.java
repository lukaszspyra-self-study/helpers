package com.epam.prejap.ess.helpers.collectionsemptylist;

import java.util.Collections;
import java.util.List;

/**
 * <p>Collections.EMPTY_LIST vs. Collections.emptyList()</p>
 *
 * <p>Both, field and method, return an empty immutable list. Collections.EMPTY_LIST returns a raw list, while
 * Collections.emptyList() is generic-friendly, thus it provides type safety. The method was introduced in Java 1.5
 * with Generics.
 * </p>
 *
 * @author Martyna Rucińska Pereira
 */
class CollectionsEmptyList {
    public static void main(String[] args) {
        // Unchecked assignment: 'java.util.List' to 'java.util.List<java.lang.String>'
        List<String> emptyListFromField = Collections.EMPTY_LIST;
        emptyListFromField.add("test"); // throws UnsupportedOperationException

        List<String> emptyListFromMethod = Collections.emptyList();
        emptyListFromMethod.add("test"); // throws UnsupportedOperationException
    }
}
