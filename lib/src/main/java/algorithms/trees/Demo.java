package algorithms.trees;

import javax.swing.*;
import java.util.function.Consumer;

class Demo {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int SLEEP_MS = 512;

    public static void main(String[] args) {
        BinaryTree<Character, Byte[]> tree = new AVLTree<>();
        JFrame frame = new JFrame("Demo");
        frame.setSize(1024, 256);
        frame.add(tree);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Consumer<Character> put = (Character key) -> tree.insert(key, new Byte[]{});
        Consumer<Character> remove = tree::delete;
        for (char ch : ALPHABET.toCharArray()) {
            process(frame, put, ch);
        }
        for (char ch : ALPHABET.toCharArray()) {
            process(frame, remove, ch);
        }
    }

    public static <K> void process(JFrame frame, Consumer<K> operation, K key) {
        try {
            operation.accept(key);
            frame.repaint();
            Thread.sleep(SLEEP_MS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
