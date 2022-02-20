package io.bootique.jdbc.test;

import org.junit.jupiter.api.Test;

public class MatcherTest extends BasicTest{

    @Test
    public void testAssertMatches() {
        t1().matcher().assertNoMatches();

        t1().insert(1, "y", "z");
        t1().matcher().assertOneMatch();

        t1().insert(2, "a", "b");
        t1().matcher().assertMatches(2);
    }

    @Test
    public void testAssertMatches_Eq() {

        t1().insertColumns("c1", "c2", "c3")
                .values(1, "y", "z")
                .values(2, "a", "b")
                .exec();

        t1().matcher().eq("c3", "z").eq("c1", 1).assertOneMatch();
    }

    @Test
    public void testAssertMatches_Eq_Null() {

        t1().insertColumns("c1", "c2", "c3")
                .values(1, "y", "z")
                .values(2, "a", null)
                .exec();

        t1().matcher().eq("c3", null).eq("c1", 2).assertOneMatch();
    }

    @Test
    public void testAssertMatches_In() {

        t1().insertColumns("c1", "c2", "c3")
                .values(1, "y", "z")
                .values(2, "a", "b")
                .values(3, "c", "d")
                .exec();

        t1().matcher().in("c3", "z", "d").assertMatches(2);
    }
}
