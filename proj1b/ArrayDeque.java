import java.util.stream.IntStream;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int isFirstEmpty; // 用于判断

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize() {
        int newLength = 0;
        if (size == items.length) {
            newLength = items.length * 2;
        } else {
            if (items.length <= 8) {
                return;
            }
            newLength = items.length / 4;
        }
        Item[] newArray = (Item[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        nextLast = size;
        nextFirst = newLength - 1;
        items = newArray;
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            // 说明装满了
            resize();
        }
        items[nextFirst] = item;
        isFirstEmpty++;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size++;
    }

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            // 说明装满了
            resize();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntStream.range(nextFirst + 1, items.length).forEach(x -> System.out.print(items[x] + " "));
        IntStream.range(0, nextLast).forEach(x -> System.out.print(items[x] + " "));
    }

    @Override
    public Item removeFirst() {
        if (size != 0) {
            // 如果还有元素
            nextFirst = (nextFirst + 1) % items.length;
            Item p = items[nextFirst];
            size--;
            isFirstEmpty--;
            if (items.length > size * 4) {
                resize();
            }
            return p;
        } else {
            return null;
        }
    }

    @Override
    public Item removeLast() {
        if (size != 0) {
            nextLast = (nextLast - 1 + items.length) % items.length;
            Item p = items[nextLast];
            size--;
            if (items.length > size * 4) {
                resize();
            }
            return p;
        } else {
            return null;
        }
    }

    @Override
    public Item get(int index) {
        if (index < size) {
            return items[(index + nextFirst + 1) % items.length];
        } else {
            return null;
        }
    }
}
