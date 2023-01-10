public class ArrayDequeTest {
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("Expected = " + expected + ", but actual = " + actual + ".");
            return false;
        }
        return true;
    }

    public static void printTestingStatus(boolean passed) {
        if (passed == true) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
        }
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
    }

    public static void main(String[] args) {
        System.out.println("Running the test...");
        testAddAndRemove();
        System.out.println("You have passed all the tests!!!!!! ");
    }
}
