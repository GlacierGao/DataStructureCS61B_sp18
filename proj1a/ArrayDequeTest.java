public class ArrayDequeTest {
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("Expected = " + expected + ", but actual = " + actual + ".");
            return false;
        }
        return true;
    }

    public static void printTestingStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
        }
    }

    public static boolean checkGetInt(int expected, int actual) {
        if (expected != actual) {
            System.out.println("Expected = " + expected + ", but actual = " + actual + ".");
            return false;
        }
        return true;
    }

    public static void testAddAndRemove() {
        ArrayDeque<Integer> testSimpleAD = new ArrayDeque<>();

        // now the AD is empty
        boolean passed = checkEmpty(true, testSimpleAD.isEmpty());

        // add(First) a new element, now the AD should be not empty
        testSimpleAD.addFirst(5);
        passed = checkEmpty(false, testSimpleAD.isEmpty()) && passed;

        // remove(Last) the element, now the AD should be empty
        testSimpleAD.removeLast();
        passed = checkEmpty(true, testSimpleAD.isEmpty()) && passed;

        printTestingStatus(passed);
    }

    public static void testTestAddAndGet() {
        ArrayDeque<Integer> testArr = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            testArr.addLast(0);
        }
        boolean passed = checkGetInt(0, testArr.get(0));

        printTestingStatus(passed);
    }

    public static void testd00002() {
        ArrayDeque<Integer> testArr = new ArrayDeque<>();
        for (int i = 0; i < 24; i++) {
            testArr.addFirst(i);
        }
//        testArr.removeFirst();
        for (int i = 0; i < 18; i++) {
            testArr.removeLast();
        }
//        testArr.removeLast();
    }

    public static void main(String[] args) {
        System.out.println("Running the test...");
        testAddAndRemove();
        testTestAddAndGet();
        testd00002();
    }
}
