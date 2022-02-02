package io.bootique.jdbc.test;

import org.junit.jupiter.api.Test;

import java.sql.Types;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertSelectUpdateTest extends BasicTest {

    @Test
    public void testUpdate() {

        // implicit column names and positions
        t1().insert(1, "x", "y");

        // explicit column names
        t1().insertColumns("c1", "c2", "c3")
                .values(2, "a", "b")
                .exec();

        t1().update()
                .set("c3", "c", Types.VARCHAR)
                .where("c1", 2)
                .exec();

        List<Object[]> data = t1().selectColumns("c3").select();
        assertEquals(2, data.size());

        assertArrayEquals(new Object[] {"y"}, data.get(0));
        assertArrayEquals(new Object[] {"c"}, data.get(1));
    }

}
