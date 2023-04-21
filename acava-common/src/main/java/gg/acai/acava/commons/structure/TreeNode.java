package gg.acai.acava.commons.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * A tree node implementation of the {@link Node} interface.
 *
 * @author Clouke
 * @since 21.04.2023 08:38
 * © Acava - All Rights Reserved
 */
public class TreeNode<T> implements Node<T> {

  private T label;
  private String attribute;
  private final Map<T, Node<T>> children;

  /**
   * Constructs a new tree node with the given label, attribute and children.
   *
   * @param label The label of this node.
   * @param attribute The attribute of this node.
   * @param children The children of this node.
   */
  public TreeNode(T label, String attribute, Map<T, Node<T>> children) {
    this.label = label;
    this.attribute = attribute;
    this.children = children;
  }

  /**
   * Constructs a new tree node.
   */
  public TreeNode() {
    this(null, null, new HashMap<>());
  }

  @Override
  public T label() {
    return label;
  }

  @Override
  public String attribute() {
    return attribute;
  }

  @Override
  public Map<T, Node<T>> children() {
    return children;
  }

  @Override
  public Node<T> getChild(T attributeValue) {
    return children.get(attributeValue);
  }

  @Override
  public Node<T> addChild(T attributeValue, Node<T> child) {
    children.put(attributeValue, child);
    return this;
  }

  @Override
  public Node<T> setLabel(T label) {
    this.label = label;
    return this;
  }

  @Override
  public Node<T> setAttribute(String attribute) {
    this.attribute = attribute;
    return this;
  }

  @Override
  public boolean isLeaf() {
    return children.isEmpty();
  }
}
