
public class LinkedListDeque<T> {

    private  LinkNode sentinel;
    private int size;
    private LinkNode last;


    private class LinkNode {
        private T item;
        private LinkNode next;
        private LinkNode prev;

        public LinkNode(T value, LinkNode n, LinkNode p) {
            item = value;
            next = n;
            prev = p;
        }

        public LinkNode() {
            next = null;
            prev = null;
        }

        public T get(int index) {
            if (index == 0) {
                return item;
            } else {
                if (next != null) {
                    return next.get(index - 1);
                } else {
                    return null;
                }
            }
        }
    }

    public LinkedListDeque() {
        sentinel = new LinkNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        last = sentinel;
    }


    public void addFirst(T item) {
        LinkNode p = new LinkNode(item, sentinel.next, sentinel);
        p.next = sentinel.next;
        sentinel.next = p;
        p.prev = sentinel;
        p.next.prev = p;
        if (isEmpty()) {
            last = p;
            sentinel.prev = last;
        }
        ++size;
    }

    public void addLast(T item) {
        LinkNode p = new LinkNode(item, sentinel, last);
        last.next = p;
        p.prev = last;
        p.next = sentinel;
        sentinel.prev = p;
        last = p;
        if (isEmpty()) {
            sentinel.next = p;
        }
        ++size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        LinkNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T p = sentinel.next.item;
        sentinel.next.prev = null;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev.next = null;
        sentinel.next.prev = sentinel;
        --size;
        return p;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T p = last.item;
        last.next = null;
        last = last.prev;
        last.next.prev = null;
        last.next = sentinel;
        sentinel.prev = last;
        --size;
        return p;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            LinkNode p = sentinel;
            while (index > 0) {
                p = p.next;
                --index;
            }
            return p.next.item;
        }
    }

    public T getRecursive(int index) {
        return sentinel.next.get(index);
    }


}
