package com.shpota.blog.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AssertTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void notEmpty() throws Exception {
        String str = null;
        String message = "Error message";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.notEmpty(str, message);
    }

    @Test
    public void isPositive() throws Exception {
        int number = -4;
        String message = "Error message";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.isPositive(number, message);
    }

    @Test
    public void notNull() throws Exception {
        Object object = null;
        String message = "Error message";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.notNull(object, message);
    }

}