package gg.acai.acava.collect.lists;

import com.google.common.base.Preconditions;
import gg.acai.acava.collect.Mutable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * LinkedList extension which provides a list with a limited size.
 * Once the maximum size is reached, adding new elements to the list will result in removing the oldest element.
 *
 * @author Clouke
 * @since 09.02.2023 17:36
 * © Acava - All Rights Reserved
 *
 * @param <E> the type of elements stored in this list
 */
public class FixedSizeQueue<E> extends LinkedList<E> implements Mutable {

  /**
   * The maximum size of the list
   */
  private final int maxSize;

  /**
   * Creates a new EvictedList with the given maximum size.
   *
   * @param maxSize the maximum size of the list. Must be greater than 0.
   */
  public FixedSizeQueue(int maxSize) {
    Preconditions.checkState(maxSize > 0, "Max size must be greater than 0");
    this.maxSize = maxSize;
  }

  /**
   * Adds the specified element to the end of the list.
   * If adding this element would exceed the maximum size, the oldest element will be removed first.
   *
   * @param e the element to be added
   * @return true if the element was successfully added, false otherwise
   */
  @Override
  public boolean add(E e) {
    if (size() >= maxSize)
      removeFirst();
    return super.add(e);
  }

  /**
   * Inserts the specified element at the specified position in this list.
   * If adding this element would exceed the maximum size, the oldest element will be removed first.
   *
   * @param index   the position at which to insert the element
   * @param element the element to be inserted
   */
  @Override
  public void add(int index, E element) {
    if (size() >= maxSize)
      removeFirst();
    super.add(index, element);
  }

  /**
   * Inserts all the elements in the specified collection into this list, starting at the specified position.
   * If adding these elements would exceed the maximum size, the oldest elements will be removed first.
   *
   * @param index the position at which to insert the first element from the specified collection
   * @param c     the collection containing elements to be added to this list
   * @return true if the list was modified as a result of the call, false otherwise
   */
  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    if (size() + c.size() >= maxSize)
      removeFirst();
    return super.addAll(index, c);
  }

  /**
   * Appends all the elements in the specified collection to the end of this list.
   * If adding these elements would exceed the maximum size, the oldest elements will be removed first.
   *
   * @param c the collection containing elements to be added to this list
   * @return true if the list was modified as a result of the call, false otherwise
   */
  @Override
  public boolean addAll(Collection<? extends E> c) {
    if (size() + c.size() >= maxSize)
      removeFirst();
    return super.addAll(c);
  }

  /**
   * Inserts the specified element at the front of this list.
   * If adding this element would exceed the maximum size, the oldest element will be removed first.
   *
   * @param e the element to be inserted
   */
  @Override
  public void addFirst(E e) {
    if (size() >= maxSize)
      removeFirst();
    super.addFirst(e);
  }

  /**
   * Appends the specified element to the end of this list.
   * If adding this element would exceed the maximum size, the oldest element will be removed first.
   *
   * @param e the element to be inserted
   */
  @Override
  public void addLast(E e) {
    if (size() >= maxSize)
      removeFirst();
    super.addLast(e);
  }

  /**
   * Pushes an element onto the stack represented by this list.
   *
   * @param e the element to push
   */
  @Override
  public void push(E e) {
    if (size() >= maxSize)
      removeFirst();
    super.push(e);
  }

  /**
   * Offers the specified element as the tail of this list.
   *
   * @param e the element to add
   * @return true if the element was added to this list, else false
   */
  @Override
  public boolean offer(E e) {
    if (size() >= maxSize)
      removeFirst();
    return super.offer(e);
  }

  /**
   * Inserts the specified element at the front of this list.
   * If adding this element would exceed the maximum size, the oldest element will be removed first.
   *
   * @param e the element to be inserted
   * @return true if the element was successfully added, false otherwise
   */
  @Override
  public boolean offerFirst(E e) {
    if (size() >= maxSize)
      removeFirst();
    return super.offerFirst(e);
  }

  /**
   * Appends the specified element to the end of this list.
   *
   * @param e the element to add
   * @return true if the element was successfully added, false otherwise
   */
  @Override
  public boolean offerLast(E e) {
    if (size() >= maxSize)
      removeFirst();
    return super.offerLast(e);
  }

  /**
   * Returns true if the list is full, false otherwise.
   *
   * @return true if the list is full, false otherwise
   */
  public boolean isFull() {
    return size() >= maxSize;
  }

  /**
   * Returns the maximum size of the list.
   *
   * @return the maximum size of the list
   */
  public int getMaxSize() {
    return maxSize;
  }

}
