package com.bidgely.library.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Utility class for collection operations. */
public class CollectionUtil {

  private CollectionUtil() {
    // Private constructor to prevent instantiation
  }

  /** Checks if a collection is null or empty. */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /** Checks if a collection is not null and not empty. */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }

  /** Returns the size of a collection, or 0 if null. */
  public static int size(Collection<?> collection) {
    return collection == null ? 0 : collection.size();
  }

  /** Returns a safe copy of a list, or empty list if null. */
  public static <T> List<T> safeCopy(List<T> list) {
    if (list == null) {
      return Collections.emptyList();
    }
    return new ArrayList<>(list);
  }

  /** Returns a safe copy of a map, or empty map if null. */
  public static <K, V> Map<K, V> safeCopy(Map<K, V> map) {
    if (map == null) {
      return Collections.emptyMap();
    }
    return new HashMap<>(map);
  }

  /** Returns the first element of a list, or null if empty. */
  public static <T> T first(List<T> list) {
    if (isEmpty(list)) {
      return null;
    }
    return list.get(0);
  }

  /** Returns the last element of a list, or null if empty. */
  public static <T> T last(List<T> list) {
    if (isEmpty(list)) {
      return null;
    }
    return list.get(list.size() - 1);
  }

  /** Partitions a list into sublists of a given size. */
  public static <T> List<List<T>> partition(List<T> list, int size) {
    if (isEmpty(list) || size <= 0) {
      return Collections.emptyList();
    }

    List<List<T>> partitions = new ArrayList<>();
    for (int i = 0; i < list.size(); i += size) {
      partitions.add(list.subList(i, Math.min(i + size, list.size())));
    }
    return partitions;
  }
}
