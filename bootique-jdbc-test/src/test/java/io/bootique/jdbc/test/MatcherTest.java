package io.bootique.jdbc.test;

import io.bootique.jdbc.test.matcher.TableMatcher;
import org.junit.Test;

public class MatcherTest extends BasicTest {

    @Test
    public void testAssertMatches() {
        TableMatcher matcher = new TableMatcher(T1);
        matcher.assertMatches(0);

        T1.insert(1, "y", "z");
        matcher.assertMatches(1);

        T1.insert(2, "a", "b");
        matcher.assertMatches(2);
    }

    @Test
    public void testAssertMatches_Eq() {
        TableMatcher matcher = new TableMatcher(T1);

        T1.insert(1, "y", "z");
        T1.insert(2, "a", "b");
        matcher.eq("c3", "z").eq("c1", 1).assertMatches(1);
    }

    @Test
    public void testAssertMatches_Eq_Null() {
        TableMatcher matcher = new TableMatcher(T1);

        T1.insert(1, "y", "z");
        T1.insert(2, "a", null);
        matcher.eq("c3", null).eq("c1", 2).assertMatches(1);
    }

    @Test
    public void testAssertMatches_In() {
        TableMatcher matcher = new TableMatcher(T1);

        T1.insert(1, "y", "z");
        T1.insert(2, "a", "b");
        T1.insert(3, "c", "d");

        matcher.in("c3", "z", "d").assertMatches(2);
    }


}
