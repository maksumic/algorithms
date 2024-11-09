package algorithms.trees;

import org.junit.jupiter.api.Test;

import static algorithms.trees.AVLTree.computeHeight;
import static algorithms.trees.AVLTree.getBalanceFactor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AVLTreeTest {
    @Test
    public void testInsert() {
        AVLTree<Integer, Byte[]> tree = new AVLTree<>();
        tree.insert(1, new Byte[]{});
        tree.insert(2, new Byte[]{});
        tree.insert(3, new Byte[]{});
        tree.insert(6, new Byte[]{});
        tree.insert(4, new Byte[]{});
        tree.insert(5, new Byte[]{});
        tree.insert(7, new Byte[]{});
        tree.insert(9, new Byte[]{});
        tree.insert(8, new Byte[]{});

        AVLNode<Integer, Byte[]> n004 = (AVLNode<Integer, Byte[]>) tree.getRoot();
        AVLNode<Integer, Byte[]> n002 = n004.getLeft();
        AVLNode<Integer, Byte[]> n006 = n004.getRight();
        AVLNode<Integer, Byte[]> n001 = n002.getLeft();
        AVLNode<Integer, Byte[]> n003 = n002.getRight();
        AVLNode<Integer, Byte[]> n005 = n006.getLeft();
        AVLNode<Integer, Byte[]> n008 = n006.getRight();
        AVLNode<Integer, Byte[]> n007 = n008.getLeft();
        AVLNode<Integer, Byte[]> n009 = n008.getRight();

        assertEquals(1, n001.getKey());
        assertEquals(2, n002.getKey());
        assertEquals(3, n003.getKey());
        assertEquals(4, n004.getKey());
        assertEquals(5, n005.getKey());
        assertEquals(6, n006.getKey());
        assertEquals(7, n007.getKey());
        assertEquals(8, n008.getKey());
        assertEquals(9, n009.getKey());
    }

    @Test
    public void testDeleteRoot() {
        AVLTree<Integer, Byte[]> tree = new AVLTree<>();
        tree.insert(1, new Byte[]{});
        tree.insert(2, new Byte[]{});
        tree.insert(3, new Byte[]{});
        tree.insert(6, new Byte[]{});
        tree.insert(4, new Byte[]{});
        tree.insert(5, new Byte[]{});
        tree.insert(7, new Byte[]{});
        tree.insert(9, new Byte[]{});
        tree.insert(8, new Byte[]{});
        tree.delete(4);
        assertEquals(5, tree.getRoot().getKey());
        assertEquals(2, tree.getRoot().getLeft().getKey());
        assertEquals(8, tree.getRoot().getRight().getKey());
        assertEquals(1, tree.getRoot().getLeft().getLeft().getKey());
        assertEquals(3, tree.getRoot().getLeft().getRight().getKey());
        assertEquals(6, tree.getRoot().getRight().getLeft().getKey());
        assertEquals(9, tree.getRoot().getRight().getRight().getKey());
        assertEquals(7, tree.getRoot().getRight().getLeft().getRight().getKey());
        assertNull(tree.getRoot().getRight().getLeft().getLeft());
    }

    @Test
    public void testRepeatDeleteRoot() {
        AVLTree<Integer, Byte[]> tree = new AVLTree<>();
        tree.insert(1, new Byte[]{});
        tree.insert(2, new Byte[]{});
        tree.insert(3, new Byte[]{});
        tree.insert(4, new Byte[]{});
        tree.insert(5, new Byte[]{});
        tree.insert(6, new Byte[]{});
        tree.insert(7, new Byte[]{});
        tree.insert(8, new Byte[]{});
        tree.insert(9, new Byte[]{});
        assertEquals(4, tree.getRoot().getKey());
        tree.delete(4);
        assertEquals(5, tree.getRoot().getKey());
        tree.delete(5);
        assertEquals(6, tree.getRoot().getKey());
        tree.delete(6);
        assertEquals(7, tree.getRoot().getKey());
        tree.delete(7);
        assertEquals(8, tree.getRoot().getKey());
        tree.delete(8);
        assertEquals(2, tree.getRoot().getKey());
        tree.delete(2);
        assertEquals(3, tree.getRoot().getKey());
        tree.delete(3);
        assertEquals(9, tree.getRoot().getKey());
        tree.delete(9);
        assertEquals(1, tree.getRoot().getKey());
        tree.delete(1);
        assertNull(tree.getRoot());
    }

    @Test
    public void testRotateLeft() {
        AVLTree<String, Byte[]> tree = new AVLTree<>();
        AVLNode<String, Byte[]> x = new AVLNode<>("X", new Byte[]{});
        AVLNode<String, Byte[]> z = new AVLNode<>("Z", new Byte[]{});
        AVLNode<String, Byte[]> t1 = new AVLNode<>("t1", new Byte[]{});
        AVLNode<String, Byte[]> t23 = new AVLNode<>("t23", new Byte[]{});
        AVLNode<String, Byte[]> t4 = new AVLNode<>("t4", new Byte[]{});

        // Create tree structure
        x.setLeft(t1);
        x.setRight(z);
        z.setLeft(t23);
        z.setRight(t4);

        // Rotate left
        tree.rotateLeft(x);

        // Assert new structure
        assertEquals(x, z.getLeft());
        assertEquals(t4, z.getRight());
        assertEquals(t1, x.getLeft());
        assertEquals(t23, x.getRight());
        assertNull(t1.getLeft());
        assertNull(t1.getRight());
        assertNull(t23.getLeft());
        assertNull(t23.getRight());
        assertNull(t4.getLeft());
        assertNull(t4.getRight());

        // Assert heights
        assertEquals(0, computeHeight(t1));
        assertEquals(0, computeHeight(t23));
        assertEquals(0, computeHeight(t4));
        assertEquals(1, computeHeight(x));
        assertEquals(2, computeHeight(z));

        // Assert balance factor
        assertEquals(0, getBalanceFactor(t1));
        assertEquals(0, getBalanceFactor(t23));
        assertEquals(0, getBalanceFactor(t4));
        assertEquals(0, getBalanceFactor(x));
        assertEquals(1, getBalanceFactor(z));
    }

    @Test
    public void testRotateRight() {
        AVLTree<String, Byte[]> tree = new AVLTree<>();
        AVLNode<String, Byte[]> x = new AVLNode<>("X", new Byte[]{});
        AVLNode<String, Byte[]> z = new AVLNode<>("Z", new Byte[]{});
        AVLNode<String, Byte[]> t1 = new AVLNode<>("t1", new Byte[]{});
        AVLNode<String, Byte[]> t23 = new AVLNode<>("t23", new Byte[]{});
        AVLNode<String, Byte[]> t4 = new AVLNode<>("t4", new Byte[]{});

        // Create tree structure
        x.setLeft(z);
        x.setRight(t4);
        z.setLeft(t1);
        z.setRight(t23);

        // Rotate left
        tree.rotateRight(x);

        // Assert new structure
        assertEquals(t1, z.getLeft());
        assertEquals(x, z.getRight());
        assertEquals(t23, x.getLeft());
        assertEquals(t4, x.getRight());
        assertNull(t1.getLeft());
        assertNull(t1.getRight());
        assertNull(t23.getLeft());
        assertNull(t23.getRight());
        assertNull(t4.getLeft());
        assertNull(t4.getRight());

        // Assert heights
        assertEquals(0, computeHeight(t1));
        assertEquals(0, computeHeight(t23));
        assertEquals(0, computeHeight(t4));
        assertEquals(1, computeHeight(x));
        assertEquals(2, computeHeight(z));

        // Assert balance factor
        assertEquals(0, getBalanceFactor(t1));
        assertEquals(0, getBalanceFactor(t23));
        assertEquals(0, getBalanceFactor(t4));
        assertEquals(0, getBalanceFactor(x));
        assertEquals(-1, getBalanceFactor(z));
    }
}
