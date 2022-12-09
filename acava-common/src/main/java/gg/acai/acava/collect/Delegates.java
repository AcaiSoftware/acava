package gg.acai.acava.collect;

import gg.acai.acava.cache.CacheDuplex;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Clouke
 * @since 06.12.2022 12:16
 * © Acava - All Rights Reserved
 */
public final class Delegates {

    public static <K, V> Map<K, V> delegate(Map<K, V> other, Map<K, V> delegated) {
        delegated.putAll(other);
        return delegated;
    }

    public static <V> Collection<V> delegate(Collection<V> other, Collection<V> delegated) {
        delegated.addAll(other);
        return delegated;
    }

    public static <V> Set<V> delegate(Set<V> other, Set<V> delegated) {
        delegated.addAll(other);
        return delegated;
    }

    public static <K, V> CacheDuplex<K, V> delegate(CacheDuplex<K, V> other, CacheDuplex<K, V> delegated) {
        delegated.delegate(other);
        return delegated;
    }

}
