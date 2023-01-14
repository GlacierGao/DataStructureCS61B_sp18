public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /* A helper method that resize the array when the oldArray is full
    while we need to add new items to it.
    @param:
    T[] oldArray: the array needed to be resized
    int leftBoundIndex: the author defines that the new spaces will be
                        inserted between the leftBoundIndex and the
                        rightBoundIndex
    finally the method returns the array which is resized successfully.
     */
    private void resizeArrayHelper(int leftBoundIndex) {
        int rightBoundIndex = leftBoundIndex + 1;
        T[] resizedArray = (T[]) new Object[arr.length + 8];
        System.arraycopy(arr, 0, resizedArray, 0, leftBoundIndex + 1);
        System.arraycopy(arr, rightBoundIndex, resizedArray, rightBoundIndex + 8, arr.length - rightBoundIndex);
        arr = resizedArray;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        // If the array is full, add 8 spaces before the existing array.
        if (size == arr.length) {
            resizeArrayHelper(nextFirst);
            nextFirst += 8;
        }
        arr[nextFirst] = item;
        nextFirst = leftIndexOfInput(nextFirst);
        size++;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        // If the array is full, add 8 spaces before the existing array.
        if (size == arr.length) {
            resizeArrayHelper(nextFirst);
            nextFirst += 8;
        }
        arr[nextLast] = item;
        nextLast = rightIndexOfInput(nextLast);
        size++;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    private int rightIndexOfInput(int index) {
        index++;
        if (index > arr.length - 1) index = 0;
        return index;
    }

    private int leftIndexOfInput(int index) {
        index--;
        if (index < 0) index = arr.length - 1;
        return index;
    }

    private int getHeadIndex() {
        return rightIndexOfInput(nextFirst);
    }

    private int getTailIndex() {
        return leftIndexOfInput(nextLast);
    }

    /**
     * Return true if the Deque have a turning
     */
    private boolean judgeWhetherTurning() {
        int headIndex = getHeadIndex();
        int tailIndex = getTailIndex();
        return headIndex >= tailIndex;
    }

    /* Prints the items in the deque from first to
    last, separated by a space. */
    public void printDeque() {
        int headIndex = getHeadIndex();
        int tailIndex = getTailIndex();
        T[] printArr = (T[]) new Object[arr.length];
        if (judgeWhetherTurning()) {
            System.arraycopy(arr, headIndex, printArr, 0, arr.length - headIndex);
            System.arraycopy(arr, 0, printArr, arr.length - headIndex, headIndex);
        } else {
            printArr = arr;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(printArr[i] + " ");
        }
    }

    private void remove8SpareSpace(int startSpareSpaceIndex) {
        T[] newArray = (T[]) new Object[arr.length - 8];
        System.arraycopy(arr, 0, newArray, 0, startSpareSpaceIndex);
        System.arraycopy(arr, startSpareSpaceIndex + 8, newArray,
                startSpareSpaceIndex, arr.length - startSpareSpaceIndex - 8);
        arr = newArray;
    }

    private void resizeDequeAfterRemoval() {
        /* if the length of array is more than 16 and it has many spare spaces,
         remove 8 spare spaces which are located after the tail of before the
         front/head of the Deque. The decision of determining the location is
         depends on if the amount of spare spaces is more than 8.
         Note that there is no spare space between any two existing elements of
         the Deque; the amount of spare spaces must more than 8 since the length
         of the array is more than 16 if the Deque meets the condition that the
         rate of usage must be more than 25%.
         */
        if (!judgeWhetherTurning()) {
            if (arr.length - nextLast >= 8) {
                remove8SpareSpace(nextLast);
            } else {
                remove8SpareSpace(nextFirst + 1 - 8);
                nextFirst -= 8;
                nextLast -= 8;
                if (nextFirst < 0) nextFirst = arr.length - 1;
            }
        } else {
            remove8SpareSpace(nextLast);
            nextFirst -= 8;
        }
    }

    /* Removes and returns the item at the front
    of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) return null;
        // 去头即可，头是first，不是arr[0]
        nextFirst = rightIndexOfInput(nextFirst);// 不对 左边要是已经被删了/空的怎么办？ 清内存要再想；什么情况下return null？除了空的时候。
        size--;
        T itemToBeReturned = arr[nextFirst];
        arr[nextFirst] = null;
        if (arr.length > 16 && size / arr.length < 0.25) {
            resizeDequeAfterRemoval();
        }
        return itemToBeReturned;
    }

    /* Removes and returns the item at the back of the
    deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) return null;
        nextLast = leftIndexOfInput(nextLast);
        size--;
        T itemToBeReturned = arr[nextLast];
        arr[nextLast] = null;
        if (arr.length > 16 && size / arr.length < 0.25) {
            resizeDequeAfterRemoval();
        }
        return itemToBeReturned;
    }

    /* Gets the item at the given index, where 0
    is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not
    alter the deque! */
    public T get(int index) {
        /* 根据正逆，遍历，参考print，同时计数？再想想 ->
        ①不需要判断正逆——改为判断head + index是否超范围（> length)
        ②借助arr[index??]，不需要遍历
        */
        int indexInActualArray = getHeadIndex() + index;
        if (indexInActualArray < arr.length) return arr[indexInActualArray];
        else return arr[indexInActualArray - arr.length];
//        if (!judgeWhetherTurning()) {
//            return arr[indexInActualArray];
//        } else {
//            if (indexInActualArray < arr.length) return arr[indexInActualArray];
//            else return arr[indexInActualArray - arr.length];
//        }
    }
}
