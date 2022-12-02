package com.awakening.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextParserTest {
    TextParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new TextParser();
    }

    @Test
    public void parseInput() {
        String example = "Go west";

        String[] expected = { "Go", "west"};
        String[] actual = parser.parseInput(example);

        assertEquals(expected, actual);
    }
}