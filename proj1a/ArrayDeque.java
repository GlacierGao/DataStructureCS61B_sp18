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

    /* traverse the AD. */
//    private T[] traverseDeque() {
//        int headIndex = getHeadIndex();
//        int tailIndex = getTailIndex();
//        T[] traversedDeque = (T[]) new Object[size];
//        int indexOfTD = 0;
//        for (int i = headIndex; indexOfTD < size; i = rightIndexOfInput(i), indexOfTD++) {
//            traversedDeque[indexOfTD] = arr[i];
//        }
//        return traversedDeque;
//    }

    /* A helper method that resize the array when the oldArray is full
    while we need to add new items to it. */
    private T[] resizeArray() {
        T[] resizedArray = (T[]) new Object[arr.length + 8];
//        T[] traversedArray = traverseDeque();
//        System.arraycopy(traversedArray, 0, resizedArray, 0, traversedArray.length);
//        return resizedArray;
        int headIndex = getHeadIndex();
        int tailIndex = getTailIndex();
        int indexOfTD = 0;
        for (int i = headIndex; indexOfTD < size; i = rightIndexOfInput(i), indexOfTD++) {
            resizedArray[indexOfTD] = arr[i];
        }
        return resizedArray;
    }

    /* an abandoned method.
    @param:
    T[] oldArray: the array needed to be resized
    int leftBoundIndex: the author defines that the new spaces will be
                        inserted between the leftBoundIndex and the
                        rightBoundIndex
    finally the method returns the array which is resized successfully.
     */
//    private void resizeArrayHelper(int leftBoundIndex) {
//        int rightBoundIndex = rightIndexOfInput(leftBoundIndex);
//        T[] resizedArray = (T[]) new Object[arr.length + 8];
//        System.arraycopy(arr, 0, resizedArray, 0, leftBoundIndex + 1);
//        System.arraycopy(arr, rightBoundIndex, resizedArray, leftBoundIndex + 1 + 8, arr.length - (leftBoundIndex + 1));
//        arr = resizedArray;
//    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        // If the array is full, add 8 spaces before the existing array.
        arr[nextFirst] = item;
//        nextFirst = leftIndexOfInput(nextFirst);
        size++;
        nextFirst = leftIndexOfInput(nextFirst);
        if (size == arr.length) {
            arr = resizeArray();
            nextFirst = arr.length - 1;
            nextLast = size;
        }
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        // If the array is full, add 8 spaces before the existing array.
        arr[nextLast] = item;
        size++;
        nextLast = rightIndexOfInput(nextLast);
        if (size == arr.length) {
            arr = resizeArray();
            nextFirst = arr.length - 1;
            nextLast = size;
        }
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
//        T[] printArr = traverseDeque();
//        for (int i = 0; i < printArr.length; i++) {
//            System.out.print(printArr[i] + " ");
//        }
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
//        int headIndex = getHeadIndex();
//        int tailIndex = getTailIndex();
//        int indexOfTD = 0;
//        for (int i = headIndex; indexOfTD < size; i = rightIndexOfInput(i), indexOfTD++) {
//            newArray[indexOfTD] = arr[i];
//        }
//        arr = newArray;
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
        // ?????????????????????first?????????arr[0]
        nextFirst = rightIndexOfInput(nextFirst);// ?????? ???????????????????????????/?????????????????? ????????????????????????????????????return null????????????????????????
        size--;
        T itemToBeReturned = arr[nextFirst];
//        arr[nextFirst] = null;
        if (arr.length > 16 && arr.length / size > 4) {
            // !!!! size / arr.length < 0.25 is always true because of the types of size and arr.length! Damn it
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
//        arr[nextLast] = null; // Deleting this piece of shit oh code instead to meet the checking of the
        // gradescope though I don't know why. It bothered me for entire 2 days!!
        if (arr.length > 16 && arr.length / size > 4) {
            resizeDequeAfterRemoval();
        }
        return itemToBeReturned;
    }

    /* Gets the item at the given index, where 0
    is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not
    alter the deque! */
    public T get(int index) {
        /* ??????????????????????????????print??????????????????????????? ->
        ??????????????????????????????????????????head + index??????????????????> length)
        ?????????arr[index??]??????????????????
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
