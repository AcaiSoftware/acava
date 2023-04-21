package gg.acai.acava.commons.structure;

import java.util.Map;

/**
 * @author Clouke
 * @since 21.04.2023 08:35
 * © Acava - All Rights Reserved
 */
public interface Node<T> {

  /**
   * Gets the label of this node.
   *
   * @return Returns the label of this node.
   */
  T label();

  /**
   * Gets the attribute of this node.
   *
   * @return Returns the attribute of this node.
   */
  String attribute();

  /**
   * Gets the children of this node.
   *
   * @return Returns a map of the children in this node.
   */
  Map<T, Node<T>> children();

  /**
   * Gets the child of this node with the given attribute value.
   *
   * @param attributeValue The attribute value of the child to get.
   * @return Returns the child of this node with the given attribute value.
   */
  Node<T> getChild(T attributeValue);

  /**
   * Adds a child to this node.
   *
   * @param attributeValue The attribute value of the child to add.
   * @param child The child to add.
   * @return Returns this node for chaining.
   */
  Node<T> addChild(T attributeValue, Node<T> child);

  /**
   * Sets the label of this node.
   *
   * @param label The label to set.
   * @return Returns this node for chaining.
   */
  Node<T> setLabel(T label);

  /**
   * Sets the attribute of this node.
   *
   * @param attribute The attribute to set.
   * @return Returns this node for chaining.
   */
  Node<T> setAttribute(String attribute);

  /**
   * Checks if this node is a leaf.
   *
   * @return Returns true if this node is a leaf, false otherwise.
   */
  boolean isLeaf();

}
