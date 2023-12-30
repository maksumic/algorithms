package algorithms;

import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@Warmup(iterations = 9, time = 1)
@Measurement(iterations = 9, time = 1)
public class AVLTreeBenchmark {
    @Param({"10000", "100000", "1000000"})
    private int size;
    private final AVLTree<Integer, Long> tree = new AVLTree<>();
    private int[] randomNumbers;

    @Setup
    public void setup() {
        Random random = new Random(42);
        randomNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            randomNumbers[i] = random.nextInt();
        }
    }

    @Benchmark
    public void benchmarkInsert(Blackhole blackhole) {
        for (int rn : randomNumbers) {
            AVLNode<Integer, Long> root = tree.insert(tree.root, rn, rn * 2L);
            blackhole.consume(root);
        }
    }
}
