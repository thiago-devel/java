package io.rubyit;
import java.io.*;
import java.util.*;
import java.util.Collections;

class Solution {
  public static void main(String[] args) {
    testMinByKey();
  }

  /**
   * # Step 1
   * Throughout this interview, we'll pretend we're building a new analytical
   * database. Don't worry about actually building a database though -- these will
   * all be toy problems.
   *
   * Here's how the database works: all records are represented as maps, with string
   * keys and integer values. The records are contained in an array, in no
   * particular order.
   *
   * To begin with, the database will support just one function: min_by_key. This
   * function scans the array of records and returns the record that has the minimum
   * value for a specified key. Records that do not contain the specified key are
   * considered to have value 0 for the key. Note that keys may map to negative values!
   *
   * Here's an example use case: each of your records contains data about a school
   * student. You can use min_by_key to answer questions such as "who is the youngest
   * student?" and "who is the student with the lowest grade-point average?"
   *
   * Implementation notes:
   * * You should handle an empty array of records in an idiomatic way in your
   *   language of choice.
   * * If several records share the same minimum value for the chosen key, you
   *   may return any of them.
   *
   * ### Java function signature:
   * ```
   * public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records);
   * ```
   *
   * ### Examples (in Python):
   * ```
   * assert min_by_key("a", [{"a": 1, "b": 2}, {"a": 2}]) == {"a": 1, "b": 2}
   * assert min_by_key("a", [{"a": 2}, {"a": 1, "b": 2}]) == {"a": 1, "b": 2}
   * assert min_by_key("b", [{"a": 1, "b": 2}, {"a": 2}]) == {"a": 2}
   * assert min_by_key("a", [{}]) == {}
   * assert min_by_key("b", [{"a": -1}, {"b": -1}]) == {"b": -1}
   * ```
   */
  public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records) {
	int minimal = Integer.MAX_VALUE;  
	Map<String, Integer> result = null;

	for (Map<String, Integer> map : records) {
		Integer value = map.getOrDefault(key, 0);
		if (value < minimal) {
			minimal = value;
			result = map;
		}
	}
	return result;
  }

  public static void testMinByKey() {
    List<Map<String, Integer>> example1 = Arrays.asList(
        Maps.of("a", 1, "b", 2),
        Maps.of("a", 2)
    );
    List<Map<String, Integer>> example2 = Arrays.asList(example1.get(1), example1.get(0));
    List<Map<String, Integer>> example3 = Arrays.asList(Maps.of());
    List<Map<String, Integer>> example4 = Arrays.asList(
        Maps.of("a", -1),
        Maps.of("b", -1)
    );

    System.out.println("minByKey");
    assertEqual(example1.get(0), minByKey("a", example1));
    assertEqual(example2.get(1), minByKey("a", example2));
    assertEqual(example1.get(1), minByKey("b", example1));
    assertEqual(example3.get(0), minByKey("a", example3));
    assertEqual(example4.get(1), minByKey("b", example4));
  }

  public static <T> void assertEqual(T expected, T actual) {
    if (expected == null && actual == null || actual != null && actual.equals(expected)) {
      System.out.println("PASSED");
    } else {
      throw new AssertionError("Expected:\n  " + expected + "\nActual:\n  " + actual + "\n");
    }
  }

  public static <T> void assertIn(T needle, List<T> haystack) {
    if (haystack.contains(needle)) {
      System.out.println("PASSED");
    } else {
      throw new AssertionError(needle + " was not found in " + haystack + "\n");
    }
  }
}

/*
 * Helpers to quickly create and populate new Maps.
 *
 * Inspired by ImmutableMap.of in guava.
 */
class Maps {
  public static <K, V> Map<K, V> of() {
    return new HashMap<K, V>();
  }

  public static <K, V> Map<K, V> of(K k1, V v1) {
    Map<K, V> map = new HashMap<K, V>();
    map.put(k1, v1);
    return map;
  }

  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    Map<K, V> map = new HashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    return map;
  }

  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    Map<K, V> map = new HashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    return map;
  }

  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    Map<K, V> map = new HashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    return map;
  }

  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    Map<K, V> map = new HashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    map.put(k5, v5);
    return map;
  }

  public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1) {
    LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
    map.put(k1, v1);
    return map;
  }

  public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2) {
    LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    return map;
  }

  public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2, K k3, V v3) {
    LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    return map;
  }
}