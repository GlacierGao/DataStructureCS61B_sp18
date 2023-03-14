public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i<word.length(); i++) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dequeWord = wordToDeque(word);
        return isPalindromeHelper(dequeWord);
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        int itemNum = d.size();
        if (itemNum == 0 || itemNum == 1) {
            return true;
        }
        if (d.removeFirst().equals(d.removeLast())) {
            return isPalindromeHelper(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isOBOHelper(d, cc);
    }

    private boolean isOBOHelper(Deque<Character> deque, CharacterComparator cc) {
        int n = deque.size();
        if (n == 0 || n == 1) {
            return true;
        }
        if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return isOBOHelper(deque, cc);
        }
        return false;
    }
}
