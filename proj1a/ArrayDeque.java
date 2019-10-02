import java.util.stream.IntStream;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int isFirstEmpty; // 用于判断


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize() {
        T[] newArray = (T[]) new Object[items.length * 2];
        if (nextFirst < nextLast) {
            System.arraycopy(items, 0, newArray, 0, nextLast);
            System.arraycopy(items, nextLast, newArray, items.length + nextLast,
                    items.length - nextFirst - 1);
            nextFirst = items.length + nextFirst;
        } else {
            if (isFirstEmpty == 0) {
                // 如果first 是空的
                System.arraycopy(items, 0, newArray, 0, items.length);
                nextLast = items.length;
                nextFirst = items.length * 2 - 1;
            } else {
                System.arraycopy(items, 0, newArray, items.length, items.length);
                nextFirst = items.length - 1;
            }
        }
        items = newArray;
    }

    private void resizeDown() {
        if (items.length <= 8) {
            return;
        }
        int newLength = items.length / 4;
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, newArray,
                newLength + nextFirst - items.length + 1, items.length - nextFirst - 1);
        nextFirst = newLength + nextFirst - items.length;
        items = newArray;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            // 说明装满了
            resize();
        }
        items[nextFirst] = item;
        isFirstEmpty++;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
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
            T p = items[nextFirst];
            size--;
            isFirstEmpty--;
            if (items.length > size * 4) {
                resizeDown();
            }
            return p;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (size != 0) {
            nextLast = (nextLast - 1 + items.length) % items.length;
            T p = items[nextLast];
            size--;
            if (items.length > size * 4) {
                resizeDown();
            }
            return p;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index < size) {
            return items[(index + nextFirst + 1) % items.length];
        } else {
            return null;
        }
    }
}
