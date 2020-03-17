package io.bootique.jdbc.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestGet extends BasicTest {

    @Test
    public void testGetString() {
        assertNull(T1.getString("c2"));
        T1.insert(1, "xr", "yr");
        assertEquals("xr", T1.getString("c2"));
    }

    @Test
    public void testGetInt() {
        assertEquals(0, T1.getInt("c1"));
        T1.insert(56, "xr", "yr");
        assertEquals(56, T1.getInt("c1"));
    }
}
