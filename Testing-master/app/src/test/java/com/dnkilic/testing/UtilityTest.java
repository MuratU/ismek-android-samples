package com.dnkilic.testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilityTest {
    @Test
    public void test() throws Exception {
        assertEquals("DoğanKılıç", Utility.concator("Doğan", "kılıç"));
        //Utility.concator("aasd", "asd");
        //Utility.concator(null, "asd");
    }

}