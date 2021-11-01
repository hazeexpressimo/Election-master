package com.election.service;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtil {
    public static <E> boolean isExistInListPyPredicate(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().anyMatch(predicate);
    }

    public static <E, V> void addObjectToListIfNotExist(Collection<E> collection, E element, Function<E, V> function) throws Exception {
        Predicate<E> predicate =  e -> function.apply(element).equals(function.apply(e));
        boolean existInListPyPredicate = isExistInListPyPredicate(collection, predicate);
        if (existInListPyPredicate) {
            throw new Exception("Данный обьект уже существует");
        }
        collection.add(element);
    }

    public static void showCollection(Collection<?> collection) {
        if (collection.isEmpty()) {
            System.out.println("Empty.........");
        }
        collection.forEach(v -> System.out.println(v + "\n" + "-----------------------------------" + "\n"));
    }

    public static <E, V> E getByValue(Collection<E> collection, V value, Function<E, V> function) throws Exception {
        Predicate<E> predicate = e -> function.apply(e).equals(value);
        return collection.stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new Exception("Елемент не найден: "));
    }
}
