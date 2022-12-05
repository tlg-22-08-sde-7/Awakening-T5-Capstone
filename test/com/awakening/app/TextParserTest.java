package com.awakening.app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextParserTest {
    TextParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new TextParser();
    }

    @Test
    public void parseInput_should_split_words_when_valid_input() {
        String example = "Go west";

        List<String> expected = new ArrayList<>(Arrays.asList("go", "west"));
        List<String> actual = parser.parseInput(example);

        assertEquals(expected, actual);
    }
}