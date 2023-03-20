package gg.acai.acava.collect.lists;

import gg.acai.acava.collect.Immutable;

import javax.annotation.Nonnull;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * An immutable list of elements.
 *
 * @param <N> the type of elements in this list
 *
 * @author Clouke
 * @since 22.02.2023 02:49
 * © Acava - All Rights Reserved
 */
public class ImmutableList<N> extends AbstractList<N> implements List<N>, RandomAccess, Immutable {

    /** The array of nodes that make up this list */
    private final N[] nodes;

    /**
     * Returns a new immutable list containing the given elements.
     *
     * @param nodes the elements to include in the list
     * @param <N>   the type of elements in the list
     * @return a new immutable list containing the given elements
     */
    @SafeVarargs
    public static <N> ImmutableList<N> of(N... nodes) {
        return new ImmutableList<>(nodes);
    }

    public static <N> ImmutableList<N> copyOf(Collection<N> nodes) {
        return new ImmutableList<>(nodes);
    }

    /**
     * Returns a new immutable list containing the given list of elements.
     *
     * @param nodes the list of elements to include in the list
     * @param <N> the type of elements in the list
     * @return a new immutable list containing the given list of elements
     */
    public static <N> ImmutableList<N> of(Collection<N> nodes) {
        return nodes instanceof ImmutableList ? ((ImmutableList<N>) nodes).clone() : new ImmutableList<>(nodes);
    }

    /**
     * Creates a new immutable list from the given array of nodes.
     *
     * @param nodes the array of nodes to use for the list
     */
    @SafeVarargs
    public ImmutableList(N... nodes) {
        this.nodes = nodes;
    }

    /**
     * Creates a new immutable list from the given list of nodes.
     *
     * @param nodes the list of nodes to use for the list
     */
    @SuppressWarnings("unchecked")
    public ImmutableList(Collection<N> nodes) {
        this.nodes = (N[]) nodes.toArray();
    }

    /**
     * Creates a new immutable list from the given immutable list of nodes.
     *
     * @param nodes the immutable list of nodes to use for the list
     */
    public ImmutableList(ImmutableList<N> nodes) {
        this.nodes = nodes.nodes;
    }

    /**
     * Returns a sequential {@code Stream} with the nodes of this list as its source.
     *
     * @return a sequential {@code Stream} over the nodes in this list
     */
    @Override
    public Stream<N> stream() {
        return Stream.of(nodes);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @throws IndexOutOfBoundsException if the index is out of range
     * @return the element at the specified position in this list
     */
    @Override
    public N get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nodes.length) throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        return nodes[index];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return nodes.length;
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be cleared.
     *
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be cleared
     */
    @Override @Deprecated
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear immutable list");
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o the element to search for in this list
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        for (N node : nodes) {
            if (node.equals(o)) return true;
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for in this list
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].equals(o)) return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for in this list
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = nodes.length - 1; i >= 0; i--) {
            if (nodes[i].equals(o)) return i;
        }
        return -1;
    }

    /**
     * Runs a for-each loop over the nodes in this list.
     *
     * @param action The action to be performed for each element
     */
    @Override
    public void forEach(Consumer<? super N> action) {
        for (N node : nodes)
            action.accept(node);
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param n element whose presence in this collection is to be ensured
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean add(N n) {
        throw new UnsupportedOperationException("Cannot add to immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public void add(int index, N element) {
        throw new UnsupportedOperationException("Cannot add to immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param c collection containing elements to be added to this list
     * @return always false
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean addAll(@Nonnull Collection<? extends N> c) {
        throw new UnsupportedOperationException("Cannot add to immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param index index at which to insert the first element from the specified collection
     * @param c collection containing elements to be added to this list
     * @return always false
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean addAll(int index, Collection<? extends N> c) {
        throw new UnsupportedOperationException("Cannot add to immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param o index of the element to be removed
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param c collection containing elements to be removed from this list
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean removeAll(@Nonnull Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param fromIndex index of first element to be removed
     * @param toIndex index after last element to be removed
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param filter a predicate which returns {@code true} for elements to be removed
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean removeIf(Predicate<? super N> filter) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param c collection containing elements to be retained in this list
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public boolean retainAll(@Nonnull Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param index the index of the element to be removed
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override @Deprecated
    public N remove(int index) {
        throw new UnsupportedOperationException("Cannot remove from immutable list");
    }

    /**
     * Throws an {@code UnsupportedOperationException}, as the list is immutable and cannot be modified.
     *
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException always thrown, as the list is immutable and cannot be modified
     */
    @Override
    public void replaceAll(UnaryOperator<N> operator) {
        throw new UnsupportedOperationException("Cannot replace in immutable list");
    }

    /**
     * Clones this list.
     *
     * @return a clone of this list
     */
    @Override @SuppressWarnings("all")
    public ImmutableList<N> clone() {
        return new ImmutableList<>(this);
    }
}
