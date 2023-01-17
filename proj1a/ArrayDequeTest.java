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

    public static boolean checkGetDouble(Double expected, Double actual) {
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
        for (int i = 1; i <= 9; i++) {
            testArr.addFirst(i);
        }
        testArr.removeFirst();
        int check = testArr.removeLast();
        boolean passed = checkGetInt(1, check);
        printTestingStatus(passed);
//        for (int i = 0; i < 18; i++) {
//            testArr.removeLast();
//        }
    }

    public static void testaFrFiE() {
        ArrayDeque<Double> arr = new ArrayDeque<>();
        boolean passed = checkEmpty(true, arr.isEmpty());
        arr.addFirst(0.0);
        passed = checkEmpty(false, arr.isEmpty()) && passed;
        arr.removeFirst();
        arr.addFirst(0.1);
        arr.addFirst(0.0);
        arr.addFirst(0.1);
        arr.addFirst(0.0);
        arr.addFirst(0.8);
        arr.addFirst(0.8);
        arr.addFirst(0.8);
        arr.removeFirst();
        arr.removeFirst();
        passed = checkEmpty(false, arr.isEmpty()) && passed;
        printTestingStatus(passed);
    }

    public static void d008() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        for (int i = 1; i <= 100; i++) {
            arr.addFirst(i);
        }
        boolean passed = checkGetInt(100, arr.removeFirst());
        arr.addFirst(101);
        printTestingStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running the test...");
//        testAddAndRemove();
//        testTestAddAndGet();
//        testd00002();
//        testaFrFiE();
        d008();
    }
}
