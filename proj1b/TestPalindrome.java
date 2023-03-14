import com.sun.source.tree.AssertTree;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } // Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("hah"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testOBO() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("cad", obo));
        assertTrue(palindrome.isPalindrome("FE", obo));
        assertTrue(palindrome.isPalindrome("DaC", obo));
        assertTrue(palindrome.isPalindrome("&a%", obo));
        assertFalse(palindrome.isPalindrome("cccd", obo));
    }
}
