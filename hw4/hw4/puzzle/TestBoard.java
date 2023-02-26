package hw4.puzzle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoard {
    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
        Board b = new Board(x);
        assertEquals("Your Board class is not being initialized with the right values.", 0, b.tileAt(0, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 1, b.tileAt(0, 1));
        assertEquals("Your Board class is not being initialized with the right values.", 2, b.tileAt(1, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 3, b.tileAt(1, 1));

        x[1][1] = 1000;
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }

    @Test
    public void testEqualsa012() {
        int r = 5;
        int c = 5;
        int[][] x = {{0, 1, 16, 22, 15},
                {18, 23, 21, 11, 9},
                {7, 17, 13, 20, 14},
                {10, 2, 24, 4, 12},
                {6, 8, 5, 19, 3}};
        Board a = new Board(x);
        assertEquals(23, a.tileAt(1, 1));
        assertEquals(13, a.tileAt(2, 2));
        assertEquals(4, a.tileAt(3, 3));
        assertEquals(0, a.tileAt(0, 0));
        assertEquals(15, a.tileAt(0, 4));
    }
}
