package algorithms.trees;

public class AVLNode<K extends Comparable<K>, V> extends BinaryNode<K, V> {
    protected int height;

    protected AVLNode(K key, V value) {
        super(key, value);
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public AVLNode<K, V> getLeft() {
        return (AVLNode<K, V>) super.getLeft();
    }

    @Override
    public AVLNode<K, V> getRight() {
        return (AVLNode<K, V>) super.getRight();
    }
}
