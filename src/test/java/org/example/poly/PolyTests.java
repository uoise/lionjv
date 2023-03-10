package org.example.poly;

import org.example.poly.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolyTests {
    @Test
    public void getSplitInfo1() {
        assertEquals(6, new Polynomial("3 + 3").calc());
    }

    @Test
    public void getSplitInfo_2() {
        assertEquals(12, new Polynomial("3 * 3 + 3").calc());
    }

    @Test
    public void getSplitInfo_3() {
        assertEquals(18, new Polynomial("(3 + 3) * 3").calc());
    }

    @Test
    public void getSplitInfo_4() {
        assertEquals(18, new Polynomial("3 * (3 + 3)").calc());
    }

    @Test
    public void getSplitInfo_5() {
        assertEquals(21, new Polynomial("3 + (3 + 3) * 3").calc());
    }

    @Test
    public void getSplitInfo_6() {
        assertEquals(-18, new Polynomial("-3 - (3 - 3 * -3) - 3").calc());
    }

    @Test
    void getSplitInfo_7() {
        assertEquals(10, new Polynomial("((1 + (2 * 3) + ((4 + 2) / 2)))").calc());
    }
}