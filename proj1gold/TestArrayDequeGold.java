import static org.junit.Assert.*;
import org.junit.Test;

import javax.management.remote.JMXServerErrorException;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        /**
         * @source provided StudentArrayDequeLauncher.java file
         */
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.2) {
                sad1.addLast(i);
                solution.addLast(i);
                message = message.concat("addFirst(" + i + ")\n");
            } else if (numberBetweenZeroAndOne < 0.4){
                sad1.addFirst(i);
                solution.addFirst(i);
                message = message.concat("addLast(" + i + ")\n");
            } else if (numberBetweenZeroAndOne < 0.6) {
                message = message.concat("isEmpty()\n");
                assertEquals(message, solution.isEmpty(), sad1.isEmpty());
                if (!sad1.isEmpty()) {
                    message = message.concat("removeFirst(" + i + ")\n");
                    assertEquals(message, solution.removeFirst(), sad1.removeFirst());
                }
            } else if (numberBetweenZeroAndOne < 0.8) {
                message = message.concat("isEmpty()\n");
                assertEquals(message, solution.isEmpty(), sad1.isEmpty());
                if (!sad1.isEmpty()) {
                    message = message.concat("removeLast(" + i + ")\n");
                    assertEquals(message, solution.removeLast(), sad1.removeLast());
                }
            } else {
                message = message.concat("size()\n");
                assertEquals(message, solution.size(), sad1.size());
                if (solution.size() != 0) {
                    int index = StdRandom.uniform(solution.size());
                    message = message.concat("get(" + index + ")\n");
                    assertEquals(message, solution.get(index), sad1.get(index));
                }
            }
        }
    }
}
