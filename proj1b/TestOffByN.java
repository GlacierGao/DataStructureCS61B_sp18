import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testOBN() {
        OffByN obn7 = new OffByN(7);
        assertTrue(palindrome.isPalindrome("bibi", obn7));
    }
}
