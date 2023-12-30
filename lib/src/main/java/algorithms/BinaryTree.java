package algorithms;

import java.awt.Graphics;
import java.util.Objects;
import javax.swing.JPanel;

public class BinaryTree<K extends Comparable<K>, V> extends JPanel {
    protected static final int VERTICAL_GAP = 32;
    protected BinaryNode<K, V> root = null;
    protected int size;

    public V get(K key) {
        BinaryNode<K, V> node = get(root, key);
        return node == null ? null : node.getValue();
    }

    public void insert(K key, V value) {
        Objects.requireNonNull(key);
        root = insert(root, key, value);
    }

    public void delete(K key) {
        Objects.requireNonNull(key);
        root = delete(root, key);
    }

    protected BinaryNode<K, V> get(BinaryNode<K, V> node, K key) {
        if (node == null) {
            return null;
        } else if (Objects.compare(key, node.getKey(), K::compareTo) < 0) {
            return get(node.getLeft(), key);
        } else if (Objects.compare(key, node.getKey(), K::compareTo) > 0) {
            return get(node.getRight(), key);
        } else {
            return node;
        }
    }

    protected BinaryNode<K, V> insert(BinaryNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return createNode(key, value);
        } else if (Objects.compare(key, node.getKey(), K::compareTo) < 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        } else if (Objects.compare(key, node.getKey(), K::compareTo) > 0) {
            node.setRight(insert(node.getRight(), key, value));
        } else {
            node.setValue(value);
        }
        return node;
    }

    protected BinaryNode<K, V> delete(BinaryNode<K, V> node, K key) {
        if (node == null) {
            return null;
        } else if (Objects.compare(key, node.getKey(), K::compareTo) < 0) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (Objects.compare(key, node.getKey(), K::compareTo) > 0) {
            node.setRight(delete(node.getRight(), key));
        } else {
            if (node.isLeaf()) {
                size--;
                return null;
            }
            if (!node.hasLeft()) {
                size--;
                return node.getRight();
            }
            if (!node.hasRight()) {
                size--;
                return node.getLeft();
            }
            BinaryNode<K, V> successor = findMin(node.getRight());
            successor.setRight(delete(node.getRight(), successor.getKey()));
            successor.setLeft(node.getLeft());
            return successor;
        }
        return node;
    }

    protected BinaryNode<K, V> findMin(BinaryNode<K, V> node) {
        if (!node.hasLeft()) {
            return node;
        }
        return findMin(node.getLeft());
    }

    protected BinaryNode<K, V> createNode(K key, V value) {
        return new BinaryNode<>(key, value);
    }

    protected BinaryNode<K, V> getRoot() {
        return root;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, root, getWidth() / 2, VERTICAL_GAP, getWidth() / 4);
    }

    protected void draw(Graphics g, BinaryNode<K, V> node, int x, int y, int horizontalGap) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null) {
            g.drawLine(x, y, x - horizontalGap, y + VERTICAL_GAP);
            draw(g, node.getLeft(), x - horizontalGap, y + VERTICAL_GAP, horizontalGap / 2);
        }
        if (node.getRight() != null) {
            g.drawLine(x, y, x + horizontalGap, y + VERTICAL_GAP);
            draw(g, node.getRight(), x + horizontalGap, y + VERTICAL_GAP, horizontalGap / 2);
        }
        node.draw(g, x, y);
    }
}
