import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;
    int isFirstEmpty; // 用于判断
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        T[] items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, items.length);
        nextFirst = other.nextFirst;
        size = other.size;
        nextLast = other.nextLast;
        isFirstEmpty = other.isFirstEmpty;
    }

    public void resize() {
        T[] newArray = (T[]) new Object[items.length * 2];
        if (nextFirst < nextLast) {
            System.arraycopy(items, 0, newArray, 0, nextLast);
            System.arraycopy(items, nextLast, newArray, items.length + nextLast, items.length - nextFirst);
            nextFirst = items.length + nextFirst;
        } else {
            if (isFirstEmpty == 0) {
                // 如果first 是空的
                System.arraycopy(items, 0, newArray, 0, items.length);
                nextLast += items.length;
            } else {
                System.arraycopy(items, 0, newArray, items.length, items.length);
                nextFirst = items.length;
            }
        }
        items = newArray;
    }

    public void addFirst(T item) {
        if((nextLast - nextFirst + items.length) % items.length == 1 && size != 0) {
            // 说明装满了
            resize();
        }
        items[nextFirst] = item;
        isFirstEmpty++;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size++;
    }

    public void addLast(T item) {
        if((nextLast - nextFirst + items.length) % items.length == 1 && size != 0) {
            // 说明装满了
            resize();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntStream.range(nextFirst + 1, items.length).forEach(x -> System.out.print(items[x] + " "));
        IntStream.range(0, nextLast).forEach(x -> System.out.print(items[x] + " "));
    }

    public T removeFirst() {
        if (size != 0) {
            // 如果还有元素
            nextFirst = (nextFirst + 1) % items.length;
            size--;
            isFirstEmpty--;
            return items[nextFirst];
        }else {
            return null;
        }
    }

    public T removeLast() {
        if (size != 0) {
            nextLast = (nextLast - 1 + items.length) % items.length;
            size--;
            return items[nextLast];
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (size == items.length) {
            // 如果已经满了
            return items[(index + nextFirst + 1) % items.length];
        } else {
            if (index <= nextLast && index >= nextFirst) {
                return null;
            } else {
                return items[(index + nextFirst + 1) % items.length];
            }
        }
    }


}
