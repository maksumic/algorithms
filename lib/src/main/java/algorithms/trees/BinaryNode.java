package algorithms.trees;

import java.awt.*;
import java.util.Objects;

public class BinaryNode<K extends Comparable<K>, V> implements Comparable<BinaryNode<K, V>> {
    protected static final int RADIUS = 8;
    protected static final int DIAMETER = RADIUS * 2;
    protected final K key;
    protected V value;
    protected BinaryNode<K, V> left;
    protected BinaryNode<K, V> right;

    public BinaryNode(K key, V value) {
        Objects.requireNonNull(key);
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BinaryNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<K, V> left) {
        this.left = left;
    }

    public BinaryNode<K, V> getRight() {
        return right;
    }

    public void setRight(BinaryNode<K, V> right) {
        this.right = right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean isLeaf() {
        return !hasLeft() && !hasRight();
    }

    @Override
    public int compareTo(BinaryNode<K, V> that) {
        return this.getKey().compareTo(that.getKey());
    }

    protected void draw(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);
        g.setColor(Color.BLACK);
        g.drawOval(x - RADIUS, y - RADIUS, DIAMETER, DIAMETER);
        g.drawString(getKey().toString(), x - DIAMETER, y + DIAMETER);
    }
}
