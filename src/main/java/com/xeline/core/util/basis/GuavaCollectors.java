package com.xeline.core.util.basis;

import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ArrayListMultimap;

/**
 * guava
 *
 * @author xenron
 */
public class GuavaCollectors {

  public static <K, V, T extends Entry<K, List<V>>> Collector<T, ArrayListMultimap<K, V>, ArrayListMultimap<K, V>> toListMultimap() {

    Supplier<ArrayListMultimap<K, V>> supplier = ArrayListMultimap::create;
    BiConsumer<ArrayListMultimap<K, V>, T> accumulator = (m, t) -> m.putAll(t.getKey(), t.getValue());
    BinaryOperator<ArrayListMultimap<K, V>> combiner = (l, r) -> {
      l.putAll(r);
      return l;
    };
    Function<ArrayListMultimap<K, V>, ArrayListMultimap<K, V>> finisher = (f) -> f;

    return Collector.of(supplier, accumulator, combiner, finisher);
  }

}
