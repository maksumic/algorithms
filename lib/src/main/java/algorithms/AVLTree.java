package algorithms;

public class AVLTree<K extends Comparable<K>, V> extends BinaryTree<K, V> {

    @Override
    protected AVLNode<K, V> createNode(K key, V value) {
        return new AVLNode<>(key, value);
    }

    @Override
    protected AVLNode<K, V> insert(BinaryNode<K, V> node, K key, V value) {
        return balance((AVLNode<K, V>) super.insert(node, key, value));
    }

    @Override
    protected BinaryNode<K, V> delete(BinaryNode<K, V> node, K key) {
        return balance((AVLNode<K, V>) super.delete(node, key));
    }

    protected AVLNode<K, V> balance(AVLNode<K, V> node) {
        if (node == null) {
            return null;
        }
        node.setHeight(computeHeight(node));
        if (getBalanceFactor(node) > 1) {
            if (getBalanceFactor(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (getBalanceFactor(node) < -1) {
            if (getBalanceFactor(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    protected AVLNode<K, V> rotateLeft(AVLNode<K, V> node) {
        AVLNode<K, V> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        node.setHeight(computeHeight(node));
        temp.setHeight(computeHeight(temp));
        return temp;
    }

    protected AVLNode<K, V> rotateRight(AVLNode<K, V> node) {
        AVLNode<K, V> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        node.setHeight(computeHeight(node));
        temp.setHeight(computeHeight(temp));
        return temp;
    }


    public static <K extends Comparable<K>, V> int getBalanceFactor(AVLNode<K, V> node) {
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return leftHeight - rightHeight;
    }

    public static <K extends Comparable<K>, V> int getHeight(AVLNode<K, V> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    public static <K extends Comparable<K>, V> int computeHeight(AVLNode<K, V> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
