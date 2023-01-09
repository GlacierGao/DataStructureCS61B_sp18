public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    private class TNode {
        public TNode front;
        public T item;
        public TNode next;

        public TNode(TNode nFront, T i, TNode nNext) {
            item = i;
            front = nFront;
            next = nNext;
        }

//        public TNode(TNode nFront, TNode nNext) {
//            front = nFront;
//            next = nNext;
//        }
    }

    /* Creates an empty linked list deque. */
    public LinkedListDeque() {
        // sentinel = new TNode(sentinel, null, sentinel); 不行！sentinel还没定义，引用的话只有null
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.front = sentinel;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        TNode n = new TNode(sentinel, item, sentinel.next);
        sentinel.next = n;
        n.next.front = n;
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        TNode n = new TNode(sentinel.front, item, sentinel);
        sentinel.front = n;
        n.front.next = n;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (sentinel.next == sentinel ||
                sentinel.front == sentinel) return true;
        else return false;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to
    last, separated by a space. */
    public void printDeque() {
        TNode first = sentinel.next;
        while (first != sentinel) { // while (first.next != sentinel) 错
            System.out.print(first.item + " ");
            first = first.next;
        }
    }

    /* Removes and returns the item at the front
    of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        sentinel.next = sentinel.next.next;
        T value = sentinel.next.front.item;
        sentinel.next.front.next = null;
        sentinel.next.front.front = null;
        sentinel.next.front = sentinel;
        size--;
        return value;
    }

    /* Removes and returns the item at the back of the
    deque. If no such item exists, returns null. */
    public T removeLast() {
        sentinel.front = sentinel.front.front;
        T value = sentinel.front.next.item;
        sentinel.front.next.next = null;
        sentinel.front.next.front = null;
        sentinel.front.next = sentinel;
        size--;
        return value;
    }

    /* Gets the item at the given index, where 0
    is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not
    alter the deque! */
    public T get(int index) {
        TNode first = sentinel.next;
        while (index > 0 && first.next != sentinel) {
            first = first.next;
            index--;
        }
        if (index == 0) return first.item;
        else return null;
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) return null;
        else return getRecursiveHelper(index, sentinel.next);
    }

    /* Helper method of the getRecursive() */
    private T getRecursiveHelper(int index, TNode startNodeOfRestList) {
//        TNode first = sentinel.next;  first不对
        if (index == 0) return startNodeOfRestList.item;
        else return getRecursiveHelper(index - 1, startNodeOfRestList.next);

    }


}
