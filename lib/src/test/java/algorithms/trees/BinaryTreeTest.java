package algorithms.trees;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinaryTreeTest {
    @Test
    public void testGet() {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.insert(1, "One");
        tree.insert(2, "Two");
        tree.insert(3, "Three");
        tree.insert(4, "Four");
        tree.insert(5, "Five");
        tree.insert(6, "Six");
        tree.insert(7, "Seven");
        tree.insert(8, "Eight");
        tree.insert(9, "Nine");
        assertEquals("One", tree.get(1));
        assertEquals("Two", tree.get(2));
        assertEquals("Three", tree.get(3));
        assertEquals("Four", tree.get(4));
        assertEquals("Five", tree.get(5));
        assertEquals("Six", tree.get(6));
        assertEquals("Seven", tree.get(7));
        assertEquals("Eight", tree.get(8));
        assertEquals("Nine", tree.get(9));
        assertNull(tree.get(0));
        assertNull(tree.get(10));
    }

    @Test
    public void testInsert() {
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        List<Integer> integers = List.of(1, 2, 3, 6, 4, 5, 7, 9, 8);
        for (int i : integers) {
            tree.insert(i, i * i);
        }

        // Get nodes
        BinaryNode<Integer, Integer> n1 = tree.getRoot();
        BinaryNode<Integer, Integer> n2 = n1.getRight();
        BinaryNode<Integer, Integer> n3 = n2.getRight();
        BinaryNode<Integer, Integer> n6 = n3.getRight();
        BinaryNode<Integer, Integer> n4 = n6.getLeft();
        BinaryNode<Integer, Integer> n7 = n6.getRight();
        BinaryNode<Integer, Integer> n5 = n4.getRight();
        BinaryNode<Integer, Integer> n9 = n7.getRight();
        BinaryNode<Integer, Integer> n8 = n9.getLeft();

        // Assert keys
        assertEquals(1, n1.getKey());
        assertEquals(2, n2.getKey());
        assertEquals(3, n3.getKey());
        assertEquals(4, n4.getKey());
        assertEquals(5, n5.getKey());
        assertEquals(6, n6.getKey());
        assertEquals(7, n7.getKey());
        assertEquals(8, n8.getKey());
        assertEquals(9, n9.getKey());

        // Assert null children
        assertNull(n1.getLeft());
        assertNull(n2.getLeft());
        assertNull(n3.getLeft());
        assertNull(n4.getLeft());
        assertNull(n5.getLeft());
        assertNull(n5.getRight());
        assertNull(n7.getLeft());
        assertNull(n8.getLeft());
        assertNull(n8.getRight());
        assertNull(n9.getRight());

        // Size
        assertEquals(9, tree.size);
    }

    @Test
    public void testDelete() {
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        List<Integer> integers = List.of(6, 4, 5, 7, 9, 8);
        for (int i : integers) {
            tree.insert(i, i * i);
        }
        assertEquals(6, tree.size);
        tree.delete(7);
        assertEquals(5, tree.size);
        assertEquals(6, tree.getRoot().getKey());
        assertEquals(9, tree.getRoot().getRight().getKey());
        assertEquals(4, tree.getRoot().getLeft().getKey());
        assertEquals(5, tree.getRoot().getLeft().getRight().getKey());
        assertEquals(8, tree.getRoot().getRight().getLeft().getKey());
        tree.delete(6);
        assertEquals(4, tree.size);
        assertEquals(8, tree.getRoot().getKey());
        assertEquals(4, tree.getRoot().getLeft().getKey());
        assertEquals(9, tree.getRoot().getRight().getKey());
        assertEquals(5, tree.getRoot().getLeft().getRight().getKey());
        assertNull(tree.getRoot().getLeft().getLeft());
        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());
    }
}
